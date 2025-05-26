package services;

import interceptors.Logged;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderService {
    @Logged
    public void placeOrder() {
        System.out.println("Order placed.");
    }
}
