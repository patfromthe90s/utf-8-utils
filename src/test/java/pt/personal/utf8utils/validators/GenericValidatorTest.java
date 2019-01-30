package pt.personal.utf8utils.validators;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.personal.utf8utils.utils.ByteType;

public class GenericValidatorTest {
	
	private ByteType mockByteType = ByteType.UTF_8_2;
	private ByteType notMockByteType = ByteType.UTF_8_1;
	
	private class MockValidator extends GenericValidator {

		@Override
		ByteType validates() {
			return mockByteType;
		}

		@Override
		String getValidationString() {
			return "00 00"; // Used for checking length validation
		}
		
	}
	
	private Validator mockValidator;
	
	@Before
	public void setUp() {
		mockValidator = new MockValidator();
	}

	
	@Test
	public void testValidatesByteType() {
		Assert.assertTrue(mockValidator.validates(mockByteType));
		Assert.assertFalse(mockValidator.validates(notMockByteType));
	}
	
	@Test
	public void testByteLengthValidated() {
		byte[] tooShort = new byte[] {0};
		byte[] tooLong = new byte[] {0, 0, 0};
		byte[] justRight = new byte[] {0, 0};
		Assert.assertFalse(mockValidator.isValid(tooShort));
		Assert.assertFalse(mockValidator.isValid(tooLong));
		Assert.assertTrue(mockValidator.isValid(justRight));
	}

}
