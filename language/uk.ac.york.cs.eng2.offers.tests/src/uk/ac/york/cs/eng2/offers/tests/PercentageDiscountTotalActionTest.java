/**
 */
package uk.ac.york.cs.eng2.offers.tests;

import junit.textui.TestRunner;

import uk.ac.york.cs.eng2.offers.OffersFactory;
import uk.ac.york.cs.eng2.offers.PercentageDiscountTotalAction;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Percentage Discount Total Action</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class PercentageDiscountTotalActionTest extends ActionTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(PercentageDiscountTotalActionTest.class);
	}

	/**
	 * Constructs a new Percentage Discount Total Action test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PercentageDiscountTotalActionTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Percentage Discount Total Action test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected PercentageDiscountTotalAction getFixture() {
		return (PercentageDiscountTotalAction)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(OffersFactory.eINSTANCE.createPercentageDiscountTotalAction());
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

} //PercentageDiscountTotalActionTest
