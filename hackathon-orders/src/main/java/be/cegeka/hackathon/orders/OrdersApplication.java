package be.cegeka.hackathon.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class OrdersApplication {

    @GetMapping("/")
    public String helloWorld() throws UnknownHostException {
        return "Hello from " + System.getenv().getOrDefault("HOSTNAME", "kweetetniejombegot");
    }

    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class, args);
    }
}
