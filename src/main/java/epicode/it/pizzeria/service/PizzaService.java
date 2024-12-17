package epicode.it.pizzeria.service;

import epicode.it.pizzeria.entity.food_and_drink.Pizza;
import epicode.it.pizzeria.entity.food_and_drink.Topping;
import epicode.it.pizzeria.repository.PizzaRepo;
import epicode.it.pizzeria.repository.ToppingRepo;
import epicode.it.pizzeria.utilities.Utilities;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PizzaService {
    private final PizzaRepo pizzaRepo;
    private final ObjectProvider<Pizza> pizzaProvider;
    private final ToppingRepo toppingRepo;


    public Pizza createPizza(List<Topping> toppings) {
        Pizza newPizza = pizzaProvider.getObject();
        String pizzaName = "";
        for (int i = 0; i < toppings.size(); i++) {
            Topping t = toppings.get(i);
            Topping topping = toppingRepo.findByName(t.getName());
            newPizza.getToppings().add(topping);
            topping.getPizzas().add(newPizza);
            newPizza.setCalories(newPizza.getCalories() + topping.getCalories());
            pizzaName += Utilities.capitalizeFirstLetter(t.getName());
            if(i<toppings.size()) pizzaName += " ";
        };
        newPizza.setName(pizzaName);
        pizzaRepo.save(newPizza);
        return newPizza;
    }
}
