package edu.cs.nyu.pqs.assign5.exception;

/**
 * This class extends {@link Exception} and is invoked in case of incorrect operation.
 */
public class IllegalOperationException extends Exception {

  /**
   * The constructor for raising this exception. We always need a message for an exception of this
   * type.
   *
   * @param message The message detailing the cause of exception.
   */
  public IllegalOperationException(String message) {
    super(message);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Illegal Operation Exception class with message : " + getMessage();
  }
}
