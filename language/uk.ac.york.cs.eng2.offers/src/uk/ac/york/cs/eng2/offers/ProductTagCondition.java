/**
 */
package uk.ac.york.cs.eng2.offers;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Product Tag Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.york.cs.eng2.offers.ProductTagCondition#getTag <em>Tag</em>}</li>
 *   <li>{@link uk.ac.york.cs.eng2.offers.ProductTagCondition#getMinQuantity <em>Min Quantity</em>}</li>
 * </ul>
 *
 * @see uk.ac.york.cs.eng2.offers.OffersPackage#getProductTagCondition()
 * @model
 * @generated
 */
public interface ProductTagCondition extends Condition {
	/**
	 * Returns the value of the '<em><b>Tag</b></em>' reference list.
	 * The list contents are of type {@link uk.ac.york.cs.eng2.offers.Tag}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tag</em>' reference list.
	 * @see uk.ac.york.cs.eng2.offers.OffersPackage#getProductTagCondition_Tag()
	 * @model
	 * @generated
	 */
	EList<Tag> getTag();

	/**
	 * Returns the value of the '<em><b>Min Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min Quantity</em>' attribute.
	 * @see #setMinQuantity(Integer)
	 * @see uk.ac.york.cs.eng2.offers.OffersPackage#getProductTagCondition_MinQuantity()
	 * @model
	 * @generated
	 */
	Integer getMinQuantity();

	/**
	 * Sets the value of the '{@link uk.ac.york.cs.eng2.offers.ProductTagCondition#getMinQuantity <em>Min Quantity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min Quantity</em>' attribute.
	 * @see #getMinQuantity()
	 * @generated
	 */
	void setMinQuantity(Integer value);

} // ProductTagCondition
