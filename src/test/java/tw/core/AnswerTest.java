package tw.core;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.core.exception.OutOfRangeAnswerException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {
    private Answer actualAnswer;
    private Answer inputAnswer;

    @Before
    public void setUp() {
        actualAnswer =Answer.createAnswer("1 2 3 4");
        inputAnswer=new Answer();
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void should_createAnswer_return_answer_when_input_string() {
        assertEquals(Answer.createAnswer("1 2 3 4").toString(),"1 2 3 4");
    }

    @Test
    public void should_validate_throw_OutOfRangeAnswerException_when_answer_contains_same_digits() throws OutOfRangeAnswerException {
        actualAnswer.setNumList(Arrays.asList("1","1","3","4"));
        exception.expect(OutOfRangeAnswerException.class);
        exception.expectMessage("Answer format is incorrect");
        actualAnswer.validate();
    }

    @Test
    public void should_validate_throw_OutOfRangeAnswerException_when_answer_contains_digit_greater_than_9() throws OutOfRangeAnswerException {
        exception.expect(OutOfRangeAnswerException.class);
        exception.expectMessage("Answer format is incorrect");
        actualAnswer.setNumList(Arrays.asList("1", "2", "3","10"));
        actualAnswer.validate();
    }

    @Test
    public void should_check_return_record_4_and_0_when_inputAnswer_all_right() {
        inputAnswer.setNumList(Arrays.asList("1","2","3","4"));
        assertArrayEquals(new int[]{4,0}, actualAnswer.check(inputAnswer).getValue());
    }

    @Test
    public void should_check_return_record_0_and_4_when_inputAnswer__all_in_wrong_positions() {
        inputAnswer.setNumList(Arrays.asList("4","3","2","1"));
        assertArrayEquals(new int[]{0,4}, actualAnswer.check(inputAnswer).getValue());
    }

    @Test
    public void should_check_return_record_0_and_0_when_inputAnswer_all_wrong() {
        inputAnswer.setNumList(Arrays.asList("5","6","7","8"));
        assertArrayEquals(new int[]{0,0}, actualAnswer.check(inputAnswer).getValue());
    }

    @Test
    public void should_check_return_record_4_and_0_when_inputAnswer_has_2_right_digits_and_2_in_wrong_positions() {
        inputAnswer.setNumList(Arrays.asList("1","2","4","3"));
        assertArrayEquals(new int[]{2,2}, actualAnswer.check(inputAnswer).getValue());
    }
}