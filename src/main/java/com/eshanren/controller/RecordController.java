package com.eshanren.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eshanren.dto.RespRet;
import com.eshanren.service.IDingDingService;
import com.eshanren.service.impl.DingDingServiceImpl;
import com.jfinal.core.Controller;
import com.jfinal.i18n.Res;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2019/3/18.
 *
 * @author whj
 */
public class RecordController extends Controller{

    private IDingDingService dingDingService = new DingDingServiceImpl();
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
    public void pushTextMsg(){
        String robotId = getPara("robotId");
        String isAtAll = getPara("isAtAll");
        String content = getPara("content");
        String atMobiles = getPara("atMobiles");
        List<String> a = Arrays.asList(atMobiles);
        boolean b = isAtAll.equals("true");
        RespRet respRet = dingDingService.pushTextMsg(robotId,content,a,b);
//        redirect("/robot/list");
        setAttr("message",respRet.isSuccess()?"成功":"失败");
        renderTemplate("message.html");
    }

    public void pushLinkMsg(){
        String robotId = getPara("robotId");
        String title = getPara("title");
        String text = getPara("text");
        String messageUrl = getPara("messageUrl");
        String picUrl = getPara("picUrl");
        RespRet respRet = dingDingService.pushLinkMsg(robotId,title,text,messageUrl,picUrl);

        setAttr("message",respRet.isSuccess()?"成功":"失败");
        renderTemplate("message.html");
    }

    public void pushMarkdownMsg(){
        String robotId = getPara("robotId");
        String title = getPara("title");
        String text = getPara("text");
        String atMobiles = getPara("atMobiles");
        String isAtAll = getPara("isAtAll");
        boolean b = isAtAll.equals("true");
        RespRet respRet = dingDingService.pushMarkdownMsg(robotId,title,text,Arrays.asList(atMobiles),b);

        setAttr("message",respRet.isSuccess()?"成功":"失败");
        renderTemplate("message.html");
    }

    public void pushIndependentActionCardMsg(){

    }

    public void pushOverallActionCardMsg(){
        String robotId = getPara("robotId");
        String title = getPara("title");
        String text = getPara("text");
        String singleTitle = getPara("singleTitle");
        String singleURL = getPara("singleURL");
        String btnOrientation = getPara("btnOrientation");
        String hideAvatar = getPara("hideAvatar");
        RespRet respRet = dingDingService.pushActionCardMsg(robotId,title,text,singleTitle,singleURL,btnOrientation,hideAvatar);

        setAttr("message",respRet.isSuccess()?"成功":"失败");
        renderTemplate("message.html");
    }

    public void pushFeedCardMsg(){
        String robotId = getPara("robotId");
        String links = getPara("links");
        JSONArray jsonArray = JSONObject.parseArray(links);
        System.out.println(jsonArray);
        RespRet respRet = dingDingService.pushFeedCardMsg(robotId, JSONObject.parseArray(links));

        setAttr("message",respRet.isSuccess()?"成功":"失败");
        renderTemplate("message.html");
    }
}
