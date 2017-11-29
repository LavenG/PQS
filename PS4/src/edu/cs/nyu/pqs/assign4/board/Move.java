package edu.cs.nyu.pqs.assign4.board;

import edu.cs.nyu.pqs.assign4.constants.ConstantsForGame;
import edu.cs.nyu.pqs.assign4.exception.IllegalMoveException;
import java.awt.Color;

/**
 * This class contains the information for a move that can be made.
 * It can only be initialized using {@link Builder}.
 */
public class Move {
  private final int columnNumber;
  private final Color color;

  private Move(int columnNumber, Color color) throws IllegalMoveException {
    if (columnNumber < 0 || columnNumber > ConstantsForGame.numColumns-1) {
      throw new IllegalMoveException("Illegal column number.");
    }
    if (!(color != null && (color.equals(Color.BLUE) || color.equals(Color.RED)))) {
      throw new IllegalMoveException("Illegal color.");
    }
    this.columnNumber = columnNumber;
    this.color = color;
  }

  /**
   * Used to get the color for the move.
   *
   * @return the move's color.
   */
  public Color getColor() {
    return color;
  }

  /**
   * Used to get the column number for the move.
   *
   * @return the move' column number.
   */
  public int getColumnNumber() {
    return columnNumber;
  }

  /**
   * This is a Builder for {@link Move}. It is used to create a move object.
   */
  public static class Builder {
    private int columnNumber = -1;
    private Color color;

    /**
     * This is used to set column number information for {@link Move}.
     * The possible values for column number are: 0 to number of columns specified (default 7.)
     *
     * @param columnNumber the column number to be set to the {@link Move} object.
     * @return an instance of the {@link Builder}.
     */
    public Builder setColumnNumber(int columnNumber) {
      this.columnNumber = columnNumber;
      return this;
    }

    /**
     * This is used to set color information for {@link Move}.
     * The possible values for color are: Color.RED and Color.BLUE.
     *
     * @param color the color to be set to the {@link Move} object.
     * @return an instance of the {@link Builder}.
     */
    public Builder setColor(Color color) {
      this.color = color;
      return this;
    }

    /**
     * This will create a {@link Move} object and return it. It will throw an error if value for
     * column number or color is not set or is set incorrectly. The possible values for color are:
     * Color.RED and Color.BLUE. The possible values for column number are: 0 to number of columns
     * specified (default 7.)
     *
     * @return the move object created with the field values specified.
     * @throws IllegalMoveException this is thrown in case of missing or incorrect column and color
     * value.
     */
    public Move build() throws IllegalMoveException {
      return new Move(columnNumber, color);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Move : " + String.valueOf(columnNumber) + String.valueOf(color);
  }
}
