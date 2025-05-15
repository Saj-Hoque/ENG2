/**
 */
package uk.ac.york.cs.eng2.offers;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Offers</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.york.cs.eng2.offers.Offers#getCatalog <em>Catalog</em>}</li>
 *   <li>{@link uk.ac.york.cs.eng2.offers.Offers#getRules <em>Rules</em>}</li>
 * </ul>
 *
 * @see uk.ac.york.cs.eng2.offers.OffersPackage#getOffers()
 * @model
 * @generated
 */
public interface Offers extends EObject {
	/**
	 * Returns the value of the '<em><b>Catalog</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Catalog</em>' containment reference.
	 * @see #setCatalog(Catalog)
	 * @see uk.ac.york.cs.eng2.offers.OffersPackage#getOffers_Catalog()
	 * @model containment="true"
	 * @generated
	 */
	Catalog getCatalog();

	/**
	 * Sets the value of the '{@link uk.ac.york.cs.eng2.offers.Offers#getCatalog <em>Catalog</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Catalog</em>' containment reference.
	 * @see #getCatalog()
	 * @generated
	 */
	void setCatalog(Catalog value);

	/**
	 * Returns the value of the '<em><b>Rules</b></em>' containment reference list.
	 * The list contents are of type {@link uk.ac.york.cs.eng2.offers.Rule}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rules</em>' containment reference list.
	 * @see uk.ac.york.cs.eng2.offers.OffersPackage#getOffers_Rules()
	 * @model containment="true"
	 * @generated
	 */
	EList<Rule> getRules();

} // Offers
