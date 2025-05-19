import java.util.*

public class GenerateOffers {

	public void applyOffers(Order order) {
		List<Rule> offers = buildOffers();
		Rule initialOffer = connectTriggers(offers);
		if (initialOffer != null){
			initialOffer.applyIfValid(order);
		}
	}

	public List<Rule> buildOffers() {
		List<Rule> offers = new ArrayList<>();

		// Auto-Generated rules from model
		Rule ruleBakewellTarts-First10Orders = new Rule("Bakewell Tarts - First 10 Orders");
		ruleBakewellTarts-First10Orders.addCondition(new ProductOrderQuantityCondition("Bakewell Tarts", 10));
		ruleBakewellTarts-First10Orders.addAction(new PercentageDiscountPerProductAction("Bakewell Tarts", 10.0));
		offers.add(ruleBakewellTarts-First10Orders);

		Rule rulePizza+IceCreamBundle = new Rule("Pizza + Ice Cream Bundle");
		rulePizza+IceCreamBundle.addCondition(new ProductTagCondition(Arrays.asList("Large", "Pizza"), 2));
		rulePizza+IceCreamBundle.addCondition(new ProductTagCondition(Arrays.asList("Large", "Ice Cream"), 1));
		rulePizza+IceCreamBundle.addAction(new BundlePriceAction(Arrays.asList(new TagQuantity(Arrays.asList("Ice Cream", "Large"), 1), new TagQuantity(Arrays.asList("Large", "Pizza"), 2)), 10.0));
		offers.add(rulePizza+IceCreamBundle);

		Rule rule2For1Pizza = new Rule("2 For 1 Pizza");
		rule2For1Pizza.addCondition(new ProductTagCondition(Arrays.asList("Pizza"), 2));
		rule2For1Pizza.addAction(new PercentageDiscountOnTaggedQuantityAction(new TagQuantity(Arrays.asList("Pizza"), 2), 50.0));
		offers.add(rule2For1Pizza);

		Rule ruleChristmasDayDiscount = new Rule("Christmas Day Discount");
		ruleChristmasDayDiscount.addCondition(new MinimumOrderAmountCondition(60.0));
		ruleChristmasDayDiscount.addCondition(new DateCondition(12, 25));
		ruleChristmasDayDiscount.addAction(new PercentageDiscountTotalAction(10.0));
		offers.add(ruleChristmasDayDiscount);

		Rule rule5OffOrdersWithChocolateCake = new Rule("5 Off Orders With Chocolate Cake");
		rule5OffOrdersWithChocolateCake.addCondition(new MinimumOrderAmountCondition(50.0));
		rule5OffOrdersWithChocolateCake.addCondition(new ProductTagCondition(Arrays.asList("Chocolate", "Cake"), 1));
		rule5OffOrdersWithChocolateCake.addAction(new FixedDiscountAction(5.0));
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
		find(offers, "Bakewell Tarts - First 10 Orders").addTrigger(TriggerType.ALWAYS, find(offers, "Pizza + Ice Cream Bundle"));
		find(offers, "Pizza + Ice Cream Bundle").addTrigger(TriggerType.IF_MATCH, find(offers, "Christmas Day Discount"));
		find(offers, "Pizza + Ice Cream Bundle").addTrigger(TriggerType.IF_NOT_MATCH, find(offers, "2 For 1 Pizza"));
		find(offers, "2 For 1 Pizza").addTrigger(TriggerType.ALWAYS, find(offers, "Christmas Day Discount"));
		find(offers, "Christmas Day Discount").addTrigger(TriggerType.IF_NOT_MATCH, find(offers, "5 Off Orders With Chocolate Cake"));

		// Custom triggers
		/* protected region custom-triggers on begin */
		// find(offers, "Custom Offer Rule").addTrigger(TriggerType.IF_MATCH, find(offers, "Custom Offer Rule 2"));
		/* protected region custom-triggers end */

		Rule initialRule = null;

		// Custom initial rule
		/* protected region custom-initial-rule on begin */
		// initialRule = find(offers, "Custom Offer Rule");
		/* protected region custom-initial-rule end */

		// Assume the first rule in the list is the initial rule if unset
		if (initialRule == null) {
			initialRule = offers.isEmpty() ? null : offers.get(0);
		}

		return initialRule;
	}

	private Rule find(List<Rule> rulings, String name) {
		for (Rule r : rulings) {
			if (r.getName().equals(name)) return r;
		}
		return null;
	}
}