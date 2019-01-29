package pt.personal.utf8utils.utils;

/**
 * Utility class relating to UTF-8 bytes.
 * 
 * @author patrick
 *
 */
public class Utf8ByteUtils {
	
	/**
	 * Helper class to provide some useful constant values as specified in Section 4 of {@code RFC-3629}.
	 * Some values (e.g. the lower bound of first byte {@code UTF8-3}) are not constant and cannot be 
	 * represented here.
	 *  
	 * @author patrick
	 * @see {@link <a href="https://tools.ietf.org/html/rfc3629#section-4">RFC-3629</a>}
	 *
	 */
	public class Constants {
		public static final int UTF_8_TAIL_MIN = 0x80;
		public static final int UTF_8_TAIL_MAX = 0x80;
		public static final int UTF_8_1_MIN = 0x00;
		public static final int UTF_8_1_MAX = 0x7F;
		public static final int UTF_8_2_MIN = 0xC2;
		public static final int UTF_8_2_MAX = 0xDF;
	}

		
	/**
	 * Assumes the given byte corresponds to a UTF-8 byte.
	 * 
	 * @param b - UTF-8 compliant byte
	 * @return corresponding to the type of UTF-8 byte provided.
	 */
	public static ByteType getByteType(byte b) {
		if (shift(b, 7) == 0) {
			// start of byte is 0xxx
			return ByteType.UTF_8_1;
		}
		else if (shift(b, 6) == 3) {
			// start of byte is 11xx (UTF-8 start byte)
			if ((shift(b, 3)) == 0x1E) {
				return ByteType.UTF_8_4;
			} else if ((shift(b, 4)) == 0x0E) { 
				return ByteType.UTF_8_3; 
			} else if ((shift(b, 5)) == 0x06) {
				return ByteType.UTF_8_2;
			}
		} else if ((shift(b, 6)) == 0x02) {
			// start of byte is 10xx
			return ByteType.UTF_8_TAIL;
		}
		
		return ByteType.UNKNOWN;
	}
		
	/**
	 * Perform an unsigned right-shift of the given byte 
	 * @param original The byte to shift
	 * @param numShifts The amount to right-shift the byte
	 * @return The byte value of {@code original} shifted {@code numShifts} times 
	 * times.
	 */
	public static byte shift(byte original, int numShifts) {
		// byte & 0xFF accounts for byte being promoted to int by java
		return (byte)((original & 0xFF) >>> numShifts);
	}
	
	/**
	 * <p>Checks whether the given {@code byte} is within the requested range after applying a 
	 * mask to remove bits used by UTF-8 as syntax.</p>
	 * 
	 * <p>Example:<br/>
	 * Use a mask of {@code 0011 1111} to check that a UTF-8-tail byte is within range.
	 * A UTF-8-tail byte is prefixed by a {@code 10} (i.e. the byte has a value of {@code 10xx xxxx}),
	 * so the {@code mask} removes the prefix bits, allowing the true value to be checked.</p>
	 * 
	 * @param b The {@code byte} to check.
	 * @param mask A {@code byte} mask to remove UTF-8 syntax. (E.g. 0011 1111)
	 * @param min The minimum allowed value (inclusive) of {code b}.
	 * @param max The maximum allowed value (inclusive) of {code b}.
	 * @return {@code True} if {code b}s true value (after applying {@code mask}) is within allowed range,
	 * {@code False} otherwise.
	 * @see {@link <a href="https://tools.ietf.org/html/rfc3629#section-4">RFC-3629</a>}
	 * 
	 */
	public static boolean isWithinRange(byte b, byte mask, int min, int max) {
		// TODO Maybe delete this method?
		b &= 0xFF;
		if (b >= min && b <= max) 
			return true;
		
		return false;
	}
	
	public static boolean isWithinRange(byte b, int min, int max) {
		int i = asUnsingned(b);
		return (i >= min && i <= max);
	}
	
	public static int asUnsingned(byte b) {
		return b & 0xFF;
	}

	public static boolean isValidTailByte(byte b) {
		if (getByteType(b).equals(ByteType.UTF_8_TAIL))
			return isWithinRange(b, Constants.UTF_8_TAIL_MIN, Constants.UTF_8_TAIL_MAX);
			
		return false;
	}

}
