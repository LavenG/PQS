package edu.cs.nyu.pqs.assign5.exception;


import static junit.framework.TestCase.assertEquals;

import org.junit.Test;

/**
 * Test for IllegalOperationExceptionClass.
 */
public class IllegalOperationExceptionTest {

  @Test
  public void toStringTest() throws Exception {
    assertEquals("Illegal Operation Exception class with message : Some message",
        new IllegalOperationException("Some message").toString());
  }

}