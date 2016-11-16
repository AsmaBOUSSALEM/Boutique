package model.product;

import model.shared.Entity;

import java.util.UUID;

/**
 * Created by asmaboussalem on 15/11/2016.
 */
public class Product implements Entity<Product>
{
    private UUID id;
    private String name;
    private String description;
    private Category productCategory;
    private double price;
    private int quantity;

    public Product() {
        this.id = null;
        this.name = "";
        this.description = "";
        this.productCategory = null;
        this.price = 0;
        this.quantity = 0;
    }

    public Product(UUID id, String name, String description, Category productCategory, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.productCategory = productCategory;
        this.price = price;
        this.quantity = quantity;
    }

    public boolean sameIdentityAs(Product other) {
        return false;
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Category getProductCategory() {
        return this.productCategory;
    }

    public double getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }
}
