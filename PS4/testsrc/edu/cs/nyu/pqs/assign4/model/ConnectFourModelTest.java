package edu.cs.nyu.pqs.assign4.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import edu.cs.nyu.pqs.assign4.exception.IllegalMoveException;
import edu.cs.nyu.pqs.assign4.player.PlayerFactory;
import edu.cs.nyu.pqs.assign4.view.ConnectFourListener;
import java.awt.Color;
import org.junit.Test;

/**
 * Test for model.
 */
public class ConnectFourModelTest {
  private ConnectFourModel model;
  private static final String firstPlayerName = "Abc";
  private static final String secondPlayerName = "Def";
  private static final String computerPlayerName = "Comp";
  private static final Color firstPlayerColor = Color.BLUE;
  private static final Color secondPlayerColor = Color.RED;
  private static final int numOfRows = 6;
  private static final int numOfColumns = 7;

  /*
   * Can't be before because also have to test for null board.
   */
  public void setUp() throws Exception {
    model = new ConnectFourModel();
    model.startGame();
  }

  @Test
  public void testToString() throws Exception {
    setUp();
    assertEquals("Connect four model.",model.toString());
  }

  @Test
  public void testGetPlayerOne() throws Exception {
    setUp();
    model.initiateNewTwoPlayerGame(firstPlayerName,secondPlayerName);
    assertEquals( PlayerFactory.getPlayer(firstPlayerName,firstPlayerColor, true),
        model.getPlayerOne());
  }

  @Test
  public void testGetPlayerTwo() throws Exception {
    setUp();
    model.initiateNewTwoPlayerGame(firstPlayerName,secondPlayerName);
    assertEquals( PlayerFactory.getPlayer(secondPlayerName,secondPlayerColor, true),
        model.getPlayerTwo());
  }

  @Test
  public void testIsOnePlayerGame() throws Exception {
    setUp();
    model.initiateNewTwoPlayerGame(firstPlayerName,secondPlayerName);
    assertFalse(model.isOnePlayerGame());
  }

  @Test
  public void testGetBoard() throws Exception {
    setUp();
    model.initiateNewOnePlayerGame(firstPlayerName);
    for (int i=0; i < numOfRows; i++) {
      for (int j=0; j < numOfColumns; j++) {
        assertTrue(model.getBoard().getLayout()[i][j].equals(Color.WHITE));
      }
    }
  }

  @Test
  public void testGetCurrentPlayer() throws Exception {
    setUp();
    model.initiateNewTwoPlayerGame(firstPlayerName,secondPlayerName);
    assertEquals(model.getPlayerOne(), model.getCurrentPlayer());
  }

  @Test
  public void testInitiateNewTwoPlayerGame() throws Exception {
    setUp();
    model.initiateNewTwoPlayerGame(firstPlayerName, secondPlayerName);
    assertFalse(model.isOnePlayerGame());
    assertEquals( PlayerFactory.getPlayer(firstPlayerName,firstPlayerColor, true),
        model.getPlayerOne());
    assertEquals( PlayerFactory.getPlayer(firstPlayerName,firstPlayerColor, true),
        model.getCurrentPlayer());
    assertEquals( PlayerFactory.getPlayer(secondPlayerName,secondPlayerColor, true),
        model.getPlayerTwo());
    assertEquals( PlayerFactory.getPlayer(secondPlayerName,secondPlayerColor, true),
        model.getOpponentPlayer());
  }

  @Test
  public void testInitiateNewOnePlayerGame() throws Exception {
    setUp();
    model.initiateNewOnePlayerGame(firstPlayerName);
    assertTrue(model.isOnePlayerGame());
    assertEquals( PlayerFactory.getPlayer(firstPlayerName,firstPlayerColor, true),
        model.getPlayerOne());
    assertEquals( PlayerFactory.getPlayer(firstPlayerName,firstPlayerColor, true),
        model.getCurrentPlayer());
    assertEquals( PlayerFactory.getPlayer(computerPlayerName,secondPlayerColor, false),
        model.getPlayerTwo());
    assertEquals( PlayerFactory.getPlayer(computerPlayerName,secondPlayerColor, false),
        model.getOpponentPlayer());
  }

  @Test
  public void testGetOpponentPlayer() throws Exception {
    setUp();
    model.initiateNewTwoPlayerGame(firstPlayerName,secondPlayerName);
    assertEquals(model.getPlayerTwo(), model.getOpponentPlayer());
  }

  @Test
  public void testRegisterListner() throws Exception {
    setUp();
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
    assertEquals(1, model.getConnectFourListners().size());
    assertEquals(listener, model.getConnectFourListners().get(0));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testRegisterListener_NullListener() throws Exception {
    setUp();
    model.registerListner(null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testDeRegisterListener_NullListener() throws Exception {
    setUp();
    model.deRegisterListener(null);
  }

  @Test
  public void testDeRegisterListener() throws Exception {
    setUp();
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
    model.deRegisterListener(listener);
    assertEquals(0, model.getConnectFourListners().size());
    model.registerListner(listener);
    model.registerListner(listener);
    assertEquals(2, model.getConnectFourListners().size());
    assertEquals(listener, model.getConnectFourListners().get(0));
    assertEquals(listener, model.getConnectFourListners().get(1));
    model.deRegisterListener(listener);
    assertEquals(1, model.getConnectFourListners().size());
    assertEquals(listener, model.getConnectFourListners().get(0));
  }

  @Test
  public void getConnectFourListners() throws Exception {
    setUp();
    assertEquals(0, model.getConnectFourListners().size());
  }

  @Test
  public void getConnectFourListners_AddListener() throws Exception {
    setUp();
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
    assertEquals(1, model.getConnectFourListners().size());
    assertEquals(listener, model.getConnectFourListners().get(0));
  }

  @Test
  public void testMakeMove() throws Exception {
    setUp();
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
    model.initiateNewOnePlayerGame(firstPlayerName);
    model.makeMove(1);
    assertEquals(Color.BLUE, model.getBoard().getLayout()[numOfRows-1][1]);
  }

  @Test
  public void testMakeMove_WinningMove() throws Exception {
    ConnectFourModel model = new ConnectFourModel();
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
    model.startGame();
    model.initiateNewTwoPlayerGame(firstPlayerName, secondPlayerName);
    model.makeMove(1);
    assertEquals(Color.BLUE, model.getBoard().getLayout()[numOfRows-1][1]);
    model.makeMove(0);
    assertEquals(Color.RED, model.getBoard().getLayout()[numOfRows-1][0]);
    model.makeMove(1);
    assertEquals(Color.BLUE, model.getBoard().getLayout()[numOfRows-2][1]);
    model.makeMove(0);
    assertEquals(Color.RED, model.getBoard().getLayout()[numOfRows-2][0]);
    model.makeMove(1);
    assertEquals(Color.BLUE, model.getBoard().getLayout()[numOfRows-3][1]);
    model.makeMove(0);
    assertEquals(Color.RED, model.getBoard().getLayout()[numOfRows-3][0]);
    model.makeMove(1);
    assertEquals(Color.BLUE, model.getBoard().getLayout()[numOfRows-4][1]);
  }

  @Test (expected = IllegalMoveException.class)
  public void testMakeMove_NullBoard() throws Exception {
    model = new ConnectFourModel();
    model.makeMove(0);
  }

  @Test (expected = IllegalMoveException.class)
  public void testMakeMove_NullPlayer() throws Exception {
    setUp();
    model.makeMove(0);
  }

  @Test
  public void testMakeMove_GameTiedFired() throws Exception {
    ConnectFourModel model = new ConnectFourModel();
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
    model.startGame();
    model.initiateNewTwoPlayerGame(firstPlayerName, secondPlayerName);
    movesForTies(model);
    model.makeMove(0);
    assertEquals(Color.RED, model.getBoard().getLayout()[numOfRows - 6][0]);
  }

  private void movesForTies(ConnectFourModel model) throws IllegalMoveException {
    for(int i=0; i < numOfColumns; i+=2) {
      model.makeMove(i);
      if (i + 1 < numOfColumns) {
        model.makeMove(i + 1);
      }
      model.makeMove(i);
      if (i + 1 < numOfColumns) {
        model.makeMove(i + 1);
      }
      model.makeMove(i);
      if (i + 1 < numOfColumns) {
        model.makeMove(i + 1);
      }
      if (i + 1 < numOfColumns) {
        model.makeMove(i + 1);
      }
      model.makeMove(i);
      if (i + 1 < numOfColumns) {
        model.makeMove(i + 1);
      }
      model.makeMove(i);
      if (i + 1 < numOfColumns) {
        model.makeMove(i + 1);
      }
      if (i != 0) {
        model.makeMove(i);
      }
    }
  }
}