package epicode.it.pizzeria.runner;

import com.github.javafaker.Faker;
import epicode.it.pizzeria.entity.pizzeria_table.PizzeriaTable;
import epicode.it.pizzeria.entity.pizzeria_table.TableStatus;
import epicode.it.pizzeria.repository.TableRepo;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(6)
public class TableRunner implements ApplicationRunner {
    private final ObjectProvider<PizzeriaTable> tableProvider;
    private final Faker faker;
    private final TableRepo tableRepo;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {

        List<PizzeriaTable> tables = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            PizzeriaTable table = tableProvider.getObject();
            table.setNumber(i+1);
            table.setMaxCustomers(faker.number().numberBetween(2,10));
            table.setStatus(faker.random().nextInt(1,10) < 6 ? TableStatus.OCCUPIED : TableStatus.FREE);
            tables.add(table);
            tableRepo.save(table);
        }

        System.out.println();
        System.out.println("Tables list:");
        tables.forEach(t -> {
            System.out.println("Table number: " + t.getNumber() + " - Table status: " + t.getStatus().toString() + " - Maximum seats: " + t.getMaxCustomers());
        });
        System.out.println();
    }
}
