package com.eshanren.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseRecord<M extends BaseRecord<M>> extends Model<M> implements IBean {

	public void setRecordId(java.lang.Integer recordId) {
		set("record_id", recordId);
	}
	
	public java.lang.Integer getRecordId() {
		return getInt("record_id");
	}

	public void setRecordData(java.lang.String recordData) {
		set("record_data", recordData);
	}
	
	public java.lang.String getRecordData() {
		return getStr("record_data");
	}

	public void setRecordTime(java.lang.Long recordTime) {
		set("record_time", recordTime);
	}
	
	public java.lang.Long getRecordTime() {
		return getLong("record_time");
	}

	public void setRobotId(java.lang.Integer robotId) {
		set("robot_id", robotId);
	}
	
	public java.lang.Integer getRobotId() {
		return getInt("robot_id");
	}

}