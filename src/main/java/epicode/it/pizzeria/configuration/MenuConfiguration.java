package epicode.it.pizzeria.configuration;

import epicode.it.pizzeria.entity.Menu;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MenuConfiguration {

    @Bean(name="menu")
    public Menu getMenu() {
        Menu menu = new Menu();
        return menu;
    }
}
