package com.eshanren.service;

import com.eshanren.model.Robot;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;

/**
 * Created by Administrator on 2019/3/18.
 */
public class RobotService {

    private Robot dao = new Robot().dao();

    public Page<Robot> paginate(int pageNumber, int pageSize){
        return dao.paginate(pageNumber,pageSize,"select *","from robot order by id asc");
    }

    public List<Robot> findAll(){
        String sql = "select * from robot";
        return dao.find(sql);
    }

    public Robot findById(int id){
        String sql = "SELECT * FROM robot WHERE robot_id = ?";
        return dao.findFirst(sql,id);

    }


    public void deleteById(int id){
        String sql = "DELETE FROM robot WHERE robot_id = ?";
        Db.update(sql,id);
    }

    public boolean addRobot(String robotName , String robotUrl){
        boolean b =new Robot().set("robot_name",robotName).set("robot_url",robotUrl).save();
        return b;

    }

    public Robot editRobot(int robotId,String robotName,String robotUrl){
//        String update ="UPDATE robot set robot_name=?,robot_url=? where robot_id=?";
        boolean b = new Robot().findById(robotId).set("robot_name",robotName).set("robot_url",robotUrl).update();
        if (b){
            return findById(robotId);
        } else {
            return null;
        }
//        int resl = Db.update(update,robotName,robotUrl,robotId);
//        if (resl >0){
//            return dao.findById(robotId);
//        } else {
//           return null;
//        }
    }
}
