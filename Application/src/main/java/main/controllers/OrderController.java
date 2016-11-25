package main.controllers;

import com.alma.boutique.api.repositories.OrderRepository;
import model.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by asmaboussalem on 25/11/2016.
 */
@RestController
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @RequestMapping("/orders")
    public List<Order> getAllproducts(Model model) {
        return orderRepository.findAll();
    }

    @RequestMapping("/orders/{id}")
    public Order getProductById(@PathVariable String id, Model model) {
        return orderRepository.findOne(id);
    }

}
