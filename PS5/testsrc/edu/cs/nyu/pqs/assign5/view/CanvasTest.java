package edu.cs.nyu.pqs.assign5.view;

import static junit.framework.TestCase.assertEquals;

import java.awt.Dimension;
import org.junit.Test;

/**
 * Test for Canvas.
 */
public class CanvasTest {

  @Test
  public void toStringTest() throws Exception {
    assertEquals("Canvas for paint.",
        new Canvas(new Dimension(300,300)).toString());
  }

}