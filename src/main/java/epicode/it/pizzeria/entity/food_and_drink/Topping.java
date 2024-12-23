package epicode.it.pizzeria.entity.food_and_drink;

import epicode.it.pizzeria.entity.Menu;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="toppings")
public class Topping extends FoodAndDrink {

    @ManyToMany(cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<Pizza> pizzas = new ArrayList<>();

    @ManyToOne
    private Menu menu;

    @Override
    public String toString() {
        return "Topping{" +
                "id=" + getId() +
                ", name=" + getName() +
                "} ";
    }
}