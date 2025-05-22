package uk.ac.york.eng2.products.services.offers.src.actions;

import uk.ac.york.eng2.products.dto.PricedOrderDTO;

public class FixedDiscountAction implements Action {

    private final Float amount;

    public FixedDiscountAction(Float amount) {
        this.amount = amount;
    }

    @Override
    public PricedOrderDTO apply(PricedOrderDTO order) {
        order.setOrderTotal( Math.max(order.getOrderTotal() - amount, 0F)); // avoid negative order totals by using max
        return order;
    }
}
