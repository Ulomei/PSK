package services;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TaxService {
    public double calculateTax(double amount) {
        return amount * 0.21;
    }
}
