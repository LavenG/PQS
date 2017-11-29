package edu.cs.nyu.pqs.assign4.exception;

/**
 * This class extends {@link Exception} and is invoked in case of incorrect moves.
 * There can be several type of incorrect moves and the message is used to determine the actual
 * reason for exception.
 */
public class IllegalMoveException extends Exception {

  /**
   * The constructor for raising this exception. We always need a message for an exception of this
   * type.
   *
   * @param message The message detailing the cause of exception.
   */
  public IllegalMoveException(String message) {
    super(message);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Illegal Move Exception class with message : " + getMessage();
  }
}
