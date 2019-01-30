package pt.personal.utf8utils.validators;

import pt.personal.utf8utils.utils.ByteType;

public class Utf83Validator extends GenericValidator {

	@Override
	ByteType validates() {
		return ByteType.UTF_8_3;
	}
	
	@Override
	public String getValidationString() {
		return "E0 A0-BF 80-BF / E1-EC 80-BF 80-BF / ED 80-9F 80-BF / EE-EF 80-BF 80-BF";
	}

}
