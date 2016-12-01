package main.controllers;

import com.alma.boutique.api.repositories.OrderRepository;
import com.alma.boutique.api.repositories.ProductRepository;
import com.alma.boutique.api.services.ValidateCreditCardSOAP;
import model.order.Order;
import model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.soap.*;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by asmaboussalem on 25/11/2016.
 */
@RestController
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;

    @RequestMapping("/orders")
    public List<Order> getAllproducts(Model model) {
        return orderRepository.findAll();
    }

    @RequestMapping("/orders/{id}")
    public Order getProductById(@PathVariable String id, Model model) {
        return orderRepository.findOne(id);
    }

    @RequestMapping("/submit-order")
    public String submitOrder(@RequestParam(value="products", required = true) String[] products)
    {
        Order order = new Order();
        List<Product> productsList = new ArrayList<>();
        double totalPrice = 0;
        for (int i = 0; i < products.length; i++) {
            productsList.add(productRepository.findOne(products[i]));
            totalPrice += productsList.get(i).getPrice();
        }
        order.setProducts(productsList);
        order.setTotalPrice(totalPrice);
        return "order Created successfully";
    }
    @RequestMapping("/validate-credit-card")
    public boolean validateCreditCard(@RequestParam(value="type", required=true) String type,
                                      @RequestParam(value="number", required=true) String number)
    {
        ValidateCreditCardSOAP validator = new ValidateCreditCardSOAP();
        return validator.validateCreditCardSOAP(type, number);
    }

}
