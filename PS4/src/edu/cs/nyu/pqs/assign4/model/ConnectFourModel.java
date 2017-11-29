package edu.cs.nyu.pqs.assign4.model;

import edu.cs.nyu.pqs.assign4.board.Board;
import edu.cs.nyu.pqs.assign4.board.Move;
import edu.cs.nyu.pqs.assign4.board.Move.Builder;
import edu.cs.nyu.pqs.assign4.constants.ConstantsForGame;
import edu.cs.nyu.pqs.assign4.exception.IllegalMoveException;
import edu.cs.nyu.pqs.assign4.player.Player;
import edu.cs.nyu.pqs.assign4.player.PlayerFactory;
import edu.cs.nyu.pqs.assign4.view.ConnectFourListener;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This is a model for Connect four game. All the actions take place here based on the changes at
 * UI.
 */
public class ConnectFourModel {
  private final List<ConnectFourListener> connectFourListners;
  private Player playerOne;
  private Player playerTwo;
  private boolean isOnePlayerGame;
  private Board board;
  private Player currentPlayer;
  private Player opponentPlayer;

  /**
   * A constructor for invoking an instance of {@link ConnectFourModel}
   */
  public ConnectFourModel() {
    this.connectFourListners = new LinkedList<>();
    board = null;
    playerOne = null;
    playerTwo = null;
    currentPlayer = playerOne;
    opponentPlayer = playerTwo;
  }

  /**
   * Used to get the first player.
   *
   * @return the first player.
   */
  public Player getPlayerOne() {
    return playerOne;
  }

  /**
   * Used to get the second player.
   *
   * @return the second player.
   */
  public Player getPlayerTwo() {
    return playerTwo;
  }

  /**
   * Used to get the information if it is a two player game or is one player playing against
   * computer
   *
   * @return true if one player is playing aainst computer, false otherwise.
   */
  public boolean isOnePlayerGame() {
    return isOnePlayerGame;
  }

  /**
   * This is used to get the board associated with the model.
   *
   * @return the board.
   */
  public Board getBoard() {
    return board;
  }

  /**
   * To get the player whose turn it is currently.
   *
   * @return the current player.
   */
  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  /**
   * To get the other player who is currently waiting for his turn.
   *
   * @return the other player.
   */
  public Player getOpponentPlayer() {
    return opponentPlayer;
  }

  /**
   * Used to get list of all the listeners listening on the model.
   *
   * @return list of all the listeners listening on the model.
   */
  public List<ConnectFourListener> getConnectFourListners() {
    return new ArrayList<>(connectFourListners);
  }

  /**
   * Used to add listener to the list of listeners.
   *
   * @param listener listener to be added which can not be null.
   */
  public void registerListner(ConnectFourListener listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Listener cannot be null");
    }
    connectFourListners.add(listener);
  }

  /**
   * Used to remove listener from list of listener. If the listener doesn't exist then nothing
   * happens.
   *
   * @param listener listener to be removed which can not be null.
   */
  public void deRegisterListener(ConnectFourListener listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Listener cannot be null");
    }
    connectFourListners.remove(listener);
  }

  /**
   * Called by the app on start of a game.
   */
  public void startGame() {
    board = new Board(ConstantsForGame.numRows, ConstantsForGame.numColumns);
    fireGameStartedEvent(board.getLayout());
  }

  /**
   * This method is invoked by the listener if two people decide to play against each other.
   * For several listeners the last selection is the valid selection if this method is invoked in
   * all of them one after another without making a move in between.
   *
   * @param playerOneName The name of the first player which can also be null.
   * @param playerTwoName The name of the second player which can also be null.
   */
  public void initiateNewTwoPlayerGame(String playerOneName, String playerTwoName) {
    playerOne = PlayerFactory.getPlayer(playerOneName, ConstantsForGame.playerOneColor,
        true);
    playerTwo = PlayerFactory.getPlayer(playerTwoName, ConstantsForGame.playerTwoColor,
        true);
    currentPlayer = playerOne;
    opponentPlayer = playerTwo;
    isOnePlayerGame = false;
    board.resetBoard();
    fireMoveCompletedSwapPlayerEvent(board.getLayout(), currentPlayer.getName());
  }

  /**
   * This method is invoked by the listener if the user decides to play against computer.
   * For several listeners the last selection is the valid selection if this method is invoked in
   * all of them one after another without making a move in between.
   *
   * @param playerName The name of the player which can also be null.
   */
  public void initiateNewOnePlayerGame(String playerName) {
    playerOne = PlayerFactory.getPlayer(playerName, ConstantsForGame.playerOneColor,
        true);
    playerTwo = PlayerFactory.getPlayer(ConstantsForGame.computerPlayerName,
        ConstantsForGame.playerTwoColor, false);
    currentPlayer = playerOne;
    opponentPlayer = playerTwo;
    isOnePlayerGame = true;
    board.resetBoard();
    fireMoveCompletedSwapPlayerEvent(board.getLayout(), currentPlayer.getName());
  }

  /**
   * This method is invoked when a move is made by a human (through a button click )
   * or a computer player(automatically). It is determined what event of the listeners is to be
   * fired based on the actions of this method. It switches the current and opposite player if this
   * is not a finishing move. This method also enforces a computer player or an AI to make a call
   * to it's optimal move function when it's the AI's turn.
   *
   * @param columnNumber the column to which a move is made.
   * @throws IllegalMoveException if board or playerOne or playerTwo is null.
   */
  public void makeMove(int columnNumber) throws IllegalMoveException {
    // The way it has been coded if player one is null then player two would be null and
    // if player one is not null then player two is not null.
    // Don't need to check both.
    if (board == null || playerOne == null) {
      throw new IllegalMoveException("Game not started or game type not selected.");
    }
    Move move = new Builder()
        .setColumnNumber(columnNumber)
        .setColor(currentPlayer.getColor())
        .build();
    board.addPiece(move);
    if (board.checkColorWon(currentPlayer.getColor())) {
      fireGameWonEvent(board.getLayout(), currentPlayer.getName());
      return;
    } else if (board.checkGameOver()) {
      fireGameTiedEvent(board.getLayout());
      return;
    } else {
      fireMoveCompletedSwapPlayerEvent(board.getLayout(), opponentPlayer.getName());
    }
    moveCompletedSwapPlayer();
    if (isCurrentPlayerComputer()) {
      currentPlayer.makeOptimalMove(this, board,
          opponentPlayer.getColor());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Connect four model.";
  }

  private boolean isCurrentPlayerComputer() {
    return isOnePlayerGame && currentPlayer == playerTwo;
  }

  private void moveCompletedSwapPlayer() {
    Player tempPlayer = currentPlayer;
    currentPlayer = opponentPlayer;
    opponentPlayer = tempPlayer;
  }

  private void fireGameStartedEvent(Color[][] layout) {
    for (ConnectFourListener listener : connectFourListners) {
      listener.gameStarted(layout);
    }
  }

  private void fireGameWonEvent(Color[][] layout, String name) {
    for (ConnectFourListener listener : connectFourListners) {
      listener.gameWon(layout, name);
    }
  }

  private void fireGameTiedEvent(Color[][] layout) {
    for (ConnectFourListener listener : connectFourListners) {
      listener.gameTied(layout);
    }
  }

  private void fireMoveCompletedSwapPlayerEvent(Color[][] layout, String name) {
    for (ConnectFourListener listener : connectFourListners) {
      listener.moveCompletedSwitchPlayer(layout, name);
    }
  }
}
