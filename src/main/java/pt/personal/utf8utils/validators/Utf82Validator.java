package pt.personal.utf8utils.validators;

import pt.personal.utf8utils.utils.ByteType;

/**
 * {@code Validator} for validating UTF-8-2 bytes.
 * 
 * @author Patrick
 * @see {@link <a href="https://tools.ietf.org/html/rfc3629#section-4">RFC-3629</a>}
 *
 */
public class Utf82Validator extends GenericValidator {

	@Override
	ByteType validates() {
		return ByteType.UTF_8_2;
	}

	@Override
	String getValidationString() {
		return "C2-DF 80-BF";
	}

}
