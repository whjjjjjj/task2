package com.eshanren.config;

import com.eshanren.model.Admin;
import com.eshanren.service.IAdminService;
import com.eshanren.service.impl.AdminServiceImpl;
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

    public void login(){
        String adminName = getPara("adminName");
        String password = getPara("adminPassword");
        List<Admin> userList = new ArrayList<Admin>();
        boolean b = adminService.findUserLogin(adminName,password,userList);
        if (b) {
            String sql = "update admin set admin_logintime=? where admin_name = ?";
            Db.update(sql,System.currentTimeMillis(),adminName);
            setAttr("status",0);
            System.out.println("登录成功");
            setAttr("admin",userList.get(0));
//            setSessionAttr("admin",userList.get(0));
            setCookie("adminName",adminName,600);
            redirect("/robot/list");


        }else{
            System.out.println("登录失败");
            setAttr("message","账号或密码错误");
            renderTemplate("message.html");
        }
    }

    public void register(){
        String adminName = getPara("admin_name");
        String adminPassword = getPara("admin_password");
        boolean b = adminService.register(adminName,adminPassword);
        if (b){
            setAttr("message","注册成功");
        } else {
            setAttr("message","注册失败");
        }
        renderTemplate("message.html");
    }
}
