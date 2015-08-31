package com.fp.domain;

import java.util.Date;

public class TcuFtcpRecord {
	private int id;
	private String vin;
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public int getCorrelateMsgId() {
		return correlateMsgId;
	}
	public void setCorrelateMsgId(int correlateMsgId) {
		this.correlateMsgId = correlateMsgId;
	}
	private String record;
	private Date insertDate;
	private int correlateMsgId;
}
