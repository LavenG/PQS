package edu.cs.nyu.pqs.assign4.constants;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/*
 * Test for constants. There are only constants in this class hence there is nothing to test except
 * toString.
 */
public class ConstantsForGameTest {

  @Test
  public void testToString() throws Exception {
    assertEquals("Constants class.", new ConstantsForGame().toString());
  }

}