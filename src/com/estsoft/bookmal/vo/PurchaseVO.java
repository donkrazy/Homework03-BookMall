package com.estsoft.bookmal.vo;

public class PurchaseVO {
	private int id;
	private int user_id;
	private String address;
	private String date;
	private int price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "PurchaseVO [id=" + id + ", user_id=" + user_id + ", address=" + address + ", date=" + date + ", price="
				+ price + "]";
	}

}
