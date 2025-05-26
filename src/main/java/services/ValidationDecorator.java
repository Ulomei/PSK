package services;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

@Decorator
public class ValidationDecorator implements PaymentProcessor {

    @Inject
    @Delegate
    private PaymentProcessor delegate;

    @Override
    public void process(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive!");
        }
        System.out.println("Validation passed.");
        delegate.process(amount);
    }
}
