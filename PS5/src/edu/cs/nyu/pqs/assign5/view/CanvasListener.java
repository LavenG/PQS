package edu.cs.nyu.pqs.assign5.view;

import java.awt.Color;

/**
 * An interface or contract for all the listeners interested in using the canvas to draw.
 */
public interface CanvasListener {

  /**
   * This method is invoked by the model to inform the listener about the start of a paint session.
   */
  void firePaintStartedEvent();

  /**
   * This method is invoked by the model to inform the listener that somewhen has made changes to
   * the canvas.
   *
   * @param x The x coordinate on canvas where the drawing has happened.
   * @param y The y coordinate on canvas where the drawing has happened.
   * @param lineWidth The width of the line used for painting.
   */
  void drewOnCanvas(int x, int y, int lineWidth);

  /**
   * This method is invoked by the model to inform the listener that the canvas was cleared.
   * This method also resets the color of the brush to the original color which is Black.
   */
  void clearCanvasAndResetBrushColor();

  /**
   * This method is invoked by the model to informt the listener that the color being used has been
   * changed.
   */
  void changeBrushColorInUse(Color color);
}