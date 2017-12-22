package edu.cs.nyu.pqs.assign5.view;

import edu.cs.nyu.pqs.assign5.model.CanvasModel;

/**
 * This factory class is used to return an implemtation of type {@link CanvasListener}.
 */
public class ViewFactory {

  /**
   * This is used to return an instance of implementation of type {@link CanvasListener}.
   *
   * @param model A model of type {@link CanvasModel} that the listener will register itself to.
   * @return An instance of the listener implementation.
   */
  public static CanvasListener getCanvasListener(CanvasModel model) {
    return new CanvasSpringView(model);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "view Factory.";
  }
}
