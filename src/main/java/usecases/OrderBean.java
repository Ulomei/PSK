package usecases;

import services.OrderService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class OrderBean {

    @Inject
    private OrderService orderService;

    public void makeOrder() {
        orderService.placeOrder();
    }

}
