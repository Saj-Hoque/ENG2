package uk.ac.york.eng2.products.services.offers.rules;

import org.junit.jupiter.api.Test;
import uk.ac.york.eng2.products.dto.PricedOrderDTO;
import uk.ac.york.eng2.products.services.offers.src.rules.Rule;
import uk.ac.york.eng2.products.services.offers.src.conditions.Condition;
import uk.ac.york.eng2.products.services.offers.src.actions.Action;
import uk.ac.york.eng2.products.services.offers.src.triggers.TriggerType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RuleTest {

    @Test
    public void testRuleActionApplyWhenConditionTrue() {

        Rule rule = new Rule("Test Rule");

        Condition condition = new Condition() {
            @Override
            public boolean check(PricedOrderDTO order) {
                return true;
            }
        };

        Action action = new Action() {
            @Override
            public PricedOrderDTO apply(PricedOrderDTO order) {
                order.setOrderTotal(order.getOrderTotal() + 20.0F);
                return order;
            }
        };

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrderTotal(100.0F);

        PricedOrderDTO expected = new PricedOrderDTO();
        expected.setOrderTotal(120.0F);


        rule.addCondition(condition);
        rule.addAction(action);
        PricedOrderDTO result = rule.checkAndApply(order);

        assertEquals(expected.getOrderTotal(), result.getOrderTotal());
    }


    @Test
    public void testRuleActionSkipWhenConditionFalse() {
        Rule rule = new Rule("Test Rule");

        Condition condition = new Condition() {
            @Override
            public boolean check(PricedOrderDTO order) {
                return false;
            }
        };

        Action action = new Action() {
            @Override
            public PricedOrderDTO apply(PricedOrderDTO order) {
                order.setOrderTotal(order.getOrderTotal() + 20.0F);
                return order;
            }
        };

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrderTotal(100.0F);

        PricedOrderDTO expected = new PricedOrderDTO();
        expected.setOrderTotal(100.0F);

        rule.addCondition(condition);
        rule.addAction(action);
        PricedOrderDTO result = rule.checkAndApply(order);

        assertEquals(expected.getOrderTotal(), result.getOrderTotal());
    }

    @Test
    public void testRuleTriggerAnotherRuleIF_MATCH() {

        Rule rule = new Rule("Test Rule");
        Rule triggeredRule = new Rule("Triggered Rule");

        Condition condition = new Condition() {
            @Override
            public boolean check(PricedOrderDTO order) {
                return true;
            }
        };

        Action action = new Action() {
            @Override
            public PricedOrderDTO apply(PricedOrderDTO order) {
                order.setOrderTotal(order.getOrderTotal() + 20.0F);
                return order;
            }
        };

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrderTotal(100.0F);

        PricedOrderDTO expected = new PricedOrderDTO();
        expected.setOrderTotal(140.0F);


        rule.addCondition(condition);
        rule.addAction(action);
        rule.addTrigger(TriggerType.IF_NOT_MATCH, triggeredRule);
        rule.addTrigger(TriggerType.IF_MATCH, triggeredRule);
        triggeredRule.addCondition(condition);
        triggeredRule.addAction(action);

        PricedOrderDTO result = rule.checkAndApply(order);

        assertEquals(expected.getOrderTotal(), result.getOrderTotal());
    }

    @Test
    public void testRuleTriggerAnotherRuleIF_NOT_MATCH() {

        Rule rule = new Rule("Test Rule");
        Rule triggeredRule = new Rule("Triggered Rule");

        Condition conditionFalse = new Condition() {
            @Override
            public boolean check(PricedOrderDTO order) {
                return false;
            }
        };

        Condition conditionTrue = new Condition() {
            @Override
            public boolean check(PricedOrderDTO order) {
                return true;
            }
        };


        Action action = new Action() {
            @Override
            public PricedOrderDTO apply(PricedOrderDTO order) {
                order.setOrderTotal(order.getOrderTotal() + 20.0F);
                return order;
            }
        };

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrderTotal(100.0F);

        PricedOrderDTO expected = new PricedOrderDTO();
        expected.setOrderTotal(120.0F);


        rule.addCondition(conditionFalse);
        rule.addAction(action);
        rule.addTrigger(TriggerType.IF_NOT_MATCH, triggeredRule);
        triggeredRule.addCondition(conditionTrue);
        triggeredRule.addAction(action);

        PricedOrderDTO result = rule.checkAndApply(order);

        assertEquals(expected.getOrderTotal(), result.getOrderTotal());
    }

    @Test
    public void testRuleTriggerAnotherRuleALWAYS() {

        Rule rule = new Rule("Test Rule");
        Rule triggeredRule = new Rule("Triggered Rule");

        Condition condition = new Condition() {
            @Override
            public boolean check(PricedOrderDTO order) {
                return true;
            }
        };

        Action action = new Action() {
            @Override
            public PricedOrderDTO apply(PricedOrderDTO order) {
                order.setOrderTotal(order.getOrderTotal() + 20.0F);
                return order;
            }
        };

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrderTotal(100.0F);

        PricedOrderDTO expected = new PricedOrderDTO();
        expected.setOrderTotal(140.0F);


        rule.addCondition(condition);
        rule.addAction(action);
        rule.addTrigger(TriggerType.ALWAYS, triggeredRule);
        triggeredRule.addCondition(condition);
        triggeredRule.addAction(action);

        PricedOrderDTO result = rule.checkAndApply(order);

        assertEquals(expected.getOrderTotal(), result.getOrderTotal());
    }



    @Test
    public void testRuleTriggerAnotherRuleInvalidConditionsPass() {

        // Conditions will pass, but triggerType is IF_NOT_MATCH - this is invalid and should not continue
        // Only first rule should apply.

        Rule rule = new Rule("Test Rule");
        Rule triggeredRule = new Rule("Triggered Rule");

        Condition condition = new Condition() {
            @Override
            public boolean check(PricedOrderDTO order) {
                return true;
            }
        };

        Action action = new Action() {
            @Override
            public PricedOrderDTO apply(PricedOrderDTO order) {
                order.setOrderTotal(order.getOrderTotal() + 20.0F);
                return order;
            }
        };

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrderTotal(100.0F);

        PricedOrderDTO expected = new PricedOrderDTO();
        expected.setOrderTotal(120.0F);


        rule.addCondition(condition);
        rule.addAction(action);
        rule.addTrigger(TriggerType.IF_NOT_MATCH, triggeredRule);
        triggeredRule.addCondition(condition);
        triggeredRule.addAction(action);

        PricedOrderDTO result = rule.checkAndApply(order);

        assertEquals(expected.getOrderTotal(), result.getOrderTotal());
    }

    @Test
    public void testRuleTriggerAnotherRuleInvalidConditionsFail() {

        // Conditions will fail, but triggerType is IF_MATCH - this is invalid and should not continue
        // Neither rule should apply.

        Rule rule = new Rule("Test Rule");
        Rule triggeredRule = new Rule("Triggered Rule");

        Condition condition = new Condition() {
            @Override
            public boolean check(PricedOrderDTO order) {
                return false;
            }
        };

        Action action = new Action() {
            @Override
            public PricedOrderDTO apply(PricedOrderDTO order) {
                order.setOrderTotal(order.getOrderTotal() + 20.0F);
                return order;
            }
        };

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrderTotal(100.0F);

        PricedOrderDTO expected = new PricedOrderDTO();
        expected.setOrderTotal(100.0F);


        rule.addCondition(condition);
        rule.addAction(action);
        rule.addTrigger(TriggerType.IF_MATCH, triggeredRule);
        triggeredRule.addCondition(condition);
        triggeredRule.addAction(action);

        PricedOrderDTO result = rule.checkAndApply(order);

        assertEquals(expected.getOrderTotal(), result.getOrderTotal());
    }


}
