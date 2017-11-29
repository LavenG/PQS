package edu.cs.nyu.pqs.assign4.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import edu.cs.nyu.pqs.assign4.model.ConnectFourModel;
import org.junit.Test;

/*
 *
 */
public class ViewFactoryTest {
  private static final ConnectFourModel connectFourModel = new ConnectFourModel();

  @Test
  public void getConnectFourListner() throws Exception {
    assertTrue(ViewFactory.getConnectFourListner(connectFourModel)
        instanceof ConnectFourSpringView);
  }

  @Test
  public void testToString() throws Exception {
    assertEquals("View Factory.", new ViewFactory().toString());
  }

}