package edu.cs.nyu.pqs.assign4.exception;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

/*
 * This method just extends an exception hence needed be tested. I am only performing a test for
 * toString.
 */
public class IllegalMoveExceptionTest {

  @Test
  public void testToString() throws Exception {
    assertEquals("Illegal Move Exception class with message : Some message",
        new IllegalMoveException("Some message").toString());
  }

}