package uk.ac.york.eng2.products.services.offers.src.actions;

import uk.ac.york.eng2.products.dto.PricedOrderDTO;

public class PercentageDiscountTotalAction implements Action {

    private final Float percentage;

    public PercentageDiscountTotalAction(Float percentage){
        this.percentage = percentage;
    }

    @Override
    public PricedOrderDTO apply(PricedOrderDTO order) {
        Float discount = order.getOrderTotal() * (percentage / 100.0F);
        order.setOrderTotal(order.getOrderTotal() - discount);
        return order;
    }
}
