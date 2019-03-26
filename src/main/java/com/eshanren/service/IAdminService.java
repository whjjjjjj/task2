package com.eshanren.service;

import com.eshanren.dto.RespRet;
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
     * @return
     */
    public RespRet findUserLogin(String adminName, String password);

    /**
     * 注册
     *
     * @param adminName
     * @param password
     * @return
     */
    public RespRet register(String adminName,String password);

    /**
     * 更新最后一次登录的时间
     *
     * @param loginTime
     * @param name
     */
    public void updateLoginTime(long loginTime , String name);

    /**
     * 根据id找管理员
     *
     * @param id
     * @return
     */
    public RespRet findById(int id);

    /**
     * 修改密码
     *
     * @param id
     * @param password
     */
    public void updateAdminPassword(int id,String password);
}
