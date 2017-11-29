package edu.cs.nyu.pqs.assign4.view;


import static org.junit.Assert.assertEquals;

import edu.cs.nyu.pqs.assign4.model.ConnectFourModel;
import org.junit.Test;

/*
 * This class contains view information and hence I am not testing the UI methods.
 */
public class ConnectFourSpringViewTest {
  private static final ConnectFourModel connectFourModel = new ConnectFourModel();

  @Test
  public void testToString() throws Exception {
    assertEquals("Connect four spring view.",
        new ConnectFourSpringView(connectFourModel).toString());
  }

}