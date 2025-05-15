/**
 */
package uk.ac.york.cs.eng2.offers;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Percentage Discount On Tagged Quantity Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.york.cs.eng2.offers.PercentageDiscountOnTaggedQuantityAction#getItems <em>Items</em>}</li>
 *   <li>{@link uk.ac.york.cs.eng2.offers.PercentageDiscountOnTaggedQuantityAction#getPercentage <em>Percentage</em>}</li>
 * </ul>
 *
 * @see uk.ac.york.cs.eng2.offers.OffersPackage#getPercentageDiscountOnTaggedQuantityAction()
 * @model
 * @generated
 */
public interface PercentageDiscountOnTaggedQuantityAction extends Action {
	/**
	 * Returns the value of the '<em><b>Items</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Items</em>' containment reference.
	 * @see #setItems(TagQuantity)
	 * @see uk.ac.york.cs.eng2.offers.OffersPackage#getPercentageDiscountOnTaggedQuantityAction_Items()
	 * @model containment="true"
	 * @generated
	 */
	TagQuantity getItems();

	/**
	 * Sets the value of the '{@link uk.ac.york.cs.eng2.offers.PercentageDiscountOnTaggedQuantityAction#getItems <em>Items</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Items</em>' containment reference.
	 * @see #getItems()
	 * @generated
	 */
	void setItems(TagQuantity value);

	/**
	 * Returns the value of the '<em><b>Percentage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Percentage</em>' attribute.
	 * @see #setPercentage(Float)
	 * @see uk.ac.york.cs.eng2.offers.OffersPackage#getPercentageDiscountOnTaggedQuantityAction_Percentage()
	 * @model
	 * @generated
	 */
	Float getPercentage();

	/**
	 * Sets the value of the '{@link uk.ac.york.cs.eng2.offers.PercentageDiscountOnTaggedQuantityAction#getPercentage <em>Percentage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Percentage</em>' attribute.
	 * @see #getPercentage()
	 * @generated
	 */
	void setPercentage(Float value);

} // PercentageDiscountOnTaggedQuantityAction
