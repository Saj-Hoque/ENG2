/**
 */
package uk.ac.york.cs.eng2.offers.tests;

import junit.textui.TestRunner;

import uk.ac.york.cs.eng2.offers.OffersFactory;
import uk.ac.york.cs.eng2.offers.PercentageDiscountPerProductAction;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Percentage Discount Per Product Action</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class PercentageDiscountPerProductActionTest extends ActionTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(PercentageDiscountPerProductActionTest.class);
	}

	/**
	 * Constructs a new Percentage Discount Per Product Action test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PercentageDiscountPerProductActionTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Percentage Discount Per Product Action test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected PercentageDiscountPerProductAction getFixture() {
		return (PercentageDiscountPerProductAction)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(OffersFactory.eINSTANCE.createPercentageDiscountPerProductAction());
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

} //PercentageDiscountPerProductActionTest
