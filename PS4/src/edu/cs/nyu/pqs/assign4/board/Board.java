package edu.cs.nyu.pqs.assign4.board;

import edu.cs.nyu.pqs.assign4.ConnectFourApp;
import edu.cs.nyu.pqs.assign4.board.Move.Builder;
import edu.cs.nyu.pqs.assign4.constants.ConstantsForGame;
import edu.cs.nyu.pqs.assign4.exception.IllegalMoveException;
import java.awt.Color;

/**
 * This is the main board class for playing the game of {@link ConnectFourApp}.
 * This class contains the actual board(ie layout) and methods to modify and analyze it.
 * To allow several people to play at a time this class is not defined as singleton.
 */
public class Board {
  private final Color[][] layout;
  private final int numberOfRows;
  private final int numberOfColumns;
  private Move lastLegalMove;

  /**
   * The constructor to the board requires the number of rows and columns the board must have.
   *
   * @param numberOfRows The number of rows the boards will have.
   * @param numberOfColumns The number of columns the board will have.
   */
  public Board(int numberOfRows, int numberOfColumns) {
    this.numberOfRows = numberOfRows;
    this.numberOfColumns = numberOfColumns;
    layout = new Color[numberOfRows][numberOfColumns];
    resetBoard();
    lastLegalMove = null;
  }

  /**
   * This method returns a copy of the actual board layout(matrix).
   * Though it takes number of rows * number of columns for making copies, since rows and
   * columns are fixed hence it makes a copy and returns in O(1) time.
   *
   * @return Copy of the board layout.
   */
  public Color[][] getLayout() {
    Color[][] returnBoard = new Color[numberOfRows][numberOfColumns];
    for (int i=0; i < numberOfRows; i++) {
      for (int j=0; j < numberOfColumns; j++) {
        returnBoard[i][j] = layout[i][j];
      }
    }
    return returnBoard;
  }

  /**
   * This method is used to reset the boards layout and set all the elements in matrix to
   * the {@link Color} WHITE
   */
  public void resetBoard() {
    for (int i=0; i < numberOfRows; i++) {
      for (int j=0; j < numberOfColumns; j++) {
        layout[i][j] = Color.WHITE;
      }
    }
  }

  /**
   * This method is used to add a piece to the board. The piece will be of a specified color and
   * will be added at a specified column. This information is wrapped in {@link Move} and passed to
   * the method.
   * The method will throw {@link IllegalMoveException} if no move is passed or if a move is made
   * at an already full column.
   *
   * @param move A class containing the color and column information where piece needs to be added.
   * @throws IllegalMoveException Will be thrown in case the a null move is passed or a move is made
   * at a already full column.
   */
  public void addPiece(Move move) throws IllegalMoveException {
    if (move == null) {
      throw new IllegalMoveException("Move cannot be null.");
    }
    int firstEmptyRowInColumn = getFirstEmptyRowInColumn(move.getColumnNumber());
    if (firstEmptyRowInColumn == -1) {
      throw new IllegalMoveException("This column is already full, please add to another column.");
    }
    lastLegalMove = move;
    layout[firstEmptyRowInColumn][move.getColumnNumber()] = move.getColor();
  }

  /**
   * This method is used to check if any more moves can be made.
   *
   * @return true if no more moves can be made, false otherwise.
   */
  public boolean checkGameOver() {
    for (int j=0; j < numberOfColumns; j++) {
      if (getFirstEmptyRowInColumn(j) != -1) {
        return false;
      }
    }
    return true;
  }

  /**
   * This method is used to check if a passed color has won(has specified number of tiles in a
   * vertical, horizotal, or diagonal row).
   *
   * @param color The color for which win needs to be checked.
   * @return true if the color passed has won, false otherwise.
   */
  public boolean checkColorWon(Color color) {
    return checkWinHorizontal(color) || checkWinMajorDiagonal(color)
        || checkWinMinorDiagonal(color) || checkWinVertical(color);
  }

  /**
   * This method is used to check what column could a move be made to for a specified color for that
   * color to win the game.
   *
   * @param color The color for which the win column needs to be found.
   * @return the column which will result in win for specified color, -1 if no such column exists.
   * @throws IllegalMoveException If the move being tried to make is illegal the error is thrown.
   */
  public int getWinColumn(Color color) throws IllegalMoveException {
    int winColumn = -1;
    for (int j=0; j < numberOfColumns; j++) {
      Move move = new Builder().setColumnNumber(j).setColor(color).build();
      //If the move is invalid then just move ahead.
      if (!checkValidMove(j)) {
        continue;
      }
      addPiece(move);
      if (checkColorWon(move.getColor())) {
        winColumn = j;
      }
      revertLastLegalMove();
      if (winColumn != -1) {
        break;
      }
    }
    return winColumn;
  }

  /**
   * Checks if another piece could be added to a certain column.
   *
   * @param moveColumn the coulmn to which the piece has to be added to.
   * @return true if the secified coulmn is not full, false otherwise.
   */
  public boolean checkValidMove(int moveColumn) {
    return layout[0][moveColumn] == Color.WHITE;
  }

  private boolean checkWinMinorDiagonal(Color color) {
    for (int i=3; i < numberOfRows; i++) {
      for (int j=0; j < numberOfColumns - 3; j++) {
        int k = 0;
        while (k < ConstantsForGame.checkConsecutiveForWin) {
          if (!layout[i - k][j + k].equals(color)) {
            break;
          }
          k++;
        }
        if (k == ConstantsForGame.checkConsecutiveForWin) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean checkWinMajorDiagonal(Color color) {
    for (int i=0; i < numberOfRows - 3; i++) {
      for (int j=0; j < numberOfColumns - 3; j++) {
        int k = 0;
        while (k < ConstantsForGame.checkConsecutiveForWin) {
          if (!layout[i + k][j + k].equals(color)) {
            break;
          }
          k++;
        }
        if (k == ConstantsForGame.checkConsecutiveForWin) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean checkWinHorizontal(Color color) {
    for (int i=0; i < numberOfRows; i++) {
      for (int j=0; j < numberOfColumns - 3; j++) {
        int k = 0;
        while (k < ConstantsForGame.checkConsecutiveForWin) {
          if (!layout[i][j + k].equals(color)) {
            break;
          }
          k++;
        }
        if (k == ConstantsForGame.checkConsecutiveForWin) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean checkWinVertical(Color color) {
    for (int i=0; i < numberOfRows - 3; i++) {
      for (int j=0; j < numberOfColumns; j++) {
        int k = 0;
        while (k < ConstantsForGame.checkConsecutiveForWin) {
          if (!layout[i + k][j].equals(color)) {
            break;
          }
          k++;
        }
        if (k == ConstantsForGame.checkConsecutiveForWin) {
          return true;
        }
      }
    }
    return false;
  }

  private void revertLastLegalMove() throws IllegalMoveException {
    int firstEmptyRowInColumn = getFirstEmptyRowInColumn(lastLegalMove.getColumnNumber());
    layout[firstEmptyRowInColumn + 1][lastLegalMove.getColumnNumber()] = Color.WHITE;
  }

  private int getFirstEmptyRowInColumn(int columnNumber) {
    for (int i=numberOfRows - 1; i >= 0; i--) {
      if (layout[i][columnNumber].equals(Color.WHITE)) {
        return i;
      }
    }
    return -1;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Board for ConnectFourGame";
  }
}
