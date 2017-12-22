package edu.cs.nyu.pqs.assign5;

import edu.cs.nyu.pqs.assign5.model.CanvasModel;
import edu.cs.nyu.pqs.assign5.model.ModelFactory;
import edu.cs.nyu.pqs.assign5.view.ViewFactory;
import java.awt.Dimension;

/**
 * This is the main app to run the paint.
 */
public class CanvasApp {

  /**
   * The main method that invokes the game.
   *
   * @param args The command line arguments passed.
   */
  public static void main(String[] args) {
    new CanvasApp().launch();
  }

  /**
   * This instance method is called by main to launch the paint screen.
   */
  private void launch() {
    CanvasModel model = ModelFactory.getCanvasModel(new Dimension(700, 700));
    ViewFactory.getCanvasListener(model);
    ViewFactory.getCanvasListener(model);
    model.startPaint();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Canvas App";
  }
}