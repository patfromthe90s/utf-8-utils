package pt.personal.utf8utils.validators;

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runner.RunWith;

@RunWith(Suite.class)
@SuiteClasses({
	GenericValidatorTest.class,
	Utf81ValidatorTest.class,
	Utf82ValidatorTest.class,
	Utf83ValidatorTest.class,
	Utf84ValidatorTest.class
})
public class ValidatorTest {
}
