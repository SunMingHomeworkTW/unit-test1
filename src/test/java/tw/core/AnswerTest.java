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
    private Answer answer;

    @Before
    public void setUp() throws Exception {
        answer=new Answer();
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testCreateAnswer() {
        assertEquals(Answer.createAnswer("1 2 3 4").toString(),"1 2 3 4");
    }

    @Test
    public void testValidate() throws OutOfRangeAnswerException {
        List<String> numList1=Arrays.asList("1","2","3");
        answer.setNumList(numList1);
        expectedException.expect(OutOfRangeAnswerException.class);
        answer.validate();

        List<String> numList2=Arrays.asList("1","2","3","4","5");
        answer.setNumList(numList2);
        expectedException.expect(OutOfRangeAnswerException.class);
        answer.validate();

        List<String> numList3=Arrays.asList("1","1","3","4");
        answer.setNumList(numList3);
        expectedException.expect(OutOfRangeAnswerException.class);
        answer.validate();

        List<String> numList4=Arrays.asList("11","2","3","4");
        answer.setNumList(numList4);
        expectedException.expect(OutOfRangeAnswerException.class);
        answer.validate();
    }

    @Test
    public void testCheck() {
        Answer inputAnswer=new Answer();

        List<String> numList=Arrays.asList("1","2","3","4");
        answer.setNumList(numList);

        List<String> numList1=Arrays.asList("1","2","3","4");
        inputAnswer.setNumList(numList1);
        assertArrayEquals(new int[]{4,0},answer.check(inputAnswer).getValue());

        List<String> numList2=Arrays.asList("4","3","2","1");
        inputAnswer.setNumList(numList2);
        assertArrayEquals(new int[]{0,4},answer.check(inputAnswer).getValue());

        List<String> numList3=Arrays.asList("5","6","7","8");
        inputAnswer.setNumList(numList3);
        assertArrayEquals(new int[]{0,0},answer.check(inputAnswer).getValue());

        List<String> numList4=Arrays.asList("1","2","4","3");
        inputAnswer.setNumList(numList4);
        assertArrayEquals(new int[]{2,2},answer.check(inputAnswer).getValue());
    }

    @Test
    public void testGetIndexOfNum() {
        List<String> numList=Arrays.asList("1","2","3","4");
        answer.setNumList(numList);

        assertEquals(0,answer.getIndexOfNum("1"));
        assertEquals(3,answer.getIndexOfNum("4"));
        assertEquals(-1,answer.getIndexOfNum("5"));
        assertEquals(-1,answer.getIndexOfNum("a"));
    }

    @Test
    public void testToString() {
        List<String> numList=Arrays.asList("1","2","3","4");
        answer.setNumList(numList);

        assertEquals("1 2 3 4",answer.toString());
    }
}