package services;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BasicPaymentProcessor implements PaymentProcessor {
    @Override
    public void process(double amount) {
        System.out.println("Processing payment: €" + amount);
    }
}
