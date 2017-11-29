package edu.cs.nyu.pqs.assign4.player;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import edu.cs.nyu.pqs.assign4.board.Board;
import edu.cs.nyu.pqs.assign4.exception.IllegalMoveException;
import edu.cs.nyu.pqs.assign4.model.ConnectFourModel;
import java.awt.Color;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

/*
 *
 */
public class PlayerTest {
  private Player player;
  private Player nullNamePlayer;
  private static final String name = "Abc";
  private static final Color color = Color.RED;

  @Before
  public void setUp() {
    player = new Player(name, color) {
      @Override
      public void makeOptimalMove(ConnectFourModel model, Board board, Color opponentsColor)
          throws IllegalMoveException {

      }
    };
    nullNamePlayer = new Player(null, color) {
      @Override
      public void makeOptimalMove(ConnectFourModel model, Board board, Color opponentsColor)
          throws IllegalMoveException {

      }
    };
  }

  @Test
  public void testCreate() {
    player = new Player(name, color) {
      @Override
      public void makeOptimalMove(ConnectFourModel model, Board board, Color opponentsColor)
          throws IllegalMoveException {

      }
    };
  }

  @Test (expected = IllegalArgumentException.class)
  public void testCreat_IncorrectColor() {
    player = new Player(name, Color.WHITE) {
      @Override
      public void makeOptimalMove(ConnectFourModel model, Board board, Color opponentsColor)
          throws IllegalMoveException {

      }
    };
  }

  @Test (expected = IllegalArgumentException.class)
  public void testCreat_NullColor() {
    player = new Player(name, null) {
      @Override
      public void makeOptimalMove(ConnectFourModel model, Board board, Color opponentsColor)
          throws IllegalMoveException {

      }
    };
  }

  @Test
  public void testGetName() throws Exception {
    assertEquals(name, player.getName());
  }

  @Test
  public void testGetColor() throws Exception {
    assertEquals(color, player.getColor());
  }

  @Test
  public void testToString() throws Exception {
    assertEquals(name + ", " + color, player.toString());
  }

  @Test
  public void testEquals() {
    assertTrue(player.equals(new Player(name, color) {
      @Override
      public void makeOptimalMove(ConnectFourModel model, Board board, Color opponentsColor)
          throws IllegalMoveException {

      }
    }));
  }

  @Test
  public void testEquals_nullName() {
    assertTrue(nullNamePlayer.equals(new Player(null, color) {
      @Override
      public void makeOptimalMove(ConnectFourModel model, Board board, Color opponentsColor)
          throws IllegalMoveException {
      }
    }));
  }

  @Test
  public void testEquals_Unequal() {
    assertFalse(nullNamePlayer.equals(player));
  }

  @Test
  public void testEquals_UnequalSecondHasNull() {
    assertFalse(player.equals(nullNamePlayer));
  }

  @Test
  public void testEquals_IncorrectObject() {
    assertFalse(player.equals(new Object()));
  }

  @Test
  public  void testEquals_NameUnequal() {
    assertFalse(player.equals(new Player("name",color) {
      @Override
      public void makeOptimalMove(ConnectFourModel model, Board board, Color opponentsColor)
          throws IllegalMoveException {

      }
    }));
  }

  @Test
  public void testEquals_ColorUnequal() {
    assertFalse(player.equals(new Player(name,Color.BLUE) {
      @Override
      public void makeOptimalMove(ConnectFourModel model, Board board, Color opponentsColor)
          throws IllegalMoveException {

      }
    }));
  }

  @Test
  public void testHashCode() {
    Set<Player> set = new HashSet<>();
    set.add(player);
    set.add(nullNamePlayer);
    assertTrue(set.contains(new Player(name, color) {
      @Override
      public void makeOptimalMove(ConnectFourModel model, Board board, Color opponentsColor)
          throws IllegalMoveException {

      }
    }));
    assertTrue(set.contains(new Player(null, color) {
      @Override
      public void makeOptimalMove(ConnectFourModel model, Board board, Color opponentsColor)
          throws IllegalMoveException {

      }
    }));
    assertFalse(set.contains(new Player(name, Color.BLUE) {
      @Override
      public void makeOptimalMove(ConnectFourModel model, Board board, Color opponentsColor)
          throws IllegalMoveException {

      }
    }));
  }
}