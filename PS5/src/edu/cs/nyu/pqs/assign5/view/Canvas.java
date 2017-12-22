package edu.cs.nyu.pqs.assign5.view;

import edu.cs.nyu.pqs.assign5.CanvasApp;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.JPanel;

/**
 * This is the Canvas class used to hold the drawing information for {@link CanvasSpringView}.
 * We have extended JPanel to add methods and data structures specific to the task of painting.
 * This is the actual canvas panel where the coloring happens. We record the coloring info
 * here by using this extension in the view.
 */
class Canvas extends JPanel {
  private Image image;
  private Graphics brush;

  /**
   * This is the constructor to invoke an instance of Canvas.
   * The dimensions of canvas is taken as input an instance is created which is used by the view.
   *
   * @param dimension The dimension of the canvas
   */
  Canvas(Dimension dimension) {
    super();
    super.setSize(dimension);
  }

  /**
   * This method is used to clear the canvas being used on the Front-end {@link CanvasSpringView}
   * of {@link CanvasApp}
   */
  public void clearCanvas() {
    updateBrushColor(Color.WHITE);
    brush.fillRect(0, 0, getWidth(), getHeight());
    repaint();
    updateBrushColor(Color.BLACK);
  }

  /**
   * This method is used to udate the color of brush in use. By changing the color value
   * we actually update the color that's produce on mouse click or drag.
   *
   * @param color The color to be set as the brush color.
   */
  public void updateBrushColor(Color color) {
    brush.setColor(color);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    if (image == null) {
      image = createImage(getWidth(), getHeight());
      brush = image.getGraphics();
      clearCanvas();
    }
    Rectangle rectangle = graphics.getClipBounds();
    int dx2 = rectangle.width + rectangle.x;
    int dy2 = rectangle.height + rectangle.y;
    int sx2 = rectangle.width + rectangle.y;
    int sy2 = rectangle.height + rectangle.y;
    graphics.drawImage(image, rectangle.x, rectangle.y, dx2, dy2,
        rectangle.x, rectangle.y, sx2, sy2, null);
  }

  /**
   * This method is used to record the stroke of the brush. It makes a rectangle of the brush color,
   * which has the size as th brush width.
   *
   * @param x The x coordinate where brush click happened.
   * @param y The y coordinate where brush click happened.
   * @param lineWidth The width of the brush tip.
   */
  protected void recordBrushStroke(int x, int y, int lineWidth) {
    brush.fillRect(x, y, lineWidth, lineWidth);
    repaint();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Canvas for paint.";
  }
}
