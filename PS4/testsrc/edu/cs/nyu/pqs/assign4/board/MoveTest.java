package edu.cs.nyu.pqs.assign4.board;

import static org.junit.Assert.assertEquals;

import edu.cs.nyu.pqs.assign4.board.Move.Builder;
import edu.cs.nyu.pqs.assign4.exception.IllegalMoveException;
import java.awt.Color;
import org.junit.Before;
import org.junit.Test;

/*
 * Tests for Move class.
 */
public class MoveTest {
  private Move move;
  private static final int columnNumber = 4;
  private static final int columnNumberUnderflow = -2;
  private static final int columnNumberOverflow = 12;
  private static final Color illegalColor = Color.WHITE;
  private static final Color firstPlayerColor = Color.BLUE;
  private static final Color secondPlayerColor = Color.RED;

  @Before
  public void setUp() throws Exception {
    move = new Builder()
        .setColumnNumber(columnNumber)
        .setColor(secondPlayerColor)
        .build();
  }

  @Test (expected = IllegalMoveException.class)
  public void testCreateMove_NoArguments() throws Exception {
    new Builder().build();
  }

  @Test (expected = IllegalMoveException.class)
  public void testCreateMove_NoColor() throws Exception {
    new Builder()
        .setColumnNumber(columnNumber)
        .build();
  }

  @Test (expected = IllegalMoveException.class)
  public void testCreateMove_NoColumnNumber() throws Exception {
    new Builder()
        .setColor(firstPlayerColor)
        .build();
  }

  @Test (expected = IllegalMoveException.class)
  public void testCreateMove_IllegalColumnNumberUnderflow() throws Exception {
    new Builder()
        .setColumnNumber(columnNumberUnderflow)
        .setColor(firstPlayerColor)
        .build();
  }

  @Test (expected = IllegalMoveException.class)
  public void testCreateMove_IllegalColumnNumberOverflow() throws Exception {
    new Builder()
        .setColumnNumber(columnNumberOverflow)
        .setColor(firstPlayerColor)
        .build();
  }

  @Test (expected = IllegalMoveException.class)
  public void testCreateMove_IllegalColor() throws Exception {
    new Builder()
        .setColumnNumber(columnNumber)
        .setColor(illegalColor)
        .build();
  }

  @Test
  public void testGetColor() throws Exception {
    assertEquals(secondPlayerColor, move.getColor());
  }

  @Test
  public void testGetColumnNumber() throws Exception {
    assertEquals(columnNumber, move.getColumnNumber());
  }

  @Test
  public void testToString() throws Exception {
    assertEquals("Move : " + String.valueOf(columnNumber)
        + String.valueOf(secondPlayerColor),
        move.toString());
  }
}