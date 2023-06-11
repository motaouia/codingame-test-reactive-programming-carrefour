package org.medmota.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.medmota.demo.entities.Product;
import org.medmota.demo.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/v1/api/products")
public class ProductController {
	
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/{size}")
    public Flux<Product> getProducts(@PathVariable int size) {
        return productService.getProducts(size);
    }

}
