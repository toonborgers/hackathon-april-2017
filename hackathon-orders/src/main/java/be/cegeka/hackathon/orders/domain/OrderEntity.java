package be.cegeka.hackathon.orders.domain;

public class OrderEntity {
    private int id;
    private String customerId;
    private double price;

    private OrderEntity() {

    }

    public int getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public double getPrice() {
        return price;
    }

    public static Builder newOrder() {
        return new Builder();
    }

    public static class Builder {
        private final OrderEntity instance;

        private Builder() {
            instance = new OrderEntity();
        }

        public OrderEntity build() {
            return instance;
        }

        public Builder withId(int id) {
            instance.id = id;
            return this;
        }

        public Builder withCustomerId(String customerId) {
            instance.customerId = customerId;
            return this;
        }

        public Builder withPrice(double price) {
            instance.price = price;
            return this;
        }
    }
}