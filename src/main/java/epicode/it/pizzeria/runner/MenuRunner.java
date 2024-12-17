package epicode.it.pizzeria.runner;

import epicode.it.pizzeria.entity.food_and_drink.Drink;
import epicode.it.pizzeria.entity.Menu;
import epicode.it.pizzeria.entity.food_and_drink.Pizza;
import epicode.it.pizzeria.entity.food_and_drink.Topping;
import epicode.it.pizzeria.repository.DrinkRepo;

import epicode.it.pizzeria.repository.MenuRepo;
import epicode.it.pizzeria.repository.PizzaRepo;
import epicode.it.pizzeria.repository.ToppingRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(3)
public class MenuRunner implements ApplicationRunner {

    @Autowired
    public Menu menu;

    @Autowired
    public MenuRepo menuRepo;

    @Autowired
    public PizzaRepo pizzaRepo;

    @Autowired
    public ToppingRepo toppingRepo;

    @Autowired
    public DrinkRepo drinkRepo;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        List<Pizza> pizzas = pizzaRepo.findAll();
        List<Topping> toppings = toppingRepo.findAll();
        List<Drink> drinks = drinkRepo.findAll();
        if (menuRepo.count() == 0) {
            menu.setName("Main menu");
            for (int i = 0; i < pizzas.size(); i++) {
                menu.getPizzas().add(pizzas.get(i));
                pizzas.get(i).setMenu(menu);
            }
            for(int i = 0; i < toppings.size(); i++) {
                menu.getToppings().add(toppings.get(i));
                toppings.get(i).setMenu(menu);
            }
            for(int i = 0; i < drinks.size(); i++) {
                menu.getDrinks().add(drinks.get(i));
                drinks.get(i).setMenu(menu);
            }
            menuRepo.save(menu);
        }
    }
}
