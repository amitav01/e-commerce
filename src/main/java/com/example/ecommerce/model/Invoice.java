package com.example.ecommerce.model;

import java.util.List;

public class Invoice {
	private List<InvoiceItem> items;
	private double total;
	
	public Invoice() {
		super();
	}
	public List<InvoiceItem> getItems() {
		return items;
	}
	public void setItems(List<InvoiceItem> items2) {
		this.items = items2;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	} 
	
}
