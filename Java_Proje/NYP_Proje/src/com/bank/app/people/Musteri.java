package com.bank.app.people;
import java.util.ArrayList;
import com.bank.app.accounts.*;
import com.bank.app.cards.*;

public class Musteri extends Kisi {
	private String musteriNumarasi;
	private ArrayList<BankaHesabi> hesaplar;
	private ArrayList<KrediKarti> krediKartlari;
	
	public Musteri(String ad, String soyad, String email, int telefonNumarasi) {
		super(ad,soyad,email,telefonNumarasi);
		this.hesaplar = new ArrayList<>();
		this.krediKartlari = new ArrayList<>();
	}
	
	
	
	
	public ArrayList<BankaHesabi> getHesaplar() {
		return hesaplar;
	}




	public ArrayList<KrediKarti> getKrediKartlari() {
		return krediKartlari;
	}




	public String getMusteriNumarasi() {
		return musteriNumarasi;
	}




	public void setMusteriNumarasi(String musteriNumarasi) {
		this.musteriNumarasi = musteriNumarasi;
	}




	//Kullanıcının hesaplar listesine ekleme yapmak için kullanılan metot
	public void hesapEkle(String hesapTuru) {
		//Boş bir banka hesabı oluşturma
		BankaHesabi yeniHesap = null;
		
		//Gelen hesap türüne göre hangi çeşit hesap açılacağı belirlenir
		
		if(hesapTuru.equals("Vadesiz Hesap")) {
			//Hesabın başlangıçta parası olmayacağı için bakiye 0.0 belirlendi
			yeniHesap = new VadesizHesap(0.0);
			System.out.println("Vadesiz Hesabınız oluşturuldu!");
			//Oluşturulan hesabı müşterinin hesaplar listesine ekliyoruz
			this.hesaplar.add(yeniHesap);
		}
		else if (hesapTuru.equals("Yatırım Hesabı")) {
			yeniHesap = new YatirimHesabi(0.0);
			System.out.println("Yatırım Hesabınız oluşturuldu!");
			this.hesaplar.add(yeniHesap);
			
		}
		else {
			//İstenilen hesap türü girilmezse hata mesajı yazdır
			System.out.println("Tanımlanamayan hesap turu seçildi!");
		}
		
	}
	
	public void krediKartiEkle(double limit) {
		
		//Kullanıcı için belirlenen limitte kredi kartı oluşturuluyor
		//Başlangıçta borcu 0 olacağından 0.0 tanımlandı
		KrediKarti yeniKart = new KrediKarti(limit,0.0);
		this.krediKartlari.add(yeniKart);
		System.out.println("Kredi Kartınız Başarıyla Tanımlandı!");
		
	}
	//ArrayList<BankaHesabi> hesaplar = new ArrayList<>(); TANIMLAMASI YAPILMIŞTIR
	public void hesapSil(BankaHesabi hesap) {
		this.hesaplar.remove(hesap);
	}
	
	public void krediKartSil(KrediKarti kart) {
		this.krediKartlari.remove(kart);
	}
	
	public String toString() {
		String metin = "Müşteri Numarası: " + musteriNumarasi +  " " + super.getAd() + "\n";
		
		for (int i = 0 ; i < hesaplar.size() ; i++) {
			metin += hesaplar.get(i).toString() + "\n";
		}
		
		for (int i = 0 ; i < krediKartlari.size() ; i++) {
			metin += krediKartlari.get(i).toString()  + "\n";
		}
		
		return metin;
	}
}
