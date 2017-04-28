package be.cegeka.hackathon.customers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class CustomersApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomersApplication.class, args);
    }

    @GetMapping("/{id}/name")
    public String getCustomer(@PathVariable("id") String id) {
        return "Customer " + id + " from " + System.getenv().getOrDefault("HOSTNAME", "kweetetniejombegot");
    }
}
