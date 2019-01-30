package pt.personal.utf8utils.utils;

/**
 * Utility class relating to UTF-8 bytes.
 * 
 * @author patrick
 *
 */
public class Utf8ByteUtils {
			
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
	 * <p>Checks whether the given {@code byte} is within the requested range 
	 * {@code min}-{@code max}.</p>
	 * 
	 * @param b The {@code byte} to check.
	 * @param min The minimum allowed value (inclusive) of {code b}.
	 * @param max The maximum allowed value (inclusive) of {code b}.
	 * @return {@code True} if {code b}s value is within allowed range, {@code False} otherwise.
	 */
	public static boolean isWithinRange(byte b, int min, int max) {
		int i = asUnsingned(b);
		return (i >= min && i <= max);
	}
	
	/**
	 * <p>Treat a {@code byte} as if it was unsigned</p>
	 * 
	 * <p>Java treats {@code byte}s as signed integers, which complicates working with
	 * hex values (e.g. {@code byte b = 0xFF} is not a valid byte in Java).</p>
	 * 
	 * @param b The signed {@code byte} to translate
	 * @return an {@code int} that represents the unsigned {@code byte}.
	 */
	public static int asUnsingned(byte b) {
		return b & 0xFF;
	}

}
