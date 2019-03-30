package com.eshanren.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eshanren.dto.RespRet;
import com.eshanren.model.Record;
import com.eshanren.service.IDingDingService;
import com.eshanren.service.IRecordService;
import com.eshanren.service.impl.DingDingServiceImpl;
import com.eshanren.service.impl.RecordServiceImpl;
import com.eshanren.validator.HeadersValidator;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.overzealous.remark.Remark;


import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2019/3/18.
 *
 * @author whj
 */
public class RecordController extends Controller{
    // 2019-03-24 缺少登录状态拦截
    // 2019-03-24 代码太乱，并且没有注释
    // 2019-03-24 参数合法性的校验太简单，可以使用jfinal 的 validator

    private IDingDingService dingDingService = new DingDingServiceImpl();
    private IRecordService recordService = new RecordServiceImpl();


    public void index(){
        String robotId = getPara("robotId");
        setAttr("robotId",robotId);
        renderTemplate("pushWay.html");
    }

    /**
     * 获取推送的方式
     */
    public void pushWay(){
        String pushWay = getPara("pushWay");
        if (pushWay==null){
            setAttr("message","您未选择发生方式");
            renderTemplate("message.html");
        } else {
            setAttr("pushWay", pushWay);
            String robotId = getPara("robotId");
            setAttr("robotId", robotId);
            renderTemplate("push.html");
        }
    }

    /**
     * 选择的推送方式对应的前端数据的读取
     */
    @Before(HeadersValidator.class)
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

    /**
     * 存储发送记录
     * @param respRet
     * @param robotId
     */
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

        List<String> a = Arrays.asList(atMobiles);
        boolean b = "true".equals(isAtAll);
        RespRet respRet = dingDingService.pushTextMsg(robotId, content, a, b);
        this.common(respRet,robotId);
        renderTemplate("message.html");
    }

    public void pushLinkMsg() {
        String robotId = getPara("robotId");
        String title = getPara("title");
        String text = getPara("text");
        String messageUrl = getPara("messageUrl");
        String picUrl = getPara("picUrl");

        RespRet respRet = dingDingService.pushLinkMsg(robotId, title, text, messageUrl, picUrl);
        this.common(respRet,robotId);

        renderTemplate("message.html");
    }

    public void pushMarkdownMsg(){
        String robotId = getPara("robotId");
        String title = getPara("title");
        String text1 = getPara("text");
        String atMobiles = getPara("atMobiles");
        String isAtAll = getPara("isAtAll");
        System.out.println("asdsadsadasd"+text1);
        Remark remark = new Remark();
        String text =remark.convertFragment(text1);
        System.out.println("asdas"+text);
        boolean b = "true".equals(isAtAll);
        RespRet respRet = dingDingService.pushMarkdownMsg(robotId, title, text, Arrays.asList(atMobiles), b);
        this.common(respRet,robotId);

        renderTemplate("message.html");
    }

    public void pushIndependentActionCardMsg() {
        String robotId = getPara("robotId");
        String title = getPara("title");
        String text1 = getPara("text");
        String btns = getPara("btns");
        String btnOrientation = getPara("btnOrientation");
        String hideAvatar = getPara("hideAvatar");

        Remark remark = new Remark();
        String text =remark.convertFragment(text1);
        JSONArray jsonArray = JSONObject.parseArray(btns);
        System.out.println(jsonArray);
        RespRet respRet = dingDingService.pushActionCardMsg(robotId, title, text, jsonArray, btnOrientation, hideAvatar);
        this.common(respRet,robotId);

        renderTemplate("message.html");
    }

    public void pushOverallActionCardMsg() {
        String robotId = getPara("robotId");
        String title = getPara("title");
        String text1 = getPara("text");
        String singleTitle = getPara("singleTitle");
        String singleURL = getPara("singleURL");
        String btnOrientation = getPara("btnOrientation");
        String hideAvatar = getPara("hideAvatar");

        Remark remark = new Remark();
        String text =remark.convertFragment(text1);
        RespRet respRet = dingDingService.pushActionCardMsg(robotId, title, text, singleTitle, singleURL, btnOrientation, hideAvatar);
        this.common(respRet,robotId);

        renderTemplate("message.html");
    }
    public void pushFeedCardMsg(){
        String robotId = getPara("robotId");
        String links = getPara("links");

        JSONArray jsonArray = JSONObject.parseArray(links);
        RespRet respRet = dingDingService.pushFeedCardMsg(robotId, JSONObject.parseArray(links));
        this.common(respRet,robotId);

        renderTemplate("message.html");
    }

    /**
     * 分页显示所有机器人发送的所有数据
     */
    public void list(){
        String num = getPara("pageNum");
        int pageNum;
        if(num==null) {
             pageNum = 1;
        } else {
             pageNum = Integer.parseInt(num);
        }
        Page<Record> recordPages = (Page<Record>)recordService.paginate(pageNum,2).getData();
        setAttr("recordPages",recordPages);
        setAttr("totalPage",recordPages.getTotalPage());
        renderTemplate("list.html");
    }

}
