package com.bank.app.accounts;

public class BankaHesabi {
	private String iban;
	private double bakiye;
	
	//Sınıfın parametreli konstürüktörü
	public BankaHesabi(double bakiye){
		this.bakiye = bakiye;
	}
	//Sınıf içerisinde tanımlanan değişkenlerin getter ve setter metotlarının oluşturulması
	public String getIban() {
		return iban;
	}
	//BankaHesabı için sonradan iban numarası oluşturulacağı için setIban metotu gereklidir.
	public void setIban (String iban) {
		this.iban = iban;
	}
	//Kullanıcının bakiye işlemleri için getter metotunun oluşturulması.
	public double getBakiye() {
		return bakiye;
	}
	//Kullanıcının bakiyesinin güncellenmesi için setter metotunun oluşturulması.
	public void setBakiye(double bakiye) {
		this.bakiye = bakiye;
	}
	//Sınıf üzerinden oluşturulan nesnenin tüm bilgilerinin ekrana yazılmasını sağlayan metot
	public String toString() {
		return "Iban: " + iban + ", Bakiye: " + bakiye;
	}
}
