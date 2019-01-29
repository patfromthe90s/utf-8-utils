package pt.personal.utf8utils.validators;

import pt.personal.utf8utils.utils.ByteType;

public abstract class GenericValidator implements Validator {

	@Override
	public boolean validates(ByteType byteType) {
		if (validates().equals(byteType)) 
			return true;
		
		return false;
	}
	
	/**
	 * @return The {@link ByteType} this {@code Validator} validates.
	 */
	abstract ByteType validates();

}
