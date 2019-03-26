package com.eshanren.service;

import com.eshanren.dto.RespRet;
import com.eshanren.model.Record;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;

/**
 * Created by Administrator on 2019/3/20.
 *
 * @author whj
 */
public interface IRecordService {

    /**
     * 取得所有记录信息
     *
     * @return
     */
    public RespRet findAll();

    /**
     * 根据id找到某一条记录信息
     *
     * @param id
     * @return
     */
    public RespRet findById(int id);

    /**
     * 根据id删除某一条记录信息
     *
     * @param id
     */
    public void deleteById(int id);

    /**
     * 添加一条记录信息
     *
     * @param recordData
     * @param recordTime
     * @param robotId
     *
     */
    public void addRecord(String recordData , long recordTime , int robotId);

    /**
     * 分页
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public RespRet paginate( int pageNum, int pageSize);

}
