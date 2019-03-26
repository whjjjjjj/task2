package com.eshanren.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * Created by Administrator on 2019/3/25.
 *
 * @author whj
 */
public class LoginValidator extends Validator {
    @Override
    protected void validate(Controller controller) {
        validateRequiredString("adminName","adminNameMsg","请输入账号");
        validateRequiredString("adminPassword","adminPasswordMsg","请输入密码");
    }

    @Override
    protected void handleError(Controller controller) {
        controller.keepPara("adminName");
        controller.keepPara("adminPassword");
        controller.render("login.html");
    }
}
