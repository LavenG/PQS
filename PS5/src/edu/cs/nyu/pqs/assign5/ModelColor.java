package edu.cs.nyu.pqs.assign5;

import java.awt.Color;

/**
 * This is a color enum class to pass color information to model.
 * This enum shields model from exact UI information but still let's it store the color
 * values for events at UI.
 */
public enum ModelColor {
  WHITE(Color.WHITE),
  BLACK(Color.BLACK),
  RED(Color.RED),
  BLUE(Color.BLUE),
  GREEN(Color.GREEN),
  YELLOW(Color.YELLOW);

  private final Color color;

  /**
   * This method gives the actual color value for any enum value.
   *
   * @return The color of the enum variable.
   */
  public Color getColor() {
    return color;
  }

  /**
   * A static method to give an enum value based on the color value.
   *
   * @param color The actual color value at the scree.
   * @return The enum equivalent for the color value.
   * @throws IllegalArgumentException if the color provided doesn't match with any enum colors.
   */
  public static ModelColor getModelColor(Color color) {
    if (color == Color.BLACK) {
      return ModelColor.BLACK;
    } else if (color == Color.RED) {
      return ModelColor.RED;
    } else if (color == Color.BLUE) {
      return ModelColor.BLUE;
    } else if (color == Color.GREEN) {
      return ModelColor.GREEN;
    } else if (color == Color.YELLOW) {
      return ModelColor.YELLOW;
    } else if (color == Color.WHITE) {
      return ModelColor.WHITE;
    }
    throw new IllegalArgumentException("Not an accepted color for model color");
  }

  /**
   * This is a package private constructor used to create the enum objects with color values.
   *
   * @param color The actutal color of the enum object.
   */
  ModelColor(Color color) {
    this.color = color;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Color : " + name();
  }
}
