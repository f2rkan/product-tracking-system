package com.takip.sist;

import java.util.Date;

public class DepoBilgileri {

	private int urunID;
	private String toptanciBilgisi;
	private String urunBilgisi;
	private String urunMiktari;
	private String alisFiyati;
	private int taksitSayisi;
	private String odemeYapildi;
	private Date girisTarihi;
	private Date sonOdeme;
	public DepoBilgileri(int urunID, String toptanciBilgisi, String urunBilgisi, String urunMiktari, String alisFiyati,
			int taksitSayisi, String odemeYapildi, Date girisTarihi, Date sonOdeme) {
		super();
		this.urunID = urunID;
		this.toptanciBilgisi = toptanciBilgisi;
		this.urunBilgisi = urunBilgisi;
		this.urunMiktari = urunMiktari;
		this.alisFiyati = alisFiyati;
		this.taksitSayisi = taksitSayisi;
		this.odemeYapildi = odemeYapildi;
		this.girisTarihi = girisTarihi;
		this.sonOdeme = sonOdeme;
	}
	public int getUrunID() {
		return urunID;
	}
	public void setUrunID(int urunID) {
		this.urunID = urunID;
	}
	public String getToptanciBilgisi() {
		return toptanciBilgisi;
	}
	public void setToptanciBilgisi(String toptanciBilgisi) {
		this.toptanciBilgisi = toptanciBilgisi;
	}
	public String getUrunBilgisi() {
		return urunBilgisi;
	}
	public void setUrunBilgisi(String urunBilgisi) {
		this.urunBilgisi = urunBilgisi;
	}
	public String getUrunMiktari() {
		return urunMiktari;
	}
	public void setUrunMiktari(String urunMiktari) {
		this.urunMiktari = urunMiktari;
	}
	public String getAlisFiyati() {
		return alisFiyati;
	}
	public void setAlisFiyati(String alisFiyati) {
		this.alisFiyati = alisFiyati;
	}
	public int getTaksitSayisi() {
		return taksitSayisi;
	}
	public void setTaksitSayisi(int taksitSayisi) {
		this.taksitSayisi = taksitSayisi;
	}
	public String getOdemeYapildi() {
		return odemeYapildi;
	}
	public void setOdemeYapildi(String odemeYapildi) {
		this.odemeYapildi = odemeYapildi;
	}
	public Date getGirisTarihi() {
		return girisTarihi;
	}
	public void setGirisTarihi(Date girisTarihi) {
		this.girisTarihi = girisTarihi;
	}
	public Date getSonOdeme() {
		return sonOdeme;
	}
	public void setSonOdeme(Date sonOdeme) {
		this.sonOdeme = sonOdeme;
	}
	
	
	
}
