/**
 */
package uk.ac.york.cs.eng2.offers.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import uk.ac.york.cs.eng2.offers.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OffersFactoryImpl extends EFactoryImpl implements OffersFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OffersFactory init() {
		try {
			OffersFactory theOffersFactory = (OffersFactory)EPackage.Registry.INSTANCE.getEFactory(OffersPackage.eNS_URI);
			if (theOffersFactory != null) {
				return theOffersFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new OffersFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OffersFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case OffersPackage.OFFERS: return createOffers();
			case OffersPackage.CATALOG: return createCatalog();
			case OffersPackage.PRODUCT: return createProduct();
			case OffersPackage.CATEGORY: return createCategory();
			case OffersPackage.TAG: return createTag();
			case OffersPackage.RULE: return createRule();
			case OffersPackage.PRODUCT_ORDER_QUANTITY_CONDITION: return createProductOrderQuantityCondition();
			case OffersPackage.PRODUCT_TAG_CONDITION: return createProductTagCondition();
			case OffersPackage.MINIMUM_ORDER_AMOUNT_CONDITION: return createMinimumOrderAmountCondition();
			case OffersPackage.DATE_CONDITION: return createDateCondition();
			case OffersPackage.TAG_QUANTITY: return createTagQuantity();
			case OffersPackage.BUNDLE_PRICE_ACTION: return createBundlePriceAction();
			case OffersPackage.PERCENTAGE_DISCOUNT_PER_PRODUCT_ACTION: return createPercentageDiscountPerProductAction();
			case OffersPackage.PERCENTAGE_DISCOUNT_ON_TAGGED_QUANTITY_ACTION: return createPercentageDiscountOnTaggedQuantityAction();
			case OffersPackage.PERCENTAGE_DISCOUNT_TOTAL_ACTION: return createPercentageDiscountTotalAction();
			case OffersPackage.FIXED_DISCOUNT_ACTION: return createFixedDiscountAction();
			case OffersPackage.TRIGGER: return createTrigger();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case OffersPackage.TRIGGER_TYPE:
				return createTriggerTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case OffersPackage.TRIGGER_TYPE:
				return convertTriggerTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Offers createOffers() {
		OffersImpl offers = new OffersImpl();
		return offers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Catalog createCatalog() {
		CatalogImpl catalog = new CatalogImpl();
		return catalog;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Product createProduct() {
		ProductImpl product = new ProductImpl();
		return product;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Category createCategory() {
		CategoryImpl category = new CategoryImpl();
		return category;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Tag createTag() {
		TagImpl tag = new TagImpl();
		return tag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Rule createRule() {
		RuleImpl rule = new RuleImpl();
		return rule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ProductOrderQuantityCondition createProductOrderQuantityCondition() {
		ProductOrderQuantityConditionImpl productOrderQuantityCondition = new ProductOrderQuantityConditionImpl();
		return productOrderQuantityCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ProductTagCondition createProductTagCondition() {
		ProductTagConditionImpl productTagCondition = new ProductTagConditionImpl();
		return productTagCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MinimumOrderAmountCondition createMinimumOrderAmountCondition() {
		MinimumOrderAmountConditionImpl minimumOrderAmountCondition = new MinimumOrderAmountConditionImpl();
		return minimumOrderAmountCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DateCondition createDateCondition() {
		DateConditionImpl dateCondition = new DateConditionImpl();
		return dateCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TagQuantity createTagQuantity() {
		TagQuantityImpl tagQuantity = new TagQuantityImpl();
		return tagQuantity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BundlePriceAction createBundlePriceAction() {
		BundlePriceActionImpl bundlePriceAction = new BundlePriceActionImpl();
		return bundlePriceAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PercentageDiscountPerProductAction createPercentageDiscountPerProductAction() {
		PercentageDiscountPerProductActionImpl percentageDiscountPerProductAction = new PercentageDiscountPerProductActionImpl();
		return percentageDiscountPerProductAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PercentageDiscountOnTaggedQuantityAction createPercentageDiscountOnTaggedQuantityAction() {
		PercentageDiscountOnTaggedQuantityActionImpl percentageDiscountOnTaggedQuantityAction = new PercentageDiscountOnTaggedQuantityActionImpl();
		return percentageDiscountOnTaggedQuantityAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PercentageDiscountTotalAction createPercentageDiscountTotalAction() {
		PercentageDiscountTotalActionImpl percentageDiscountTotalAction = new PercentageDiscountTotalActionImpl();
		return percentageDiscountTotalAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FixedDiscountAction createFixedDiscountAction() {
		FixedDiscountActionImpl fixedDiscountAction = new FixedDiscountActionImpl();
		return fixedDiscountAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Trigger createTrigger() {
		TriggerImpl trigger = new TriggerImpl();
		return trigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TriggerType createTriggerTypeFromString(EDataType eDataType, String initialValue) {
		TriggerType result = TriggerType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTriggerTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OffersPackage getOffersPackage() {
		return (OffersPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static OffersPackage getPackage() {
		return OffersPackage.eINSTANCE;
	}

} //OffersFactoryImpl
