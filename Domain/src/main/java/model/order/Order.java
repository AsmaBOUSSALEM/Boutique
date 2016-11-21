package model.order;

import model.shared.Entity;
import model.shopping_cart.ShoppingCart;
import model.user.User;

import java.util.UUID;

/**
 * Created by asmaboussalem on 16/11/2016.
 */
public class Order implements Entity<Order> {
    private UUID id;
    private User user;
    private ShoppingCart shoppingCart;
    private OrderStatus orderStatus;

    public Order() {
        this.id = null;
        this.user = null;
        this.shoppingCart = null;
        this.orderStatus = null;
    }

    public Order(UUID id, User user, ShoppingCart shoppingCart, OrderStatus orderStatus) {
        this.id = id;
        this.user = user;
        this.shoppingCart = shoppingCart;
        this.orderStatus = orderStatus;
    }

    public UUID getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
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

    public void setUser(User user) {
        this.user = user;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean sameIdentityAs(Order other) {
        return false;
    }
}
