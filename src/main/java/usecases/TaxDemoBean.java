package usecases;

import services.TaxService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class TaxDemoBean {

    @Inject
    private TaxService taxService;

    private double amount = 100;

    public String getCalculatedTax() {
        return "Tax: EUR" + taxService.calculateTax(amount);
    }
}
