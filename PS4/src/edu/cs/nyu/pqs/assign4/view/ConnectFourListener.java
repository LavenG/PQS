package edu.cs.nyu.pqs.assign4.view;

import java.awt.Color;

/**
 * An interface or contract for all the listeners of for the game of Connect Four.
 */
public interface ConnectFourListener {

  /**
   * This method is invoked by the model to inform the listener about the start of a game.
   *
   * @param layout The layout that needs to be displayed on the UI board.
   */
  void gameStarted(Color[][] layout);

  /**
   * This method is invoked by the model to inform the listener if someone has won a game.
   *
   * @param layout The layout that needs to be displayed on the UI board.
   * @param currentPlayerName The name of current player which can also be null.
   */
  void gameWon(Color[][] layout, String currentPlayerName);

  /**
   * This method is invoked by the model if the game ends in a tie.
   *
   * @param layout The layout that needs to be displayed on the UI board.
   */
  void gameTied(Color[][] layout);

  /**
   * This method is invoked by the model after completion of a move if the game wasn't over due to
   * win or tie.
   *
   * @param layout The layout that needs to be displayed on the UI board.
   * @param name The name of current player which can also be null.
   */
  void moveCompletedSwitchPlayer(Color[][] layout, String name);
}
