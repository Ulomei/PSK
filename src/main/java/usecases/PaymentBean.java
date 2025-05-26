package usecases;

import services.PaymentProcessor;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class PaymentBean {
    @Inject
    private PaymentProcessor paymentProcessor;

    private double amount;

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String makePayment() {
        try {
            paymentProcessor.process(amount);
        } catch (Exception e) {
            return "Payment failed: " + e.getMessage();
        }
        return "Payment processed successfully.";
    }
}
