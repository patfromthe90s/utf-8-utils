package pt.personal.utf8utils.validators;

import pt.personal.utf8utils.exceptions.NoValidatorException;
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
	 * @throws NoValidatorException if there is no {@code Validator} for 
	 * the requested {@code ByteType}
	 */
	public Validator getValidatorFor(ByteType byteType) throws NoValidatorException;

}
