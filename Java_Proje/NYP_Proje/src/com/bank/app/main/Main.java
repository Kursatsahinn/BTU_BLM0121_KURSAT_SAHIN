package com.bank.app.main;
import com.bank.app.people.*;
import java.util.ArrayList;
import com.bank.app.accounts.*;
import com.bank.app.cards.*;
import com.bank.app.service.*;
import java.util.Scanner;


public class Main {
	
	public static void satirBosluguEkleme() {
		System.out.printf("\n \n");
	}
	
	public static void personelIslemler() {
		System.out.println("HANGİ İŞLEMİ YAPMAK İSTİYORSUNUZ?");
		System.out.println("1) Müşteri Kaydı Oluşturma");
		System.out.println("2) Müşteri Hesap Ekleme");
		System.out.println("3) Müşteri Hesap Silme");
		System.out.println("4) Müşteri Kredi Kartı Ekle");
		System.out.println("5) Müşteri Kredi Kartı Sil");
		System.out.println("6) Güncel Müşterileri Listele");
		System.out.println("7) Müşteri Seçimi");
		System.out.println("8) Çıkış");
	}
	
	public static void musteriIslemler() {
		System.out.println("HANGİ İŞLEMİ YAPMAK İSTİYORSUNUZ?");
		System.out.println("1) Hesap Bilgilerini Göster");
		System.out.println("2) Hesaba Para Yatır");
		System.out.println("3) Hesaptan Para Çek");
		System.out.println("4) Hesaplar Arası Para Transferi");
		System.out.println("5) Kredi Kartı Borç Ödeme");
		System.out.println("6) Çıkış");
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		//İŞLEMLERİN YAPILABİLMESİ İÇİN BANKA PERSONELİNE İHTİYAÇ VAR
		BankaPersoneli patron = BankaService.bankaPersonelOluşturma("Kürşat", "Şahin", "kursat@gmail.com", 1234567);
	
		//İŞLEMLERİN GÖSTERİLEBİLMESİ İÇİN İLK MÜŞTERİNİN OLUŞTURULMASI
		Musteri ilkMusteri = BankaService.musteriOlusturma("Halit", "Kılıç", "halit@gmail.com", 12345678, patron);
		
		BankaService.musteriHesapEkle(ilkMusteri, "Vadesiz Hesap");
		BankaService.musteriHesapEkle(ilkMusteri, "Yatırım Hesabı");
		BankaService.krediKartiEkle(ilkMusteri, 15000);
		ilkMusteri.getKrediKartlari().get(0).setGuncelBorc(1453);
		
		
		
		
		//BURADAKİ MAİL VE TELEFON NUMARASI KULLANICI ADI ŞİFRE GİBİ DÜŞÜNÜLEBİLİR
		//PROGRAMI KULLANAN KİŞİDEN BİR KULLANICI ADI VE ŞİFRE GİRMESİ İSTENECEK
		Kisi kisiOrnek = null;
		System.out.print("Kullanıcı Adınızı Giriniz: ");
		String kullaniciAdi =input.nextLine();
		System.out.print("Şifre Giriniz: ");
		int sifre = input.nextInt();
		boolean kullaniciVarMi = false;
		//KULLANICIDAN ALINAN VERİLERİN BİR KULLANICI İLE EŞLEŞİP EŞLEŞMEDİĞİNİ KONTROL EDECEĞİZ
		ArrayList<Kisi> kayitliKisiler = Kisi.getKisiler();
		for (Kisi k : kayitliKisiler) {
		    
		    if (k.getEmail().equals(kullaniciAdi) && k.getTelefonNumarasi() == sifre) {
		    	//GİRİŞ BAŞARILI İSE K NESNESİNDEKİ BİLGİLERİ kisiOrnek NESNESİNE ATIYORUZ
		        kisiOrnek = k; 
		        kullaniciVarMi= true;
		        System.out.println("Giriş Başarılı!");
		        break;
		    }
		}

		if (!kullaniciVarMi) {
			System.out.println("Kullanıcı Bulunamadı!");
		}
		
		if (kisiOrnek instanceof BankaPersoneli) {
			//GİRİŞ YAPAN KİŞİ BANKA PERSONELİ İSE kisiOrnek NESNESİ BANKA PERSONELİ SINIFINA CAST EDİLİR
			BankaPersoneli personel = (BankaPersoneli) kisiOrnek;
			//BANKA PERSONELİNE BAĞLI MÜŞTERİLERİN GETİRİLMESİ
			ArrayList<Musteri> musteriler = personel.getMusteriler();
			
			//MÜŞTERİ SEÇİMİ İÇİN GEREKLİ METOTUN ÇAĞIRILMASI
			Musteri secilenMusteri = BankaService.musteriSecimi(musteriler);
			
			boolean islemDevami = true;
			while(islemDevami) {
				personelIslemler();
				System.out.print("Yapılacak İşlemi Seçin: ");
				int secilenIslem = input.nextInt();
				input.nextLine();
				
				switch(secilenIslem) {
					case 1://MÜŞTERİ KAYDININ OLUŞTURULMASI İÇİN GEREKLİ BİLGİLERİN ALINMASI
						System.out.print("Müşteri Adını Giriniz: ");
						String musteriAdi = input.nextLine();
						System.out.print("Müşteri Soyadını Giriniz: ");
						String musteriSoyadi = input.nextLine();
						System.out.print("Müşteri E-Mail Bilgisini Giriniz: ");
						String musteriEmail = input.nextLine();
						System.out.print("Müşteri Telefon Numarasını Giriniz: ");
						int tel = input.nextInt();
						BankaService.musteriOlusturma(musteriAdi, musteriSoyadi, musteriEmail, tel, personel);
						satirBosluguEkleme();
						break;
					case 2://MÜŞTERİ ADINA HESAP AÇILMA İŞLEMİ
						System.out.print("Açmak İstediğiniz Hesap Türünü Yazınız: (Vadesiz Hesap/ Yatırım Hesabı)");
						String acilacakHesap = input.nextLine();
						if (acilacakHesap.equals("Vadesiz Hesap") || acilacakHesap.equals("Yatırım Hesabı")) {
							BankaService.musteriHesapEkle(secilenMusteri, acilacakHesap);
							satirBosluguEkleme();
						}
						else {
							System.out.println("İstenilen Formatta Hesap Türü Seçiniz!");
						}
						break;
					case 3://MÜŞTERİNİN BANKA HESABININ SİLİNMESİ
						if (secilenMusteri.getHesaplar().size() > 0) {
							BankaService.musteriHesapListele(secilenMusteri);
							System.out.print("Hangi Hesabı Silmek İstersiniz: ");
							int secilenHesapIndex = input.nextInt();
							//MÜŞTERİNİN HESAPLARINA ERİŞİLEREK GİRİLEN İNDEX BİLGİSİNDEKİ HESABI GETİRME İŞLEMİ
							//secilenHesapIndex - 1 DENMESİNİN SEBEBİ İNDEXLEMENİN 0 DAN BAŞLIYOR OLMASIDIR
							BankaHesabi secilenHesap = secilenMusteri.getHesaplar().get(secilenHesapIndex -1);
							BankaService.musteriHesapSil(secilenMusteri, secilenHesap);
							satirBosluguEkleme();
						}
						else {
							System.out.println("Müşterinin Hesabı Bulunmamaktadır!");
							satirBosluguEkleme();
						}
						
						break;
					case 4://MÜŞTERİYE KREDİ KARTI TANIMLANMASI
						System.out.print("Kredi Kartı İçin Limit Belirleyin: ");
						int limit = input.nextInt();
						BankaService.krediKartiEkle(secilenMusteri, limit);
						satirBosluguEkleme();
						break;
					case 5://MÜŞTERİ KREDİ KARTI SİLME İŞLEMİ
						if (secilenMusteri.getKrediKartlari().size() > 0) {
							BankaService.musteriKrediKartiListele(secilenMusteri);
							System.out.print("Hangi Kartı Silmek İstersiniz: ");
							int secilenKartIndex = input.nextInt();
							//GİRİLEN İNDEX BİLGİSİNE GÖRE KREDİ KARTI NESNESİNİN ÇEKİLMESİ
							KrediKarti secilenKart = secilenMusteri.getKrediKartlari().get(secilenKartIndex -1 );
							BankaService.krediKartiSil(secilenMusteri, secilenKart);
							satirBosluguEkleme();
						}
						else {
							System.out.println("Müşterinin Kredi Kartı Bulunmamaktadır!");
							satirBosluguEkleme();
						}
						
						break;
					case 6://PERSONELE AİT MÜŞTERİLER LİSTEYE ATANARAK BİLGİLERİYLE YAZDIRILMAKTADIR
						ArrayList<Musteri> guncelMusteriler = personel.getMusteriler();
						for (int i = 0 ; i < guncelMusteriler.size() ; i++) {
							System.out.printf("%d) " + guncelMusteriler.get(i).toString(),i+1);
							satirBosluguEkleme();
						}
						break;
					case 7://MÜŞTERİ DEĞİŞTİRME SEÇENEĞİ
						secilenMusteri = BankaService.musteriSecimi(personel.getMusteriler());
						satirBosluguEkleme();
						break;
					
					case 8:
						islemDevami = false;
						System.out.println("Programdan Çıkış Yapılıyor...");
						break;
						
					default:
						System.out.println("Hatalı Seçim Yaptınız!!!");
						
					
				}
			}
			
		}
		else if (kisiOrnek instanceof Musteri) {

			Musteri musteri = (Musteri) kisiOrnek;
			boolean islemDevami = true;
			
			while(islemDevami) {
				musteriIslemler();
				System.out.print("Yapılacak İşlemi Seçin: ");
				int secilenIslem = input.nextInt();
				input.nextLine();
				
				switch(secilenIslem) {
					case 1: //MÜŞTERİNİN BİLGİLERİNİN YAZDIRILMASI
						System.out.println(musteri.toString());
						satirBosluguEkleme();
						break;
					case 2://MÜŞTERİNİN HESAPLARININ LİSTELENMESİ
						BankaService.musteriHesapListele(musteri);
						System.out.print("Para Yatıralacak Hesabı Seçiniz: ");
						int hesapSecimiParaEkleme = input.nextInt();
						//HESAP SAYISINDAN FAZLA VEYA NEGATİF BİR SEÇİM YAPILIRSA HATA MESAJI YAZDIRMA
						if (hesapSecimiParaEkleme >  musteri.getHesaplar().size() || hesapSecimiParaEkleme < 0) {
							System.out.println("Böyle Bir Hesabınız Bulunmamaktadır!");
						}
						else {
							//HESABIN SEÇİLMESİ
							BankaHesabi secilenHesapParaEkleme = musteri.getHesaplar().get(hesapSecimiParaEkleme - 1);
							
							System.out.print("Hesaba Yatırılacak Para Miktarını Giriniz: ");
							double yatirilanMiktar = input.nextDouble();
							
							if (yatirilanMiktar >= 0) {
								BankaService.hesabaParaEkle(musteri, secilenHesapParaEkleme, yatirilanMiktar);
							}
							else {
								System.out.println("Hesabınıza Böyle Bir Para Yatırılamaz!");
							}
						}
						
						satirBosluguEkleme();
						break;
					case 3:
						//MÜŞTERİNİN PARA ÇEKEBİLMESİ İÇİN HESAPLARININ LİSTELENMESİ
						BankaService.musteriHesapListele(musteri);
						System.out.print("Para Çekilecek Hesabı Seçiniz: ");
						int hesapSecimiParaCekme = input.nextInt();
						
						//İSTENMEYEN GİRİŞ DURUMLARI İÇİN HATA KONTROLÜ
						if (hesapSecimiParaCekme > musteri.getHesaplar().size() || hesapSecimiParaCekme < 0) {
							System.out.println("Böyle Bir Hesabınız Bulunmamaktadır!");
						}
						else {
							BankaHesabi secilenHesapParaCekme = musteri.getHesaplar().get(hesapSecimiParaCekme - 1);
							
							
							System.out.print("Hesaptan Çekilecek Para Miktarını Giriniz: ");
							double cekilenMiktar = input.nextDouble();
							
							if (cekilenMiktar > secilenHesapParaCekme.getBakiye()) {
								System.out.println("Hesabınızda Bu Miktarda Para Bulunmamaktadır!");
							}
							else {
								BankaService.hesaptanParaCek(musteri, secilenHesapParaCekme, cekilenMiktar);
							}
						}
						
						satirBosluguEkleme();
						break;
					case 4:
						BankaService.musteriHesapListele(musteri);
						//PARANIN GÖNDERİLECEĞİ VE ALINACAĞI HESAPLAR İÇİN SEÇİM ALANI
						System.out.print("Parayı Gönderen Hesabı Seçiniz: ");
						int gonderenHesap = input.nextInt();
						System.out.print("Paranın Yatırılacağı Hesabı Seçiniz: ");
						int yatirilanHesap = input.nextInt();
						if (gonderenHesap > musteri.getHesaplar().size() || gonderenHesap < 0) {
							System.out.println("Böyle Bir Hesabınız Bulunmamaktadır!");
						}
						else if (yatirilanHesap > musteri.getHesaplar().size() || yatirilanHesap < 0) {
							System.out.println("Böyle Bir Hesabınız Bulunmamaktadır!");
						}
						else {
							//BANKA HESAPLARININ İNDEX'E DİKKAT EDİLEREK BİR NESNEYE ATANMASI
							BankaHesabi gonderen = musteri.getHesaplar().get(gonderenHesap - 1);
							BankaHesabi yatirilan = musteri.getHesaplar().get(yatirilanHesap - 1);
							System.out.print("Aktarılacak Para Miktarını Giriniz: ");
							double aktarilacakMiktar = input.nextDouble();
							
							BankaService.paraTransferi(yatirilan, gonderen, aktarilacakMiktar, musteri);
						}
						satirBosluguEkleme();
						break;
					case 5:
						BankaService.musteriKrediKartiListele(musteri);
						System.out.print("Borç Ödemesi Yapılacak Kartı Seçiniz: ");
						int kartIndex = input.nextInt();
						
						if (kartIndex > musteri.getKrediKartlari().size() || kartIndex < 0) {
							System.out.println("Böyle Bir Kartınız Bulunmamaktadır!");
						}
						else {
							//ALINAN KARTIN İNDEX NUMARASINA GÖRE BİR KART NESNESİNİN OLUŞTURULMASI
							KrediKarti secilenKart = musteri.getKrediKartlari().get(kartIndex - 1);
							System.out.println(secilenKart.toString());
							System.out.print("Ödemek İstediğiniz Borç Miktarını Giriniz: ");
							double odenecekBorc = input.nextDouble();
							
							BankaService.musteriHesapListele(musteri);
							System.out.print("Ödemenin Yapılacağı Hesabı Seçiniz: ");
							int hesapIndex = input.nextInt();
							if (odenecekBorc < 0) {
								System.out.println("Geçersiz Borç Ödeme İşlemi!");
							}
							else if (hesapIndex > musteri.getHesaplar().size() || hesapIndex < 0) {
								System.out.println("Geçersiz Banka Hesabı Seçimi!");
							}
							else {
								BankaService.krediKartiBorcOdeme(secilenKart, odenecekBorc, musteri, hesapIndex - 1);
							}
						}
						satirBosluguEkleme();
						break;
					case 6:
						islemDevami = false;
						System.out.println("Program Kapatılıyor...");
						break;
					default:
						System.out.println("Hatalı Seçim Yaptınız!");
						
						
				}
			}
			
		}
		
		
		
		
		
		input.close();
		
		
	}

}
