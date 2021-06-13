package com.example.ecommerce.model;

public class InvoiceItem {
	private String item;
	private double unitPrice;
	private int quantity;
	private String offer_applied;
	private double amount;
	
	public InvoiceItem() {
		super();
	}
	
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getOffer_applied() {
		return offer_applied;
	}

	public void setOffer_applied(String offer_applied) {
		this.offer_applied = offer_applied;
	}

	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
