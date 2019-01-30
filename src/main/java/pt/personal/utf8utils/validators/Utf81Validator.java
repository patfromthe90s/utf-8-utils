package pt.personal.utf8utils.validators;

import pt.personal.utf8utils.utils.ByteType;

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
	public ByteType validates() {
		return ByteType.UTF_8_1;
	}
	
	@Override
	public String getValidationString() {
		return "00-7F";
	}
}
