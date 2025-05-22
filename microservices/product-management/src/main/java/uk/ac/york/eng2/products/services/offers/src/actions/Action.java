package uk.ac.york.eng2.products.services.offers.src.actions;

import uk.ac.york.eng2.products.dto.PricedOrderDTO;

public interface Action {
    PricedOrderDTO apply(PricedOrderDTO order);
}
