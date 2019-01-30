package pt.personal.utf8utils.validators;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Utf81ValidatorTest {
	
	private static final byte UTF_8_1_MIN = 0x00;
	private static final byte UTF_8_1_MAX = 0x7F;
	Validator validator;
	
	@Before
	public void setUp() {
		validator = new Utf81Validator();
	}
	
	@Test
	public void testUtf81ValidatorIsValid() {
		// Valid
		Assert.assertTrue(validator.isValid(new byte[] {UTF_8_1_MIN})); // Lower bound
		Assert.assertTrue(validator.isValid(new byte[] {UTF_8_1_MAX})); // Upper bound
		
		// Invalid
		Assert.assertFalse(validator.isValid(new byte[] {UTF_8_1_MIN - 1})); // Upper bound
		Assert.assertFalse(validator.isValid(new byte[] {(byte) (UTF_8_1_MAX + 1)})); // Upper bound
	}
}
