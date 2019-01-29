package pt.personal.utf8utils.validators;

import java.util.HashMap;
import java.util.Map;

import pt.personal.utf8utils.utils.ByteType;

public class SimpleValidatorMiddleman implements ValidatorMiddleman {
	
	private Map<ByteType, Validator> validators;
	
	public SimpleValidatorMiddleman() {
		validators = new HashMap<>();
		validators.put(ByteType.UTF_8_1, new Utf81Validator());
		validators.put(ByteType.UTF_8_2, new Utf82Validator());
		validators.put(ByteType.UTF_8_3, new Utf83Validator());
	}

	@Override
	public Validator getValidatorFor(ByteType byteType) {
		if (validators.containsKey(byteType)) {
			return validators.get(byteType);
		}
		
		return null; // TODO: throw an exception here
	}

}
