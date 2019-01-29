package pt.personal.utf8utils.validators;

import pt.personal.utf8utils.utils.ByteType;

/**
 * Factory class that retrieves appropriate {@link Validator}s according to {@link ByteType}
 * 
 * @author Patrick
 *
 */
public interface ValidatorMiddleman {
	
	/**
	 * 
	 * @param byteType The {@code ByteType} you want to validate.
	 * @return A {@link Validator} appropriate for the given {@code ByteType}.
	 */
	public Validator getValidatorFor(ByteType byteType);

}
