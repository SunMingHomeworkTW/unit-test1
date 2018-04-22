package tw.controllers;

import org.junit.Before;
import org.junit.Test;
import tw.commands.GuessInputCommand;
import tw.commands.InputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.generator.AnswerGenerator;
import tw.views.GameView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {
    private GameController gameController;
    private InputCommand command;
    private AnswerGenerator answerGenerator;

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        command =mock(GuessInputCommand.class);

        answerGenerator=mock(AnswerGenerator.class);
        when(answerGenerator.generate()).thenReturn(Answer.createAnswer("1 2 3 4"));
        gameController=new GameController(new Game(answerGenerator),new GameView());

        System.setOut(new PrintStream(outContent));
    }

    private String systemOut() {
        return outContent.toString();
    }

    @Test
    public void should_print_beginMsg_when_beginGame() throws IOException {
        gameController.beginGame();
        assertEquals("------Guess Number Game, You have 6 chances to guess!  ------\r\n",systemOut());
    }

    @Test
    public void should_print_history_when_success_first() throws Exception{
        when(command.input()).thenReturn(Answer.createAnswer("1 2 3 4"));
        gameController.play(command);
//        assertThat(systemOut()).endsWith("Game Status: success\r\n");
        assertThat(systemOut()).isEqualTo("Guess Result: 4A0B\r\n" +
                "Guess History:\r\n" +
                "[Guess Numbers: 1 2 3 4, Guess Result: 4A0B]\r\n" +
                "Game Status: success\r\n");
        verify(command, times(1)).input();
    }

    @Test
    public void should_print_history_when_success_second() throws Exception{
        when(command.input()).thenReturn(Answer.createAnswer("1 5 3 4"))
                .thenReturn(Answer.createAnswer("1 2 3 4"));
        gameController.play(command);
        assertThat(systemOut()).isEqualTo("Guess Result: 3A0B\r\n" +
                "Guess History:\r\n" +
                "[Guess Numbers: 1 5 3 4, Guess Result: 3A0B]\r\n" +
                "Guess Result: 4A0B\r\n" +
                "Guess History:\r\n" +
                "[Guess Numbers: 1 5 3 4, Guess Result: 3A0B]\r\n" +
                "[Guess Numbers: 1 2 3 4, Guess Result: 4A0B]\r\n" +
                "Game Status: success\r\n");
        assertThat(systemOut()).endsWith("Game Status: success\r\n");
        verify(command, times(2)).input();
    }

    @Test
    public void should_print_GuessHistory_when_success_sixth() throws IOException {
        Answer actualAnswer=Answer.createAnswer("1 2 3 4");
        Answer answer=Answer.createAnswer("5 6 7 8");
        when(command.input()).thenReturn(answer)
                .thenReturn(answer)
                .thenReturn(answer)
                .thenReturn(answer)
                .thenReturn(answer)
                .thenReturn(actualAnswer);
        gameController.play(command);
        assertThat(systemOut()).contains(
                "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\r\n" +
                        "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\r\n" +
                        "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\r\n" +
                        "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\r\n" +
                        "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\r\n" +
                        "[Guess Numbers: 1 2 3 4, Guess Result: 4A0B]\r\n" +
                        "Game Status: success");
        verify(command, times(6)).input();
    }

    @Test
    public void should_print_GuessHistory_when_fail() throws Exception{
        Answer answer=Answer.createAnswer("5 6 7 8");
        when(command.input()).thenReturn(answer)
                .thenReturn(answer)
                .thenReturn(answer)
                .thenReturn(answer)
                .thenReturn(answer)
                .thenReturn(answer);
        gameController.play(command);
        assertThat(systemOut()).contains(
                "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\r\n" +
                        "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\r\n" +
                        "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\r\n" +
                        "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\r\n" +
                        "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\r\n" +
                        "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\r\n" +
                        "Game Status: fail");
        verify(command, times(6)).input();
    }

}