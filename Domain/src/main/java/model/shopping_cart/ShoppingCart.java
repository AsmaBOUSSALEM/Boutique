package model.shopping_cart;

import model.product.Product;
import model.shared.Entity;
import model.user.User;

import java.util.List;

/**
 * Created by asmaboussalem on 16/11/2016.
 */
public class ShoppingCart implements Entity<ShoppingCart> {
    User user;
    List<Product> products;

    public ShoppingCart() {
        this.user = null;
        this.products = null;
    }

    public ShoppingCart(User user, List<Product> products) {
        this.user = user;
        this.products = products;
    }

    public User getUser() {
        return this.user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean sameIdentityAs(ShoppingCart other) {
        return false;
    }
}
