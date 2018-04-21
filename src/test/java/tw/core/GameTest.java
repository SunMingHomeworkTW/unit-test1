package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 在GameTest文件中完成Game中对应的单元测试
 */


public class GameTest {
    private Game game;

    @Before
    public void setUp() throws Exception {
        AnswerGenerator answerGenerator=mock(AnswerGenerator.class);
        when(answerGenerator.generate()).thenReturn(Answer.createAnswer("1 2 3 4"));
        game=new Game(answerGenerator);
    }

    @Test
    public void testGuess() throws OutOfRangeAnswerException {
        assertEquals("4A0B",game.guess(Answer.createAnswer("1 2 3 4")).getResult());
        assertEquals("0A4B",game.guess(Answer.createAnswer("4 3 2 1")).getResult());
        assertEquals("0A0B",game.guess(Answer.createAnswer("5 6 7 8")).getResult());
        assertEquals("2A2B",game.guess(Answer.createAnswer("1 2 4 3")).getResult());
    }

    @Test
    public void testGuessHistory() {
        assertEquals(0, game.guessHistory().size());

        game.guess(Answer.createAnswer("1 2 3 4"));
        assertEquals(1, game.guessHistory().size());
    }

    @Test
    public void testCheckStatus_success_first() {
        game.guess(Answer.createAnswer("1 2 3 4"));
        assertEquals("success",game.checkStatus());
    }

    @Test
    public void testCheckStatus_success_second(){
        game.guess(Answer.createAnswer("5 6 7 8"));
        assertEquals("continue",game.checkStatus());

        game.guess(Answer.createAnswer("1 2 3 4"));
        assertEquals("success",game.checkStatus());
    }
    @Test
    public void testCheckStatus_success_sixth(){
        game.guess(Answer.createAnswer("5 6 7 8"));
        assertEquals("continue",game.checkStatus());

        game.guess(Answer.createAnswer("1 2 3 9"));
        assertEquals("continue",game.checkStatus());

        game.guess(Answer.createAnswer("2 1 3 9"));
        assertEquals("continue",game.checkStatus());

        game.guess(Answer.createAnswer("1 2 9 3"));
        assertEquals("continue",game.checkStatus());

        game.guess(Answer.createAnswer("4 3 2 1"));
        assertEquals("continue",game.checkStatus());

        game.guess(Answer.createAnswer("1 2 3 4"));
        assertEquals("success",game.checkStatus());
    }
    @Test
    public void testCheckStatus_fail(){
        game.guess(Answer.createAnswer("5 6 7 8"));
        assertEquals("continue",game.checkStatus());

        game.guess(Answer.createAnswer("1 2 3 9"));
        assertEquals("continue",game.checkStatus());

        game.guess(Answer.createAnswer("2 1 3 9"));
        assertEquals("continue",game.checkStatus());

        game.guess(Answer.createAnswer("1 2 9 3"));
        assertEquals("continue",game.checkStatus());

        game.guess(Answer.createAnswer("4 3 2 1"));
        assertEquals("continue",game.checkStatus());

        game.guess(Answer.createAnswer("1 2 3 5"));
        assertEquals("fail",game.checkStatus());
    }

    @Test
    public void testCheckContinue_success() {
        game.guess(Answer.createAnswer("1 2 3 4"));
        assertFalse(game.checkCoutinue());
    }

    @Test
    public void testCheckContinue_sixth() {
        assertTrue(game.checkCoutinue());

        game.guess(Answer.createAnswer("5 6 7 8"));
        assertTrue(game.checkCoutinue());

        game.guess(Answer.createAnswer("1 2 3 9"));
        assertTrue(game.checkCoutinue());

        game.guess(Answer.createAnswer("2 1 3 9"));
        assertTrue(game.checkCoutinue());

        game.guess(Answer.createAnswer("1 2 9 3"));
        assertTrue(game.checkCoutinue());

        game.guess(Answer.createAnswer("4 3 2 1"));
        assertTrue(game.checkCoutinue());

        game.guess(Answer.createAnswer("1 2 3 4"));
        assertFalse(game.checkCoutinue());
    }
}
