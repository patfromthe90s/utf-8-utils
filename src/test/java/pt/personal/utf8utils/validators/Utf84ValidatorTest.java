package pt.personal.utf8utils.validators;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Utf84ValidatorTest {

	Validator validator;
	
	@Before
	public void setUp() {
		validator = new Utf84Validator();
	}
	
	@Test
	public void testF0Validation() {

		// Valid
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xF0, (byte) 0x90, (byte) 0x80, (byte) 0x80 }));
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xF0, (byte) 0xBF, (byte) 0xBF, (byte) 0xBF }));
		
		// Invalid
		// Lower bounds too small
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xF0 - 1, (byte) 0x80, (byte) 0x80, (byte) 0x80 })); 
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xF0, (byte) ((byte) 0x80 - 1), (byte) 0x80, (byte) 0x80 })); 
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xF0, (byte) 0x80, (byte) ((byte) 0x80 - 1), (byte) 0x80 })); 
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xF0, (byte) 0x80, (byte) 0x80, (byte) ((byte) 0x80 - 1) })); 
		
		// Upper bounds too big
		// {(byte) 0xF0 + 1, (byte) 0xBF, (byte) 0xBF, (byte) 0xBF } is valid as per F1-F3 80-BF 80-BF 80-BF syntax
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xF0, (byte) 0xBF + 1, (byte) 0xBF, (byte) 0xBF }));
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xF0, (byte) 0xBF, (byte) 0xBF + 1, (byte) 0xBF }));
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xF0, (byte) 0xBF, (byte) 0xBF, (byte) 0xBF + 1 }));
		
	}
	
	@Test
	public void testF1_F3Validation() {
		// Valid
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xF1, (byte) 0x80, (byte) 0x80, (byte) 0x80 }));
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xF3, (byte) 0xBF, (byte) 0xBF, (byte) 0xBF }));
		
		// Invalid
		// Lower bounds too small
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xF1 - 1, (byte) 0x80, (byte) 0x80, (byte) 0x80 }));
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xF1, (byte) ((byte) 0x80 - 1), (byte) 0x80, (byte) 0x80 }));
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xF1, (byte) 0x80, (byte) ((byte) 0x80 - 1), (byte) 0x80 }));
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xF1, (byte) 0x80, (byte) 0x80, (byte) ((byte) 0x80 - 1) }));
		
		// Upper bounds too big
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xF3 + 1, (byte) 0xBF, (byte) 0xBF, (byte) 0xBF }));
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xF3, (byte) 0xBF + 1, (byte) 0xBF, (byte) 0xBF }));
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xF3, (byte) 0xBF, (byte) 0xBF + 1, (byte) 0xBF }));
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xF3, (byte) 0xBF, (byte) 0xBF, (byte) 0xBF + 1}));
	}
	
	@Test
	public void testF4Validation() {
		// Valid
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xF4, (byte) 0x80, (byte) 0x80, (byte) 0x80 }));
		Assert.assertTrue(validator.isValid(new byte[] { (byte) 0xF4, (byte) 0x8F, (byte) 0xBF, (byte) 0xBF }));
		
		// Invalid
		// Lower bounds too small
		// { (byte) 0xF4 - 1, (byte) 0x80, (byte) 0x80, (byte) 0x80 } is valid as per F1-F3 80-BF 80-BF 80-BF syntax
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xF4, (byte) ((byte) 0x80 - 1), (byte) 0x80, (byte) 0x80 }));
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xF4, (byte) 0x80, (byte) ((byte) 0x80 - 1), (byte) 0x80 }));
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xF4, (byte) 0x80, (byte) 0x80, (byte) ((byte) 0x80 - 1) }));
		
		// Upper bounds too big
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xF4 + 1, (byte) 0x8F, (byte) 0xBF, (byte) 0xBF }));
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xF4, (byte) 0x8F + 1, (byte) 0xBF, (byte) 0xBF }));
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xF4, (byte) 0x8F, (byte) 0xBF + 1, (byte) 0xBF }));
		Assert.assertFalse(validator.isValid(new byte[] { (byte) 0xF4, (byte) 0x8F, (byte) 0xBF, (byte) 0xBF + 1 }));
	}
	
}
