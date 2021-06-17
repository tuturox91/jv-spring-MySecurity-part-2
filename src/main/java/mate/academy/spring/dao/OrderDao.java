package mate.academy.spring.dao;

import mate.academy.spring.model.Order;
import mate.academy.spring.model.User;
import java.util.List;

public interface OrderDao {
    Order add(Order order);

    List<Order> getOrdersHistory(User user);
}
