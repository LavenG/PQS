package edu.cs.nyu.pqs.assign5.model;


import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Dimension;
import org.junit.Test;

/**
 * Created by naman on 12/21/2017.
 */
public class ModelFactoryTest {

  @Test
  public void getCanvasModelTest() throws Exception {
    assertTrue(ModelFactory.getCanvasModel(new Dimension(2,2)) instanceof CanvasModel);
  }

  @Test
  public void toStringTest() throws Exception {
    assertEquals("Canvas Model Factory", new ModelFactory().toString());
  }

}