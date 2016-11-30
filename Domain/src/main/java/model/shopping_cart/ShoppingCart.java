package model.shopping_cart;

import model.product.Product;
import model.shared.Entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by asmaboussalem on 16/11/2016.
 */
public class ShoppingCart implements Entity<ShoppingCart> {
    HashMap<Integer, Product> products;

    public ShoppingCart() {
        this.products = new HashMap<Integer, Product>();
    }

    public ShoppingCart(HashMap<Integer, Product> products) {
        this.products = products;
    }

    public HashMap<Integer, Product> getProducts() {
        return products;
    }

    public void setProducts(HashMap<Integer, Product> products) {
        this.products = products;
    }

    public boolean sameIdentityAs(ShoppingCart other) {
        return false;
    }
}
