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
    public void testValidate() {
        String numStr1="1 2 3 4";
        String numStr2="1 2 3";
        String numStr3="1 2 3 4 5";
        String numStr4="1 1 3 4";
        String numStr5="10 2 3 4";

        assertTrue(inputValidator.validate(numStr1));
        assertFalse(inputValidator.validate(numStr2));
        assertFalse(inputValidator.validate(numStr3));
        assertFalse(inputValidator.validate(numStr4));
        assertFalse(inputValidator.validate(numStr5));
    }
}
