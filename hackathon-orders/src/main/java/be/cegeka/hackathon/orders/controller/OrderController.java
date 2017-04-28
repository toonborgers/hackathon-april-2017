package be.cegeka.hackathon.orders.controller;

import be.cegeka.hackathon.orders.domain.OrderEntity;
import be.cegeka.hackathon.orders.domain.OrderRTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static be.cegeka.hackathon.orders.domain.OrderEntity.newOrder;
import static java.util.stream.Collectors.toList;

@RestController
public class OrderController {
    private final CustomersClient customersClient;

    private List<OrderEntity> orders = Arrays.asList(
            newOrder().withId(1).withCustomerId("1").withPrice(51.5).build(),
            newOrder().withId(2).withCustomerId("2").withPrice(651).build(),
            newOrder().withId(3).withCustomerId("3").withPrice(6846).build()
    );

    @Autowired
    public OrderController(CustomersClient customersClient) {
        this.customersClient = customersClient;
    }

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<OrderRTO>> getOrders() {
        return ok().body(orders.stream().map(this::toRTO).collect(toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderRTO> getOrder(@PathVariable("id") int id) {
        Optional<OrderRTO> optionaOrder = orders.stream().filter(order -> order.getId() == id).map(this::toRTO).findFirst();

        return optionaOrder.map(order -> ok().body(order)).orElseGet(this::notFound);
    }

    private ResponseEntity.BodyBuilder ok() {
        return ResponseEntity.ok().header("Order-Server", System.getenv().getOrDefault("HOSTNAME", "kweetetniejombegot"));
    }

    private ResponseEntity notFound() {
        return ResponseEntity.notFound().header("Order-Server", System.getenv().getOrDefault("HOSTNAME", "kweetetniejombegot")).build();
    }

    private OrderRTO toRTO(OrderEntity orderEntity) {
        return OrderRTO.newOrder()
                .withId(orderEntity.getId())
                .withPrice(orderEntity.getPrice())
                .withCustomer(customersClient.getCustomerName(orderEntity.getCustomerId()))
                .build();
    }
}
