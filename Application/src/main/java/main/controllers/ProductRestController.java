package main.controllers;

import com.alma.boutique.api.repositories.ProductRepository;
import model.product.Product;
import model.shopping_cart.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
public class ProductRestController {
    @Autowired
    ProductRepository productRepository;

    ShoppingCart shoppingCart = null;

    @RequestMapping("/api/products")
    public List<Product> getAllproducts(Model model) {
        return productRepository.findAll();
    }

    @RequestMapping("/api/products/{id}")
    public Product getProductById(@PathVariable String id, Model model) {
        return productRepository.findOne(id);
    }

    @RequestMapping("/api/add-to-cart")
    public int addToCart(@RequestParam(value="id", required= true) String id) {
        Product product = productRepository.findOne(id);
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
        }
        shoppingCart.getProducts().put(shoppingCart.getProducts().size() + 1, product);
        return shoppingCart.getProducts().get(product).;
    }
}
