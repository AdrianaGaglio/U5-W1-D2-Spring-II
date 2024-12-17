package epicode.it.pizzeria.repository;

import epicode.it.pizzeria.entity.food_and_drink.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepo extends JpaRepository<Drink,Long> {
}
