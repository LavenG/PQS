package edu.cs.nyu.pqs.assign5.view;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import edu.cs.nyu.pqs.assign5.model.CanvasModel;
import edu.cs.nyu.pqs.assign5.model.ModelFactory;
import java.awt.Dimension;
import org.junit.Test;

/**
 * Test for View Factory.
 */
public class ViewFactoryTest {

  private static final CanvasModel model =
      ModelFactory.getCanvasModel(new Dimension(600,600));

  @Test
  public void getCanvasListnerTest() throws Exception {
    assertTrue(ViewFactory.getCanvasListener(model) instanceof CanvasSpringView);
  }

  @Test
  public void testToString() throws Exception {
    assertEquals("view Factory.", new ViewFactory().toString());
  }

}