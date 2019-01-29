package pt.personal.utf8utils.validators;

import pt.personal.utf8utils.utils.ByteType;
import pt.personal.utf8utils.utils.Utf8ByteUtils;

/**
 * {@code Validator} for validating UTF-8-1 bytes.
 * 
 * @author Patrick
 * @see {@link <a href="https://tools.ietf.org/html/rfc3629#section-4">RFC-3629</a>}
 *
 */
public class Utf81Validator extends GenericValidator {
	
	/**
	 * Package-Protected. Should only be used by Factory.
	 */
	Utf81Validator() {}

	@Override
	public boolean isValid(byte[] bytes) {
		final int MIN = Utf8ByteUtils.Constants.UTF_8_1_MIN;
		final int MAX = Utf8ByteUtils.Constants.UTF_8_1_MAX;
		if (bytes.length == 1 && Utf8ByteUtils.isWithinRange(bytes[0], MIN, MAX))
				return true;
		
		return false;
	}

	@Override
	public ByteType validates() {
		return ByteType.UTF_8_1;
	}
}
