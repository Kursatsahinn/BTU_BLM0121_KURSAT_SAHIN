package com.bank.app.people;

import java.util.ArrayList;

public class Kisi {
	private String ad;
	private String soyad;
	private String email;
	private int telefonNumarasi;
	
	private static ArrayList<Kisi> kisiler = new ArrayList<Kisi>();
	
	public Kisi(String ad, String soyad, String email, int telefonNumarasi) {
		this.ad = ad;
		this.soyad = soyad;
		this.email = email;
		this.telefonNumarasi = telefonNumarasi;
		
	}
	
	public String getAd() {
		return ad;
	}

	//SİSTEMDEKİ TÜM KULLANICILARIN TUTULMASI İÇİN OLUŞTURULAN LİSTE
	public static void kisiEkle(Kisi kisi) {
		kisiler.add(kisi);
	}

	public static ArrayList<Kisi> getKisiler(){
		return kisiler;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}
	
	public String getSoyad() {
		return soyad;
	}




	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public int getTelefonNumarasi() {
		return telefonNumarasi;
	}




	public void setTelefonNumarasi(int telefonNumarasi) {
		this.telefonNumarasi = telefonNumarasi;
	}




	public String toString() {
		return "Ad: " + ad + ", Soyad: " + soyad + ", Email: " + email + ", Telefon Numarası: " + telefonNumarasi;
	}
}
