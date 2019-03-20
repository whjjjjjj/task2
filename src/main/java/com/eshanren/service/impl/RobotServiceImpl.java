package com.eshanren.service.impl;

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

    @Override
    public List<Robot> findAll() {
        String sql = "select * from robot";
        return dao.find(sql);
    }

    @Override
    public Robot findById(int id) {
        String sql = "SELECT * FROM robot WHERE robot_id = ?";
        return dao.findFirst(sql,id);
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM robot WHERE robot_id = ?";
        Db.update(sql,id);
    }

    @Override
    public boolean addRobot(String robotName, String robotUrl) {
        boolean b =new Robot().set("robot_name",robotName).set("robot_url",robotUrl).save();
        return b;
    }

    @Override
    public Robot editRobot(int robotId, String robotName, String robotUrl) {
        boolean b = new Robot().findById(robotId).set("robot_name",robotName).set("robot_url",robotUrl).update();
        if (b){
            return findById(robotId);
        } else {
            return null;
        }
    }
}
