package model.order;

import model.product.Product;
import model.shared.Entity;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by asmaboussalem on 16/11/2016.
 */
public class Order implements Entity<Order> {
    @Id
    private String id;
    private double totalPrice;
    private List<Product> products;
    private OrderStatus orderStatus;

    public Order() {
        this.id = null;
        this.totalPrice = 0;
        this.products = new ArrayList<Product>();
        this.orderStatus = null;
    }

    public Order(String id, double totalPrice, List<Product> products, OrderStatus orderStatus) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.products = products;
        this.orderStatus = orderStatus;
    }

    public String getId() {
        return this.id;
    }

    public OrderStatus getOrderStatus() {
        return this.orderStatus;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean sameIdentityAs(Order other) {
        return false;
    }
}
