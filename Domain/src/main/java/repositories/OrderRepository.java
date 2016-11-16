package repositories;

import model.order.Order;

import java.util.List;
import java.util.UUID;

/**
 * Created by asmaboussalem on 16/11/2016.
 */
public interface OrderRepository {

    /**
     * Finds an order given id.
     *
     * @param id
     * @return Order if found, else {@code null}
     */
    Order find(UUID id);

    /**
     * Finds all orders.
     *
     * @return All orders.
     */
    List<Order> findAll();

    /**
     * Saves given order.
     *
     * @param order order to save
     */
    void store(Order order);
}
