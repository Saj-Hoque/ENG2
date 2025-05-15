/**
 */
package uk.ac.york.cs.eng2.offers;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Date Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.york.cs.eng2.offers.DateCondition#getMonth <em>Month</em>}</li>
 *   <li>{@link uk.ac.york.cs.eng2.offers.DateCondition#getDay <em>Day</em>}</li>
 * </ul>
 *
 * @see uk.ac.york.cs.eng2.offers.OffersPackage#getDateCondition()
 * @model
 * @generated
 */
public interface DateCondition extends Condition {
	/**
	 * Returns the value of the '<em><b>Month</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Month</em>' attribute.
	 * @see #setMonth(Integer)
	 * @see uk.ac.york.cs.eng2.offers.OffersPackage#getDateCondition_Month()
	 * @model
	 * @generated
	 */
	Integer getMonth();

	/**
	 * Sets the value of the '{@link uk.ac.york.cs.eng2.offers.DateCondition#getMonth <em>Month</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Month</em>' attribute.
	 * @see #getMonth()
	 * @generated
	 */
	void setMonth(Integer value);

	/**
	 * Returns the value of the '<em><b>Day</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Day</em>' attribute.
	 * @see #setDay(Integer)
	 * @see uk.ac.york.cs.eng2.offers.OffersPackage#getDateCondition_Day()
	 * @model
	 * @generated
	 */
	Integer getDay();

	/**
	 * Sets the value of the '{@link uk.ac.york.cs.eng2.offers.DateCondition#getDay <em>Day</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Day</em>' attribute.
	 * @see #getDay()
	 * @generated
	 */
	void setDay(Integer value);

} // DateCondition
