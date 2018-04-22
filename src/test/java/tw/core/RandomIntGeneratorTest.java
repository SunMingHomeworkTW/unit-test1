package tw.core;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.core.generator.RandomIntGenerator;
import tw.validator.InputValidator;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * 在RandomIntGeneratorTest文件中完成RandomIntGenerator中对应的单元测试
 */
public class RandomIntGeneratorTest {
    private RandomIntGenerator randomIntGenerator;
    
    @Before
    public void setUp() {
        randomIntGenerator=new RandomIntGenerator();
    }
    
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void should_throw_IllegalArgumentException_when_digitmax_less_than_numbersOfNeed() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Can't ask for more numbers than are available");
        randomIntGenerator.generateNums(2,4);
    }

    @Test
    public void should_generate_digit_number_equals_numbersOfNeed() {
        String generateNumsStr = randomIntGenerator.generateNums(9,5);
        assertEquals(9,generateNumsStr.length());
    }

    @Test
    public void should_generate_different_digits() {
        String generateNumsStr = randomIntGenerator.generateNums(9,4);
        assertEquals(4, Arrays.stream(generateNumsStr.split(" ")).distinct().count());
    }

    @Test
    public void should_generate_digits_less_than_digmax() {
        String generateNumsStr = randomIntGenerator.generateNums(9,4);
        assertTrue( Arrays.stream(generateNumsStr.split(" ")).allMatch(i->Integer.valueOf(i)<=9));
    }

    @Test
    public void should_generate_random_digits() {
        String generateNumsStr1 = randomIntGenerator.generateNums(9,4);
        String generateNumsStr2 = randomIntGenerator.generateNums(9,4);
        assertNotEquals(generateNumsStr1,generateNumsStr2);
    }
}