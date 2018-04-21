package tw.controllers;

import org.junit.Before;
import org.junit.Test;
import tw.core.Game;
import tw.views.GameView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {
//    private GameController gameController;
//    private Game game;
//    private GameView gameView;
//
//    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//
//    @Before
//    public void setUp() throws Exception {
//        game=mock(Game.class);
//        gameView=spy(GameView.class);
//        gameController=new GameController(game,gameView);
//
//        System.setOut(new PrintStream(outContent));
//    }
//
//    private String systemOut() {
//        return outContent.toString();
//    }
//
//    @Test
//    public void testBeginGame() throws IOException {
//        gameController.beginGame();
//        assertEquals("------Guess Number Game, You have 6 chances to guess!  ------\r\n",systemOut());
//    }

//    @Test
//    public void testPlay() throws IOException{
//        when(game.checkCoutinue()).thenReturn(true).thenReturn(false);
//
//        Answer actualAnswer=Answer.createAnswer("1 2 3 4");
//
//        InputCommand inputCommand=mock(GuessInputCommand.class);
//        when(inputCommand.input()).thenReturn(actualAnswer);
//
//        Mockito.doCallRealMethod().when(game).guess(actualAnswer);
////        when(game.guess(actualAnswer)).thenReturn(new GuessResult("4A0B",actualAnswer));
//
//        gameController.play(inputCommand);
//
//        assertEquals("Game Status: success\r\n",systemOut());
//    }
}