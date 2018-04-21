package tw.core;


import org.junit.Before;
import org.junit.Test;
import tw.core.generator.RandomIntGenerator;
import tw.validator.InputValidator;

import static org.junit.Assert.assertTrue;

/**
 * 在RandomIntGeneratorTest文件中完成RandomIntGenerator中对应的单元测试
 */
public class RandomIntGeneratorTest {
    private RandomIntGenerator randomIntGenerator;


    @Before
    public void setUp() throws Exception {
        randomIntGenerator=new RandomIntGenerator();
    }

    @Test
    public void testGenerateNums() {
        int digitmax=9;
        int numbersOfNeed=4;

        String generatedNumsStr=randomIntGenerator.generateNums(digitmax,numbersOfNeed);

        assertTrue(new InputValidator().validate(generatedNumsStr));
    }
}