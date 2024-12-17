package epicode.it.pizzeria.runner;

import epicode.it.pizzeria.repository.OrderRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
@Order(8)
@RequiredArgsConstructor
public class HandleOrders implements ApplicationRunner {
    private final OrderRepo orderRepo;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        List<epicode.it.pizzeria.entity.order.Order> orders = orderRepo.findAll();
        while (true) {
            System.out.println("List of orders:");
            orders.forEach(System.out::println);
            System.out.println("Select order to manage:");


        }
    }
}
