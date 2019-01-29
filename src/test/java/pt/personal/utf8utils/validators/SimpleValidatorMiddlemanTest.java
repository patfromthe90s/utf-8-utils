package pt.personal.utf8utils.validators;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.personal.utf8utils.utils.ByteType;


public class SimpleValidatorMiddlemanTest {
	
	ValidatorMiddleman simpleValidatorMiddleman;
	
	@Before
	public void setUp() {
		simpleValidatorMiddleman = new SimpleValidatorMiddleman();
	}
	
	@Test
	public void testCorrectValidatorReturned() {
		Assert.assertTrue(simpleValidatorMiddleman.getValidatorFor(ByteType.UTF_8_1) instanceof Utf81Validator);
		Assert.assertTrue(simpleValidatorMiddleman.getValidatorFor(ByteType.UTF_8_2) instanceof Utf82Validator);
		Assert.assertTrue(simpleValidatorMiddleman.getValidatorFor(ByteType.UTF_8_3) instanceof Utf83Validator);
	}

}
