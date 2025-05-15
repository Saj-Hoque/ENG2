/**
 */
package uk.ac.york.cs.eng2.offers.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import uk.ac.york.cs.eng2.offers.OffersPackage;
import uk.ac.york.cs.eng2.offers.Product;
import uk.ac.york.cs.eng2.offers.ProductOrderQuantityCondition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Product Order Quantity Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.york.cs.eng2.offers.impl.ProductOrderQuantityConditionImpl#getProduct <em>Product</em>}</li>
 *   <li>{@link uk.ac.york.cs.eng2.offers.impl.ProductOrderQuantityConditionImpl#getDailyOrders <em>Daily Orders</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProductOrderQuantityConditionImpl extends ConditionImpl implements ProductOrderQuantityCondition {
	/**
	 * The cached value of the '{@link #getProduct() <em>Product</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProduct()
	 * @generated
	 * @ordered
	 */
	protected Product product;

	/**
	 * The default value of the '{@link #getDailyOrders() <em>Daily Orders</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDailyOrders()
	 * @generated
	 * @ordered
	 */
	protected static final Integer DAILY_ORDERS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDailyOrders() <em>Daily Orders</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDailyOrders()
	 * @generated
	 * @ordered
	 */
	protected Integer dailyOrders = DAILY_ORDERS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProductOrderQuantityConditionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OffersPackage.Literals.PRODUCT_ORDER_QUANTITY_CONDITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Product getProduct() {
		if (product != null && product.eIsProxy()) {
			InternalEObject oldProduct = (InternalEObject)product;
			product = (Product)eResolveProxy(oldProduct);
			if (product != oldProduct) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OffersPackage.PRODUCT_ORDER_QUANTITY_CONDITION__PRODUCT, oldProduct, product));
			}
		}
		return product;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Product basicGetProduct() {
		return product;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProduct(Product newProduct) {
		Product oldProduct = product;
		product = newProduct;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OffersPackage.PRODUCT_ORDER_QUANTITY_CONDITION__PRODUCT, oldProduct, product));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Integer getDailyOrders() {
		return dailyOrders;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDailyOrders(Integer newDailyOrders) {
		Integer oldDailyOrders = dailyOrders;
		dailyOrders = newDailyOrders;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OffersPackage.PRODUCT_ORDER_QUANTITY_CONDITION__DAILY_ORDERS, oldDailyOrders, dailyOrders));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OffersPackage.PRODUCT_ORDER_QUANTITY_CONDITION__PRODUCT:
				if (resolve) return getProduct();
				return basicGetProduct();
			case OffersPackage.PRODUCT_ORDER_QUANTITY_CONDITION__DAILY_ORDERS:
				return getDailyOrders();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case OffersPackage.PRODUCT_ORDER_QUANTITY_CONDITION__PRODUCT:
				setProduct((Product)newValue);
				return;
			case OffersPackage.PRODUCT_ORDER_QUANTITY_CONDITION__DAILY_ORDERS:
				setDailyOrders((Integer)newValue);
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
			case OffersPackage.PRODUCT_ORDER_QUANTITY_CONDITION__PRODUCT:
				setProduct((Product)null);
				return;
			case OffersPackage.PRODUCT_ORDER_QUANTITY_CONDITION__DAILY_ORDERS:
				setDailyOrders(DAILY_ORDERS_EDEFAULT);
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
			case OffersPackage.PRODUCT_ORDER_QUANTITY_CONDITION__PRODUCT:
				return product != null;
			case OffersPackage.PRODUCT_ORDER_QUANTITY_CONDITION__DAILY_ORDERS:
				return DAILY_ORDERS_EDEFAULT == null ? dailyOrders != null : !DAILY_ORDERS_EDEFAULT.equals(dailyOrders);
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
		result.append(" (dailyOrders: ");
		result.append(dailyOrders);
		result.append(')');
		return result.toString();
	}

} //ProductOrderQuantityConditionImpl
