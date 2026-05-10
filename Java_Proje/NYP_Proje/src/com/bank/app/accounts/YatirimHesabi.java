package com.bank.app.accounts;

public class YatirimHesabi extends BankaHesabi {
	private String hesapTuru;
	
	public YatirimHesabi(double bakiye) {
		//ÜST SINIF İÇERİSİNDEKİ KONSTÜRÜKTÖRE PARAMETRELERİN GÖNDERİLMESİ
		super(bakiye);
		this.hesapTuru = "Yatırım Hesabı";
	}
	
	
	
	public String getHesapTuru() {
		return hesapTuru;
	}



	public void setHesapTuru(String hesapTuru) {
		this.hesapTuru = hesapTuru;
	}
	
	public void paraTransferi(BankaHesabi aliciHesap, BankaHesabi gonderenHesap, double miktar) {
		aliciHesap.setBakiye(aliciHesap.getBakiye() + miktar);
		gonderenHesap.setBakiye(gonderenHesap.getBakiye() - miktar);
	}

	public void paraEkle(double miktar) {
		super.setBakiye(super.getBakiye() + miktar);
	}
	
	public void paraCek(double miktar) {
		if (miktar <= super.getBakiye()) {
			super.setBakiye(super.getBakiye() - miktar);
		}
		else {
			System.out.println("Hesabınızda Bu Miktarda Para Bulunmamaktadır!");
		}
		
	}
	
	public String toString() {
		return super.toString() + ", Hesap Türü: " + hesapTuru;
	}
	
	
	
}
