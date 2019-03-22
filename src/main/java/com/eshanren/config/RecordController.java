package com.eshanren.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eshanren.dto.RespRet;
import com.eshanren.model.Record;
import com.eshanren.service.IDingDingService;
import com.eshanren.service.IRecordService;
import com.eshanren.service.impl.DingDingServiceImpl;
import com.eshanren.service.impl.RecordServiceImpl;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;


import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2019/3/18.
 *
 * @author whj
 */
public class RecordController extends Controller{

    private IDingDingService dingDingService = new DingDingServiceImpl();
    private IRecordService recordService = new RecordServiceImpl();
    public void index(){
        String robotId = getPara("robotId");
        setAttr("robotId",robotId);
        renderTemplate("pushWay.html");
    }
    public void pushWay(){
        String pushWay = getPara("pushWay");
        setAttr("pushWay",pushWay);
        String robotId = getPara("robotId");
        setAttr("robotId",robotId);
        renderTemplate("push.html");
    }
    public void chooseWay(){
        String pushWay = getPara("pushWay");
        switch (pushWay) {
            case "text" : pushTextMsg(); break;
            case "link" : pushLinkMsg(); break;
            case "markdown" : pushMarkdownMsg(); break;
            case "overallActionCard" : pushOverallActionCardMsg(); break;
            case "independentActionCard" : pushIndependentActionCardMsg(); break;
            case "feedCard" : pushFeedCardMsg(); break;
            default: break;
        }
    }

    private void common(RespRet respRet,String robotId){
        if (respRet.isSuccess()) {
            recordService.addRecord((String) respRet.getData(),  System.currentTimeMillis(), Integer.parseInt(robotId));
            setAttr("message", "成功" );
        } else {
            setAttr("message", "失败");
        }
    }
    public void pushTextMsg(){
        String robotId = getPara("robotId");
        String isAtAll = getPara("isAtAll");
        String content = getPara("content");
        String atMobiles = getPara("atMobiles");

        System.out.println(content);
        if ("".equals(content)) {
            setAttr("message","发送失败,内容不能为空");
        } else {
            List<String> a = Arrays.asList(atMobiles);
            boolean b = "true".equals(isAtAll);
            RespRet respRet = dingDingService.pushTextMsg(robotId, content, a, b);
            this.common(respRet,robotId);
        }
        renderTemplate("message.html");
    }

    public void pushLinkMsg() {
        String robotId = getPara("robotId");
        String title = getPara("title");
        String text = getPara("text");
        String messageUrl = getPara("messageUrl");
        String picUrl = getPara("picUrl");
        if ("".equals(title)||"".equals(text)||"".equals(messageUrl)) {
            setAttr("message","发送失败,标题、内容、消息地址都不能为空");
        } else {
            RespRet respRet = dingDingService.pushLinkMsg(robotId, title, text, messageUrl, picUrl);
            this.common(respRet,robotId);
        }
        renderTemplate("message.html");
    }

    public void pushMarkdownMsg(){
        String robotId = getPara("robotId");
        String title = getPara("title");
        String text = getPara("text");
        String atMobiles = getPara("atMobiles");
        String isAtAll = getPara("isAtAll");
        if ("".equals(title)||"".equals(text)){
            setAttr("message","发送失败,标题和内容都不能为空");
        } else {
            boolean b = "true".equals(isAtAll);
            RespRet respRet = dingDingService.pushMarkdownMsg(robotId, title, text, Arrays.asList(atMobiles), b);
            this.common(respRet,robotId);
        }
        renderTemplate("message.html");
    }

    public void pushIndependentActionCardMsg() {
        String robotId = getPara("robotId");
        String title = getPara("title");
        String text = getPara("text");
        String btns = getPara("btns");
        String btnOrientation = getPara("btnOrientation");
        String hideAvatar = getPara("hideAvatar");
        if ("".equals(title)||"".equals(text)||"".equals(btns)) {
            setAttr("message","发送失败,标题、内容、按钮信息都不能为空");
        } else {
            JSONArray jsonArray = JSONObject.parseArray(btns);
            System.out.println(jsonArray);
            RespRet respRet = dingDingService.pushActionCardMsg(robotId, title, text, jsonArray, btnOrientation, hideAvatar);
            this.common(respRet,robotId);
        }
        renderTemplate("message.html");
    }

    public void pushOverallActionCardMsg() {
        String robotId = getPara("robotId");
        String title = getPara("title");
        String text = getPara("text");
        String singleTitle = getPara("singleTitle");
        String singleURL = getPara("singleURL");
        String btnOrientation = getPara("btnOrientation");
        String hideAvatar = getPara("hideAvatar");
        if ("".equals(title)||"".equals(text)||"".equals(singleTitle)||"".equals(singleURL)) {
            setAttr("message","发送失败,标题、内容、单个按钮的方案URL都不能为空");
        } else {
            RespRet respRet = dingDingService.pushActionCardMsg(robotId, title, text, singleTitle, singleURL, btnOrientation, hideAvatar);
            this.common(respRet,robotId);
        }
        renderTemplate("message.html");
    }

    public void pushFeedCardMsg(){
        String robotId = getPara("robotId");
        String links = getPara("links");
        if ("".equals(links)) {
            setAttr("message","发送失败,内容不能为空");
        } else {
            JSONArray jsonArray = JSONObject.parseArray(links);
            RespRet respRet = dingDingService.pushFeedCardMsg(robotId, JSONObject.parseArray(links));
            this.common(respRet,robotId);
        }
        renderTemplate("message.html");
    }

    public void list(){
        String num = getPara("pageNum");
        int pageNum;
        if(num==null) {
             pageNum = 1;
        } else {
             pageNum = Integer.parseInt(num);
        }
        Page<Record> recordPages = recordService.paginate(pageNum,2);
        setAttr("recordPages",recordPages);
        setAttr("totalPage",recordPages.getTotalPage());
        renderTemplate("list.html");
    }

}
