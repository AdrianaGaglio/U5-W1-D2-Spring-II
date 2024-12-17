package epicode.it.pizzeria.repository;

import epicode.it.pizzeria.entity.food_and_drink.Topping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToppingRepo extends JpaRepository<Topping, Long> {

    public Topping findByName(String name);

}
