package epicode.it.pizzeria.runner;

import epicode.it.pizzeria.entity.food_and_drink.Topping;
import epicode.it.pizzeria.repository.ToppingRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ToppingRunner implements ApplicationRunner {

    @Autowired
    @Qualifier("cheese")
    private Topping cheese;

    @Autowired
    @Qualifier("tomato")
    private Topping tomato;

    @Autowired
    @Qualifier("ham")
    private Topping ham;

    @Autowired
    @Qualifier("pineapple")
    private Topping pineapple;

    @Autowired
    @Qualifier("salami")
    private Topping salami;

    @Autowired
    @Qualifier("salami")
    private Topping onions;

    @Autowired
    private ToppingRepo toppingRepo;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {



    }
}
