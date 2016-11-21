package repositories;

import model.order.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by asmaboussalem on 16/11/2016.
 */
public interface OrderRepository extends MongoRepository<Order, String> {

    /**
     * Finds an order given id.
     *
     * @param id
     * @return Order if found, else {@code null}
     */
    public Order findById(UUID id);

}
