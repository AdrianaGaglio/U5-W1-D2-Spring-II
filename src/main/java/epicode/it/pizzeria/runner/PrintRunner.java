package epicode.it.pizzeria.runner;

import epicode.it.pizzeria.utilities.Utilities;
import epicode.it.pizzeria.entity.food_and_drink.Drink;
import epicode.it.pizzeria.entity.Menu;
import epicode.it.pizzeria.entity.food_and_drink.Pizza;
import epicode.it.pizzeria.entity.food_and_drink.Topping;
import epicode.it.pizzeria.repository.MenuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
public class PrintRunner implements ApplicationRunner {

    @Autowired
    private MenuRepo menuRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Menu menu = menuRepo.findAll().get(0);
        System.out.println();
        System.out.printf("%-80s %-15s %-15s%n", "Pizzas", "Calories", "Price");

        for (int i = 0; i < menu.getPizzas().size(); i++) {
            Pizza pizza = menu.getPizzas().get(i);
            String toppings = "";
            for (int j = 0; j < pizza.getToppings().size(); j++) {
                String toppingName = pizza.getToppings().get(j).getName();
                if(j < pizza.getToppings().size() - 1) {
                    toppings += Utilities.capitalizeFirstLetter(toppingName) + ", ";
                } else {
                    toppings += Utilities.capitalizeFirstLetter(toppingName);
                }
            }
            System.out.printf("%-80s %-15s %15s%n", Utilities.capitalizeFirstLetter(pizza.getName()) +
                    " (" + toppings + ")", pizza.getCalories(), "€ " + String.format("%.2f", pizza.getPrice()));
        }
        System.out.println();
        System.out.printf("%-80s %-15s %15s%n", "Toppings", "Calories", "Price");

        for (int i = 0; i < menu.getToppings().size(); i++) {
            Topping topping = menu.getToppings().get(i);
            System.out.printf("%-80s %-15s %15s%n", Utilities.capitalizeFirstLetter(topping.getName()), topping.getCalories(), "€ " + String.format("%.2f", topping.getPrice()));
        }
        System.out.println();
        System.out.printf("%-80s %-15s %15s%n", "Drinks", "Calories", "Price");
        for (int i = 0; i < menu.getDrinks().size(); i++) {
            Drink drink = menu.getDrinks().get(i);
            System.out.printf("%-80s %-15s %15s%n", Utilities.capitalizeFirstLetter(drink.getName()) + " (" + drink.getQuantity() + "l)", drink.getCalories(), "€ " + String.format("%.2f", drink.getPrice()));
        }
        System.out.println();
    }
}
