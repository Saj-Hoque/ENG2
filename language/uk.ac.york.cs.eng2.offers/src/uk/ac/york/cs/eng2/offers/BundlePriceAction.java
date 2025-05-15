/**
 */
package uk.ac.york.cs.eng2.offers;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bundle Price Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.york.cs.eng2.offers.BundlePriceAction#getItems <em>Items</em>}</li>
 *   <li>{@link uk.ac.york.cs.eng2.offers.BundlePriceAction#getBundlePrice <em>Bundle Price</em>}</li>
 * </ul>
 *
 * @see uk.ac.york.cs.eng2.offers.OffersPackage#getBundlePriceAction()
 * @model
 * @generated
 */
public interface BundlePriceAction extends Action {
	/**
	 * Returns the value of the '<em><b>Items</b></em>' containment reference list.
	 * The list contents are of type {@link uk.ac.york.cs.eng2.offers.TagQuantity}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Items</em>' containment reference list.
	 * @see uk.ac.york.cs.eng2.offers.OffersPackage#getBundlePriceAction_Items()
	 * @model containment="true"
	 * @generated
	 */
	EList<TagQuantity> getItems();

	/**
	 * Returns the value of the '<em><b>Bundle Price</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bundle Price</em>' attribute.
	 * @see #setBundlePrice(Float)
	 * @see uk.ac.york.cs.eng2.offers.OffersPackage#getBundlePriceAction_BundlePrice()
	 * @model
	 * @generated
	 */
	Float getBundlePrice();

	/**
	 * Sets the value of the '{@link uk.ac.york.cs.eng2.offers.BundlePriceAction#getBundlePrice <em>Bundle Price</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bundle Price</em>' attribute.
	 * @see #getBundlePrice()
	 * @generated
	 */
	void setBundlePrice(Float value);

} // BundlePriceAction
