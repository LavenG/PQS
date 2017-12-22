package edu.cs.nyu.pqs.assign5.model;

import java.awt.Dimension;

/**
 * This is a factory class for getting an instance of {@link CanvasModel}.
 */
public class ModelFactory {

  /**
   * This method is called to get an instance of {@link CanvasModel}
   *
   * @return An instance of {@link CanvasModel}
   */
  public static CanvasModel getCanvasModel(Dimension dimension) {
    return new CanvasModel(dimension);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Canvas Model Factory";
  }
}