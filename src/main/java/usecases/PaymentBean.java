package usecases;

import services.PaymentProcessor;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

    public void makePayment() {
        try {
            paymentProcessor.process(amount);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Payment processed successfully.", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Payment failed: " + e.getMessage(), null));
        }
    }
}
