/**
 */
package uk.ac.york.cs.eng2.offers;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see uk.ac.york.cs.eng2.offers.OffersPackage
 * @generated
 */
public interface OffersFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OffersFactory eINSTANCE = uk.ac.york.cs.eng2.offers.impl.OffersFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Offers</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Offers</em>'.
	 * @generated
	 */
	Offers createOffers();

	/**
	 * Returns a new object of class '<em>Catalog</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Catalog</em>'.
	 * @generated
	 */
	Catalog createCatalog();

	/**
	 * Returns a new object of class '<em>Product</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Product</em>'.
	 * @generated
	 */
	Product createProduct();

	/**
	 * Returns a new object of class '<em>Category</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Category</em>'.
	 * @generated
	 */
	Category createCategory();

	/**
	 * Returns a new object of class '<em>Tag</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tag</em>'.
	 * @generated
	 */
	Tag createTag();

	/**
	 * Returns a new object of class '<em>Rule</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rule</em>'.
	 * @generated
	 */
	Rule createRule();

	/**
	 * Returns a new object of class '<em>Product Order Quantity Condition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Product Order Quantity Condition</em>'.
	 * @generated
	 */
	ProductOrderQuantityCondition createProductOrderQuantityCondition();

	/**
	 * Returns a new object of class '<em>Product Tag Condition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Product Tag Condition</em>'.
	 * @generated
	 */
	ProductTagCondition createProductTagCondition();

	/**
	 * Returns a new object of class '<em>Minimum Order Amount Condition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Minimum Order Amount Condition</em>'.
	 * @generated
	 */
	MinimumOrderAmountCondition createMinimumOrderAmountCondition();

	/**
	 * Returns a new object of class '<em>Date Condition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Date Condition</em>'.
	 * @generated
	 */
	DateCondition createDateCondition();

	/**
	 * Returns a new object of class '<em>Tag Quantity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tag Quantity</em>'.
	 * @generated
	 */
	TagQuantity createTagQuantity();

	/**
	 * Returns a new object of class '<em>Bundle Price Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Bundle Price Action</em>'.
	 * @generated
	 */
	BundlePriceAction createBundlePriceAction();

	/**
	 * Returns a new object of class '<em>Percentage Discount Per Product Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Percentage Discount Per Product Action</em>'.
	 * @generated
	 */
	PercentageDiscountPerProductAction createPercentageDiscountPerProductAction();

	/**
	 * Returns a new object of class '<em>Percentage Discount On Tagged Quantity Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Percentage Discount On Tagged Quantity Action</em>'.
	 * @generated
	 */
	PercentageDiscountOnTaggedQuantityAction createPercentageDiscountOnTaggedQuantityAction();

	/**
	 * Returns a new object of class '<em>Percentage Discount Total Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Percentage Discount Total Action</em>'.
	 * @generated
	 */
	PercentageDiscountTotalAction createPercentageDiscountTotalAction();

	/**
	 * Returns a new object of class '<em>Fixed Discount Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Fixed Discount Action</em>'.
	 * @generated
	 */
	FixedDiscountAction createFixedDiscountAction();

	/**
	 * Returns a new object of class '<em>Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Trigger</em>'.
	 * @generated
	 */
	Trigger createTrigger();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	OffersPackage getOffersPackage();

} //OffersFactory
