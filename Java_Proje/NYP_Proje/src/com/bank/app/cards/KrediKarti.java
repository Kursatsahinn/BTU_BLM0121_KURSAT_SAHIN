package com.bank.app.cards;

public class KrediKarti {
	private String kartNumarasi;
	private double limit;
	private double guncelBorc;
	private double kullanilabilirLimit;
	
	
	public KrediKarti(double limit, double guncelBorc) {
		this.limit = limit;
		this.guncelBorc = guncelBorc;
		//Başlangıçta kullanılabilir limit ile genel limit değeri aynı olur
		kullanilabilirLimit = limit - guncelBorc;
	}
	
	public String getKartNumarasi() {
		return kartNumarasi;
	}
	//KART NUMARASI SONRADAN OLUŞTURULACAĞI İÇİN SETTER METODU BULUNMAKTADIR.
	public void setKartNumarasi(String kartNumarasi) {
		this.kartNumarasi = kartNumarasi;
	}
	
	public double getLimit() {
		return limit;
	}
	
	public void setLimit(double limit) {
		this.limit = limit;
	}
	
	public double getGuncelBorc() {
		return guncelBorc;
	}
	
	public void setGuncelBorc(double guncelBorc) {
		this.guncelBorc = guncelBorc;
		this.kullanilabilirLimit -= guncelBorc;
	}
	
	public double getKullanilabilirLimit() {
		return kullanilabilirLimit;
	}
	
	public void setKullanilabilirLimit(double kullanilabilirLimit) {
		this.kullanilabilirLimit = kullanilabilirLimit;
	}
	
	public String toString() {
		return "Kart Numarası: " + kartNumarasi + ", Limit: " + limit +
				", Güncel Borç: " + guncelBorc + ", Kullanılabilir Limit: " + kullanilabilirLimit;
	}
}
