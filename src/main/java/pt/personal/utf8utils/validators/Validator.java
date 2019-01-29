package pt.personal.utf8utils.validators;

import pt.personal.utf8utils.utils.ByteType;

/**
 * <p>A {@code Validator} checks if a given set of {@code byte}s are valid according to
 * the syntax as described in the UTF-8 encoding specification.</p>
 * 
 * <p>Each concrete {@code Validator} should validate the {@code byte}s for a single {@code ByteType}.</p>
 * 
 * @author Patrick
 * @see {@link <a href="https://tools.ietf.org/html/rfc3629#section-4">RFC-3629</a>}
 *
 */
public interface Validator {

	/**
	 * 
	 * @param bytes The UTF-8 Char bytes to validate
	 * @return {@code True} if the bytes are valid, {@code False} otherwise.
	 */
	public boolean isValid(byte[] bytes);

	/**
	 * 
	 * @return {@code True} if this Validator validates the given {@code byteType}, 
	 * {@code False} otherwise.
	 */
	boolean validates(ByteType byteType);

}
