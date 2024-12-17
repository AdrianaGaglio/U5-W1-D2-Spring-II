package epicode.it.pizzeria.entity.pizzeria_table;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Entity
@Table(name="tables")
public class PizzeriaTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private int number;

    @Enumerated(EnumType.STRING)
    private TableStatus status;

    @Column(name="max_customers")
    private int maxCustomers;

}