package edu.cs.nyu.pqs.assign4.player;

import edu.cs.nyu.pqs.assign4.board.Board;
import edu.cs.nyu.pqs.assign4.constants.ConstantsForGame;
import edu.cs.nyu.pqs.assign4.exception.IllegalMoveException;
import edu.cs.nyu.pqs.assign4.model.ConnectFourModel;
import java.awt.Color;

/**
 * This is an abstract player class. It contains name and color properties that can only be
 * inherited and accessed by it's children.
 */
public abstract class Player {
  protected final String name;
  protected final Color color;

  /**
   * A package private constructor for a player. An instance can only be created using the factory
   * {@link PlayerFactory}. It sets value to name and color variables. It throws an exception
   * {@link IllegalArgumentException} if the color is incorrect or null.
   *
   * @param name the name of the player.
   * @param color the color of the players piece.
   */
  Player(String name, Color color) {
    this.name = name;
    if (!(color != null && (color == ConstantsForGame.playerOneColor
        || color == ConstantsForGame.playerTwoColor))) {
      throw new IllegalArgumentException("Incorrect color.");
    }
    this.color = color;
  }

  /**
   * This will return the name of the player. The name can also be null in which case it returns a
   * null.
   *
   * @return the name of the player or null.
   */
  public String getName() {
    return name;
  }

  /**
   * This will return the color of the player. The color cannot be null or invalid as specified in
   * the constructor.
   *
   * @return the color of the player.
   */
  public Color getColor() {
    return color;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return name + ", " + color.toString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Player)) {
      return false;
    }
    Player player = (Player) obj;
    if ((name != null && player.name == null) || (name == null && player.name != null)) {
      return false;
    }
    return color == player.getColor()
        &&  (name == null ||  name.equals(player.getName()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    if (name == null) {
      return String.valueOf(color).hashCode();
    } else {
      return (name + String.valueOf(color)).hashCode();
    }
  }

  /**
   * This method makes a move for a computer player and is invoked everytime a human plays a
   * computer and finished his move.
   * The move made has the following algorithm:
   * i. If there is a move which will lead to win for the computer then the computer make that move.
   * ii. If above is not true and there is a move which could prevent a win for the opponent in the
   * next move then the computer makes this particular preventive move. Please note if there are
   * several wining moves for the human then the computer can only prevent one of them. The human
   * may still win.
   * iii. A random move if none of the above is possible.
   *
   * This method should never be called for a human player. This is only invoked for computer.
   * If this is ever called for a human it means there is an issue with
   *
   * @param model The model where the move needs to be made by computer player.
   * @param board The board which contains all the move information for computer player.
   * @param opponentsColor The color of opponents piece of computer player.
   * @throws IllegalMoveException If an illegal move is made this exception is thrown. This could
   * happen if the color was not correctly specified or the move itself is invalid. This always is
   * thrown if the method is invoked for a human player.
   */
  abstract public void makeOptimalMove(ConnectFourModel model, Board board,
      Color opponentsColor) throws IllegalMoveException;
}
