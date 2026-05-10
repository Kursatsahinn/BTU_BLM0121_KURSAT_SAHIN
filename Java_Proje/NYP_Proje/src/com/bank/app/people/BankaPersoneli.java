package com.bank.app.people;
import java.util.ArrayList;

public class BankaPersoneli extends Kisi {
	private String personelID;
	private ArrayList<Musteri> musteriler;
	
	public BankaPersoneli(String ad, String soyad, String email, int telefonNumarasi) {
		super(ad,soyad,email,telefonNumarasi);
		this.musteriler = new ArrayList<Musteri>();
	}
	
	
	
	public ArrayList<Musteri> getMusteriler() {
		return musteriler;
	}
	
	public void musteriEkle(Musteri musteri) {
		musteriler.add(musteri);
	}


	public String getPersonelID() {
		return personelID;
	}

	public void setPersonelID(String personelID) {
		this.personelID = personelID;
	}
	//BANKA PERSONELİNİN BİLGİLERİNİ YAZDIRAN toString() METODU
	public String toString() {
		return super.toString() + ", Personel ID: " + personelID;
	}
}
