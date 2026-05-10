package com.bank.app.service;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.bank.app.accounts.*;
import com.bank.app.cards.KrediKarti;
import com.bank.app.people.*;




public class BankaService {
	
	/*
	 * Burada yeni müşteri oluşturulurken hem Musteri sınıfı içerisindeki konstürüktöre veriler gönderilerek
	 * nesne oluşturuluyor. Hem de random sınıfı ile yeni bir müşteri numarası oluşturuluyor. En son da işlemler
	 * devam edebilsin diye bir müşteri nesnesi dışarıya döndürülüyor.
	 * Metotların static olmasının sebebi burada sadece işlem yapılacak. Herhangi bir BankaService sınıfından
	 * nesneye ihtiyaç yoktur.
	 * */
	public static BankaPersoneli bankaPersonelOluşturma(String ad, String soyad, String email, int tel) {
		//PERSONEL ID'NİN RASTGELE OLUŞTURULABİLMESİ İÇİN RANDOM SINIFINA AİT NESNE OLUŞTURMA
		Random r = new Random();
		
		String personelID = "";
		//PARAMETRE OLARAK ALINAN BİLGİLERİN BankaPersoneli NESNESİNE AKTARILARAK YENİ PERSONEL OLUŞTURULMASI
		BankaPersoneli yeniPersonel = new BankaPersoneli(ad,soyad,email,tel);
		
		for (int i = 0 ; i < 10 ; i++) {
			personelID += (r.nextInt(9) + 1);
		}
		//RASTGELE OLUŞTURULAN ID'NİN PERSONELE TANIMLANMASI
		yeniPersonel.setPersonelID(personelID);
		System.out.println("Yeni Banka Personeli Oluşturuldu!");
		//SİSTEMDEKİ TÜM KİŞİLERİN BİR ARADA TUTULMASI İÇİN ARRAYLİST İÇERİSİNE KAYDEDİLMESİNİ SAĞLAYAN METOT
		Kisi.kisiEkle(yeniPersonel);
		return yeniPersonel;
	}
	
	public static Musteri musteriOlusturma(String ad, String soyad, String email, int tel, BankaPersoneli personel) {
		//Kullanıcıdan başlangıçta bu bilgileri alarak Musteri nesnesi oluşturacağız.
		//BANKA PERSONELİNDE OLDUĞU GİBİ RASTGELE MÜŞTERİ NUMARASI OLUŞTURMAK İÇİN OLUŞTURULAN RANDOM SINIFINA AİT NESNE
		Random r = new Random();
		String musteriNo = "";
		//ALINAN BİLGİLERİN MÜŞTERİ SINIFINA AKTARILARAK NESNE OLUŞTURULMASI
		Musteri yeniMusteri = new Musteri(ad,soyad,email,tel);
		
		//Müşteri numarasını 11 haneli kabul ettim.
		for (int i = 0 ; i < 10 ; i++) {
			musteriNo += (r.nextInt(9)+ 1);
		}
		//RASTGELE OLUŞTURULAN MÜŞTERİ NUMARASININ MÜŞTERİYE TANIMLANMASI
		yeniMusteri.setMusteriNumarasi(musteriNo);
		System.out.println("Müşteri Kaydı Başarıyla Gerçekleşti!");
		Kisi.kisiEkle(yeniMusteri);
		//OLUŞTURULAN MÜŞTERİNİN PERSONEL İLE İLİŞKİLENDİRİLMESİ
		personel.musteriEkle(yeniMusteri);
		return yeniMusteri;
		
	}
	
	public static void musteriHesapEkle(Musteri musteri, String hesapTuru) {
		Random r = new Random();

		String yeniIban = "TR" ;
		
		for(int i = 0 ; i < 23 ; i++) {
			yeniIban += (r.nextInt(9) + 1);
		}
		
		musteri.hesapEkle(hesapTuru);
		
		//Müşteri üzerinden son eklenan hesaba giderek iban numarasını atıyoruz
		BankaHesabi sonHesap = musteri.getHesaplar().get(musteri.getHesaplar().size() - 1);
		sonHesap.setIban(yeniIban);
		
		
	}
	
	public static void musteriHesapSil(Musteri musteri, BankaHesabi hesap) {
		//MÜŞTERİNİN HESAPLARI ARRAYLİST OLARAK ALINIR
		ArrayList<BankaHesabi> hesaplar = musteri.getHesaplar();
		int hesapIndexi = hesaplar.indexOf(hesap);
		
		//MÜŞTERİNİN BAKİYESİNİN 0 OLUP OLMADIĞI KONTROL EDİLİR
 		if (hesaplar.get(hesapIndexi).getBakiye() != 0) {
			System.out.println("Lütfen Öncelikle Bakiyenizi Başka Hesaba Aktarın");
		}
		else {
			musteri.hesapSil(hesap);
			System.out.println("Hesabınız Başarıyla Silindi");
		}
		
	}
	
	public static void musteriHesapListele(Musteri musteri) {
		//PARAMETRE OLARAK GÖNDERİLEN MÜŞTERİ NESNESİ ÜZERİNDEN HESAPLARA ULAŞILARAK HESAP BİLGİLERİ YAZDIRILMAKTADIR
		ArrayList<BankaHesabi> hesaplar = musteri.getHesaplar();
		for (int i = 0 ; i < hesaplar.size() ; i++) {
			System.out.printf("%d) %s \n",(i+1),hesaplar.get(i).toString());
		}
	}
	
	public static void paraTransferi(BankaHesabi alici, BankaHesabi gonderen, double miktar,Musteri musteri) {
		double gonderenBakiye = gonderen.getBakiye();
		if(miktar < 0) {
			System.out.println("Böyle Bir İşlem Yapamazsınız!");
		}
		else if (gonderenBakiye >= miktar) {
			//GONDEREN HESABI VADESİZ VEYA YATIRIM HESABI OLARAK CAST EDEREK paraTransferi METODUNA ULAŞILMAKTADIR
			
			if (gonderen instanceof VadesizHesap) {
				((VadesizHesap)gonderen).paraTransferi(alici, gonderen, miktar);
			}
			else {
				((YatirimHesabi)gonderen).paraTransferi(alici, gonderen, miktar);
			}
			
			System.out.println("Para Transferi Başarıyla Gerçekleşti");
		}
		else {
			System.out.println("Hesabınızdaki Para Miktarı Eksik!");
		}
	}
	
	public static void krediKartiEkle(Musteri musteri, double limit) {
		Random r = new Random();
		String kartNum = "";
		for (int i = 0 ; i < 15 ; i++) {
			kartNum += (r.nextInt(9) + 1);
		}
		
		musteri.krediKartiEkle(limit);
		ArrayList<KrediKarti> kartlar = musteri.getKrediKartlari();
		
		KrediKarti sonKart = kartlar.get(kartlar.size()-1);
		//KART NUMARASI BURADA RASTGELE ULAŞACAĞI İÇİN setter METODU BURADA KULLANILMIŞTIR
		sonKart.setKartNumarasi(kartNum);
		
	}
	
	public static void krediKartiSil(Musteri musteri,KrediKarti kart) {
		
		//MÜŞTERİNİN SEÇİLEN KARTINDAKİ GÜNCEL BORÇ BİLGİSİNİN ÇEKİLMESİ
		double guncelBorc = kart.getGuncelBorc();
		//GÜNCEL BORÇ 0 İSE KART SİLİNEBİLİR
		if (guncelBorc == 0) {
			musteri.krediKartSil(kart);
			System.out.println("Kredi Kartınız Başarıyla Silindi");
		}
		else {// GÜNCEL BORÇ 0 DEĞİL İSE MÜŞTERİ BORÇ ÖDEMESİ YAPMALI
			System.out.println("Kartınızı Silmeden Önce Borç Ödemesi Yapmalısınız!");
			System.out.println("Güncel Borcunuz: " + guncelBorc);
		}
	}
	//GÖNDERİLEN MÜŞTERİ NESNESİ ÜZERİNDEN KART BİLGİLERİNE ULAŞILARAK YAZDIRILMASI
	public static void musteriKrediKartiListele(Musteri musteri) {
		ArrayList<KrediKarti> kartlar = musteri.getKrediKartlari();
		for (int i = 0 ; i < kartlar.size() ; i++) {
			System.out.printf("%d) %s \n",(i+1),kartlar.get(i).toString());
		}
		
	}
	
	public static void krediKartiBorcOdeme(KrediKarti kart, double odenecekBorc, Musteri musteri, int hesapIndex) {
		ArrayList<BankaHesabi> hesaplar = musteri.getHesaplar();
		double guncelBorc = kart.getGuncelBorc();
		BankaHesabi odemeYapilacakHesap = hesaplar.get(hesapIndex);
		double hesapBakiye = odemeYapilacakHesap.getBakiye();
		if (guncelBorc == 0) {
			System.out.println("Kartınızda Borç Bulunmamaktadır!");
		}
		else if (odenecekBorc > hesapBakiye) {
			System.out.println("Hesabınızdaki Para Miktarı Bu Ödeme İçin Yetersiz!");
		}
		else if (odenecekBorc > guncelBorc) {
			System.out.println("Borcunuzdan Fazla Ödeme Miktarı Yaptınız Borcunuz Sıfırlanıyor...");
			System.out.println("Kalan Paranız Hesabınıza Geri Yatırılıyor...");
			kart.setGuncelBorc(0);
			odemeYapilacakHesap.setBakiye(hesapBakiye - guncelBorc);
			kart.setKullanilabilirLimit(kart.getKullanilabilirLimit() + guncelBorc);
		}
		else {
			System.out.println("Borç Ödemesi Yapılıyor..");
			kart.setGuncelBorc(guncelBorc - odenecekBorc);
			odemeYapilacakHesap.setBakiye(hesapBakiye - odenecekBorc);
			kart.setKullanilabilirLimit(kart.getKullanilabilirLimit() + odenecekBorc);
			System.out.println("Güncel Borcunuz: "+ kart.getGuncelBorc());
		}
	}
	
	
	public static void hesabaParaEkle(Musteri musteri, BankaHesabi hesap, double miktar) {
		
		if (hesap.getClass() == YatirimHesabi.class) {
			YatirimHesabi yatirimHesabi = (YatirimHesabi) hesap;
			yatirimHesabi.paraEkle(miktar);
		}
		else if (hesap.getClass() == VadesizHesap.class) {
			VadesizHesap vadesizHesap = (VadesizHesap) hesap;
			vadesizHesap.paraEkle(miktar);
		}
		else {
			System.out.println("Tanımlanamayan Hesap!");
		}
	}
	
	public static void hesaptanParaCek(Musteri musteri, BankaHesabi hesap, double miktar) {
		
		if (hesap.getClass() == YatirimHesabi.class) {
			YatirimHesabi yatirimHesabi = (YatirimHesabi) hesap;
			yatirimHesabi.paraCek(miktar);
		}
		else if (hesap.getClass() == VadesizHesap.class) {
			VadesizHesap vadesizHesap = (VadesizHesap) hesap;
			vadesizHesap.paraCek(miktar);
		}
		else {
			System.out.println("Tanımlanamayan Hesap!");
		}
	}
	
	
	public static Musteri musteriSecimi(ArrayList<Musteri> musteriler) {
		Scanner input = new Scanner(System.in);
		//PERSONELİN HATALI GİRİŞ YAPMASINA KARŞIN MÜŞTERİ SAYISININ ALINMASI
		int musteriSayisi = musteriler.size();
		int musteriSecimi;
		//BOŞ BİR MÜŞTERİ NESNESİNİN OLUŞTURULMASI
		Musteri secilenMusteri = null;
		//GİRİŞ YAPAN PERSONELE BAĞLI MÜŞTERİLERİN LİSTELENMESİ
		for (int i = 0 ; i < musteriSayisi ; i++) {
			System.out.printf("%d) " + musteriler.get(i).toString(),i+1);
		}
		//MÜŞTERİ SAYISI 1 VEYA DAHA FAZLA İSE SEÇİM İŞLEMİNİN YAPILMASI
		if (musteriSayisi >= 1) {
			//PERSONELİN DOĞRU BİR SEÇİM YAPANA KADAR SİSTEMDE KALMASINI SAĞLAYAN DÖNGÜ
			while(true) {
				System.out.print("Hangi Müşteriniz ile İşlem Yapmak İstiyorsunuz: ");
				musteriSecimi = input.nextInt();
				if (musteriSecimi > musteriSayisi || musteriSecimi < 0) {
					System.out.println("Hatalı Seçim Yaptınız Lütfen Tekrar Seçim Yapınız!");
				}
				else {
					break;
				}
			}
			//BELİRLENEN MÜŞTERİ NESNESİNİN BİLGİLERİNİN secilenMusteri'YE AKTARILMASI
			secilenMusteri = musteriler.get(musteriSecimi-1);
		}
		//MÜŞTERİNİN GERİ DÖNDÜRÜLMESİ
		return secilenMusteri;
	}
	
	
	
	
	
	
	
	
}
