package model.product;

import model.shared.Entity;
import org.springframework.data.annotation.Id;

/**
 * Created by asmaboussalem on 15/11/2016.
 */
public class Product implements Entity<Product>
{
    @Id
    private String id;

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

    public Product(String id, String name, String description, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.productCategory = productCategory;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", productCategory=" + productCategory +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    public boolean sameIdentityAs(Product other) {
        return false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
