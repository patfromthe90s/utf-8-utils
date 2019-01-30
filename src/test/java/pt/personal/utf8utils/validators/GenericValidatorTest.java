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
			return null;
		}
		
	}
	
	private Validator mockValidator;
	
	@Before
	public void setUp() {
		mockValidator = new MockValidator();
	}

	
	@Test
	public void testValidates() {
		Assert.assertTrue(mockValidator.validates(mockByteType));
		Assert.assertFalse(mockValidator.validates(notMockByteType));
	}

}
