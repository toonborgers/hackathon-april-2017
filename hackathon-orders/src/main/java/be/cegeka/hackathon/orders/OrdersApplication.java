package be.cegeka.hackathon.orders;

import be.cegeka.hackathon.orders.controller.CustomersClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class OrdersApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class, args);
    }

    @Bean
    public CustomersClient.CustomersClientFallback customersClientFallback() {
        return new CustomersClient.CustomersClientFallback();
    }
}
