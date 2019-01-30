package pt.personal.utf8utils.validators;

import org.junit.Assert;
import org.junit.Test;

public class Utf81ValidatorTest {
	
	Validator validator;
	
	@Test
	public void testUtf81ValidatorIsValid() {
		validator = new Utf81Validator();
		byte[] bytes = new byte[1];
		bytes[0] = 0x00; // Lower bound
		Assert.assertTrue(validator.isValid(bytes));
		
		bytes[0] = 0x7F; // Upper bound
		Assert.assertTrue(validator.isValid(bytes));
		
		bytes[0] = 0x65; // in-between lower and upper bound
		Assert.assertTrue(validator.isValid(bytes));
		
		bytes[0] = -0x01; // Outside validation range, but is a valid byte.
		Assert.assertFalse(validator.isValid(bytes));
		
		Assert.assertFalse(validator.isValid(new byte[2])); // Array length incorrect
	}
}
