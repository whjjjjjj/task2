package com.eshanren.controller;

import com.eshanren.model.Admin;
import com.jfinal.core.Controller;

import java.util.List;

/**
 * Created by Administrator on 2019/3/18.
 */
public class AdminController extends Controller {

    public void index(){
        renderTemplate("login.html");
    }

    public void login(){
        String name = getPara("admin_name");
        String password = getPara("admin_password");
//        System.out.println(name);
//        setAttr("name",name);
        List<Admin> admins = Admin.dao.find("SELECT * FROM admin WHERE admin_name = '"+name+"' AND admin_password = '"+password+"'");
//        Admin admin = getModel(Admin.class,"");
        System.out.println(admins);
        if(admins!=null){
            System.out.println("登录成功");
            setSessionAttr("admins",admins);
//            renderTemplate("/robot/list");

            redirect("/robot/list");
        }else{
            System.out.println("登录失败");
            renderTemplate("message.html");
        }
    }

    public void regist(){
        String admin_name = getPara("admin_name");
        String admin_password = getPara("admin_password");
        boolean b = new Admin().set("admin_name",admin_name).set("admin_password",admin_password).save();
        if (b){
            setAttr("message","注册成功");
        } else {
            setAttr("message","注册失败");
        }
        renderTemplate("login.html");
    }
}
