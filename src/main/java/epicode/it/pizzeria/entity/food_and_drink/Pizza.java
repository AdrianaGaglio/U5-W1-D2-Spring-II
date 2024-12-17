package epicode.it.pizzeria.entity.food_and_drink;

import epicode.it.pizzeria.entity.Menu;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="pizzas")
public class Pizza extends FoodAndDrink {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;



    @ManyToMany(mappedBy = "pizzas", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Topping> toppings = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @ToString.Exclude
    private Menu menu;
}