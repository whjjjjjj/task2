package com.eshanren.interceptor;

import com.eshanren.model.Admin;
import com.eshanren.service.IAdminService;
import com.eshanren.service.impl.AdminServiceImpl;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * Created by Administrator on 2019/3/25.
 */
public class LoginInterceptor implements Interceptor {
    IAdminService adminService = new AdminServiceImpl();

    @Override
    public void intercept(Invocation invocation) {
        Controller controller = invocation.getController();
        String adminId = controller.getCookie("adminId");
        if (adminId!=null&&adminService.findById(Integer.parseInt(adminId))!=null){
            invocation.invoke();
        } else {
            controller.renderTemplate("/admin/login.html");
        }
    }
}
