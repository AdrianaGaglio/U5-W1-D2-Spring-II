package epicode.it.pizzeria.service;

import com.github.javafaker.Faker;
import epicode.it.pizzeria.entity.Menu;
import epicode.it.pizzeria.entity.OrderedItems;
import epicode.it.pizzeria.entity.food_and_drink.FoodAndDrink;
import epicode.it.pizzeria.entity.order.Order;
import epicode.it.pizzeria.entity.order.Status;
import epicode.it.pizzeria.entity.pizzeria_table.PizzeriaTable;
import epicode.it.pizzeria.entity.pizzeria_table.TableStatus;
import epicode.it.pizzeria.repository.OrderRepo;
import epicode.it.pizzeria.repository.TableRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;
    private final TableRepo tableRepo;
    private final Faker faker;

    @Value("${application.cover_charge}")
    private double charge;

    public Order createOrder(PizzeriaTable table, List<FoodAndDrink> orderedItems) {
        Order order = new Order();
        order.setTable(table);
        order.setCustomersNumber(faker.number().numberBetween(1, table.getMaxCustomers()));
        double chargeSum = charge * order.getCustomersNumber();
        order.setOrderTime(LocalTime.now());
        order.setStatus(Status.CREATED);
        long lastOrder = orderRepo.count();
        order.setNumber(order.getNumber() + lastOrder);
        orderedItems.forEach(item -> order.getOrderedItems().add(item));
        order.setTotalPrice(order.getTotalOrderedItems() + chargeSum);
        orderRepo.save(order);
        return order;
    }

}
