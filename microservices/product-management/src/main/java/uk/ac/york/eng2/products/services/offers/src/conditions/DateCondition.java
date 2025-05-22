package uk.ac.york.eng2.products.services.offers.src.conditions;

import uk.ac.york.eng2.products.dto.PricedOrderDTO;

import java.time.LocalDate;

public class DateCondition implements Condition {
    private final Integer month;
    private final Integer day;

    public DateCondition(Integer month, Integer day) {
        this.month = month;
        this.day = day;
    }

    @Override
    public boolean check(PricedOrderDTO order) {
        LocalDate date = LocalDate.now();
        return this.month == date.getMonthValue() && this.day == date.getDayOfMonth();
    }
}
