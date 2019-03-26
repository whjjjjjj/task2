package com.eshanren.service.impl;

import com.eshanren.dto.RespRet;
import com.eshanren.model.Record;
import com.eshanren.service.IRecordService;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;

/**
 * Created by Administrator on 2019/3/20.
 *
 * @author whj
 */
public class RecordServiceImpl implements IRecordService{

    private Record dao = new Record().dao();
    RespRet respRet;

    @Override
    public RespRet findAll() {
        String sql = Db.getSql("record.findAll");
        respRet = new RespRet();
        respRet.setData(dao.find(sql));
        return respRet;
    }

    @Override
    public RespRet findById(int id) {
        String sql = Db.getSql("record.findById");
        respRet = new RespRet();
        respRet.setData(dao.findFirst(sql,id));
        return respRet;
    }

    @Override
    public void deleteById(int id) {
        String sql = Db.getSql("record.deleteById");
        Db.update(sql,id);
    }

    @Override
    public void addRecord(String recordData, long recordTime, int robotId) {
        new Record().set("record_data",recordData).set("record_time",recordTime).set("robot_id",robotId).save();
    }

    @Override
    public RespRet paginate(int pageNum, int pageSize) {
        respRet = new RespRet();
        respRet.setData(dao.paginate(pageNum,pageSize,"select *","from record order by record_id asc"));
        return respRet;
    }
}
