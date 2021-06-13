package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.model.Invoice;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProductService;

@RestController
@CrossOrigin
public class ProductController {

	@Autowired 
	private ProductService service;
	
	@GetMapping("/status")
	public String status() {
		return "Status Ok";
	}
	
	@GetMapping("/products")
	public List<Product> getProducts() {
		return service.getProducts();
	}
	
	@GetMapping("/products/category/{name}")
	public List<Product> getProductsByCategory(@PathVariable(name="name") String name) {
		return service.getProductsByCategory(name);
	}
	
	@PostMapping("/create-order")
	public Invoice createOrder(@RequestBody List<Order> orders) {
		return service.createOrder(orders);
	}
	
}
