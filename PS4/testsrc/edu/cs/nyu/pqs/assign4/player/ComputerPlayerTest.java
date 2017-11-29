package edu.cs.nyu.pqs.assign4.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import edu.cs.nyu.pqs.assign4.board.Board;
import edu.cs.nyu.pqs.assign4.board.Move;
import edu.cs.nyu.pqs.assign4.exception.IllegalMoveException;
import edu.cs.nyu.pqs.assign4.model.ConnectFourModel;
import edu.cs.nyu.pqs.assign4.view.ConnectFourListener;
import java.awt.Color;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/*
 *
 */
public class ComputerPlayerTest {
  private ComputerPlayer computerPlayer;
  private static final String name = "Abc";
  private static final Color color = Color.RED;
  private static final ConnectFourModel model= new ConnectFourModel();
  private static final int numRows = 6;
  private static final int numColumns = 7;
  private static final Board board = new Board(numRows,numColumns);
  private static final Color opponentsColor = Color.BLUE;

  @Before
  public void setUp() {
    computerPlayer = new ComputerPlayer(name, color);
    ConnectFourListener listener = new ConnectFourListener() {
      @Override
      public void gameStarted(Color[][] layout) {

      }

      @Override
      public void gameWon(Color[][] layout, String currentPlayerName) {

      }

      @Override
      public void gameTied(Color[][] layout) {

      }

      @Override
      public void moveCompletedSwitchPlayer(Color[][] layout, String name) {

      }
    };
    model.registerListner(listener);
  }

  @Test
  public void testToString() throws Exception {
    assertEquals("Computer Player: " + name + ", " + color.toString(),
        computerPlayer.toString());
  }

  @Test
  public void testMakeOptimalMove() throws Exception {
    model.startGame();
    model.initiateNewOnePlayerGame("Abc2");
    computerPlayer.makeOptimalMove(model, model.getBoard(), opponentsColor);
    int count = 0;
    for (int i=0; i < numRows; i++) {
      for (int j=0; j < numRows; j++) {
        if (model.getBoard().getLayout()[i][j].equals(color)) {
          count++;
        }
      }
    }
    assertEquals(1, count);
  }

  @Test
  public void testMakeOptimalMove_OptimalMoveExistsForWin() throws Exception {
    model.startGame();
    model.initiateNewOnePlayerGame("Abc2");
    addPiecesInFirstColumnForWinInOneMoveForColor(model.getBoard(),color);
    model.makeMove(5);
    assertTrue(model.getBoard().getLayout()[numRows - 1][3].equals(color));
  }

  @Test
  public void testMakeOptimalMove_OptimalMoveExistsForStoppingWin() throws Exception {
    model.startGame();
    model.initiateNewOnePlayerGame("Abc2");
    addPiecesInFirstColumnForWinInOneMoveForColor(model.getBoard(),opponentsColor);
    model.makeMove(5);
    assertTrue(model.getBoard().getLayout()[numRows -1][3].equals(color));
  }

  @Test
  public void testMakeOptimalMove_ShouldFailOnRandomsReturn() throws Exception {
    ComputerPlayer mockComputerPlayer = spy(computerPlayer);
    when(mockComputerPlayer.getRandomColumn(numColumns)).thenAnswer(new Answer() {
      private int count = 0;

      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        if(count++ == 0) {
          return 0;
        }
        return 2;
      }
    });
    model.startGame();
    model.initiateNewOnePlayerGame("Abc2");
    fillFirstColumn(model.getBoard());
    mockComputerPlayer.makeOptimalMove(model, model.getBoard(),opponentsColor);
    assertTrue(model.getBoard().getLayout()[numRows-1][0].equals(color));
  }

  private void fillFirstColumn(Board board) throws IllegalMoveException {
    for (int i=0; i < numRows/2; i++) {
      board.addPiece(new Move.Builder().setColor(color).setColumnNumber(0).build());
      board.addPiece(new Move.Builder().setColor(opponentsColor).setColumnNumber(0).build());
    }
  }

  private void addPiecesInFirstColumnForWinInOneMoveForColor(Board board, Color color)
      throws IllegalMoveException {
    board.addPiece(new Move.Builder().setColor(color).setColumnNumber(0).build());
    board.addPiece(new Move.Builder().setColor(color).setColumnNumber(1).build());
    board.addPiece(new Move.Builder().setColor(color).setColumnNumber(2).build());
  }

  @Test (expected = IllegalMoveException.class)
  public void testMakeOptimalMove_NullModel() throws Exception {
    computerPlayer.makeOptimalMove(null, board, opponentsColor);
  }

  @Test (expected = IllegalMoveException.class)
  public void testMakeOptimalMove_NullBoard() throws Exception {
    computerPlayer.makeOptimalMove(model, null, opponentsColor);
  }

  @Test (expected = IllegalMoveException.class)
  public void testMakeOptimalMove_InvalidOpponentColor() throws Exception {
    computerPlayer.makeOptimalMove(model, board, Color.RED);
  }

  @Test (expected = IllegalMoveException.class)
  public void testMakeOptimalMove_NullOpponentColor() throws Exception {
    computerPlayer.makeOptimalMove(model, board, null);
  }

  @Test (expected = IllegalMoveException.class)
  public void testMakeOptimalMove_InvalidSelfColor() throws Exception {
    computerPlayer = new ComputerPlayer(name, Color.BLUE);
    computerPlayer.makeOptimalMove(model, board, Color.BLUE);
  }
}