package edu.cs.nyu.pqs.assign5.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import edu.cs.nyu.pqs.assign5.exception.IllegalOperationException;
import edu.cs.nyu.pqs.assign5.view.CanvasListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for Canvas Model.
 */
public class CanvasModelTest {
  private CanvasModel model;
  private CanvasListener listener;
  private Dimension dimension;
  private Color color;
  private int widthOfBrush;

  @Before
  public void setUp() {
    dimension = new Dimension(6,6);
    color = Color.BLACK;
    widthOfBrush = 3;
    model = new CanvasModel(dimension);
    listener = mock(CanvasListener.class);
  }

  @Test
  public void startPaintTest() throws Exception {
    model.registerListener(listener);
    model.startPaint();
    verify(listener, times(1)).firePaintStartedEvent();
  }

  @Test
  public void getDimensionTest() throws Exception {
    assertEquals(dimension, model.getDimension());
  }

  @Test
  public void getBrushColorTest() throws Exception {
    assertEquals(Color.BLACK, model.getBrushColor());
  }

  @Test
  public void getWidthOfBrushTest() throws Exception {
    assertEquals(3, model.getWidthOfBrush());
  }

  @Test
  public void setColorTest() throws Exception {
    model.registerListener(listener);
    model.setColor(color);
    verify(listener, times(1)).changeBrushColorInUse(color);
    assertEquals(color, model.getBrushColor());
  }

  @Test
  public void clearCanvasTest() throws Exception {
    model.registerListener(listener);
    model.setColor(Color.CYAN);
    model.clearCanvas();
    verify(listener, times(1)).clearCanvasAndResetBrushColor();
    assertEquals(color, model.getBrushColor());
  }

  @Test
  public void increaseBrushWidthTest() throws Exception {
    model.increaseBrushWidth();
    assertEquals(4, model.getWidthOfBrush());
  }

  @Test (expected = IllegalOperationException.class)
  public void increaseBrushWidthTest_Beyond30() throws Exception {
    for (int i = 0; i < 30; i++) {
      model.increaseBrushWidth();
    }
  }

  @Test
  public void decreaseBrushWidthTest() throws Exception {
    model.decreaseBrushWidth();
    assertEquals(2, model.getWidthOfBrush());
  }

  @Test (expected = IllegalOperationException.class)
  public void decreaseBrushWidthTest_LessThan1() throws Exception {
    for (int i = 0; i < 4 ; i++) {
      model.decreaseBrushWidth();
    }
  }

  @Test
  public void mouseDragEventHandlerForModelTest() throws Exception {
    model.registerListener(listener);
    model.mousePressEventHandlerAtModel(new Point(1,1));
    model.mouseDragEventHandlerAtModel(2,2);
    verify(listener, times(1)).drewOnCanvas(1,1,3);
  }

  @Test (expected = IllegalOperationException.class)
  public void mouseDragEventHandlerForModelTest_NoPreviousPoint() throws Exception {
    model.registerListener(listener);
    model.mouseDragEventHandlerAtModel(2,2);
    verify(listener, times(1)).drewOnCanvas(1,1,3);
  }

  @Test (expected = IllegalArgumentException.class)
  public void mouseDragEventHandlerForModelTest_IllegalStartPointNegativeX() throws Exception {
    model.mouseDragEventHandlerAtModel(-1,2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void mouseDragEventHandlerForModelTest_IllegalStartPointNegativeY() throws Exception {
    model.mouseDragEventHandlerAtModel(2,-1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void mouseDragEventHandlerForModelTest_IllegalStartPointGraterX() throws Exception {
    model.mouseDragEventHandlerAtModel(10,2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void mouseDragEventHandlerForModelTest_IllegalStartPointGreaterY() throws Exception {
    model.mouseDragEventHandlerAtModel(2,10);
  }

  @Test
  public void mousePressEventHandlerAtModelTest() throws Exception {
    model.registerListener(listener);
    model.mousePressEventHandlerAtModel(new Point(2,2));
    verify(listener, times(1)).drewOnCanvas(1,1,3);
  }

  @Test (expected = IllegalArgumentException.class)
  public void mousePressEventHandlerAtModelTest_IllegalStartPointNegativeX() throws Exception {
    model.mousePressEventHandlerAtModel(new Point(-1,2));
  }

  @Test (expected = IllegalArgumentException.class)
  public void mousePressEventHandlerAtModelTest_IllegalStartPointNegativeY() throws Exception {
    model.mousePressEventHandlerAtModel(new Point(2,-1));
  }

  @Test (expected = IllegalArgumentException.class)
  public void mousePressEventHandlerAtModelTest_IllegalStartPointGraterX() throws Exception {
    model.mousePressEventHandlerAtModel(new Point(10,2));
  }

  @Test (expected = IllegalArgumentException.class)
  public void mousePressEventHandlerAtModelTest_IllegalStartPointGreaterY() throws Exception {
    model.mousePressEventHandlerAtModel(new Point(2,10));
  }

  @Test
  public void drawPointTest() throws Exception {
    model.registerListener(listener);
    model.drawPoint(new Point(3,3));
    verify(listener, times(1)).drewOnCanvas(2,2,3);
  }

  @Test (expected = IllegalArgumentException.class)
  public void drawPointTest_IllegalStartPointNegativeX() throws Exception {
    model.drawPoint(new Point(-1,2));
  }

  @Test (expected = IllegalArgumentException.class)
  public void drawPointTest_IllegalStartPointNegativeY() throws Exception {
    model.drawPoint(new Point(2,-1));
  }

  @Test (expected = IllegalArgumentException.class)
  public void drawPointTest_IllegalStartPointGraterX() throws Exception {
    model.drawPoint(new Point(10,2));
  }

  @Test (expected = IllegalArgumentException.class)
  public void drawPointTest_IllegalStartPointGreaterY() throws Exception {
    model.drawPoint(new Point(2,10));
  }

  @Test (expected = IllegalArgumentException.class)
  public void drawPointTest_IllegalPointAfterComputationNegativeX() throws Exception {
    model.drawPoint(new Point(0,2));
  }

  @Test (expected = IllegalArgumentException.class)
  public void drawPointTest_IllegalPointAfterComputationNegativeY() throws Exception {
    model.drawPoint(new Point(2,0));
  }

  @Test
  public void registerListenerTest() throws Exception {
    model.registerListener(listener);
    assertEquals(1,model.getListenersForTest().size());
    assertEquals(listener, model.getListenersForTest().get(0));
  }

  @Test (expected = IllegalArgumentException.class)
  public void registerListenerTest_NullListener() throws Exception {
    model.registerListener(null);
  }

  @Test
  public void deRegisterListenerTest() throws Exception {
    model.registerListener(listener);
    model.deRegisterListener(listener);
    assertEquals(0, model.getListenersForTest().size());
  }

  @Test (expected = IllegalArgumentException.class)
  public void deRegisterListenerTest_NullListener() throws Exception {
    model.deRegisterListener(null);
  }

  @Test
  public void toStringTest() throws Exception {
    assertEquals("Canvas Model", model.toString());
  }

}