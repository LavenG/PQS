package edu.cs.nyu.pqs.assign4.player;

import java.awt.Color;

/**
 * This is used to get an instance of {@link Player} abstract class.
 */
public class PlayerFactory {

  /**
   * It gets the information if human or non-human instance is needed and returns an instance
   * accordingly.
   *
   * @param name The player name.
   * @param color The player color.
   * @param isHuman variable determining the type of instance needed.
   * @return a HumanPlayer instance if isHuman is true, a ComputerPlayer instance otherwise.
   */
  public static Player getPlayer(String name, Color color, boolean isHuman) {
    if (isHuman) {
      return new HumanPlayer(name, color);
    } else {
      return new ComputerPlayer(name, color);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Player factory.";
  }
}
