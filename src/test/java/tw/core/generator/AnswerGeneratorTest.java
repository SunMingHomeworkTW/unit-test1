package tw.core.generator;

import org.junit.Before;
import org.junit.Test;
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
    public void setUp() throws Exception {
        randomIntGenerator=mock(RandomIntGenerator.class);
        answerGenerator=new AnswerGenerator(randomIntGenerator);
    }

    @Test
    public void testGenerate() throws OutOfRangeAnswerException {
        when(randomIntGenerator.generateNums(9,4)).thenReturn("1 2 3 4");

        assertEquals(Answer.createAnswer("1 2 3 4").toString(),answerGenerator.generate().toString());
    }
}

