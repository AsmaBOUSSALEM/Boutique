package repositories;

import model.shopping_cart.ShoppingCart;

import java.util.List;
import java.util.UUID;

/**
 * Created by asmaboussalem on 16/11/2016.
 */
public interface ShoppingCartRepository {

    /**
     * Finds an shoppingCart given id.
     *
     * @param id
     * @return shoppingCart if found, else {@code null}
     */
    ShoppingCart find(UUID id);

    /**
     * Finds all shoppingCarts.
     *
     * @return All shoppingCarts.
     */
    List<ShoppingCart> findAll();

    /**
     * Saves given shoppingCart.
     *
     * @param shoppingCart shoppingCart to save
     */
    void store(ShoppingCart shoppingCart);
}
