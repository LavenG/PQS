package edu.cs.nyu.pqs.assign4.constants;

import edu.cs.nyu.pqs.assign4.ConnectFourApp;
import java.awt.Color;

/**
 * This class contains constants or default values for {@link ConnectFourApp}.
 * These constants could be changed based on the game requirements and hence should not be used
 * outside of this game.
 * This contains number of columns, number of rows, player one color, player two color,
 * number of consecutive rows for a win, and computer players name.
 */
public class ConstantsForGame {

  /**
   * The default number of columns for a board in the game.
   * This could be changed based on the game requirements and hence should not be used
   * outside of this game.
   */
  public static final int numColumns = 7;

  /**
   * The default number of rows for a board in the game.
   * This could be changed based on the game requirements and hence should not be used
   * outside of this game.
   */
  public static final int numRows = 6;

  /**
   * The default color for first player.
   * This could be changed based on the game requirements and hence should not be used
   * outside of this game.
   */
  public static final Color playerOneColor = Color.BLUE;

  /**
   * The default color for second player.
   * This could be changed based on the game requirements and hence should not be used
   * outside of this game.
   */
  public static final Color playerTwoColor = Color.RED;

  /**
   * The default name of the computer player.
   * This could be changed based on the game requirements and hence should not be used
   * outside of this game.
   */
  public static final String computerPlayerName = "Comp";

  /**
   * The defaultnumber of consecutive rows having same color in any direction for a game to be won
   * by that color.
   * This could be changed based on the game requirements and hence should not be used
   * outside of this game.
   */
  public static final int checkConsecutiveForWin = 4;

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Constants class.";
  }
}
