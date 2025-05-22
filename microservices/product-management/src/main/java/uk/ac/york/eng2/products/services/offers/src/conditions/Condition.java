package uk.ac.york.eng2.products.services.offers.src.conditions;

import uk.ac.york.eng2.products.dto.PricedOrderDTO;

public interface Condition {
    boolean check(PricedOrderDTO order);
}
