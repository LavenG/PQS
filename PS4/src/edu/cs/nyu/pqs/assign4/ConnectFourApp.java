package edu.cs.nyu.pqs.assign4;

import edu.cs.nyu.pqs.assign4.model.ConnectFourModel;
import edu.cs.nyu.pqs.assign4.view.ConnectFourSpringView;
import edu.cs.nyu.pqs.assign4.view.ViewFactory;

/**
 * This is the main app to run the game.
 */
public class ConnectFourApp {

  /**
   * The main method that invokes the game.
   *
   * @param args The command line arguments passed.
   */
  public static void main(String[] args) {
    new ConnectFourApp().go();
  }

  /**
   * This instance method is called by main to start a game.
   */
  private void go() {
    ConnectFourModel model = new ConnectFourModel();
    ViewFactory.getConnectFourListner(model);
    model.startGame();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Connect four app.";
  }
}
