/**
 */
package uk.ac.york.cs.eng2.offers;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Product Order Quantity Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.york.cs.eng2.offers.ProductOrderQuantityCondition#getProduct <em>Product</em>}</li>
 *   <li>{@link uk.ac.york.cs.eng2.offers.ProductOrderQuantityCondition#getDailyOrders <em>Daily Orders</em>}</li>
 * </ul>
 *
 * @see uk.ac.york.cs.eng2.offers.OffersPackage#getProductOrderQuantityCondition()
 * @model
 * @generated
 */
public interface ProductOrderQuantityCondition extends Condition {
	/**
	 * Returns the value of the '<em><b>Product</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Product</em>' reference.
	 * @see #setProduct(Product)
	 * @see uk.ac.york.cs.eng2.offers.OffersPackage#getProductOrderQuantityCondition_Product()
	 * @model
	 * @generated
	 */
	Product getProduct();

	/**
	 * Sets the value of the '{@link uk.ac.york.cs.eng2.offers.ProductOrderQuantityCondition#getProduct <em>Product</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Product</em>' reference.
	 * @see #getProduct()
	 * @generated
	 */
	void setProduct(Product value);

	/**
	 * Returns the value of the '<em><b>Daily Orders</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Daily Orders</em>' attribute.
	 * @see #setDailyOrders(Integer)
	 * @see uk.ac.york.cs.eng2.offers.OffersPackage#getProductOrderQuantityCondition_DailyOrders()
	 * @model
	 * @generated
	 */
	Integer getDailyOrders();

	/**
	 * Sets the value of the '{@link uk.ac.york.cs.eng2.offers.ProductOrderQuantityCondition#getDailyOrders <em>Daily Orders</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Daily Orders</em>' attribute.
	 * @see #getDailyOrders()
	 * @generated
	 */
	void setDailyOrders(Integer value);

} // ProductOrderQuantityCondition
