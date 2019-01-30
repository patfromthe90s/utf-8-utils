package pt.personal.utf8utils.validators;

import org.junit.Assert;
import org.junit.Test;

import pt.personal.utf8utils.utils.Utf8ByteUtils;

public class Utf82ValidatorTest {
	
	Validator validator;
	
	@Test
	public void testUtf82ValidatorIsValid() {
		validator = new Utf82Validator();
		byte[] bytes = new byte[2];
		
		// Lower bound
		bytes[0] = (byte) Utf8ByteUtils.Constants.UTF_8_2_MIN;
		bytes[1] = (byte) Utf8ByteUtils.Constants.UTF_8_TAIL_MIN;
		Assert.assertTrue(validator.isValid(bytes));
		
		// Upper bound
		bytes[0] = (byte) Utf8ByteUtils.Constants.UTF_8_2_MAX;
		bytes[1] = (byte) Utf8ByteUtils.Constants.UTF_8_TAIL_MAX;
		Assert.assertTrue(validator.isValid(bytes));
		
		// Only first byte out of bounds
		bytes[0] = (byte) Utf8ByteUtils.Constants.UTF_8_2_MAX + 1; // Outside validation range, but is a valid byte.
		Assert.assertFalse(validator.isValid(bytes));
		
		// Only second byte out of bounds
		bytes[0] = (byte) Utf8ByteUtils.Constants.UTF_8_2_MAX; // Upper bound
		bytes[1] = (byte) Utf8ByteUtils.Constants.UTF_8_TAIL_MAX + 1; // Outside validation range, but is a valid byte.
		Assert.assertFalse(validator.isValid(bytes));
		
		Assert.assertFalse(validator.isValid(new byte[3])); // Array length incorrect
	}

}
