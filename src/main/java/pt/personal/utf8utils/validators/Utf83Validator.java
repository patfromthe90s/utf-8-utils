package pt.personal.utf8utils.validators;

import pt.personal.utf8utils.utils.ByteType;
import pt.personal.utf8utils.utils.Utf8ByteUtils;

public class Utf83Validator extends GenericValidator {
	
	private static final int OPTION_1_VAL = 0xE0;
	private static final int OPTION_2_MIN = 0xE1;
	private static final int OPTION_2_MAX = 0xEC;
	private static final int OPTION_3_VAL = 0xED;
	private static final int OPTION_4_MIN = 0xEE;
	private static final int OPTION_4_MAX = 0xEE;

	@Override
	public boolean isValid(byte[] bytes) {
		/* Delegate depending on the value of the first byte, as this affects the rules for 
		 * remaining bytes. If..Else used as these few rules are set and highly unlikely to change.
		 */
		byte initialByte = bytes[0];
		if (Utf8ByteUtils.isWithinRange(initialByte, OPTION_1_VAL, OPTION_1_VAL)) {
			return isValidOption1(bytes);
		} else if (Utf8ByteUtils.isWithinRange(initialByte, OPTION_2_MIN, OPTION_2_MAX)) {
			return isValidOption2(bytes);
		} else if (Utf8ByteUtils.isWithinRange(initialByte, OPTION_3_VAL, OPTION_3_VAL)) {
			return isValidOption3(bytes);
		} else if (Utf8ByteUtils.isWithinRange(initialByte, OPTION_4_MIN, OPTION_4_MAX)) {
			return isValidOption4(bytes);
		}
			
		return false;
	}
	
	/*
	 *  Option 1 = 0xE0 0xA0-BF 0x80-BF (from RFC-3296).
	 *  Assumes first byte has already been checked.
	 */
	private boolean isValidOption1(byte[] bytes) {
		if (Utf8ByteUtils.isWithinRange(bytes[1], 0xA0, 0xBF)
				&& Utf8ByteUtils.isValidTailByte(bytes[2])) {
			return true;
		}
		
		return false;
	}
	
	/*
	 *  Option 2 = 0xE1-EC 0x80-BF 0x80-BF (from RFC-3296).
	 *  Assumes first byte has already been checked.
	 */
	private boolean isValidOption2(byte[] bytes) {
		if (Utf8ByteUtils.isValidTailByte(bytes[1])
				&& Utf8ByteUtils.isValidTailByte(bytes[2])) {
			return true;
		}
		
		return false;
	}
	
	/*
	 *  Option 3 = 0xED 0x80-9F 0x80-BF (from RFC-3296).
	 *  Assumes first byte has already been checked.
	 */
	private boolean isValidOption3(byte[] bytes) {
		if (Utf8ByteUtils.isWithinRange(bytes[1], 0x80, 0x9F)
				&& Utf8ByteUtils.isValidTailByte(bytes[2])) {
			return true;
		}
		
		return false;
	}
	
	/*
	 *  Option 3 = 0xEE-EF 0x80-BF 0x80-BF (from RFC-3296).
	 *  Assumes first byte has already been checked.
	 */
	private boolean isValidOption4(byte[] bytes) {
		if (Utf8ByteUtils.isValidTailByte(bytes[1])
				&& Utf8ByteUtils.isValidTailByte(bytes[2])) {
			return true;
		}
		
		return false;
	}


	@Override
	ByteType validates() {
		return ByteType.UTF_8_3;
	}

}
