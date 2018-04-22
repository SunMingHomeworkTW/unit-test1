package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.validator.InputValidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 在InputValidatorTest文件中完成InputValidator中对应的单元测试
 */
public class InputValidatorTest {
    private InputValidator inputValidator;

    @Before
    public void setUp() throws Exception {
        inputValidator=new InputValidator();
    }

    @Test
    public void should_return_true_when_input_is_valid() {
        assertTrue(inputValidator.validate("1 2 3 4"));
    }

    @Test
    public void should_return_false_when_input_digit_number_is_less_than_4() {
        assertFalse(inputValidator.validate("1 2 3"));
    }

    @Test
    public void should_return_false_when_input_digit_number_is_larger_than_4() {
        assertFalse(inputValidator.validate("1 2 3 4 5"));
    }

    @Test
    public void should_return_false_when_input_has_same_digits() {
        assertFalse(inputValidator.validate("1 1 3 4"));
    }

    @Test
    public void should_return_false_when_input_digit_is_larger_than_9() {
        assertFalse(inputValidator.validate("10 2 3 4"));
    }

    @Test
    public void should_return_false_when_input_has_same_digits_and_digit_number_is_larger_than_4() {
        assertFalse(inputValidator.validate("1 1 2 3 4"));
    }

    @Test
    public void should_return_false_when_input_has_same_digits_and_digit_is_larger_than_9() {
        assertFalse(inputValidator.validate("1 2 3 3 10"));
    }
}
