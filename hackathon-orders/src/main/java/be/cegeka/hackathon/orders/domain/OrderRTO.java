package be.cegeka.hackathon.orders.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class OrderRTO {
    private int id;
    private String customer;
    private double price;

    private OrderRTO() {
    }

    public int getId() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }

    public double getPrice() {
        return price;
    }

    public static Builder newOrder() {
        return new Builder();
    }

    public static class Builder {
        private final OrderRTO instance;

        private Builder() {
            instance = new OrderRTO();
        }

        public OrderRTO build() {
            return instance;
        }

        public Builder withId(int id) {
            instance.id = id;
            return this;
        }

        public Builder withCustomer(String customer) {
            instance.customer = customer;
            return this;
        }

        public Builder withPrice(double price) {
            instance.price = price;
            return this;
        }
    }
}