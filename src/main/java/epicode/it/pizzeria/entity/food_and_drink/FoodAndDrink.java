package epicode.it.pizzeria.entity.food_and_drink;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract public class FoodAndDrink {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private int calories;
    private double price;
}