package com.eshanren.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * Created by Administrator on 2019/3/25.
 *
 * @author whj
 */
public class HeadersValidator extends Validator{
    @Override
    protected void validate(Controller controller) {
        switch (controller.getPara("pushWay")) {
            case "text" : validateRequiredString("content","contentMsg","不可为空"); break;
            case "link" : validateRequiredString("title","titleMsg","不可为空");
                validateRequiredString("text","textMsg","不可为空");
                validateRequiredString("messageUrl","messageUrlMsg","不可为空");
                break;
            case "markdown" : validateRequiredString("title","titleMsg","不可为空");
                validateRequiredString("text","textMsg","不可为空");
                break;
            case "overallActionCard" : validateRequiredString("title","titleMsg","不可为空");
                validateRequiredString("text","textMsg","不可为空");
                validateRequiredString("singleUrl","singleUrlMsg","不可为空");
                validateRequiredString("singleTitle","singleTitleMsg","不可为空");
                break;
            case "independentActionCard" : validateRequiredString("title","titleMsg","不可为空");
                validateRequiredString("text","textMsg","不可为空");
                validateRequiredString("btns","btnsMsg","不可为空");
                break;
            case "feedCard" : validateRequiredString("links","linksMsg","不可为空"); break;
            default: break;
        }
    }

    @Override
    protected void handleError(Controller controller) {
        controller.keepPara("pushWay");
        controller.render("push.html");
    }
}
