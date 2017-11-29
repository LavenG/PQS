package edu.cs.nyu.pqs.assign4.player;

import edu.cs.nyu.pqs.assign4.board.Board;
import edu.cs.nyu.pqs.assign4.exception.IllegalMoveException;
import edu.cs.nyu.pqs.assign4.model.ConnectFourModel;
import java.awt.Color;

/**
 * This is an extension of the abstract class {@link Player}.
 * An instance of this class is used for human players.
 * It inherits the name and color parameters from {@link Player}.
 */
public class HumanPlayer extends Player {

  /**
   * This is a package private constructor for the class.
   * It is used to set given properties to the same variables of parent class.
   *
   * @param name The name of the player.
   * @param color The color alloted to the player.
   */
  HumanPlayer(String name, Color color) {
    super(name, color);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Human Player: " + super.toString();
  }

  /**
   * {@inheritDoc}
   */
  public void makeOptimalMove(ConnectFourModel model, Board board, Color opponentsColor)
      throws IllegalMoveException {
    throw new IllegalMoveException("This method should never be invoked for human player."
        + "Issue with game. Please restart.");
  }
}
