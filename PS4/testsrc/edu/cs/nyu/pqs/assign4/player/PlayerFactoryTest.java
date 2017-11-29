package edu.cs.nyu.pqs.assign4.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import org.junit.Test;

/*
 *
 */
public class PlayerFactoryTest {
  private static final String playerName = "Abc";
  private static final Color color = Color.BLUE;

  @Test
  public void getPlayer_GetHumanPlayer() throws Exception {
    assertTrue(PlayerFactory.getPlayer(playerName, color, true) instanceof
        HumanPlayer);
    assertFalse(PlayerFactory.getPlayer(playerName, color, true) instanceof
        ComputerPlayer);
  }

  @Test
  public void getPlayer_GetComputerPlayer() throws Exception {
    assertTrue(PlayerFactory.getPlayer(playerName, color, false) instanceof
        ComputerPlayer);
    assertFalse(PlayerFactory.getPlayer(playerName, color, false) instanceof
        HumanPlayer);
  }

  @Test
  public void testToString() {
    assertEquals("Player factory.", new PlayerFactory().toString());
  }

}