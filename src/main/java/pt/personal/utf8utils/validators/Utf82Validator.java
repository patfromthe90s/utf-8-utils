package pt.personal.utf8utils.validators;

import pt.personal.utf8utils.utils.ByteType;
import pt.personal.utf8utils.utils.Utf8ByteUtils;

/**
 * {@code Validator} for validating UTF-8-2 bytes.
 * 
 * @author Patrick
 * @see {@link <a href="https://tools.ietf.org/html/rfc3629#section-4">RFC-3629</a>}
 *
 */
public class Utf82Validator extends GenericValidator {

	@Override
	public boolean isValid(byte[] bytes) {
		final int MIN = Utf8ByteUtils.Constants.UTF_8_2_MIN;
		final int MAX = Utf8ByteUtils.Constants.UTF_8_2_MAX;
		if (bytes.length == 2) {
			if ( (Utf8ByteUtils.isWithinRange(bytes[0], MIN, MAX))
				&&	(Utf8ByteUtils.isValidTailByte(bytes[1]))) {
					return true;
				}
		}
		
		return false;
	}

	@Override
	ByteType validates() {
		return ByteType.UTF_8_2;
	}

}
