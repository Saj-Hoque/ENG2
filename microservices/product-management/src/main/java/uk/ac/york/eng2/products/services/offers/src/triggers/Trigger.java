package uk.ac.york.eng2.products.services.offers.src.triggers;

import uk.ac.york.eng2.products.services.offers.src.rules.Rule;

public class Trigger {

    private TriggerType type;
    private Rule triggeredRule;

    public Trigger(TriggerType type, Rule triggeredRule) {
        this.type = type;
        this.triggeredRule = triggeredRule;
    }

    public TriggerType getType() { return type; }
    public Rule getTriggeredRule() { return triggeredRule; }
}
