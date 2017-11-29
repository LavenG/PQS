package edu.cs.nyu.pqs.assign4.player;

import edu.cs.nyu.pqs.assign4.board.Board;
import edu.cs.nyu.pqs.assign4.constants.ConstantsForGame;
import edu.cs.nyu.pqs.assign4.exception.IllegalMoveException;
import edu.cs.nyu.pqs.assign4.model.ConnectFourModel;
import java.awt.Color;
import java.util.Random;

/**
 * This is an extension of the abstract class {@link Player}.
 * An instance of this class is used when one of the players is computer.
 * It inherits the name and color parameters from {@link Player}.
 */
public class ComputerPlayer extends Player {
  private Random random = new Random();

  /**
   * This is a package private constructor for the class.
   * It is used to set given properties to the same variables of parent class.
   *
   * @param name The name of the player.
   * @param color The color alloted to the player.
   */
  ComputerPlayer(String name, Color color) {
    super(name, color);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void makeOptimalMove(ConnectFourModel model, Board board, Color opponentsColor)
      throws IllegalMoveException {
    if(model == null || board == null) {
      throw new IllegalMoveException("Model and board cannot be null. Restart game.");
    }
    int columnToMakeMove = optimalMoveColumn(board, opponentsColor);
    model.makeMove(columnToMakeMove);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Computer Player: " + super.toString();
  }

  private int optimalMoveColumn(Board board, Color opponentsColor)
      throws IllegalMoveException {
    if (opponentsColor == null) {
      throw new IllegalMoveException("Color can't be null.");
    }
    if (color != ConstantsForGame.playerTwoColor) {
      throw new IllegalMoveException("Inavalid color for current player");
    }
    if (opponentsColor != ConstantsForGame.playerOneColor) {
      throw new IllegalMoveException("Invalid color for opponent.");
    }
    int winMoveForCurrentPlayer = board.getWinColumn(color);
    //Check if you could win in this move, if yes, return the column that could get you to win.
    if (winMoveForCurrentPlayer != -1) {
      return winMoveForCurrentPlayer;
    } else {
      int firstWinMoveForOpponent = board.getWinColumn(opponentsColor);
      //check what is the first column the opponent could add piece to so that they win in
      // next move. Return column number that prevents this,
      if (firstWinMoveForOpponent != -1) {
        return firstWinMoveForOpponent;
      }
    }
    int moveColumn = getRandomColumn(ConstantsForGame.numColumns);
    while (!board.checkValidMove(moveColumn)) {
      moveColumn = getRandomColumn(ConstantsForGame.numColumns);
    }
    return moveColumn;
  }

  /**
   * This is only public for testing purposes.
   * This method generates a random number in a range specified by used.
   *
   * @param range the range specified.
   * @return It returns a random number in the range specified.
   */
  public int getRandomColumn(int range) {
    return random.nextInt(range);
  }
}
