package be.cegeka.hackathon.jos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;

@SpringBootApplication
@RestController
public class JosApplication {

    @GetMapping("/")
    public String helloWorld() throws UnknownHostException {
        return "Hello from " + System.getenv().getOrDefault("HOSTNAME", "kweetetniejombegot");
    }

    public static void main(String[] args) {
        SpringApplication.run(JosApplication.class, args);
    }
}
