package com.estsoft.bookmal.vo;

public class Purchase_detailVO {
	private int purchase_id;
	private int book_id;
	private int quantity;
	public int getPurchase_id() {
		return purchase_id;
	}
	public void setPurchase_id(int purchase_id) {
		this.purchase_id = purchase_id;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Purchase_detailVO [purchase_id=" + purchase_id + ", book_id=" + book_id + ", quantity=" + quantity
				+ "]";
	}
	
}
