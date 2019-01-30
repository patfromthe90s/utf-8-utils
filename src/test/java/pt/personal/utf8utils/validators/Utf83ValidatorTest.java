package pt.personal.utf8utils.validators;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Utf83ValidatorTest {
	
	Validator validator;
	
	@Before
	public void setUp() {
		validator = new Utf83Validator();
	}

	// TODO Python script to move comments to upper line
	@Test
	public void testE0Validation() {
		/*
		 * Valid bytes
		 */
		// Lower bound both
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xE0, (byte) 0xA0, (byte) 0x80 }));
		// Lower bound 1, Upper Bound 2
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xE0, (byte) 0xA0, (byte) 0xBF }));
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xE0, (byte) 0xBF, (byte) 0x80 })); // Upper Bound 1, Lower Bound 2
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xE0, (byte) 0xBF, (byte) 0xBF })); // Upper Bound both
		
		/*
		 * Invalid bytes
		 */
		// Lower bounds too small
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xE0 - 1, (byte) 0xA0, (byte) 0x80 }));
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xE0, (byte) 0xA0 - 1, (byte) 0x80 }));
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xE0, (byte) 0xA0, (byte) ((byte) 0x80 - 1) }));
		
		// Upper bounds too big
		// { (byte) 0xE0 + 1, (byte) 0xBF, (byte) 0xBF } is valid as it per E1-EC 80-BF 80-BF syntax 
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xE0, (byte) 0xBF + 1, (byte) 0xBF })); 
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xE0, (byte) 0xBF, (byte) 0xBF + 1}));
	}
	
	@Test
	public void testE1_ECValidation() {
		/*
		 * Valid bytes
		 */
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xE1, (byte) 0x80, (byte) 0x80 })); // Lower bound all
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xE1, (byte) 0x80, (byte) 0xBF })); // Lower bound 0 1, Upper Bound 2
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xE1, (byte) 0xBF, (byte) 0x80 })); // Lower bound 0 2, Upper Bound 1
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xE1, (byte) 0xBF, (byte) 0xBF })); // Lower bound 0, Upper Bound 1 2
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xEC, (byte) 0x80, (byte) 0x80 })); // Upper Bound 0, Lower Bound 1 2
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xEC, (byte) 0xBF, (byte) 0x80 })); // Upper Bound 0 1, Lower Bound 2
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xEC, (byte) 0x80, (byte) 0xBF })); // Upper Bound 0 2, Lower Bound 1
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xEC, (byte) 0xBF, (byte) 0xBF })); // Upper Bound all
		
		/*
		 * Invalid bytes
		 */
		// Lower bounds too small
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xE1, (byte) ((byte) 0x80 - 1), (byte) 0x80 }));
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xE1, (byte) 0x80, (byte) ((byte) 0x80 - 1) }));
		
		// Upper bounds too big
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xEC + 1, (byte) 0xBF, (byte) 0xBF }));
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xEC, (byte) 0xBF + 1, (byte) 0xBF }));
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xEC, (byte) 0xBF, (byte) 0xBF + 1 }));
	}
	
	@Test
	public void testEDValidation() {
		/*
		 * Valid bytes
		 */
		// Lower bound both
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xED, (byte) 0x80, (byte) 0x80 }));
		// Lower bound 1, Upper Bound 2
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xED, (byte) 0x80, (byte) 0xBF }));
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xED, (byte) 0x9F, (byte) 0x80 })); // Upper Bound 1, Lower Bound 2
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xED, (byte) 0x9F, (byte) 0xBF })); // Upper Bound both
		
		/*
		 * Invalid bytes
		 */
		// Lower bounds too small
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xED, (byte) ((byte) 0x80 - 1), (byte) 0x80 }));
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xED, (byte) 0x80, (byte) ((byte) 0x80 - 1)}));
		
		// Upper bounds too big
		// { (byte) 0xED + 1, (byte) 0x9F, (byte) 0xBF } is valid as per EE-EF 80-BF 80-BF syntax.
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xED, (byte) 0x9F + 1, (byte) 0xBF }));
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xED, (byte) 0x9F, (byte) 0xBF + 1}));
		
	}
	
	// Test for invalid bytes
	@Test
	public void testEE_EFValidation() {
		/*
		 * Valid bytes
		 */
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xEE, (byte) 0x80, (byte) 0x80 })); // Lower bound all
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xEE, (byte) 0x80, (byte) 0xBF })); // Lower bound 0 1, Upper Bound 2
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xEE, (byte) 0xBF, (byte) 0x80 })); // Lower bound 0 2, Upper Bound 1
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xEE, (byte) 0xBF, (byte) 0xBF })); // Lower bound 0, Upper Bound 1 2
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xEF, (byte) 0x80, (byte) 0x80 })); // Upper Bound 0, Lower Bound 1 2
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xEF, (byte) 0xBF, (byte) 0x80 })); // Upper Bound 0 1, Lower Bound 2
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xEF, (byte) 0x80, (byte) 0xBF })); // Upper Bound 0 2, Lower Bound 1
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xEF, (byte) 0xBF, (byte) 0xBF })); // Upper Bound all
		
		/*
		 * Invalid bytes
		 */
		// Lower bounds too small
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xEE, (byte) ((byte) 0x80 - 1), (byte) 0x80 }));
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xEE, (byte) 0x80, (byte) ((byte) 0x80 - 1)}));
		
		// Upper bounds too big
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xEF + 1, (byte) 0xBF, (byte) 0xBF }));
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xEF, (byte) 0xBF + 1, (byte) 0xBF }));
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xEF, (byte) 0xBF, (byte) 0xBF + 1}));
	}

}
