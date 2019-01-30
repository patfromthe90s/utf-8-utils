package pt.personal.utf8utils.validators;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Utf82ValidatorTest {
	
	private static final byte UTF_8_2_MIN = (byte) 0xC2;
	private static final byte UTF_8_2_MAX = (byte) 0xDF;
	public static final byte UTF_8_TAIL_MIN = (byte) 0x80;
	public static final byte UTF_8_TAIL_MAX = (byte) 0xBF;
	Validator validator;
	
	@Before
	public void setUp() {
		validator = new Utf82Validator();
	}
	
	@Test
	public void testUtf82ValidatorIsValid() {
		// Valid
		Assert.assertTrue(validator.isValid(new byte[] {UTF_8_2_MIN, UTF_8_TAIL_MIN })); // Lower bound
		Assert.assertTrue(validator.isValid(new byte[] {UTF_8_2_MAX, UTF_8_TAIL_MAX })); // Upper bound

		// Invalid
		Assert.assertFalse(validator.isValid(new byte[] {(byte) (0xDF + 1), UTF_8_TAIL_MAX }));
		Assert.assertFalse(validator.isValid(new byte[] {UTF_8_2_MAX, (byte) (0xBF + 1) })); 
	}

}
