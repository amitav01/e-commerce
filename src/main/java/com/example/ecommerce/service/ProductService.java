package com.example.ecommerce.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.springframework.stereotype.Service;

import com.example.ecommerce.model.Invoice;
import com.example.ecommerce.model.InvoiceItem;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.Product;

@Service
public class ProductService {

	private File file = new File("assets/Product.txt");
	
	public List<Product> getProducts() {
		BufferedReader br = null;
		List<Product> products = new ArrayList<Product>();
		
		try {
			br = new BufferedReader(new FileReader(file));
			String st;
			
			Product product = null;
			while ((st = br.readLine()) != null) {
			   String[] arr = st.split(";");
			   product = new Product();
			   product.setProductId(Integer.parseInt(arr[0]));
			   product.setItem(arr[1]);
			   product.setCategories(Arrays.asList(arr[2].split(" / ")));
			   product.setPrice(Double.parseDouble(arr[3]));
			   product.setOffer(arr.length == 5 ? arr[4] : null);
			   
			   products.add(product);
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		  
		return products;
	}

	public List<Product> getProductsByCategory(String name) {
		List<Product> products = getProducts();
		List<Product> productsByCategory = new ArrayList<Product>();
		
		Iterator<Product> itr = products.iterator();
		while(itr.hasNext()) {
			Product product = (Product) itr.next();
			if(product.getCategories().contains(name)) {
				productsByCategory.add(product);
			}
		}
		return productsByCategory;
	}

	private Product getProductById(List<Product> products, int productId) {
		
		Iterator<Product> itr = products.iterator();
		while(itr.hasNext()) {
			Product product = (Product) itr.next();
			if(product.getProductId() == productId) return product;
		}
		return null;
	}

	public Invoice createOrder(List<Order> orders) {
		List<Product> products = getProducts();
		Invoice invoice = new Invoice();
		List<InvoiceItem> items = new ArrayList<InvoiceItem>();
		double total = 0;
		
		Iterator<Order> itr = orders.iterator();
		while(itr.hasNext()) {
			Order order = (Order) itr.next();
			Product product = getProductById(products, order.getProductId());
			
			InvoiceItem item = new InvoiceItem();
			item.setItem(product.getItem());
			item.setQuantity(order.getQuantity());
			item.setUnitPrice(product.getPrice());
			item.setOffer_applied(product.getOffer());
			double amount = getAmount(product.getPrice(), order.getQuantity(), product.getOffer());
			item.setAmount(amount);
			total += amount;
			items.add(item);
		}
		
		invoice.setItems(items);
		invoice.setTotal(total);
		
		return invoice;
	}
	
	private double getAmount(double price, int qty, String offer) {
		if(offer == null) return price;
		
		switch(offer) {
			case "Buy1 Get 1": {
				return (qty - (qty/2)) * price;
			}
			case "Buy1 Get 2": {
				return (qty == 1) ? price : ((qty == 2) ? (2 * price) : (qty/2) * price);
			}
			case "Buy2 Get 1": {
				return (qty - (qty/3)) * price;
			}
			case "Buy3 Get 1": {
				return (qty - (qty/4)) * price;
			}
			default: {
				if(offer.indexOf("%") > -1) {
					double perc = Double.parseDouble(offer.trim().replace("%", ""));
					return qty * (price - (price * (perc / 100)));
				}
				return price;
			}
		}
	}
	
}
