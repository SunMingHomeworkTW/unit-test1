package tw.core.generator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.core.Answer;
import tw.core.exception.OutOfRangeAnswerException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 在AnswerGeneratorTest文件中完成AnswerGenerator中对应的单元测试
 */
public class AnswerGeneratorTest {
    private AnswerGenerator answerGenerator;
    private RandomIntGenerator randomIntGenerator;

    @Before
    public void setUp() {
        randomIntGenerator = mock(RandomIntGenerator.class);
        answerGenerator = new AnswerGenerator(randomIntGenerator);
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void should_return_the_answer_same_as_generateNums() throws OutOfRangeAnswerException {
        when(randomIntGenerator.generateNums(9, 4)).thenReturn("1 2 3 4");

        assertEquals(Answer.createAnswer("1 2 3 4").toString(), answerGenerator.generate().toString());
    }

    @Test
    public void should_throw_OutOfRangeAnswerException_when_generateNums_contains_same_digits() throws OutOfRangeAnswerException {
        when(randomIntGenerator.generateNums(9, 4)).thenReturn("1 1 2 3");
        exception.expect(OutOfRangeAnswerException.class);
        exception.expectMessage("Answer format is incorrect");
        answerGenerator.generate();
    }

    @Test
    public void should_throw_OutOfRangeAnswerException_when_generateNums_contains_digit_greater_than_9() throws OutOfRangeAnswerException {
        when(randomIntGenerator.generateNums(9, 4)).thenReturn("1 2 3 10");
        exception.expect(OutOfRangeAnswerException.class);
        exception.expectMessage("Answer format is incorrect");
        answerGenerator.generate();
    }
}

