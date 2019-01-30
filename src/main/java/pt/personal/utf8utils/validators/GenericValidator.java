package pt.personal.utf8utils.validators;

import pt.personal.utf8utils.utils.ByteType;
import pt.personal.utf8utils.utils.Utf8ByteUtils;

public abstract class GenericValidator implements Validator {

	@Override
	public boolean validates(ByteType byteType) {
		if (validates().equals(byteType)) 
			return true;
		
		return false;
	}
	
	private boolean isValid(byte[] bytes, String[] possibleSyntax) {
		int expectedByteLength = possibleSyntax.length;
		
		boolean valid = bytes.length == expectedByteLength ? true : false;
		if (valid) {
			for (int i = 0; i < bytes.length; i++) {
				String s = possibleSyntax[i];
				if (s.contains("-")) {
					String[] minMax = s.split("-");
					int min = Integer.parseInt(minMax[0], 16);
					int max = Integer.parseInt(minMax[1], 16);
					if (!(Utf8ByteUtils.isWithinRange(bytes[i], min, max))) {
						valid = false;
						break;
					}
				} else {
					int expected = Integer.parseInt(s, 16);
					if (!(Utf8ByteUtils.asUnsingned(bytes[i]) == expected)) {
						valid = false;
						break;
					}
				}
			}
			
		}
		
		return valid;
	}
	

	@Override
	public boolean isValid(byte[] bytes) {
		String[] syntaxes = getValidationString().split("/");
		
		for (String syntax : syntaxes) {
			syntax = syntax.strip();
			String[] syntaxOption = syntax.split("\\s");
			if (isValid(bytes, syntaxOption)) {
				return true;
			}
		}

		
		return false;
	}
	
	/**
	 * @return The {@link ByteType} this {@code Validator} validates.
	 */
	abstract ByteType validates();
	
	abstract String getValidationString();

}
