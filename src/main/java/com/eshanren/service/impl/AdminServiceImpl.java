package com.eshanren.service.impl;

import com.eshanren.dto.RespRet;
import com.eshanren.model.Admin;
import com.eshanren.service.IAdminService;
import com.jfinal.plugin.activerecord.Db;

import java.util.List;

/**
 * Created by Administrator on 2019/3/21.
 *
 * @author whj
 */
public class AdminServiceImpl implements IAdminService{

    private Admin dao = new Admin().dao();
    private RespRet respRet ;
    //  2019-03-24 sql 不要硬编码
    //  2019-03-24 业务层返回值应用Ret
    @Override
    public RespRet findUserLogin(String adminName, String password) {
        String sql = Db.getSql("admin.login");
        Admin admin = dao.findFirst(sql,adminName,password);

        if (admin==null) {
            respRet = RespRet.fail();
        } else {
            respRet = new RespRet();
            respRet.setData(admin);
            respRet.setSuccess(true);
        }
        return  respRet;
    }

    @Override
    public RespRet register(String adminName, String password) {
        respRet = new RespRet();
        respRet.setSuccess(new Admin().set("admin_name", adminName).set("admin_password", password).save());
        return respRet;


    }

    @Override
    public void updateLoginTime(long loginTime, String name) {
        String sql = Db.getSql("admin.updateLoginTime");
        Db.update(sql,loginTime,name);
    }

    @Override
    public Admin findById(int id) {
        String sql = Db.getSql("admin.findById");
        return dao.findFirst(sql,id);

    }


}
