package edu.cs.nyu.pqs.assign5;

import static junit.framework.TestCase.assertEquals;

import org.junit.Test;

/**
 * This is test class for app.
 */
public class CanvasAppTest {

  @Test
  public void toStringTest() throws Exception {
    assertEquals("Canvas App", new CanvasApp().toString());
  }

}