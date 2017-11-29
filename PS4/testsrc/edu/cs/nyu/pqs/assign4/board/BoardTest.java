package edu.cs.nyu.pqs.assign4.board;


import edu.cs.nyu.pqs.assign4.board.Move.Builder;
import edu.cs.nyu.pqs.assign4.exception.IllegalMoveException;
import java.awt.Color;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/*
 * Tests for board class.
 */
public class BoardTest {
  private Board board;
  private static final int numberOfRows = 6;
  private static final int numberOfColumns = 7;
  private static final Color firstPlayerColor = Color.BLUE;
  private static final Color secondPlayerColor = Color.RED;

  @Before
  public void setUp() {
    board = new Board(numberOfRows, numberOfColumns);
  }

  @Test
  public void getLayout() throws Exception {
    Color[][] layout = board.getLayout();
    assertEquals(numberOfRows, layout.length);
    assertEquals(numberOfColumns, layout[0].length);
    assertEquals(Color.WHITE, layout[0][0]);
    assertEquals(Color.WHITE, layout[numberOfRows-1][numberOfColumns-1]);
  }

  @Test
  public void resetBoard() throws Exception {
    board.resetBoard();
    for (int i=0; i < numberOfRows; i++) {
      for (int j=0; j < numberOfColumns; j++) {
        assertEquals(Color.WHITE, board.getLayout()[i][j]);
      }
    }
  }

  @Test
  public void resetBoard_AddItemAndReset() throws Exception {
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(firstPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(firstPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(firstPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(firstPlayerColor)
        .build());
    board.resetBoard();
    for (int i=0; i < numberOfRows; i++) {
      for (int j=0; j < numberOfColumns; j++) {
        assertEquals(Color.WHITE, board.getLayout()[i][j]);
      }
    }
  }

  @Test
  public void addPiece() throws Exception {
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(firstPlayerColor)
        .build());
    assertEquals(firstPlayerColor, board.getLayout()[numberOfRows-1][0]);
  }

  @Test
  public void addPiece_TwoInSameColumn() throws Exception {
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(firstPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(firstPlayerColor)
        .build());
    assertEquals(firstPlayerColor, board.getLayout()[numberOfRows-1][0]);
    assertEquals(firstPlayerColor, board.getLayout()[numberOfRows-2][0]);
  }

  @Test
  public void addPiece_TwoInDifferentColumn() throws Exception {
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(firstPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(1)
        .setColor(firstPlayerColor)
        .build());
    assertEquals(firstPlayerColor, board.getLayout()[numberOfRows-1][0]);
    assertEquals(firstPlayerColor, board.getLayout()[numberOfRows-1][1]);
  }

  @Test
  public void addPiece_TwoOfSameColor() throws Exception {
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(firstPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(5)
        .setColor(firstPlayerColor)
        .build());
    assertEquals(firstPlayerColor, board.getLayout()[numberOfRows-1][0]);
    assertEquals(firstPlayerColor, board.getLayout()[numberOfRows-1][5]);
  }

  @Test
  public void addPiece_TwoOfDifferentColor() throws Exception {
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(firstPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(secondPlayerColor)
        .build());
    assertEquals(firstPlayerColor, board.getLayout()[numberOfRows-1][0]);
    assertEquals(secondPlayerColor, board.getLayout()[numberOfRows-2][0]);
  }

  @Test (expected = IllegalMoveException.class)
  public void addPiece_NullMove() throws Exception {
    board.addPiece(null);
  }

  @Test (expected = IllegalMoveException.class)
  public void addPiece_ToFullColumn() throws Exception {
    for (int i = 0; i <= numberOfRows; i++) {
      board.addPiece(new Builder()
          .setColumnNumber(0)
          .setColor(firstPlayerColor)
          .build());
    }
  }

  @Test
  public void checkGameOver() throws Exception {
    assertFalse(board.checkGameOver());
  }

  @Test
  public void checkGameOver_GameNotOverWhenOneSLotLeft() throws Exception {
    for (int i=0; i < numberOfRows; i++) {
      for (int j=0; j < numberOfColumns; j++) {
        if (j == numberOfColumns -1 && i == 0) {
          continue;
        }
        board.addPiece(new Builder()
            .setColumnNumber(j)
            .setColor(firstPlayerColor)
            .build());
      }
    }
    assertFalse(board.checkGameOver());
  }

  @Test
  public void checkGameOver_GameOver() throws Exception {
    for (int i=0; i < numberOfRows; i++) {
      for (int j=0; j < numberOfColumns; j++) {
        board.addPiece(new Builder()
            .setColumnNumber(j)
            .setColor(firstPlayerColor)
            .build());
      }
    }
    assertTrue(board.checkGameOver());
  }

  @Test
  public void testCheckColorWon_NotWon() {
    assertFalse(board.checkColorWon(firstPlayerColor));
  }

  @Test
  public void testCheckColorWon_Vertical() throws Exception {
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(firstPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(firstPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(firstPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(firstPlayerColor)
        .build());
    assertTrue(board.checkColorWon(firstPlayerColor));
  }

  @Test
  public void testCheckColorWon_Horizontal() throws Exception {
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(firstPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(1)
        .setColor(firstPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(2)
        .setColor(firstPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(3)
        .setColor(firstPlayerColor)
        .build());
    assertTrue(board.checkColorWon(firstPlayerColor));
  }

  @Test
  public void testCheckColorWon_MinorDiagonal() throws Exception {
    //Add playerOne at bottom of first column
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(firstPlayerColor)
        .build());
    //Add player one at the second last position of second column
    board.addPiece(new Builder()
        .setColumnNumber(1)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(1)
        .setColor(firstPlayerColor)
        .build());
    //Add player one at the third last position of third column
    board.addPiece(new Builder()
        .setColumnNumber(2)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(2)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(2)
        .setColor(firstPlayerColor)
        .build());
    //Add player one at the fourth last position of fourth column
    board.addPiece(new Builder()
        .setColumnNumber(3)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(3)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(3)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(3)
        .setColor(firstPlayerColor)
        .build());
    assertTrue(board.checkColorWon(firstPlayerColor));
  }

  @Test
  public void testCheckColorWon_MajorDiagonal() throws Exception {
    //Add playerOne at bottom of fourth column
    board.addPiece(new Builder()
        .setColumnNumber(3)
        .setColor(firstPlayerColor)
        .build());
    //Add player one at the second last position of third column
    board.addPiece(new Builder()
        .setColumnNumber(2)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(2)
        .setColor(firstPlayerColor)
        .build());
    //Add player one at the third last position of second column
    board.addPiece(new Builder()
        .setColumnNumber(1)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(1)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(1)
        .setColor(firstPlayerColor)
        .build());
    //Add player one at the last position of fourth column
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(firstPlayerColor)
        .build());
    assertTrue(board.checkColorWon(firstPlayerColor));
  }

  @Test
  public void getWinColumn_Vertical() throws Exception {
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(firstPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(firstPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(firstPlayerColor)
        .build());
    assertEquals(0, board.getWinColumn(firstPlayerColor));
  }

  @Test
  public void getWinColumn_Horizontal() throws Exception {
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(firstPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(1)
        .setColor(firstPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(2)
        .setColor(firstPlayerColor)
        .build());
    assertEquals(3, board.getWinColumn(firstPlayerColor));
  }

  @Test
  public void getWinColumn_MinorDiagonal() throws Exception {
    //Add playerOne at bottom of first column
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(firstPlayerColor)
        .build());
    //Add player one at the second last position of second column
    board.addPiece(new Builder()
        .setColumnNumber(1)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(1)
        .setColor(firstPlayerColor)
        .build());
    //Add player one at the third last position of third column
    board.addPiece(new Builder()
        .setColumnNumber(2)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(2)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(2)
        .setColor(firstPlayerColor)
        .build());
    //Add player one at the fourth last position of fourth column
    board.addPiece(new Builder()
        .setColumnNumber(3)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(3)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(3)
        .setColor(secondPlayerColor)
        .build());
    assertEquals(3, board.getWinColumn(firstPlayerColor));
  }

  @Test
  public void getWinColumn_MajorDiagonal() throws Exception {
    //Add playerOne at bottom of fourth column
    board.addPiece(new Builder()
        .setColumnNumber(3)
        .setColor(firstPlayerColor)
        .build());
    //Add player one at the second last position of third column
    board.addPiece(new Builder()
        .setColumnNumber(2)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(2)
        .setColor(firstPlayerColor)
        .build());
    //Add player one at the third last position of second column
    board.addPiece(new Builder()
        .setColumnNumber(1)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(1)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(1)
        .setColor(firstPlayerColor)
        .build());
    //Add player one at the last position of fourth column
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(secondPlayerColor)
        .build());
    assertEquals(0, board.getWinColumn(firstPlayerColor));
  }

  @Test
  public void getWinColumn_MajorDiagonalEnd() throws Exception {
    //Add player one at the second last position of third column
    board.addPiece(new Builder()
        .setColumnNumber(2)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(2)
        .setColor(firstPlayerColor)
        .build());
    //Add player one at the third last position of second column
    board.addPiece(new Builder()
        .setColumnNumber(1)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(1)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(1)
        .setColor(firstPlayerColor)
        .build());
    //Add player one at the last position of fourth column
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(secondPlayerColor)
        .build());
    board.addPiece(new Builder()
        .setColumnNumber(0)
        .setColor(firstPlayerColor)
        .build());
    assertEquals(3, board.getWinColumn(firstPlayerColor));
  }

  @Test
  public void getWinColumn_NoWinColumn() throws Exception {
    assertEquals(-1, board.getWinColumn(firstPlayerColor));
  }

  @Test
  public void checkValidMove() throws Exception {
    for (int i=0; i < numberOfRows; i++) {
      assertTrue(board.checkValidMove(0));
      board.addPiece(new Builder()
          .setColumnNumber(0)
          .setColor(firstPlayerColor)
          .build());
    }
  }

  @Test
  public void checkValidMove_InvalidMove() throws Exception {
    for (int i=0; i < numberOfRows; i++) {
      assertTrue(board.checkValidMove(0));
      board.addPiece(new Builder()
          .setColumnNumber(0)
          .setColor(firstPlayerColor)
          .build());
    }
    assertFalse(board.checkValidMove(0));
  }

  @Test
  public void testToString() {
    assertEquals("Board for ConnectFourGame", board.toString());
  }

}