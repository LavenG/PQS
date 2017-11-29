package edu.cs.nyu.pqs.assign4;

import static org.junit.Assert.*;

import org.junit.Test;

/*
 * This is the app class and nothing is done here except for starting a game.
 * Also testing main method in this case doesn't solve any purpose as we just start the game from
 * main.
 */
public class ConnectFourAppTest {

  @Test
  public void testToString() throws Exception {
    assertEquals("Connect four app.", new ConnectFourApp().toString());
  }

}