package repositories;

import model.product.Product;

import java.util.List;
import java.util.UUID;

/**
 * Created by asmaboussalem on 16/11/2016.
 */
public interface ProductRepository {

    /**
     * Finds an product given id.
     *
     * @param id
     * @return Product if found, else {@code null}
     */
    Product find(UUID id);

    /**
     * Finds all products.
     *
     * @return All products.
     */
    List<Product> findAll();

    /**
     * Saves given product.
     *
     * @param product product to save
     */
    void store(Product product);
}
