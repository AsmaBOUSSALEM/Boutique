package main.controllers;

import main.repositories.ProductRepository;
import model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @RequestMapping("/products")
    public List<Product> getAllproducts(Model model) {
        return productRepository.findAll();
    }

    @RequestMapping("/products/{id}")
    public Product getProductById(@PathVariable String id, Model model) {
        return productRepository.findOne(id);
    }

}
