package com.eshanren.service;

import com.eshanren.model.Robot;

import java.util.List;

/**
 * Created by Administrator on 2019/3/20.
 *
 * @author whj
 */
public interface IRobotService {

    /**
     * 查到所有机器人信息
     *
     * @return
     */
    public List<Robot> findAll();

    /**
     * 根据id查到某个机器人信息
     *
     * @param id
     * @return
     */
    public Robot findById(int id);

    /**
     * 删除此id机器人信息
     *
     * @param id
     */
    public void deleteById(int id);

    /**
     * 添加机器人信息
     *
     * @param robotName
     * @param robotUrl
     * @return
     */
    public boolean addRobot(String robotName , String robotUrl);

    /**
     * 编辑更改机器人信息
     *
     * @param robotId
     * @param robotName
     * @param robotUrl
     * @return
     */
    public Robot editRobot(int robotId,String robotName,String robotUrl);
}
