package pt.personal.utf8utils.validators;

import pt.personal.utf8utils.utils.ByteType;

public class Utf84Validator extends GenericValidator {

	@Override
	ByteType validates() {
		return ByteType.UTF_8_4;
	}

	@Override
	String getValidationString() {
		return "F0 90-BF 80-BF 80-BF / F1-F3 80-BF 80-BF 80-BF / F4 80-8F 80-BF 80-BF";
	}

}
