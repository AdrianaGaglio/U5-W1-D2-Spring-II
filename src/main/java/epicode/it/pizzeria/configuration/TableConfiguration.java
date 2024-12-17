package epicode.it.pizzeria.configuration;

import epicode.it.pizzeria.entity.pizzeria_table.PizzeriaTable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TableConfiguration {

    @Bean
    @Scope("prototype")
    public PizzeriaTable getTable() {
        PizzeriaTable table = new PizzeriaTable();
        return table;
    }
}
