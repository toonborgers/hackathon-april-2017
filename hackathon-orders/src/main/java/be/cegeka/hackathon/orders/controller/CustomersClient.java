package be.cegeka.hackathon.orders.controller;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("customers")
public interface CustomersClient {
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/name")
    String getCustomerName(@PathVariable("id") String id);
}
