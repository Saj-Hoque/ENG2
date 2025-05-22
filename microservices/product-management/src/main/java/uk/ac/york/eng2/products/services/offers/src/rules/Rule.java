package uk.ac.york.eng2.products.services.offers.src.rules;

import uk.ac.york.eng2.products.dto.PricedOrderDTO;
import uk.ac.york.eng2.products.services.offers.src.actions.Action;
import uk.ac.york.eng2.products.services.offers.src.conditions.Condition;
import uk.ac.york.eng2.products.services.offers.src.triggers.Trigger;
import uk.ac.york.eng2.products.services.offers.src.triggers.TriggerType;

import java.util.ArrayList;
import java.util.List;

public class Rule {

    private String name;
    private List<Condition> conditions = new ArrayList<>();
    private List<Action> actions = new ArrayList<>();
    private List<Trigger> triggers = new ArrayList<>();

    public String getName() { return name; }
    public Rule(String name) { this.name = name; }

    public void addCondition(Condition condition) { conditions.add(condition); }

    public void addAction(Action action) { actions.add(action); }

    public void addTrigger(TriggerType type, Rule triggeredRule) { triggers.add(new Trigger(type, triggeredRule)); }

    public PricedOrderDTO checkAndApply(PricedOrderDTO order){

        boolean conditionsPassed = checkConditions(order);

        if (conditionsPassed) {
            order = applyActions(order);
        }

        order = applyTriggers(conditionsPassed, order);
        return order;
    }

    public boolean checkConditions(PricedOrderDTO order) {
        for (Condition condition : conditions) {
            if (condition.check(order) == false) {
                return false;
            }
        }
        return true;
    }

    public PricedOrderDTO applyActions(PricedOrderDTO order) {
        for (Action action : actions){
            order = action.apply(order);
        }
        return order;
    }

    public PricedOrderDTO applyTriggers(boolean conditionsPassed, PricedOrderDTO order) {
        for (Trigger trigger : triggers){
            // Assuming validation has ensured if an ALWAYS trigger type is present, no other triggers are present
            if (trigger.getType() == TriggerType.ALWAYS) {
                order = trigger.getTriggeredRule().checkAndApply(order);
                break;
            }

            // Otherwise find the IF_MATCH trigger if conditions pass, else find the IF_MATCH trigger
            if (conditionsPassed) {
                if (trigger.getType() == TriggerType.IF_MATCH) {
                    order = trigger.getTriggeredRule().checkAndApply(order);
                    break;
                }
            } else if (trigger.getType() == TriggerType.IF_NOT_MATCH) {
                order = trigger.getTriggeredRule().checkAndApply(order);
                break;
            }
        }

        return order;

    }


}