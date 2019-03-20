package com.eshanren.service.impl;

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

    @Override
    public List<Record> findAll() {
        String sql = "SELECT * FROM record";
        return dao.find(sql);
    }

    @Override
    public Record findById(int id) {
        String sql = "SELECT * FROM record WHERE record_id = ?";
        return dao.findFirst(sql,id);
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM record WHERE record_id = ?";
        Db.update(sql,id);
    }

    @Override
    public boolean addRecord(String recordData, long recordTime, int robotId) {
        return new Record().set("record_data",recordData).set("record_time",recordTime).set("robot_id",robotId).save();
    }

    @Override
    public Page<Record> paginate(int pageNum, int pageSize) {
        return dao.paginate(pageNum,pageSize,"select *","from record order by record_id asc");
    }
}
