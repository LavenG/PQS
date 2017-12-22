package edu.cs.nyu.pqs.assign5.model;

import edu.cs.nyu.pqs.assign5.exception.IllegalOperationException;
import edu.cs.nyu.pqs.assign5.view.CanvasListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This is a model for Canvas paint app. All the actions take place here based on the changes at
 * UI. This model also invokes the necessary function calls based on changes in the view.
 */
public class CanvasModel {
  private final List<CanvasListener> listeners;
  private final Dimension dimension;
  private Color colorOfBrush;
  private int widthOfBrush;
  private Point previousPointOnCanvas;

  /**
   * A package private constructor for invoking an instance of {@link CanvasModel}.
   * We can only get an instance of this class by using the {@link ModelFactory}
   */
  CanvasModel(Dimension dimension) {
    listeners = new LinkedList<>();
    setBrushColor(Color.BLACK);
    widthOfBrush = 3;
    this.dimension = dimension;
  }

  /**
   * Called by the app when a new drawing is started.
   */
  public void startPaint() {
    firePaintStartedEvent();
  }

  /**
   * This method is used to get the dimension of the canvas.
   * It is used by the view to get the canvas dimensions.
   * It is added to model to record the canvas dimension for this interaction.
   *
   * @return A copy of the dimesnion of the canvas.
   */
  public Dimension getDimension() {
    return new Dimension(dimension);
  }

  /**
   * This method is used to get the current color being used.
   *
   * @return a copy of the color of brush in use.
   */
  public Color getBrushColor() {
    return new Color(colorOfBrush.getRed(), colorOfBrush.getGreen(), colorOfBrush.getBlue());
  }

  /**
   * This method is used to get the width of the brush.
   *
   * @return the width of line currently in use.
   */
  public int getWidthOfBrush() {
    return widthOfBrush;
  }

  /**
   * This method is used to set the changed color of brush from view to backend and inform all ohter
   * listeners about the same.
   *
   * @param color The new color the brush takes.
   */
  public void setColor(Color color) {
    setBrushColor(color);
    fireChangeColorInUse();
  }

  /**
   * This method is invoked by the view when a clear operation takes place.
   * It resets the brush color to black and informs all the listeners about the same.
   */
  public void clearCanvas() {
    setBrushColor(Color.BLACK);
    fireClearCanvas();
  }

  /**
   * This method is used to increase the width of brush.
   * This width won't increase beyond 30.
   *
   * @throws IllegalOperationException An exception is thrown if we try to increase width beyond 30.
   */
  public void increaseBrushWidth() throws IllegalOperationException {
    if (widthOfBrush > 30) {
      throw new IllegalOperationException("Brush width can't be greater than 30.");
    }
    widthOfBrush++;
  }

  /**
   * This method is used to increase the width of brush.
   * This width won't decrease beyond 1.
   * @throws IllegalOperationException An exception is thrown if we try to decrease width beyond 1.
   */
  public void decreaseBrushWidth() throws IllegalOperationException {
    if (widthOfBrush <= 1) {
      throw new IllegalOperationException("Brush width can't be less than 1.");
    }
    widthOfBrush--;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Canvas Model";
  }

  /**
   * This method is only used for testing purpose and returns new list of listeners.
   *
   * @return Copy of all the listeners the model holds.
   */
  public List<CanvasListener> getListenersForTest() {
    return new ArrayList<>(listeners);
  }

  /**
   * This method is used to draw all points when a mouse is dragged.
   *
   * @param x The x coordinate where mouse is dragging
   * @param y The y coordinate where mouse is dragging.
   * @throws IllegalArgumentException If the point is not within the dimension bound or any of
   * it's  coordinates is less than 0.
   * @throws IllegalOperationException If no previous point has been defined yet then the app has
   * been broken and should be restarted.
   */
  public void mouseDragEventHandlerAtModel(int x, int y) throws IllegalOperationException {
    if (x < 0 || y < 0 || x > dimension.getWidth() || y > dimension.getHeight()) {
      throw new IllegalArgumentException("Point out of bound");
    }
    if (previousPointOnCanvas == null) {
      throw new IllegalOperationException("No previous point. The app is broken. Restart.");
    }
    double xD = x - previousPointOnCanvas.getX();
    double yD = y - previousPointOnCanvas.getY();
    double diff = Math.max(Math.abs(xD), Math.abs(yD));
    double xOth = xD / diff;
    double yOth = yD / diff;
    double xS = previousPointOnCanvas.getX();
    double yS = previousPointOnCanvas.getY();
    for (int i = 0; i < diff; i++) {
      Point point = new Point((int) xS, (int) yS);
      drawPoint(point);
      xS += xOth;
      yS += yOth;
    }
    drawPoint(new Point(x, y));
    previousPointOnCanvas = new Point(x, y);
  }

  /**
   * The method records at the backend the operation of a mouse being pressed.
   *
   * @param point The point where the press action took place.
   * @throws IllegalArgumentException If the point is not within the dimension bound or any of
   * it's  coordinates is less than 0.
   */
  public void mousePressEventHandlerAtModel(Point point) {
    if (point.getX() < 0 || point.getY() < 0
        || point.getX() > dimension.getWidth() || point.getY() > dimension.getHeight()) {
      throw new IllegalArgumentException("Point out of bound");
    }
    previousPointOnCanvas = point;
    drawPoint(point);
  }

  /**
   * This method is invoked to inform the listener that a point has been drawn.
   * It in turn informs all the listener about the same.
   *
   * @param point The point that needs to be drawn across all listener.
   * @throws IllegalArgumentException if the point is out of dimesion bound either before or after
   * computation.
   */
  public void drawPoint(Point point) {
    if (point.getX() < 0 || point.getY() < 0
        || point.getX() > dimension.getWidth() || point.getY() > dimension.getHeight()) {
      throw new IllegalArgumentException("Point out of dimension bound.");
    }
    int drewX = (int) (point.getX() - widthOfBrush / 2);
    int drewY = (int) (point.getY() - widthOfBrush / 2);
    if (drewX < 0 || drewY < 0) {
      throw new IllegalArgumentException("Point out of dimension bound.");
    }
    fireDrewOnCanvas(drewX, drewY);
  }

  /**
   * Used to add listener to the list of listener.
   *
   * @param listener listener to be added which can not be null.
   * @throws IllegalArgumentException This method will throw an IllegalArgumentException if null
   * listener is passed.
   */
  public void registerListener(CanvasListener listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Listener cannot be null.");
    }
    listeners.add(listener);
  }

  /**
   * Used to remove listener from list of listener. If the listener doesn't exist then nothing
   * happens.
   *
   * @param listener listener to be removed which can not be null.
   * @throws IllegalArgumentException This method will throw an IllegalArgumentException if null
   * listener is passed.
   */
  public void deRegisterListener(CanvasListener listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Listener cannot be null.");
    }
    listeners.remove(listener);
  }

  private void firePaintStartedEvent() {
    for (CanvasListener listener : listeners) {
      listener.firePaintStartedEvent();
    }
  }

  private void fireDrewOnCanvas(int x, int y) {
    for (CanvasListener listener : listeners) {
      listener.drewOnCanvas(x, y, widthOfBrush);
    }
  }

  private void fireChangeColorInUse() {
    for (CanvasListener listener : listeners) {
      listener.changeBrushColorInUse(getBrushColor());
    }
  }

  private void fireClearCanvas() {
    for (CanvasListener listener : listeners) {
      listener.clearCanvasAndResetBrushColor();
    }
  }

  private void setBrushColor(Color brushColor) {
    this.colorOfBrush = brushColor;
  }
}