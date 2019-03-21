package com.eshanren.service;

import com.eshanren.model.Admin;

import java.util.List;

/**
 * Created by Administrator on 2019/3/21.
 *
 * @author whj
 */
public interface IAdminService {

    /**
     * 登录
     *
     * @param adminName
     * @param password
     * @param userList
     * @return
     */
    public boolean findUserLogin(String adminName, String password, List<Admin> userList);

    /**
     * 注册
     *
     * @param adminName
     * @param password
     * @return
     */
    public boolean register(String adminName,String password);


}
