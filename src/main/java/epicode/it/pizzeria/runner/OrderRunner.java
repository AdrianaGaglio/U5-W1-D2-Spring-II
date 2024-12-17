package epicode.it.pizzeria.runner;

import com.github.javafaker.Faker;
import epicode.it.pizzeria.entity.Menu;
import epicode.it.pizzeria.entity.food_and_drink.Drink;
import epicode.it.pizzeria.entity.food_and_drink.FoodAndDrink;
import epicode.it.pizzeria.entity.food_and_drink.Pizza;
import epicode.it.pizzeria.entity.food_and_drink.Topping;
import epicode.it.pizzeria.entity.order.Order;
import epicode.it.pizzeria.entity.pizzeria_table.PizzeriaTable;
import epicode.it.pizzeria.entity.pizzeria_table.TableStatus;
import epicode.it.pizzeria.repository.*;
import epicode.it.pizzeria.service.OrderService;
import epicode.it.pizzeria.service.PizzaService;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@org.springframework.core.annotation.Order(7)
public class OrderRunner implements ApplicationRunner {
    private final OrderService orderService;
    private final PizzaService pizzaService;
    private final MenuRepo menuRepo;
    private final TableRepo tableRepo;
    private final ToppingRepo toppingRepo;
    private final Faker faker;
    private final OrderRepo orderRepo;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {

        Menu menu = menuRepo.getReferenceById(1L);

        List<PizzeriaTable> tables = tableRepo.findAll();

        for (int i = 0; i < tables.size(); i++) {
            PizzeriaTable table = tables.get(i);
            if (table.getStatus() != TableStatus.FREE) {
                List<FoodAndDrink> orderedItems = new ArrayList<>();
                int seats = faker.number().numberBetween(1, table.getMaxCustomers());
                for (int j = 0; j < seats; j++) {
                    List<Drink> drinks = menu.getDrinks();
                    int randomChoise = faker.random().nextInt(1, 2);
                    if (randomChoise == 1) {
                        List<Pizza> pizzas = menu.getPizzas();
                        orderedItems.add(drinks.get(faker.random().nextInt(0, drinks.size() - 1)));
                        orderedItems.add(pizzas.get(faker.random().nextInt(0, pizzas.size() - 1)));
                    } else {
                        List<Topping> additional = new ArrayList<>();
                        for (int k = 0; k < 3; k++) {
                            Topping topping = menu.getToppings().get(faker.random().nextInt(0, menu.getToppings().size() - 1));
                            additional.add(topping);
                        }
                        pizzaService.createPizza(additional);
                    }
                }
                Order order = orderService.createOrder(table, orderedItems);
            }
        }

        List<Order> orders = orderRepo.findAll();

        System.out.println();
        System.out.println("Orders");
        System.out.printf("%-15s %15s %15s %15s%n", "Order", "Total price", "Status", "Seats");
        orders.forEach(o -> {
            System.out.printf("%-15s %15s %15s %15s%n", o.getNumber(), "€ " + String.format("%.2f", o.getTotalPrice()), o.getStatus().toString(), o.getCustomersNumber());
        });
        System.out.println();
    }
}
