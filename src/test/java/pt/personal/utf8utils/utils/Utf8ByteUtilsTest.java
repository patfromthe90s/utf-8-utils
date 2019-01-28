package pt.personal.utf8utils.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Utf8ByteUtilsTest {
	
	private final byte BYTE_ASCII = 0x7F; // 0111 1111
	private final byte BYTE_START_2 = (byte) 0xDF; // 1101 1111
	private final byte BYTE_START_3 = (byte) 0xEF; // 1110 1111
	private final byte BYTE_START_4 = (byte) 0XF7; // 1111 0111
	private final byte BYTE_TAIL = (byte) 0xBF; // 1011 1111
	private final byte BYTE_INVALID_UTF = (byte) 0xFF; // 1111 1111
	
	
	@Test
	public void testGetByteType() {
		assertEquals(ByteType.UTF_8_1, Utf8ByteUtils.getByteType(BYTE_ASCII));
		assertEquals(ByteType.UTF_8_2, Utf8ByteUtils.getByteType(BYTE_START_2));
		assertEquals(ByteType.UTF_8_3, Utf8ByteUtils.getByteType(BYTE_START_3));
		assertEquals(ByteType.UTF_8_4, Utf8ByteUtils.getByteType(BYTE_START_4));
		assertEquals(ByteType.UTF_8_TAIL, Utf8ByteUtils.getByteType(BYTE_TAIL));
		assertEquals(ByteType.UNKNOWN, Utf8ByteUtils.getByteType(BYTE_INVALID_UTF));
		
	}
	
	@Test
	public void testShift() {
		byte b = (byte) 0xFF;
		assertEquals(0, Utf8ByteUtils.shift(b, 8));
		assertEquals(0x7F, Utf8ByteUtils.shift(b, 1));
		assertEquals(0, Utf8ByteUtils.shift(b, Integer.MAX_VALUE));
		assertEquals(b, Utf8ByteUtils.shift(b, 0));
	}

}
