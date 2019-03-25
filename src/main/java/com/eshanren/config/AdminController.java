package com.eshanren.config;

import com.eshanren.dto.RespRet;
import com.eshanren.interceptor.LoginInterceptor;
import com.eshanren.model.Admin;
import com.eshanren.service.IAdminService;
import com.eshanren.service.impl.AdminServiceImpl;
import com.eshanren.validator.LoginValidator;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/3/18.
 *
 * @author whj
 */
public class AdminController extends Controller {

    private IAdminService adminService = new AdminServiceImpl();

    public void index(){
        renderTemplate("login.html");
    }

    @Before(LoginValidator.class)
    @Clear(LoginInterceptor.class)
    public void login(){
        String adminName = getPara("adminName");
        String password = getPara("adminPassword");
        RespRet respRet = adminService.findUserLogin(adminName,password);
        boolean b = respRet.isSuccess();
        Admin admin = (Admin)respRet.getData();
        if (b) {
            adminService.updateLoginTime(System.currentTimeMillis(),adminName);
            System.out.println("登录成功");
            setCookie("adminId",admin.getStr("admin_id"),-1);
//            setSessionAttr("admin",admin);
//            setCookie("adminName",adminName,600);
//            redirect("/robot/list");
            setAttr("adminName",adminName);
            render("/index.html");
        }else{
            System.out.println("登录失败");
            setAttr("message","账号或密码错误");
            renderTemplate("message.html");
        }
    }
    @Clear
    public void register(){
        String adminName = getPara("admin_name");
        String adminPassword = getPara("admin_password");
        boolean b = adminService.register(adminName,adminPassword).isSuccess();
        if (b){
            setAttr("message","注册成功");
        } else {
            setAttr("message","注册失败");
        }
        renderTemplate("message.html");
    }
}
