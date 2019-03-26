package com.eshanren.service.impl;

import com.eshanren.dto.RespRet;
import com.eshanren.model.Robot;
import com.eshanren.service.IRobotService;
import com.jfinal.plugin.activerecord.Db;

import java.util.List;

/**
 * Created by Administrator on 2019/3/20.
 *
 * @author whj
 */
public class RobotServiceImpl implements IRobotService {

    private Robot dao = new Robot().dao();
    RespRet respRet;

    @Override
    public RespRet findAll() {
//        String sql = "select * from robot";
        String sql = Db.getSql("robot.findAll");
        respRet = new RespRet();
        respRet.setData(dao.find(sql));
        return respRet;
    }

    @Override
    public RespRet findById(int id) {
//        String sql = "SELECT * FROM robot WHERE robot_id = ?";
        String sql = Db.getSql("robot.findById");
        respRet = new RespRet();
        respRet.setData(dao.findFirst(sql,id));
        return respRet;
    }

    @Override
    public void deleteById(int id) {
//        String sql = "DELETE FROM robot WHERE robot_id = ?";
        String sql = Db.getSql("robot.deleteById");
        Db.update(sql,id);
    }

    @Override
    public RespRet addRobot(String robotName, String robotUrl) {
        respRet = new RespRet();
        respRet.setSuccess(new Robot().set("robot_name",robotName).set("robot_url",robotUrl).save());
        return respRet;
    }

    @Override
    public RespRet editRobot(int robotId, String robotName, String robotUrl) {
        boolean b = new Robot().findById(robotId).set("robot_name",robotName).set("robot_url",robotUrl).update();
        if (b){
            return findById(robotId);
        } else {
            return null;
        }
    }
}
