package uk.ac.york.eng2.products.services.offers.triggers;

import org.junit.jupiter.api.Test;
import uk.ac.york.eng2.products.services.offers.src.rules.Rule;
import uk.ac.york.eng2.products.services.offers.src.triggers.Trigger;
import uk.ac.york.eng2.products.services.offers.src.triggers.TriggerType;

import static org.junit.jupiter.api.Assertions.*;

public class TriggerTest {

    @Test
    public void testTriggerTypesExist() {
        assertNotNull(TriggerType.IF_MATCH);
        assertNotNull(TriggerType.IF_NOT_MATCH);
        assertNotNull(TriggerType.ALWAYS);
    }

    @Test
    public void testTriggerGettersSetters() {
        Rule rule = new Rule("Test Rule");

        Trigger trigger = new Trigger(TriggerType.IF_MATCH, rule);

        assertEquals(TriggerType.IF_MATCH, trigger.getType());
        assertEquals(rule, trigger.getTriggeredRule());
    }

}
