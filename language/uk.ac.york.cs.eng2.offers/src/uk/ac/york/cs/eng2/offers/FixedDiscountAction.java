/**
 */
package uk.ac.york.cs.eng2.offers;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fixed Discount Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.york.cs.eng2.offers.FixedDiscountAction#getAmount <em>Amount</em>}</li>
 * </ul>
 *
 * @see uk.ac.york.cs.eng2.offers.OffersPackage#getFixedDiscountAction()
 * @model
 * @generated
 */
public interface FixedDiscountAction extends Action {
	/**
	 * Returns the value of the '<em><b>Amount</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Amount</em>' attribute.
	 * @see #setAmount(Float)
	 * @see uk.ac.york.cs.eng2.offers.OffersPackage#getFixedDiscountAction_Amount()
	 * @model
	 * @generated
	 */
	Float getAmount();

	/**
	 * Sets the value of the '{@link uk.ac.york.cs.eng2.offers.FixedDiscountAction#getAmount <em>Amount</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Amount</em>' attribute.
	 * @see #getAmount()
	 * @generated
	 */
	void setAmount(Float value);

} // FixedDiscountAction
