package epicode.it.pizzeria.runner;

import epicode.it.pizzeria.entity.food_and_drink.Alcohol;
import epicode.it.pizzeria.entity.food_and_drink.Drink;
import epicode.it.pizzeria.repository.DrinkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class DrinkRunner implements ApplicationRunner {

    @Autowired
    @Qualifier("lemonade")
    private Drink lemonade;

    @Autowired
    @Qualifier("coca")
    private Drink coca;

    @Autowired
    @Qualifier("fanta")
    private Drink fanta;

    @Autowired
    @Qualifier("water")
    private Drink water;

    @Autowired
    @Qualifier("wine")
    private Alcohol wine;

    @Autowired
    @Qualifier("beer")
    private Alcohol beer;

    @Autowired
    private DrinkRepo drinkRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {

         if(drinkRepo.count() == 0) {
             drinkRepo.save(lemonade);
             drinkRepo.save(coca);
             drinkRepo.save(fanta);
             drinkRepo.save(water);
             drinkRepo.save(wine);
             drinkRepo.save(beer);
         }

    }
}
