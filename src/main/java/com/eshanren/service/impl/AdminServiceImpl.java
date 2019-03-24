package com.eshanren.service.impl;

import com.eshanren.model.Admin;
import com.eshanren.service.IAdminService;

import java.util.List;

/**
 * Created by Administrator on 2019/3/21.
 *
 * @author whj
 */
public class AdminServiceImpl implements IAdminService{

    private Admin dao = new Admin().dao();

    // TODO: 2019-03-24 sql 不要硬编码
    // TODO: 2019-03-24 业务层返回值应用Ret
    @Override
    public boolean findUserLogin(String adminName, String password, List<Admin> userList) {
        String sql = "SELECT * FROM admin WHERE admin_name = ? AND admin_password = ?";
        Admin admin = dao.findFirst(sql,adminName,password);
        if (admin==null) {
            return false;
        } else {
            userList.add(admin);
            return true;
        }

    }

    @Override
    public boolean register(String adminName, String password) {
        return new Admin().set("admin_name", adminName).set("admin_password", password).save();
    }
}
