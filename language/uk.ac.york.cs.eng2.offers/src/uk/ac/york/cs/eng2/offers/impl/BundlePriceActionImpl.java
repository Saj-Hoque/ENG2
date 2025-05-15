/**
 */
package uk.ac.york.cs.eng2.offers.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import uk.ac.york.cs.eng2.offers.BundlePriceAction;
import uk.ac.york.cs.eng2.offers.OffersPackage;
import uk.ac.york.cs.eng2.offers.TagQuantity;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Bundle Price Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.york.cs.eng2.offers.impl.BundlePriceActionImpl#getItems <em>Items</em>}</li>
 *   <li>{@link uk.ac.york.cs.eng2.offers.impl.BundlePriceActionImpl#getBundlePrice <em>Bundle Price</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BundlePriceActionImpl extends ActionImpl implements BundlePriceAction {
	/**
	 * The cached value of the '{@link #getItems() <em>Items</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getItems()
	 * @generated
	 * @ordered
	 */
	protected EList<TagQuantity> items;

	/**
	 * The default value of the '{@link #getBundlePrice() <em>Bundle Price</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBundlePrice()
	 * @generated
	 * @ordered
	 */
	protected static final Float BUNDLE_PRICE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBundlePrice() <em>Bundle Price</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBundlePrice()
	 * @generated
	 * @ordered
	 */
	protected Float bundlePrice = BUNDLE_PRICE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BundlePriceActionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OffersPackage.Literals.BUNDLE_PRICE_ACTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<TagQuantity> getItems() {
		if (items == null) {
			items = new EObjectContainmentEList<TagQuantity>(TagQuantity.class, this, OffersPackage.BUNDLE_PRICE_ACTION__ITEMS);
		}
		return items;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getBundlePrice() {
		return bundlePrice;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBundlePrice(Float newBundlePrice) {
		Float oldBundlePrice = bundlePrice;
		bundlePrice = newBundlePrice;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OffersPackage.BUNDLE_PRICE_ACTION__BUNDLE_PRICE, oldBundlePrice, bundlePrice));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OffersPackage.BUNDLE_PRICE_ACTION__ITEMS:
				return ((InternalEList<?>)getItems()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OffersPackage.BUNDLE_PRICE_ACTION__ITEMS:
				return getItems();
			case OffersPackage.BUNDLE_PRICE_ACTION__BUNDLE_PRICE:
				return getBundlePrice();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case OffersPackage.BUNDLE_PRICE_ACTION__ITEMS:
				getItems().clear();
				getItems().addAll((Collection<? extends TagQuantity>)newValue);
				return;
			case OffersPackage.BUNDLE_PRICE_ACTION__BUNDLE_PRICE:
				setBundlePrice((Float)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case OffersPackage.BUNDLE_PRICE_ACTION__ITEMS:
				getItems().clear();
				return;
			case OffersPackage.BUNDLE_PRICE_ACTION__BUNDLE_PRICE:
				setBundlePrice(BUNDLE_PRICE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case OffersPackage.BUNDLE_PRICE_ACTION__ITEMS:
				return items != null && !items.isEmpty();
			case OffersPackage.BUNDLE_PRICE_ACTION__BUNDLE_PRICE:
				return BUNDLE_PRICE_EDEFAULT == null ? bundlePrice != null : !BUNDLE_PRICE_EDEFAULT.equals(bundlePrice);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (bundlePrice: ");
		result.append(bundlePrice);
		result.append(')');
		return result.toString();
	}

} //BundlePriceActionImpl
