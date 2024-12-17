package epicode.it.pizzeria.repository;

import epicode.it.pizzeria.entity.pizzeria_table.PizzeriaTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepo extends JpaRepository<PizzeriaTable, Long> {
}
