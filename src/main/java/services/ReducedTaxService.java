package services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;

@Specializes
@ApplicationScoped
public class ReducedTaxService extends TaxService{
    @Override
    public double calculateTax(double amount) {
        return amount * 0.18;
    }
}
