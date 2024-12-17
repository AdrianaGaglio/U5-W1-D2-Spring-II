package epicode.it.pizzeria.repository;

import epicode.it.pizzeria.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepo extends JpaRepository<Menu, Long> {

}
