package com.takip.sist;

import java.sql.Date;

public class YeniKayit {

	private Integer user_id;
	private String username;
	private String password;
	private Date kayit_tarihi;
	public YeniKayit(Integer user_id, String username, String password, Date kayit_tarihi) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.kayit_tarihi = kayit_tarihi;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getKayit_tarihi() {
		return kayit_tarihi;
	}
	public void setKayit_tarihi(Date kayit_tarihi) {
		this.kayit_tarihi = kayit_tarihi;
	}
	
	
}
