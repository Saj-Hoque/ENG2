/**
 */
package uk.ac.york.cs.eng2.offers;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see uk.ac.york.cs.eng2.offers.OffersFactory
 * @model kind="package"
 * @generated
 */
public interface OffersPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "offers";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://cs.york.ac.uk/eng2/202425/offers";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "dis";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OffersPackage eINSTANCE = uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl.init();

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.offers.impl.OffersImpl <em>Offers</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.offers.impl.OffersImpl
	 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getOffers()
	 * @generated
	 */
	int OFFERS = 0;

	/**
	 * The feature id for the '<em><b>Catalog</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERS__CATALOG = 0;

	/**
	 * The feature id for the '<em><b>Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERS__RULES = 1;

	/**
	 * The number of structural features of the '<em>Offers</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERS_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Offers</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFERS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.offers.impl.CatalogImpl <em>Catalog</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.offers.impl.CatalogImpl
	 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getCatalog()
	 * @generated
	 */
	int CATALOG = 1;

	/**
	 * The feature id for the '<em><b>Products</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATALOG__PRODUCTS = 0;

	/**
	 * The feature id for the '<em><b>Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATALOG__CATEGORIES = 1;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATALOG__TAGS = 2;

	/**
	 * The number of structural features of the '<em>Catalog</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATALOG_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Catalog</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATALOG_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.offers.impl.ProductImpl <em>Product</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.offers.impl.ProductImpl
	 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getProduct()
	 * @generated
	 */
	int PRODUCT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Price</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__PRICE = 1;

	/**
	 * The feature id for the '<em><b>Categories</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__CATEGORIES = 2;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__TAGS = 3;

	/**
	 * The number of structural features of the '<em>Product</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Product</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.offers.impl.CategoryImpl <em>Category</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.offers.impl.CategoryImpl
	 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getCategory()
	 * @generated
	 */
	int CATEGORY = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__NAME = 0;

	/**
	 * The feature id for the '<em><b>Subcategories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__SUBCATEGORIES = 1;

	/**
	 * The feature id for the '<em><b>Products</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__PRODUCTS = 2;

	/**
	 * The number of structural features of the '<em>Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.offers.impl.TagImpl <em>Tag</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.offers.impl.TagImpl
	 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getTag()
	 * @generated
	 */
	int TAG = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__NAME = 0;

	/**
	 * The number of structural features of the '<em>Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.offers.impl.RuleImpl <em>Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.offers.impl.RuleImpl
	 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getRule()
	 * @generated
	 */
	int RULE = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Conditions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__CONDITIONS = 2;

	/**
	 * The feature id for the '<em><b>Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__ACTIONS = 3;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__TRIGGERS = 4;

	/**
	 * The number of structural features of the '<em>Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.offers.impl.ConditionImpl <em>Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.offers.impl.ConditionImpl
	 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getCondition()
	 * @generated
	 */
	int CONDITION = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION__NAME = 0;

	/**
	 * The number of structural features of the '<em>Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.offers.impl.ProductOrderQuantityConditionImpl <em>Product Order Quantity Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.offers.impl.ProductOrderQuantityConditionImpl
	 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getProductOrderQuantityCondition()
	 * @generated
	 */
	int PRODUCT_ORDER_QUANTITY_CONDITION = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_ORDER_QUANTITY_CONDITION__NAME = CONDITION__NAME;

	/**
	 * The feature id for the '<em><b>Product</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_ORDER_QUANTITY_CONDITION__PRODUCT = CONDITION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Daily Orders</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_ORDER_QUANTITY_CONDITION__DAILY_ORDERS = CONDITION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Product Order Quantity Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_ORDER_QUANTITY_CONDITION_FEATURE_COUNT = CONDITION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Product Order Quantity Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_ORDER_QUANTITY_CONDITION_OPERATION_COUNT = CONDITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.offers.impl.ProductTagConditionImpl <em>Product Tag Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.offers.impl.ProductTagConditionImpl
	 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getProductTagCondition()
	 * @generated
	 */
	int PRODUCT_TAG_CONDITION = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_TAG_CONDITION__NAME = CONDITION__NAME;

	/**
	 * The feature id for the '<em><b>Tag</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_TAG_CONDITION__TAG = CONDITION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Min Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_TAG_CONDITION__MIN_QUANTITY = CONDITION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Product Tag Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_TAG_CONDITION_FEATURE_COUNT = CONDITION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Product Tag Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_TAG_CONDITION_OPERATION_COUNT = CONDITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.offers.impl.MinimumOrderAmountConditionImpl <em>Minimum Order Amount Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.offers.impl.MinimumOrderAmountConditionImpl
	 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getMinimumOrderAmountCondition()
	 * @generated
	 */
	int MINIMUM_ORDER_AMOUNT_CONDITION = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MINIMUM_ORDER_AMOUNT_CONDITION__NAME = CONDITION__NAME;

	/**
	 * The feature id for the '<em><b>Min Amount</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MINIMUM_ORDER_AMOUNT_CONDITION__MIN_AMOUNT = CONDITION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Minimum Order Amount Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MINIMUM_ORDER_AMOUNT_CONDITION_FEATURE_COUNT = CONDITION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Minimum Order Amount Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MINIMUM_ORDER_AMOUNT_CONDITION_OPERATION_COUNT = CONDITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.offers.impl.DateConditionImpl <em>Date Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.offers.impl.DateConditionImpl
	 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getDateCondition()
	 * @generated
	 */
	int DATE_CONDITION = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_CONDITION__NAME = CONDITION__NAME;

	/**
	 * The feature id for the '<em><b>Month</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_CONDITION__MONTH = CONDITION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Day</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_CONDITION__DAY = CONDITION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Date Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_CONDITION_FEATURE_COUNT = CONDITION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Date Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_CONDITION_OPERATION_COUNT = CONDITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.offers.impl.TagQuantityImpl <em>Tag Quantity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.offers.impl.TagQuantityImpl
	 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getTagQuantity()
	 * @generated
	 */
	int TAG_QUANTITY = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_QUANTITY__NAME = 0;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_QUANTITY__TAGS = 1;

	/**
	 * The feature id for the '<em><b>Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_QUANTITY__QUANTITY = 2;

	/**
	 * The number of structural features of the '<em>Tag Quantity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_QUANTITY_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Tag Quantity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_QUANTITY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.offers.impl.ActionImpl <em>Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.offers.impl.ActionImpl
	 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getAction()
	 * @generated
	 */
	int ACTION = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__NAME = 0;

	/**
	 * The number of structural features of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.offers.impl.BundlePriceActionImpl <em>Bundle Price Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.offers.impl.BundlePriceActionImpl
	 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getBundlePriceAction()
	 * @generated
	 */
	int BUNDLE_PRICE_ACTION = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE_PRICE_ACTION__NAME = ACTION__NAME;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE_PRICE_ACTION__ITEMS = ACTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Bundle Price</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE_PRICE_ACTION__BUNDLE_PRICE = ACTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Bundle Price Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE_PRICE_ACTION_FEATURE_COUNT = ACTION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Bundle Price Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE_PRICE_ACTION_OPERATION_COUNT = ACTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.offers.impl.PercentageDiscountPerProductActionImpl <em>Percentage Discount Per Product Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.offers.impl.PercentageDiscountPerProductActionImpl
	 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getPercentageDiscountPerProductAction()
	 * @generated
	 */
	int PERCENTAGE_DISCOUNT_PER_PRODUCT_ACTION = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERCENTAGE_DISCOUNT_PER_PRODUCT_ACTION__NAME = ACTION__NAME;

	/**
	 * The feature id for the '<em><b>Product</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERCENTAGE_DISCOUNT_PER_PRODUCT_ACTION__PRODUCT = ACTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Percentage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERCENTAGE_DISCOUNT_PER_PRODUCT_ACTION__PERCENTAGE = ACTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Percentage Discount Per Product Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERCENTAGE_DISCOUNT_PER_PRODUCT_ACTION_FEATURE_COUNT = ACTION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Percentage Discount Per Product Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERCENTAGE_DISCOUNT_PER_PRODUCT_ACTION_OPERATION_COUNT = ACTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.offers.impl.PercentageDiscountOnTaggedQuantityActionImpl <em>Percentage Discount On Tagged Quantity Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.offers.impl.PercentageDiscountOnTaggedQuantityActionImpl
	 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getPercentageDiscountOnTaggedQuantityAction()
	 * @generated
	 */
	int PERCENTAGE_DISCOUNT_ON_TAGGED_QUANTITY_ACTION = 15;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERCENTAGE_DISCOUNT_ON_TAGGED_QUANTITY_ACTION__NAME = ACTION__NAME;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERCENTAGE_DISCOUNT_ON_TAGGED_QUANTITY_ACTION__ITEMS = ACTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Percentage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERCENTAGE_DISCOUNT_ON_TAGGED_QUANTITY_ACTION__PERCENTAGE = ACTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Percentage Discount On Tagged Quantity Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERCENTAGE_DISCOUNT_ON_TAGGED_QUANTITY_ACTION_FEATURE_COUNT = ACTION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Percentage Discount On Tagged Quantity Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERCENTAGE_DISCOUNT_ON_TAGGED_QUANTITY_ACTION_OPERATION_COUNT = ACTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.offers.impl.PercentageDiscountTotalActionImpl <em>Percentage Discount Total Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.offers.impl.PercentageDiscountTotalActionImpl
	 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getPercentageDiscountTotalAction()
	 * @generated
	 */
	int PERCENTAGE_DISCOUNT_TOTAL_ACTION = 16;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERCENTAGE_DISCOUNT_TOTAL_ACTION__NAME = ACTION__NAME;

	/**
	 * The feature id for the '<em><b>Percentage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERCENTAGE_DISCOUNT_TOTAL_ACTION__PERCENTAGE = ACTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Percentage Discount Total Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERCENTAGE_DISCOUNT_TOTAL_ACTION_FEATURE_COUNT = ACTION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Percentage Discount Total Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERCENTAGE_DISCOUNT_TOTAL_ACTION_OPERATION_COUNT = ACTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.offers.impl.FixedDiscountActionImpl <em>Fixed Discount Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.offers.impl.FixedDiscountActionImpl
	 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getFixedDiscountAction()
	 * @generated
	 */
	int FIXED_DISCOUNT_ACTION = 17;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_DISCOUNT_ACTION__NAME = ACTION__NAME;

	/**
	 * The feature id for the '<em><b>Amount</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_DISCOUNT_ACTION__AMOUNT = ACTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Fixed Discount Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_DISCOUNT_ACTION_FEATURE_COUNT = ACTION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Fixed Discount Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_DISCOUNT_ACTION_OPERATION_COUNT = ACTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.offers.impl.TriggerImpl <em>Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.offers.impl.TriggerImpl
	 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getTrigger()
	 * @generated
	 */
	int TRIGGER = 18;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Triggered Rule</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__TRIGGERED_RULE = 1;

	/**
	 * The number of structural features of the '<em>Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link uk.ac.york.cs.eng2.offers.TriggerType <em>Trigger Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see uk.ac.york.cs.eng2.offers.TriggerType
	 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getTriggerType()
	 * @generated
	 */
	int TRIGGER_TYPE = 19;


	/**
	 * Returns the meta object for class '{@link uk.ac.york.cs.eng2.offers.Offers <em>Offers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Offers</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Offers
	 * @generated
	 */
	EClass getOffers();

	/**
	 * Returns the meta object for the containment reference '{@link uk.ac.york.cs.eng2.offers.Offers#getCatalog <em>Catalog</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Catalog</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Offers#getCatalog()
	 * @see #getOffers()
	 * @generated
	 */
	EReference getOffers_Catalog();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.york.cs.eng2.offers.Offers#getRules <em>Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rules</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Offers#getRules()
	 * @see #getOffers()
	 * @generated
	 */
	EReference getOffers_Rules();

	/**
	 * Returns the meta object for class '{@link uk.ac.york.cs.eng2.offers.Catalog <em>Catalog</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Catalog</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Catalog
	 * @generated
	 */
	EClass getCatalog();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.york.cs.eng2.offers.Catalog#getProducts <em>Products</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Products</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Catalog#getProducts()
	 * @see #getCatalog()
	 * @generated
	 */
	EReference getCatalog_Products();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.york.cs.eng2.offers.Catalog#getCategories <em>Categories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Categories</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Catalog#getCategories()
	 * @see #getCatalog()
	 * @generated
	 */
	EReference getCatalog_Categories();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.york.cs.eng2.offers.Catalog#getTags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tags</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Catalog#getTags()
	 * @see #getCatalog()
	 * @generated
	 */
	EReference getCatalog_Tags();

	/**
	 * Returns the meta object for class '{@link uk.ac.york.cs.eng2.offers.Product <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Product
	 * @generated
	 */
	EClass getProduct();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.offers.Product#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Product#getName()
	 * @see #getProduct()
	 * @generated
	 */
	EAttribute getProduct_Name();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.offers.Product#getPrice <em>Price</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Price</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Product#getPrice()
	 * @see #getProduct()
	 * @generated
	 */
	EAttribute getProduct_Price();

	/**
	 * Returns the meta object for the reference list '{@link uk.ac.york.cs.eng2.offers.Product#getCategories <em>Categories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Categories</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Product#getCategories()
	 * @see #getProduct()
	 * @generated
	 */
	EReference getProduct_Categories();

	/**
	 * Returns the meta object for the reference list '{@link uk.ac.york.cs.eng2.offers.Product#getTags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Tags</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Product#getTags()
	 * @see #getProduct()
	 * @generated
	 */
	EReference getProduct_Tags();

	/**
	 * Returns the meta object for class '{@link uk.ac.york.cs.eng2.offers.Category <em>Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Category</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Category
	 * @generated
	 */
	EClass getCategory();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.offers.Category#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Category#getName()
	 * @see #getCategory()
	 * @generated
	 */
	EAttribute getCategory_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.york.cs.eng2.offers.Category#getSubcategories <em>Subcategories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Subcategories</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Category#getSubcategories()
	 * @see #getCategory()
	 * @generated
	 */
	EReference getCategory_Subcategories();

	/**
	 * Returns the meta object for the reference list '{@link uk.ac.york.cs.eng2.offers.Category#getProducts <em>Products</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Products</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Category#getProducts()
	 * @see #getCategory()
	 * @generated
	 */
	EReference getCategory_Products();

	/**
	 * Returns the meta object for class '{@link uk.ac.york.cs.eng2.offers.Tag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Tag
	 * @generated
	 */
	EClass getTag();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.offers.Tag#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Tag#getName()
	 * @see #getTag()
	 * @generated
	 */
	EAttribute getTag_Name();

	/**
	 * Returns the meta object for class '{@link uk.ac.york.cs.eng2.offers.Rule <em>Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rule</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Rule
	 * @generated
	 */
	EClass getRule();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.offers.Rule#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Rule#getName()
	 * @see #getRule()
	 * @generated
	 */
	EAttribute getRule_Name();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.offers.Rule#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Rule#getDescription()
	 * @see #getRule()
	 * @generated
	 */
	EAttribute getRule_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.york.cs.eng2.offers.Rule#getConditions <em>Conditions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Conditions</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Rule#getConditions()
	 * @see #getRule()
	 * @generated
	 */
	EReference getRule_Conditions();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.york.cs.eng2.offers.Rule#getActions <em>Actions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Actions</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Rule#getActions()
	 * @see #getRule()
	 * @generated
	 */
	EReference getRule_Actions();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.york.cs.eng2.offers.Rule#getTriggers <em>Triggers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Triggers</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Rule#getTriggers()
	 * @see #getRule()
	 * @generated
	 */
	EReference getRule_Triggers();

	/**
	 * Returns the meta object for class '{@link uk.ac.york.cs.eng2.offers.Condition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Condition</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Condition
	 * @generated
	 */
	EClass getCondition();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.offers.Condition#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Condition#getName()
	 * @see #getCondition()
	 * @generated
	 */
	EAttribute getCondition_Name();

	/**
	 * Returns the meta object for class '{@link uk.ac.york.cs.eng2.offers.ProductOrderQuantityCondition <em>Product Order Quantity Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product Order Quantity Condition</em>'.
	 * @see uk.ac.york.cs.eng2.offers.ProductOrderQuantityCondition
	 * @generated
	 */
	EClass getProductOrderQuantityCondition();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.york.cs.eng2.offers.ProductOrderQuantityCondition#getProduct <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Product</em>'.
	 * @see uk.ac.york.cs.eng2.offers.ProductOrderQuantityCondition#getProduct()
	 * @see #getProductOrderQuantityCondition()
	 * @generated
	 */
	EReference getProductOrderQuantityCondition_Product();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.offers.ProductOrderQuantityCondition#getDailyOrders <em>Daily Orders</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Daily Orders</em>'.
	 * @see uk.ac.york.cs.eng2.offers.ProductOrderQuantityCondition#getDailyOrders()
	 * @see #getProductOrderQuantityCondition()
	 * @generated
	 */
	EAttribute getProductOrderQuantityCondition_DailyOrders();

	/**
	 * Returns the meta object for class '{@link uk.ac.york.cs.eng2.offers.ProductTagCondition <em>Product Tag Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product Tag Condition</em>'.
	 * @see uk.ac.york.cs.eng2.offers.ProductTagCondition
	 * @generated
	 */
	EClass getProductTagCondition();

	/**
	 * Returns the meta object for the reference list '{@link uk.ac.york.cs.eng2.offers.ProductTagCondition#getTag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Tag</em>'.
	 * @see uk.ac.york.cs.eng2.offers.ProductTagCondition#getTag()
	 * @see #getProductTagCondition()
	 * @generated
	 */
	EReference getProductTagCondition_Tag();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.offers.ProductTagCondition#getMinQuantity <em>Min Quantity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min Quantity</em>'.
	 * @see uk.ac.york.cs.eng2.offers.ProductTagCondition#getMinQuantity()
	 * @see #getProductTagCondition()
	 * @generated
	 */
	EAttribute getProductTagCondition_MinQuantity();

	/**
	 * Returns the meta object for class '{@link uk.ac.york.cs.eng2.offers.MinimumOrderAmountCondition <em>Minimum Order Amount Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Minimum Order Amount Condition</em>'.
	 * @see uk.ac.york.cs.eng2.offers.MinimumOrderAmountCondition
	 * @generated
	 */
	EClass getMinimumOrderAmountCondition();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.offers.MinimumOrderAmountCondition#getMinAmount <em>Min Amount</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min Amount</em>'.
	 * @see uk.ac.york.cs.eng2.offers.MinimumOrderAmountCondition#getMinAmount()
	 * @see #getMinimumOrderAmountCondition()
	 * @generated
	 */
	EAttribute getMinimumOrderAmountCondition_MinAmount();

	/**
	 * Returns the meta object for class '{@link uk.ac.york.cs.eng2.offers.DateCondition <em>Date Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Date Condition</em>'.
	 * @see uk.ac.york.cs.eng2.offers.DateCondition
	 * @generated
	 */
	EClass getDateCondition();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.offers.DateCondition#getMonth <em>Month</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Month</em>'.
	 * @see uk.ac.york.cs.eng2.offers.DateCondition#getMonth()
	 * @see #getDateCondition()
	 * @generated
	 */
	EAttribute getDateCondition_Month();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.offers.DateCondition#getDay <em>Day</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Day</em>'.
	 * @see uk.ac.york.cs.eng2.offers.DateCondition#getDay()
	 * @see #getDateCondition()
	 * @generated
	 */
	EAttribute getDateCondition_Day();

	/**
	 * Returns the meta object for class '{@link uk.ac.york.cs.eng2.offers.TagQuantity <em>Tag Quantity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag Quantity</em>'.
	 * @see uk.ac.york.cs.eng2.offers.TagQuantity
	 * @generated
	 */
	EClass getTagQuantity();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.offers.TagQuantity#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see uk.ac.york.cs.eng2.offers.TagQuantity#getName()
	 * @see #getTagQuantity()
	 * @generated
	 */
	EAttribute getTagQuantity_Name();

	/**
	 * Returns the meta object for the reference list '{@link uk.ac.york.cs.eng2.offers.TagQuantity#getTags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Tags</em>'.
	 * @see uk.ac.york.cs.eng2.offers.TagQuantity#getTags()
	 * @see #getTagQuantity()
	 * @generated
	 */
	EReference getTagQuantity_Tags();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.offers.TagQuantity#getQuantity <em>Quantity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Quantity</em>'.
	 * @see uk.ac.york.cs.eng2.offers.TagQuantity#getQuantity()
	 * @see #getTagQuantity()
	 * @generated
	 */
	EAttribute getTagQuantity_Quantity();

	/**
	 * Returns the meta object for class '{@link uk.ac.york.cs.eng2.offers.Action <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Action
	 * @generated
	 */
	EClass getAction();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.offers.Action#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Action#getName()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_Name();

	/**
	 * Returns the meta object for class '{@link uk.ac.york.cs.eng2.offers.BundlePriceAction <em>Bundle Price Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bundle Price Action</em>'.
	 * @see uk.ac.york.cs.eng2.offers.BundlePriceAction
	 * @generated
	 */
	EClass getBundlePriceAction();

	/**
	 * Returns the meta object for the containment reference list '{@link uk.ac.york.cs.eng2.offers.BundlePriceAction#getItems <em>Items</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Items</em>'.
	 * @see uk.ac.york.cs.eng2.offers.BundlePriceAction#getItems()
	 * @see #getBundlePriceAction()
	 * @generated
	 */
	EReference getBundlePriceAction_Items();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.offers.BundlePriceAction#getBundlePrice <em>Bundle Price</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bundle Price</em>'.
	 * @see uk.ac.york.cs.eng2.offers.BundlePriceAction#getBundlePrice()
	 * @see #getBundlePriceAction()
	 * @generated
	 */
	EAttribute getBundlePriceAction_BundlePrice();

	/**
	 * Returns the meta object for class '{@link uk.ac.york.cs.eng2.offers.PercentageDiscountPerProductAction <em>Percentage Discount Per Product Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Percentage Discount Per Product Action</em>'.
	 * @see uk.ac.york.cs.eng2.offers.PercentageDiscountPerProductAction
	 * @generated
	 */
	EClass getPercentageDiscountPerProductAction();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.york.cs.eng2.offers.PercentageDiscountPerProductAction#getProduct <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Product</em>'.
	 * @see uk.ac.york.cs.eng2.offers.PercentageDiscountPerProductAction#getProduct()
	 * @see #getPercentageDiscountPerProductAction()
	 * @generated
	 */
	EReference getPercentageDiscountPerProductAction_Product();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.offers.PercentageDiscountPerProductAction#getPercentage <em>Percentage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Percentage</em>'.
	 * @see uk.ac.york.cs.eng2.offers.PercentageDiscountPerProductAction#getPercentage()
	 * @see #getPercentageDiscountPerProductAction()
	 * @generated
	 */
	EAttribute getPercentageDiscountPerProductAction_Percentage();

	/**
	 * Returns the meta object for class '{@link uk.ac.york.cs.eng2.offers.PercentageDiscountOnTaggedQuantityAction <em>Percentage Discount On Tagged Quantity Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Percentage Discount On Tagged Quantity Action</em>'.
	 * @see uk.ac.york.cs.eng2.offers.PercentageDiscountOnTaggedQuantityAction
	 * @generated
	 */
	EClass getPercentageDiscountOnTaggedQuantityAction();

	/**
	 * Returns the meta object for the containment reference '{@link uk.ac.york.cs.eng2.offers.PercentageDiscountOnTaggedQuantityAction#getItems <em>Items</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Items</em>'.
	 * @see uk.ac.york.cs.eng2.offers.PercentageDiscountOnTaggedQuantityAction#getItems()
	 * @see #getPercentageDiscountOnTaggedQuantityAction()
	 * @generated
	 */
	EReference getPercentageDiscountOnTaggedQuantityAction_Items();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.offers.PercentageDiscountOnTaggedQuantityAction#getPercentage <em>Percentage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Percentage</em>'.
	 * @see uk.ac.york.cs.eng2.offers.PercentageDiscountOnTaggedQuantityAction#getPercentage()
	 * @see #getPercentageDiscountOnTaggedQuantityAction()
	 * @generated
	 */
	EAttribute getPercentageDiscountOnTaggedQuantityAction_Percentage();

	/**
	 * Returns the meta object for class '{@link uk.ac.york.cs.eng2.offers.PercentageDiscountTotalAction <em>Percentage Discount Total Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Percentage Discount Total Action</em>'.
	 * @see uk.ac.york.cs.eng2.offers.PercentageDiscountTotalAction
	 * @generated
	 */
	EClass getPercentageDiscountTotalAction();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.offers.PercentageDiscountTotalAction#getPercentage <em>Percentage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Percentage</em>'.
	 * @see uk.ac.york.cs.eng2.offers.PercentageDiscountTotalAction#getPercentage()
	 * @see #getPercentageDiscountTotalAction()
	 * @generated
	 */
	EAttribute getPercentageDiscountTotalAction_Percentage();

	/**
	 * Returns the meta object for class '{@link uk.ac.york.cs.eng2.offers.FixedDiscountAction <em>Fixed Discount Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fixed Discount Action</em>'.
	 * @see uk.ac.york.cs.eng2.offers.FixedDiscountAction
	 * @generated
	 */
	EClass getFixedDiscountAction();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.offers.FixedDiscountAction#getAmount <em>Amount</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Amount</em>'.
	 * @see uk.ac.york.cs.eng2.offers.FixedDiscountAction#getAmount()
	 * @see #getFixedDiscountAction()
	 * @generated
	 */
	EAttribute getFixedDiscountAction_Amount();

	/**
	 * Returns the meta object for class '{@link uk.ac.york.cs.eng2.offers.Trigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trigger</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Trigger
	 * @generated
	 */
	EClass getTrigger();

	/**
	 * Returns the meta object for the attribute '{@link uk.ac.york.cs.eng2.offers.Trigger#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Trigger#getType()
	 * @see #getTrigger()
	 * @generated
	 */
	EAttribute getTrigger_Type();

	/**
	 * Returns the meta object for the reference '{@link uk.ac.york.cs.eng2.offers.Trigger#getTriggeredRule <em>Triggered Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Triggered Rule</em>'.
	 * @see uk.ac.york.cs.eng2.offers.Trigger#getTriggeredRule()
	 * @see #getTrigger()
	 * @generated
	 */
	EReference getTrigger_TriggeredRule();

	/**
	 * Returns the meta object for enum '{@link uk.ac.york.cs.eng2.offers.TriggerType <em>Trigger Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Trigger Type</em>'.
	 * @see uk.ac.york.cs.eng2.offers.TriggerType
	 * @generated
	 */
	EEnum getTriggerType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	OffersFactory getOffersFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.offers.impl.OffersImpl <em>Offers</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.offers.impl.OffersImpl
		 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getOffers()
		 * @generated
		 */
		EClass OFFERS = eINSTANCE.getOffers();

		/**
		 * The meta object literal for the '<em><b>Catalog</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OFFERS__CATALOG = eINSTANCE.getOffers_Catalog();

		/**
		 * The meta object literal for the '<em><b>Rules</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OFFERS__RULES = eINSTANCE.getOffers_Rules();

		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.offers.impl.CatalogImpl <em>Catalog</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.offers.impl.CatalogImpl
		 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getCatalog()
		 * @generated
		 */
		EClass CATALOG = eINSTANCE.getCatalog();

		/**
		 * The meta object literal for the '<em><b>Products</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CATALOG__PRODUCTS = eINSTANCE.getCatalog_Products();

		/**
		 * The meta object literal for the '<em><b>Categories</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CATALOG__CATEGORIES = eINSTANCE.getCatalog_Categories();

		/**
		 * The meta object literal for the '<em><b>Tags</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CATALOG__TAGS = eINSTANCE.getCatalog_Tags();

		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.offers.impl.ProductImpl <em>Product</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.offers.impl.ProductImpl
		 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getProduct()
		 * @generated
		 */
		EClass PRODUCT = eINSTANCE.getProduct();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT__NAME = eINSTANCE.getProduct_Name();

		/**
		 * The meta object literal for the '<em><b>Price</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT__PRICE = eINSTANCE.getProduct_Price();

		/**
		 * The meta object literal for the '<em><b>Categories</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT__CATEGORIES = eINSTANCE.getProduct_Categories();

		/**
		 * The meta object literal for the '<em><b>Tags</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT__TAGS = eINSTANCE.getProduct_Tags();

		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.offers.impl.CategoryImpl <em>Category</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.offers.impl.CategoryImpl
		 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getCategory()
		 * @generated
		 */
		EClass CATEGORY = eINSTANCE.getCategory();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CATEGORY__NAME = eINSTANCE.getCategory_Name();

		/**
		 * The meta object literal for the '<em><b>Subcategories</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CATEGORY__SUBCATEGORIES = eINSTANCE.getCategory_Subcategories();

		/**
		 * The meta object literal for the '<em><b>Products</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CATEGORY__PRODUCTS = eINSTANCE.getCategory_Products();

		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.offers.impl.TagImpl <em>Tag</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.offers.impl.TagImpl
		 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getTag()
		 * @generated
		 */
		EClass TAG = eINSTANCE.getTag();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG__NAME = eINSTANCE.getTag_Name();

		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.offers.impl.RuleImpl <em>Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.offers.impl.RuleImpl
		 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getRule()
		 * @generated
		 */
		EClass RULE = eINSTANCE.getRule();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE__NAME = eINSTANCE.getRule_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE__DESCRIPTION = eINSTANCE.getRule_Description();

		/**
		 * The meta object literal for the '<em><b>Conditions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE__CONDITIONS = eINSTANCE.getRule_Conditions();

		/**
		 * The meta object literal for the '<em><b>Actions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE__ACTIONS = eINSTANCE.getRule_Actions();

		/**
		 * The meta object literal for the '<em><b>Triggers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE__TRIGGERS = eINSTANCE.getRule_Triggers();

		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.offers.impl.ConditionImpl <em>Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.offers.impl.ConditionImpl
		 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getCondition()
		 * @generated
		 */
		EClass CONDITION = eINSTANCE.getCondition();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONDITION__NAME = eINSTANCE.getCondition_Name();

		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.offers.impl.ProductOrderQuantityConditionImpl <em>Product Order Quantity Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.offers.impl.ProductOrderQuantityConditionImpl
		 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getProductOrderQuantityCondition()
		 * @generated
		 */
		EClass PRODUCT_ORDER_QUANTITY_CONDITION = eINSTANCE.getProductOrderQuantityCondition();

		/**
		 * The meta object literal for the '<em><b>Product</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT_ORDER_QUANTITY_CONDITION__PRODUCT = eINSTANCE.getProductOrderQuantityCondition_Product();

		/**
		 * The meta object literal for the '<em><b>Daily Orders</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT_ORDER_QUANTITY_CONDITION__DAILY_ORDERS = eINSTANCE.getProductOrderQuantityCondition_DailyOrders();

		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.offers.impl.ProductTagConditionImpl <em>Product Tag Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.offers.impl.ProductTagConditionImpl
		 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getProductTagCondition()
		 * @generated
		 */
		EClass PRODUCT_TAG_CONDITION = eINSTANCE.getProductTagCondition();

		/**
		 * The meta object literal for the '<em><b>Tag</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT_TAG_CONDITION__TAG = eINSTANCE.getProductTagCondition_Tag();

		/**
		 * The meta object literal for the '<em><b>Min Quantity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT_TAG_CONDITION__MIN_QUANTITY = eINSTANCE.getProductTagCondition_MinQuantity();

		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.offers.impl.MinimumOrderAmountConditionImpl <em>Minimum Order Amount Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.offers.impl.MinimumOrderAmountConditionImpl
		 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getMinimumOrderAmountCondition()
		 * @generated
		 */
		EClass MINIMUM_ORDER_AMOUNT_CONDITION = eINSTANCE.getMinimumOrderAmountCondition();

		/**
		 * The meta object literal for the '<em><b>Min Amount</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MINIMUM_ORDER_AMOUNT_CONDITION__MIN_AMOUNT = eINSTANCE.getMinimumOrderAmountCondition_MinAmount();

		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.offers.impl.DateConditionImpl <em>Date Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.offers.impl.DateConditionImpl
		 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getDateCondition()
		 * @generated
		 */
		EClass DATE_CONDITION = eINSTANCE.getDateCondition();

		/**
		 * The meta object literal for the '<em><b>Month</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATE_CONDITION__MONTH = eINSTANCE.getDateCondition_Month();

		/**
		 * The meta object literal for the '<em><b>Day</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATE_CONDITION__DAY = eINSTANCE.getDateCondition_Day();

		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.offers.impl.TagQuantityImpl <em>Tag Quantity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.offers.impl.TagQuantityImpl
		 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getTagQuantity()
		 * @generated
		 */
		EClass TAG_QUANTITY = eINSTANCE.getTagQuantity();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_QUANTITY__NAME = eINSTANCE.getTagQuantity_Name();

		/**
		 * The meta object literal for the '<em><b>Tags</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG_QUANTITY__TAGS = eINSTANCE.getTagQuantity_Tags();

		/**
		 * The meta object literal for the '<em><b>Quantity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_QUANTITY__QUANTITY = eINSTANCE.getTagQuantity_Quantity();

		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.offers.impl.ActionImpl <em>Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.offers.impl.ActionImpl
		 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getAction()
		 * @generated
		 */
		EClass ACTION = eINSTANCE.getAction();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__NAME = eINSTANCE.getAction_Name();

		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.offers.impl.BundlePriceActionImpl <em>Bundle Price Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.offers.impl.BundlePriceActionImpl
		 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getBundlePriceAction()
		 * @generated
		 */
		EClass BUNDLE_PRICE_ACTION = eINSTANCE.getBundlePriceAction();

		/**
		 * The meta object literal for the '<em><b>Items</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BUNDLE_PRICE_ACTION__ITEMS = eINSTANCE.getBundlePriceAction_Items();

		/**
		 * The meta object literal for the '<em><b>Bundle Price</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUNDLE_PRICE_ACTION__BUNDLE_PRICE = eINSTANCE.getBundlePriceAction_BundlePrice();

		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.offers.impl.PercentageDiscountPerProductActionImpl <em>Percentage Discount Per Product Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.offers.impl.PercentageDiscountPerProductActionImpl
		 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getPercentageDiscountPerProductAction()
		 * @generated
		 */
		EClass PERCENTAGE_DISCOUNT_PER_PRODUCT_ACTION = eINSTANCE.getPercentageDiscountPerProductAction();

		/**
		 * The meta object literal for the '<em><b>Product</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERCENTAGE_DISCOUNT_PER_PRODUCT_ACTION__PRODUCT = eINSTANCE.getPercentageDiscountPerProductAction_Product();

		/**
		 * The meta object literal for the '<em><b>Percentage</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERCENTAGE_DISCOUNT_PER_PRODUCT_ACTION__PERCENTAGE = eINSTANCE.getPercentageDiscountPerProductAction_Percentage();

		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.offers.impl.PercentageDiscountOnTaggedQuantityActionImpl <em>Percentage Discount On Tagged Quantity Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.offers.impl.PercentageDiscountOnTaggedQuantityActionImpl
		 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getPercentageDiscountOnTaggedQuantityAction()
		 * @generated
		 */
		EClass PERCENTAGE_DISCOUNT_ON_TAGGED_QUANTITY_ACTION = eINSTANCE.getPercentageDiscountOnTaggedQuantityAction();

		/**
		 * The meta object literal for the '<em><b>Items</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERCENTAGE_DISCOUNT_ON_TAGGED_QUANTITY_ACTION__ITEMS = eINSTANCE.getPercentageDiscountOnTaggedQuantityAction_Items();

		/**
		 * The meta object literal for the '<em><b>Percentage</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERCENTAGE_DISCOUNT_ON_TAGGED_QUANTITY_ACTION__PERCENTAGE = eINSTANCE.getPercentageDiscountOnTaggedQuantityAction_Percentage();

		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.offers.impl.PercentageDiscountTotalActionImpl <em>Percentage Discount Total Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.offers.impl.PercentageDiscountTotalActionImpl
		 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getPercentageDiscountTotalAction()
		 * @generated
		 */
		EClass PERCENTAGE_DISCOUNT_TOTAL_ACTION = eINSTANCE.getPercentageDiscountTotalAction();

		/**
		 * The meta object literal for the '<em><b>Percentage</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERCENTAGE_DISCOUNT_TOTAL_ACTION__PERCENTAGE = eINSTANCE.getPercentageDiscountTotalAction_Percentage();

		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.offers.impl.FixedDiscountActionImpl <em>Fixed Discount Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.offers.impl.FixedDiscountActionImpl
		 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getFixedDiscountAction()
		 * @generated
		 */
		EClass FIXED_DISCOUNT_ACTION = eINSTANCE.getFixedDiscountAction();

		/**
		 * The meta object literal for the '<em><b>Amount</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIXED_DISCOUNT_ACTION__AMOUNT = eINSTANCE.getFixedDiscountAction_Amount();

		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.offers.impl.TriggerImpl <em>Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.offers.impl.TriggerImpl
		 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getTrigger()
		 * @generated
		 */
		EClass TRIGGER = eINSTANCE.getTrigger();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER__TYPE = eINSTANCE.getTrigger_Type();

		/**
		 * The meta object literal for the '<em><b>Triggered Rule</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRIGGER__TRIGGERED_RULE = eINSTANCE.getTrigger_TriggeredRule();

		/**
		 * The meta object literal for the '{@link uk.ac.york.cs.eng2.offers.TriggerType <em>Trigger Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see uk.ac.york.cs.eng2.offers.TriggerType
		 * @see uk.ac.york.cs.eng2.offers.impl.OffersPackageImpl#getTriggerType()
		 * @generated
		 */
		EEnum TRIGGER_TYPE = eINSTANCE.getTriggerType();

	}

} //OffersPackage
