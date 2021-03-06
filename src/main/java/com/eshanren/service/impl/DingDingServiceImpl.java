package com.eshanren.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eshanren.dto.RespRet;
import com.eshanren.model.Robot;
import com.eshanren.service.IDingDingService;
import com.eshanren.service.IRobotService;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * @author WWF
 *
 *
 *
 * 使用HttpClient发送请求、接收响应
 * 1.创建HttpClient对象    HttpClient httpClient = HttpClients.createDefault();
 * 2.创建请求方法的实例并指定请求URL.如果需要发送get请求,创建HttpGet对象;如果需要发送post请求,创建HttpPost对象  Httppost httppost = new Httppost(URL);
 * 3.如果需要发送请求参数,可调用setEntity(HttpEntity entity)方法来设置请求参数.  httppost.addHeader(name,value)   HttpEntity httpEntity = new HttpEntity(参数,charset);
 * 4.调用HttpClient对象的execute(HttpUriRequest request)发送请求,该方法反悔一个HttpResponse.  httppost.setEntity(httpEntity)  HttpResponse httpResponse = httpClient.execute(httppost);
 * 5.调用HttpResponce的getAllHeaders()、getHeaders(String name)等方法可获取服务器的响应头;调用HttpResponse
 * 的getEntity()方法可获取HttpEntity对象,该对象包装了服务器的响应内容.
 * 6.无论成否,都会释放连接.
 *
 *
 */
public class DingDingServiceImpl implements IDingDingService {

    RespRet respRet;
    private IRobotService robotService = new RobotServiceImpl();


    @Override
    public RespRet getRobotById(int id) {
        respRet = new RespRet();
        Robot robot = (Robot)robotService.findById(id).getData();
        String robotUrl = robot.getStr("robot_url");
        respRet.setMessage(robotUrl);
        return respRet;
    }

    private RespRet common(String webhookToken,String textMsg)  {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost httppost = null;
        respRet = RespRet.fail();
        try {
             httppost = new HttpPost(webhookToken);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        httppost.addHeader("Content-Type","application/json;charset=utf-8");
        StringEntity se = new StringEntity(textMsg,"utf-8");
        httppost.setEntity(se);
        try {
            HttpResponse response = closeableHttpClient.execute(httppost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(result);
                respRet.setSuccess(true);
                respRet.setError("null");
                respRet.setData(textMsg);
                respRet.setMessage(result);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return respRet;
    }


    @Override
    public RespRet pushTextMsg(String id, String content, List<String> atMobiles, Boolean isAtAll)  {
        String webhookToken = getRobotById(Integer.parseInt(id)).getMessage();

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("atMobiles",atMobiles);
        jsonObject2.put("isAtAll",isAtAll);
        jsonObject1.put("content",content);
        jsonObject.put("msgtype","text");
        jsonObject.put("text",jsonObject1);
        jsonObject.put("at",jsonObject2);

        String textMsg = jsonObject.toJSONString();

        return this.common(webhookToken,textMsg);
    }


    @Override
    public RespRet pushLinkMsg(String id, String title, String text, String messageUrl, String picUrl) {
        String webhookToken = getRobotById(Integer.parseInt(id)).getMessage();

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("text",text);
        jsonObject1.put("title",title);
        jsonObject1.put("picUrl",picUrl);
        jsonObject1.put("messageUrl",messageUrl);
        jsonObject.put("msgtype","link");
        jsonObject.put("link",jsonObject1);

        String textMsg = jsonObject.toJSONString();
        return this.common(webhookToken,textMsg);
    }

    @Override
    public RespRet pushMarkdownMsg(String id, String title, String text, List<String> atMobiles, Boolean isAtAll) {
        String webhookToken = getRobotById(Integer.parseInt(id)).getMessage();

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();

        jsonObject2.put("atMobiles",atMobiles);
        jsonObject2.put("isAtAll",isAtAll);
        jsonObject1.put("title",title);
        jsonObject1.put("text",text);
        jsonObject.put("msgtype","markdown");
        jsonObject.put("markdown",jsonObject1);
        jsonObject.put("at",jsonObject2);

        String textMsg = jsonObject.toJSONString();

        return this.common(webhookToken,textMsg);
    }

    @Override
    public RespRet pushActionCardMsg(String id, String title, String text, String singleTitle, String singleURL, String btnOrientation, String hideAvatar) {
        String webhookToken = getRobotById(Integer.parseInt(id)).getMessage();

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("title",title);
        jsonObject1.put("text",text);
        jsonObject1.put("hideAvatar",hideAvatar);
        jsonObject1.put("btnOrientation",btnOrientation);
        jsonObject1.put("singleTitle",singleTitle);
        jsonObject1.put("singleURL",singleURL);
        jsonObject.put("actionCard",jsonObject1);
        jsonObject.put("msgtype","actionCard");

        String textMsg = jsonObject.toJSONString();
        System.out.println(textMsg);

        return this.common(webhookToken,textMsg);
    }

    @Override
    public RespRet pushActionCardMsg(String id, String title, String text, JSONArray btns, String btnOrientation, String hideAvatar) {
        String webhookToken = getRobotById(Integer.parseInt(id)).getMessage();

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("title",title);
        jsonObject1.put("text",text);
        jsonObject1.put("hideAvatar",hideAvatar);
        jsonObject1.put("btnOrientation",btnOrientation);
        jsonObject1.put("btns",btns);
        jsonObject.put("actionCard",jsonObject1);
        jsonObject.put("msgtype","actionCard");

        String textMsg = jsonObject.toJSONString();
        System.out.println(btns);
        return this.common(webhookToken,textMsg);
    }

    @Override
    public RespRet pushFeedCardMsg(String id, JSONArray links)  {
        String webhookToken = getRobotById(Integer.parseInt(id)).getMessage();

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("links",links);
        jsonObject.put("feedCard",jsonObject1);
        jsonObject.put("msgtype","feedCard");

        String textMsg = jsonObject.toJSONString();

        return this.common(webhookToken,textMsg);

    }
}
