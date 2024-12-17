package epicode.it.pizzeria.repository;

import epicode.it.pizzeria.entity.food_and_drink.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepo extends JpaRepository<Pizza, Long> {
}
