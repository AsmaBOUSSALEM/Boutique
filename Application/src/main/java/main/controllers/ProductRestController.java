package main.controllers;

import com.alma.boutique.api.repositories.ProductRepository;
import model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductRestController {
    @Autowired
    ProductRepository productRepository;


    @RequestMapping("/api/products")
    public List<Product> getAllproducts(Model model) {
        return productRepository.findAll();
    }

    @RequestMapping("/api/products/{id}")
    public Product getProductById(@PathVariable String id, Model model) {
        return productRepository.findOne(id);
    }


    @RequestMapping("/api/get-total-price")
    public double getTotalPrice(@RequestParam(value="products", required = true) String[] products)
    {
        List<Product> productsList = new ArrayList<>();
        double totalPrice = 0;
        for (int i = 0; i < products.length; i++) {
            productsList.add(productRepository.findOne(products[i]));
            totalPrice += productsList.get(i).getPrice();
        }
        return totalPrice;
    }

}
