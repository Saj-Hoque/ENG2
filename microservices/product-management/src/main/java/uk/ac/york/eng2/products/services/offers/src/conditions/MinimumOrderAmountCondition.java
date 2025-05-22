package uk.ac.york.eng2.products.services.offers.src.conditions;

import uk.ac.york.eng2.products.dto.PricedOrderDTO;

public class MinimumOrderAmountCondition implements Condition {

    private final Float minAmount;

    public MinimumOrderAmountCondition(Float minAmount) { this.minAmount = minAmount; }

    @Override
    public boolean check(PricedOrderDTO order) { return order.getOrderTotal() >= minAmount; }
}
