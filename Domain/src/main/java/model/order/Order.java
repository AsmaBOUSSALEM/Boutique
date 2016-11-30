package model.order;

import model.shared.Entity;
import model.shopping_cart.ShoppingCart;

import java.util.UUID;

/**
 * Created by asmaboussalem on 16/11/2016.
 */
public class Order implements Entity<Order> {
    private UUID id;
    private double totalPrice;
    private ShoppingCart shoppingCart;
    private OrderStatus orderStatus;

    public Order() {
        this.id = null;
        this.totalPrice = 0;
        this.shoppingCart = null;
        this.orderStatus = null;
    }

    public Order(UUID id, double totalPrice, ShoppingCart shoppingCart, OrderStatus orderStatus) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.shoppingCart = shoppingCart;
        this.orderStatus = orderStatus;
    }

    public UUID getId() {
        return this.id;
    }

    public ShoppingCart getShoppingCart() {
        return this.shoppingCart;
    }

    public OrderStatus getOrderStatus() {
        return this.orderStatus;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
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

    public boolean sameIdentityAs(Order other) {
        return false;
    }
}
