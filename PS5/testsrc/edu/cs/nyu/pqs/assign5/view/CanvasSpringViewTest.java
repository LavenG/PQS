package edu.cs.nyu.pqs.assign5.view;

import static junit.framework.TestCase.assertEquals;

import edu.cs.nyu.pqs.assign5.model.ModelFactory;
import java.awt.Dimension;
import org.junit.Test;

/**
 * This
 */
public class CanvasSpringViewTest {

  @Test
  public void toStringTest() throws Exception {
    assertEquals("Canvas Spring View",
        new CanvasSpringView(ModelFactory
            .getCanvasModel(new Dimension(2,2))).toString());
  }

}