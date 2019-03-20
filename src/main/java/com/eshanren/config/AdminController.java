package com.eshanren.config;

import com.eshanren.model.Admin;
import com.jfinal.core.Controller;

import java.util.List;

/**
 * Created by Administrator on 2019/3/18.
 *
 * @author whj
 */
public class AdminController extends Controller {

    public void index(){
        renderTemplate("login.html");
    }

    public void login(){
        String name = getPara("adminName");
        String password = getPara("adminPassword");
        String sql = "SELECT * FROM admin WHERE admin_name = ? AND admin_password = ?";
        Admin admins = new Admin().dao().findFirst(sql,name,password);
        System.out.println(admins);

        if(admins!=null){
            System.out.println("登录成功");
            setSessionAttr("admins",admins);
            redirect("/robot/list");
        }else{
            System.out.println("登录失败");
            renderTemplate("message.html");
        }
    }

    public void regist(){
        String adminName = getPara("admin_name");
        String adminPassword = getPara("admin_password");
        boolean b = new Admin().set("admin_name",adminName).set("admin_password",adminPassword).save();
        if (b){
            setAttr("message","注册成功");
        } else {
            setAttr("message","注册失败");
        }
        renderTemplate("login.html");
    }
}
