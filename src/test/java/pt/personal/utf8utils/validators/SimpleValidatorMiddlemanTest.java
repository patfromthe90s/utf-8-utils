package pt.personal.utf8utils.validators;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.personal.utf8utils.exceptions.NoValidatorException;
import pt.personal.utf8utils.utils.ByteType;


public class SimpleValidatorMiddlemanTest {
	
	ValidatorMiddleman simpleValidatorMiddleman;
	
	@Before
	public void setUp() {
		simpleValidatorMiddleman = new SimpleValidatorMiddleman();
	}
	
	@Test
	public void testCorrectValidatorReturned() {
		try {
			Assert.assertTrue(simpleValidatorMiddleman.getValidatorFor(ByteType.UTF_8_1) instanceof Utf81Validator);
			Assert.assertTrue(simpleValidatorMiddleman.getValidatorFor(ByteType.UTF_8_2) instanceof Utf82Validator);
			Assert.assertTrue(simpleValidatorMiddleman.getValidatorFor(ByteType.UTF_8_3) instanceof Utf83Validator);
			Assert.assertTrue(simpleValidatorMiddleman.getValidatorFor(ByteType.UTF_8_4) instanceof Utf84Validator);
		} catch (NoValidatorException e) {
			Assert.fail("Exception was thrown, but wasn't expected");
		}
		
	}
	
	@Test(expected = NoValidatorException.class)
	public void testValidatorThrowsException() throws NoValidatorException {
		simpleValidatorMiddleman.getValidatorFor(ByteType.UNKNOWN);
	}

}
