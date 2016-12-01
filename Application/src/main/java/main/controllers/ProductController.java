package main.controllers;

import com.alma.boutique.api.repositories.ProductRepository;
import model.order.Order;
import model.product.Product;
import model.shopping_cart.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asmaboussalem on 26/11/2016.
 */
@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping("/")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @RequestMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    @RequestMapping("/import-products")
    public String importProducts(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ArrayList<Product>> productResponse = restTemplate.exchange("http://localhost:8080/api/products",
                HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<Product>>() {
                });
        ArrayList<Product> products = productResponse.getBody();
        productRepository.save(products);
        return "products";
    }



    @RequestMapping("/")
    public String init(){
        return "welcome to boutique";
    }






}
