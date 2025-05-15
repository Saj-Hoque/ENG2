/**
 */
package uk.ac.york.cs.eng2.offers.tests;

import junit.textui.TestRunner;

import uk.ac.york.cs.eng2.offers.OffersFactory;
import uk.ac.york.cs.eng2.offers.ProductOrderQuantityCondition;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Product Order Quantity Condition</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ProductOrderQuantityConditionTest extends ConditionTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ProductOrderQuantityConditionTest.class);
	}

	/**
	 * Constructs a new Product Order Quantity Condition test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProductOrderQuantityConditionTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Product Order Quantity Condition test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ProductOrderQuantityCondition getFixture() {
		return (ProductOrderQuantityCondition)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(OffersFactory.eINSTANCE.createProductOrderQuantityCondition());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //ProductOrderQuantityConditionTest
