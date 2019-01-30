package pt.personal.utf8utils.chars;

import org.junit.Assert;
import org.junit.Test;

public class SimpleUtf8CharTest {
	
	private Utf8Char utf8Char;
	
	@Test
	public void testGetBytes() {
		byte[] bytes = new byte[] { 0x00, 0x01 };
		utf8Char = new SimpleUtf8Char(bytes);
		Assert.assertEquals(bytes, utf8Char.getBytes());
	}

}
