package com.takip.sist;

import java.sql.Date;

public class MusteriIslemleri {

	private Integer musteri_id;
	private String musteri_ismi;
	private String urun_ismi;
	private String urun_miktari;
	private String satis_miktari;
	private Integer taksit_sayisi;
	private Date son_odeme;
	private String odeme_yapildi;
	public MusteriIslemleri(Integer musteri_id, String musteri_ismi, String urun_ismi, String urun_miktari,
			String satis_miktari, Integer taksit_sayisi, Date son_odeme, String odeme_yapildi) {
		super();
		this.musteri_id = musteri_id;
		this.musteri_ismi = musteri_ismi;
		this.urun_ismi = urun_ismi;
		this.urun_miktari = urun_miktari;
		this.satis_miktari = satis_miktari;
		this.taksit_sayisi = taksit_sayisi;
		this.son_odeme = son_odeme;
		this.odeme_yapildi = odeme_yapildi;
	}
	public Integer getMusteri_id() {
		return musteri_id;
	}
	public void setMusteri_id(Integer musteri_id) {
		this.musteri_id = musteri_id;
	}
	public String getMusteri_ismi() {
		return musteri_ismi;
	}
	public void setMusteri_ismi(String musteri_ismi) {
		this.musteri_ismi = musteri_ismi;
	}
	public String getUrun_ismi() {
		return urun_ismi;
	}
	public void setUrun_ismi(String urun_ismi) {
		this.urun_ismi = urun_ismi;
	}
	public String getUrun_miktari() {
		return urun_miktari;
	}
	public void setUrun_miktari(String urun_miktari) {
		this.urun_miktari = urun_miktari;
	}
	public String getSatis_miktari() {
		return satis_miktari;
	}
	public void setSatis_miktari(String satis_miktari) {
		this.satis_miktari = satis_miktari;
	}
	public Integer getTaksit_sayisi() {
		return taksit_sayisi;
	}
	public void setTaksit_sayisi(Integer taksit_sayisi) {
		this.taksit_sayisi = taksit_sayisi;
	}
	public Date getSon_odeme() {
		return son_odeme;
	}
	public void setSon_odeme(Date son_odeme) {
		this.son_odeme = son_odeme;
	}
	public String getOdeme_yapildi() {
		return odeme_yapildi;
	}
	public void setOdeme_yapildi(String odeme_yapildi) {
		this.odeme_yapildi = odeme_yapildi;
	}
}
