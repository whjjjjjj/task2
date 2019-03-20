package com.eshanren.controller;

import com.eshanren.model.Admin;
import com.eshanren.model.Robot;
import com.eshanren.service.RobotService;
import com.jfinal.core.Controller;

import java.util.List;

/**
 * Created by Administrator on 2019/3/18.
 */
public class RobotController extends Controller {
    RobotService robotService = new RobotService();
    public void index(){
        list();
    }
    public void list(){
        List<Robot> robots = robotService.findAll();
        System.out.println(robots);
        setAttr("robots",robots);
        renderTemplate("list.html");
    }

    public void add(){
        String robotName = getPara("robotName");
        String robotUrl = getPara("robotUrl");

        boolean b = robotService.addRobot(robotName,robotUrl);
        if(b){
            list();
        }else{
            setAttr("message","失败");
            renderTemplate("message.html");
        }
    }

    public void delete(){
        String robotId = getPara("robotId");
        System.out.println(robotId);
        robotService.deleteById(Integer.parseInt(robotId));
        list();
    }

    public void update(){
        String robotId = getPara("robotId");
        String robotName = getPara("robotName");
        String robotUrl = getPara("robotUrl");
        Robot robot =robotService.editRobot(Integer.parseInt(robotId),robotName,robotUrl);
        if (robot!=null){
            list();
        } else {
           renderTemplate("message.html");
        }
    }

    public void editRobot(){
        String robotId = getPara("robotId");
        Robot robot = robotService.findById(Integer.parseInt(robotId));
        setAttr("robot",robot);
        renderTemplate("editRobot.html");
    }

    public void edit(){
        String robotId = getPara("robotId");
        String robotId1 = robotId.substring(0,1);
        System.out.println(robotId1);
        String robotName = getPara("robotName");
        String robotUrl = getPara("robotUrl");

        Robot robot = robotService.editRobot(Integer.parseInt(robotId1),robotName,robotUrl);
        if (robot==null){
            setAttr("message","修改失败");
        } else {
            list();
        }
    }
}
