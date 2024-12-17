package epicode.it.pizzeria.entity.food_and_drink;

import epicode.it.pizzeria.entity.Menu;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="drinks")
public class Drink extends FoodAndDrink {
    private double quantity;

    @ManyToOne
    private Menu menu;
}