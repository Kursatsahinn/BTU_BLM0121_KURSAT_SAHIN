package com.bank.app.accounts;
import com.bank.app.cards.*;
//VADESİZ HESAP BANKA HESABININ ALT SINIFI OLDUĞU İÇİN EXTENDS İFADESİ KULLANILIYOR
public class VadesizHesap  extends BankaHesabi{
	private String hesapTuru;
	
	public VadesizHesap(double bakiye) {
		//ÜST SINIF İÇERİSİNDEKİ KONSTÜRÜKTÖRE PARAMETRELERİN GÖNDERİLMESİ
		super(bakiye);
		this.hesapTuru = "Vadesiz Hesap";
	}
	public String getHesapTuru() {
		return hesapTuru;
	}
	public void setHesapTuru(String hesapTuru) {
		this.hesapTuru = hesapTuru;
	}
	
	//KULLANICININ HESAPLARI ARASI PARA TRANSFERİNİN YAPILMASINI SAĞLAYAN METOT
	public void paraTransferi(BankaHesabi aliciHesap, BankaHesabi gonderenHesap, double miktar) {
		aliciHesap.setBakiye(aliciHesap.getBakiye() + miktar);
		gonderenHesap.setBakiye(gonderenHesap.getBakiye() - miktar);
	}
	//HESABA PARA EKLEME FONKSİYONU
	public void paraEkle(double miktar) {
		super.setBakiye(super.getBakiye() + miktar);
	}
	//HESAPTAN PARA ÇEKME FONKSİYONU
	public void paraCek(double miktar) {
		if (miktar <= super.getBakiye()) {
			super.setBakiye(super.getBakiye() - miktar);
		}
		else {
			System.out.println("Hesabınızda Bu Miktarda Para Bulunmamaktadır!");
		}
		
	}
	//KULLANICININ KREDİ KARTINDA BORÇ VAR İSE ÖDEMESİNİ SAĞLAYAN METOT
	public void krediKartiBorcOdeme(KrediKarti kart, double miktar) {
		kart.setGuncelBorc(kart.getGuncelBorc() - miktar);
	}
	public String toString() {
		return super.toString() + ", Hesap Türü: " + hesapTuru;
	}
}
