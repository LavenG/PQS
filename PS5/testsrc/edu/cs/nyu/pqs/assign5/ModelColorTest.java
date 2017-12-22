package edu.cs.nyu.pqs.assign5;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import org.junit.Test;

/**
 * Test for ModelColor enum
 */
public class ModelColorTest {

  @Test
  public void getColor() throws Exception {
    assertEquals(Color.RED, ModelColor.RED.getColor());
  }

  @Test
  public void getModelColorTest() throws Exception {
    assertEquals(ModelColor.BLACK, ModelColor.getModelColor(Color.BLACK));
    assertEquals(ModelColor.BLUE, ModelColor.getModelColor(Color.BLUE));
    assertEquals(ModelColor.GREEN, ModelColor.getModelColor(Color.GREEN));
    assertEquals(ModelColor.RED, ModelColor.getModelColor(Color.RED));
    assertEquals(ModelColor.YELLOW, ModelColor.getModelColor(Color.YELLOW));
    assertEquals(ModelColor.WHITE, ModelColor.getModelColor(Color.WHITE));
  }

  @Test (expected = IllegalArgumentException.class)
  public void getModelColorTest_ColorDoenNotExistInEnum() throws Exception {
    ModelColor.getModelColor(Color.CYAN);
  }

  @Test
  public void toStringTest() throws Exception {
    assertEquals("Color : BLUE", ModelColor.BLUE.toString());
  }

}