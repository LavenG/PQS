package edu.cs.nyu.pqs.assign4.player;

import static org.junit.Assert.assertEquals;

import edu.cs.nyu.pqs.assign4.board.Board;
import edu.cs.nyu.pqs.assign4.exception.IllegalMoveException;
import edu.cs.nyu.pqs.assign4.model.ConnectFourModel;
import java.awt.Color;
import org.junit.Before;
import org.junit.Test;

/*
 *
 */
public class HumanPlayerTest {
  private HumanPlayer humanPlayer;
  private static final String name = "Abc";
  private static final Color color = Color.BLUE;
  private static final ConnectFourModel model= new ConnectFourModel();
  private static final Board board = new Board(6,7);
  private static final Color opponentsColor = Color.BLUE;

  @Before
  public void setUp() {
    humanPlayer = new HumanPlayer(name, color);
  }

  @Test
  public void testToString() throws Exception {
    assertEquals("Human Player: " + name + ", " + color.toString(), humanPlayer.toString());
  }

  @Test (expected = IllegalMoveException.class)
  public void testMakeOptimalMove() throws Exception {
    humanPlayer.makeOptimalMove(model, board, opponentsColor);
  }

}