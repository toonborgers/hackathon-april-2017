package be.cegeka.hackathon.orders.controller;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "customers", fallback = CustomersClient.CustomersClientFallback.class)
public interface CustomersClient {
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/name")
    String getCustomerName(@PathVariable("id") String id);

    static class CustomersClientFallback implements CustomersClient {
        @Override
        public String getCustomerName(String id) {
            return "Some default name";
        }
    }
}
