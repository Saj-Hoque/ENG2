// Custom imports
/* protected region imports on begin */
package uk.ac.york.eng2.products.services.offers.src_gen;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import uk.ac.york.eng2.products.dto.PricedOrderDTO;
import uk.ac.york.eng2.products.repository.OrdersByDayRepository;
import uk.ac.york.eng2.products.repository.ProductRepository;
import uk.ac.york.eng2.products.repository.TagRepository;
import uk.ac.york.eng2.products.services.offers.src.actions.*;
		import uk.ac.york.eng2.products.services.offers.src.conditions.DateCondition;
import uk.ac.york.eng2.products.services.offers.src.conditions.MinimumOrderAmountCondition;
import uk.ac.york.eng2.products.services.offers.src.conditions.ProductOrderQuantityCondition;
import uk.ac.york.eng2.products.services.offers.src.conditions.ProductTagCondition;
import uk.ac.york.eng2.products.services.offers.src.rules.Rule;
import uk.ac.york.eng2.products.services.offers.src.triggers.TriggerType;
/* protected region imports end */

import java.util.*;

@Singleton
public class OffersGenerator {

	@Inject
	private ProductRepository productRepository;

	@Inject
	private TagRepository tagRepository;

	@Inject
	private OrdersByDayRepository ordersByDayRepository;

	public PricedOrderDTO applyOffers(PricedOrderDTO order) {
		List<Rule> offers = buildOffers();
		Rule initialOffer = connectTriggers(offers);
		order = initialOffer.checkAndApply(order);
		return order;
	}

	public List<Rule> buildOffers() {
		List<Rule> offers = new ArrayList<>();

		// Auto-Generated rules from model
		Rule ruleBakewellTartsFirst10Orders = new Rule("Bakewell Tarts First 10 Orders");
		ruleBakewellTartsFirst10Orders.addCondition(new ProductOrderQuantityCondition("Bakewell Tarts", 10, productRepository, ordersByDayRepository));
		ruleBakewellTartsFirst10Orders.addAction(new PercentageDiscountPerProductAction("Bakewell Tarts", 10.0F, productRepository));
		offers.add(ruleBakewellTartsFirst10Orders);

		Rule rulePizzaAndIceCreamBundle = new Rule("Pizza And Ice Cream Bundle");
		rulePizzaAndIceCreamBundle.addCondition(new ProductTagCondition(Arrays.asList("Large", "Pizza"), 2, productRepository, tagRepository));
		rulePizzaAndIceCreamBundle.addCondition(new ProductTagCondition(Arrays.asList("Large", "Ice Cream"), 1, productRepository, tagRepository));
		rulePizzaAndIceCreamBundle.addAction(new BundlePriceAction(Arrays.asList(new TagQuantity(Arrays.asList("Ice Cream", "Large"), 1, tagRepository), new TagQuantity(Arrays.asList("Large", "Pizza"), 2, tagRepository)), 10.0F, productRepository));
		offers.add(rulePizzaAndIceCreamBundle);

		Rule rule2For1Pizza = new Rule("2 For 1 Pizza");
		rule2For1Pizza.addCondition(new ProductTagCondition(Arrays.asList("Pizza"), 2, productRepository, tagRepository));
		rule2For1Pizza.addAction(new PercentageDiscountOnTaggedQuantityAction(new TagQuantity(Arrays.asList("Pizza"), 2, tagRepository), 50.0F, productRepository));
		offers.add(rule2For1Pizza);

		Rule ruleChristmasDayDiscount = new Rule("Christmas Day Discount");
		ruleChristmasDayDiscount.addCondition(new MinimumOrderAmountCondition(60.0F));
		ruleChristmasDayDiscount.addCondition(new DateCondition(12, 25));
		ruleChristmasDayDiscount.addAction(new PercentageDiscountTotalAction(10.0F));
		offers.add(ruleChristmasDayDiscount);

		Rule rule5OffOrdersWithChocolateCake = new Rule("5 Off Orders With Chocolate Cake");
		rule5OffOrdersWithChocolateCake.addCondition(new MinimumOrderAmountCondition(50.0F));
		rule5OffOrdersWithChocolateCake.addCondition(new ProductTagCondition(Arrays.asList("Chocolate", "Cake"), 1, productRepository, tagRepository));
		rule5OffOrdersWithChocolateCake.addAction(new FixedDiscountAction(5.0F));
		offers.add(rule5OffOrdersWithChocolateCake);

		// Custom rules
		/* protected region custom-rules on begin */
		// Rule ruleCustom = new Rule("Custom Offer Rule");
		// ruleCustom.addCondition(new customCondition("customParam"));
		// ruleCustom.addAction(new customAction("customParam"));
		// offers.add(ruleCustom);
		/* protected region custom-rules end */

		return offers;
	}

	protected Rule connectTriggers(List<Rule> offers) {

		// Auto-Generated triggers from model
		find(offers, "Bakewell Tarts First 10 Orders").addTrigger(TriggerType.ALWAYS, find(offers, "Pizza And Ice Cream Bundle"));
		find(offers, "Pizza And Ice Cream Bundle").addTrigger(TriggerType.IF_MATCH, find(offers, "Christmas Day Discount"));
		find(offers, "Pizza And Ice Cream Bundle").addTrigger(TriggerType.IF_NOT_MATCH, find(offers, "2 For 1 Pizza"));
		find(offers, "2 For 1 Pizza").addTrigger(TriggerType.ALWAYS, find(offers, "Christmas Day Discount"));
		find(offers, "Christmas Day Discount").addTrigger(TriggerType.IF_NOT_MATCH, find(offers, "5 Off Orders With Chocolate Cake"));

		// Custom triggers
		/* protected region custom-triggers on begin */
		// find(offers, "Custom Offer Rule").addTrigger(TriggerType.IF_MATCH, find(offers, "Custom Offer Rule 2"));
		/* protected region custom-triggers end */

		// Assume the first rule in the list is the initial rule
		Rule initialRule = offers.get(0);

		// Custom initial rule
		/* protected region custom-initial-rule on begin */
		// initialRule = find(offers, "Custom Offer Rule");
		/* protected region custom-initial-rule end */
		return initialRule;
	}

	public static Rule find(List<Rule> rulings, String name) {
		for (Rule r : rulings) {
			if (r.getName().equals(name)) return r;
		}
		return null;
	}
}