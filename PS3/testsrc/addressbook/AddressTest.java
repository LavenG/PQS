package addressbook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

/**
 * Unit Test for {@link Address}
 * Have individual test case for each scenario so that failure can be easily detected
 */
public class AddressTest {
  private Address address;
  private static final String presentInSearch = "NY";
  private static final String street = "8th Street";
  private static final String city = "New York";
  private static final String state = "NY";
  private static final String zip = "10012";
  private static final String country = "US";

  @Before
  public void setup() {
    address = new Address(street,city,state,zip,country);
  }

  @Test
  public void testSearchAddressFields() {
    assertTrue(address.searchAddressFields(presentInSearch));
  }

  @Test
  public void testSearchAddressFields_SearchTermMatchesStreet() {
    assertTrue(address.searchAddressFields(street));
  }

  @Test
  public void testSearchAddressFields_SearchTermMatchesCity() {
    assertTrue(address.searchAddressFields(city));
  }

  @Test
  public void testSearchAddressFields_SearchTermMatchesState() {
    assertTrue(address.searchAddressFields(state));
  }

  @Test
  public void testSearchAddressFields_SearchTermMatchesZip() {
    assertTrue(address.searchAddressFields(zip));
  }

  @Test
  public void testSearchAddressFields_SearchTermMatchesCountry() {
    assertTrue(address.searchAddressFields(country));
  }

  @Test
  public void testSearchAddressFields_SearchTermMatchesNothing() {
    assertFalse(address.searchAddressFields(""));
  }

  // Neither handled in code nor documented that nullPointerException is thrown or null search term
  // not allowed. This test case would hence fail as it throws NullPointerException.
  @Test
  public void testSearchAddressFields_NullString() {
    assertFalse(address.searchAddressFields(null));
  }

  @Test
  public void testEquals() {
    //Check same
    Address sameAddress = new Address(street, city, state, zip, country);
    assertTrue(address.equals(sameAddress));
  }

  @Test
  public void testEquals_ForSymmetry() {
    // test equals is symmetric : that is x.equlas(y) then y.equals(x) is also true
    Address sameAddress = new Address(street, city, state, zip, country);
    assertTrue(address.equals(sameAddress));
    assertTrue(sameAddress.equals(address));
  }

  @Test
  public void testEquals_ForTransitivity() {
    // test equals is tranvisitive, taht is if x.equals(y) is true y.equals(z) is true
    // then x.equals(z) is true
    Address sameAddressOne = new Address(street, city, state, zip, country);
    Address sameAddressTwo = new Address(street, city, state, zip, country);
    assertTrue(address.equals(sameAddressOne));
    assertTrue(sameAddressOne.equals(sameAddressTwo));
    assertTrue(address.equals(sameAddressTwo));
  }

  @Test
  public void testEquals_ForConsistency() {
    // Test the code works the same if the statement is called multiple times
    int testTimes = new Random().nextInt(10);
    Address sameAddress = new Address(street, city, state, zip, country);
    for (int i=0; i<testTimes; i++) {
      assertTrue(address.equals(sameAddress));
    }
    Address differentAddress = new Address("", "", "", "", "");
    for (int i=0; i<testTimes; i++) {
      assertFalse(address.equals(differentAddress));
    }
  }

  @Test
  public void testEquals_DifferentAddresses() {
    //Check different
    Address differentAddress = new Address("", "", "", "", "");
    assertFalse(address.equals(differentAddress));
  }

  @Test
  public void testEquals_AllEmptyFieldSameAddresses() {
    //Test same with both completely empty
    Address testAddress = new Address("", "", "", "", "");
    Address sameTestAddress = new Address("", "", "", "", "");
    assertTrue(testAddress.equals(sameTestAddress));
  }

  @Test
  public void testEquals_AddressWithEmptyStreet() {
    //Check empty street
    Address emptyStreet = new Address("", city, state, zip, country);
    assertFalse(address.equals(emptyStreet));
  }

  @Test
  public void testEquals_AddressWithEmptyCity() {
    //Check empty city
    Address emptyCity = new Address(street, "", state, zip, country);
    assertFalse(address.equals(emptyCity));
  }

  @Test
  public void testEquals_AddressWithEmptyState() {
    //Check empty state
    Address emptyState = new Address(street, city, "", zip, country);
    assertFalse(address.equals(emptyState));
  }

  @Test
  public void testEquals_AddressWithEmptyZip() {
    //Check empty zip
    Address emptyZip = new Address(street, city, state, "", country);
    assertFalse(address.equals(emptyZip));
  }

  @Test
  public void testEquals_AddressWithEmptyCountry() {
    //Check empty country
    Address emptyCountry = new Address(street,city,state,zip,"");
    assertFalse(address.equals(emptyCountry));
  }

  @Test
  public void testEquals_AddressWithNullStreet() {
    //Check null street
    Address emptyStreet = new Address(null, city, state, zip, country);
    assertFalse(address.equals(emptyStreet));
  }

  @Test
  public void testEquals_AddressWithNullCity() {
    //Check null city
    Address emptyCity = new Address(city, null, state, zip, country);
    assertFalse(address.equals(emptyCity));
  }

  @Test
  public void testEquals_AddressWithNullState() {
    //Check null state
    Address emptyState = new Address(city, city, null, zip, country);
    assertFalse(address.equals(emptyState));
  }

  @Test
  public void testEquals_AddressWithNullZip() {
    //Check null zip
    Address emptyZip = new Address(city, city, state, null, country);
    assertFalse(address.equals(emptyZip));
  }

  @Test
  public void testEquals_AddressWithNullCountry() {
    //Check null country
    Address emptyCountry = new Address(city,city,state,zip,null);
    assertFalse(address.equals(emptyCountry));
  }

  // Neither handled in code nor documented that NullPointerException is thrown or null address
  // not allowed. This test case would hence fail as it throws NullPointerException.
  @Test
  public void testEquals_NullTestAddress() {
    assertFalse(address.equals(null));
  }

  @Test
  public void testToString() {
    //Test base toString
    assertEquals(street + "\n"
        + city + "\n"
        + state + "\n"
        + zip + "\n"
        + country, address.toString());
  }

  @Test
  public void testToString_AddressWithEmptyStreet() {
    Address addressWithEmptyStreet = new Address("", city, state, zip, country);
    assertEquals("" + "\n"
        + city + "\n"
        + state + "\n"
        + zip + "\n"
        + country, addressWithEmptyStreet.toString());
  }

  @Test
  public void testToString_AddressWithEmptyCity() {
    Address addressWithEmptyCity = new Address(street, "", state, zip, country);
    assertEquals(street + "\n"
        + "" + "\n"
        + state + "\n"
        + zip + "\n"
        + country, addressWithEmptyCity.toString());
  }

  @Test
  public void testToString_AddressWithEmptyState() {
    Address addressWithEmptyState = new Address(street, city, "", zip, country);
    assertEquals(street + "\n"
        + city + "\n"
        + "" + "\n"
        + zip + "\n"
        + country, addressWithEmptyState.toString());
  }

  @Test
  public void testToString_AddressWithEmptyZip() {
    Address addressWithEmptyZip = new Address(street, city, state, "", country);
    assertEquals(street + "\n"
        + city + "\n"
        + state + "\n"
        + "" + "\n"
        + country, addressWithEmptyZip.toString());
  }


  @Test
  public void testToString_AddressWithEmptyCountry() {
    Address addressWithEmptyCountry = new Address(street,city,state,zip,"");
    assertEquals(street + "\n"
        + city + "\n"
        + state + "\n"
        + zip + "\n"
        + "", addressWithEmptyCountry.toString());
  }

  // Neither handled in code nor documented that nullPointerException is thrown or null street
  // not allowed. This test case would hence fail as it throws NullPointerException.
  @Test
  public void testToString_NullStreet() {
    Address addressWithNullStreet = new Address(null,city,state,zip,country);
    assertEquals(city + "\n"
        + state + "\n"
        + zip + "\n"
        + country, addressWithNullStreet.toString());
  }

  // Neither handled in code nor documented that nullPointerException is thrown or null city
  // not allowed. This test case would hence fail as it throws NullPointerException.
  @Test
  public void testToString_NullCity() {
    Address addressWithNullCity = new Address(street,null,state,null,country);
    assertEquals(street + "\n"
        + state + "\n"
        + zip + "\n"
        + country, addressWithNullCity.toString());
  }

  // Neither handled in code nor documented that nullPointerException is thrown or null state
  // not allowed. This test case would hence fail as it throws NullPointerException.
  @Test
  public void testToString_NullState() {
    Address addressWithNullState = new Address(street,city,null,zip,country);
    assertEquals(street + "\n"
        + city + "\n"
        + zip + "\n"
        + country, addressWithNullState.toString());
  }

  // Neither handled in code nor documented that nullPointerException is thrown or null zip
  // not allowed. This test case would hence fail as it throws NullPointerException.
  @Test
  public void testToString_NullZip() {
    Address addressWithNullZip = new Address(street,city,state,null,country);
    assertEquals(street + "\n"
        + city + "\n"
        + state + "\n"
        + country, addressWithNullZip.toString());
  }

  // Neither handled in code nor documented that nullPointerException is thrown or null country
  // not allowed. This test case would hence fail as it throws NullPointerException.
  @Test
  public void testToString_NullCountry() {
    Address addressWithNullCountry = new Address(street,city,state,zip,null);
    assertEquals(street + "\n"
        + city + "\n"
        + state + "\n"
        + zip + "\n", addressWithNullCountry.toString());
  }

  @Test
  public void testRemovePhoneSymbols() {
    //Check symbols are removed
    String phoneNumber = "+1 (234)-567-8910";
    assertEquals("1 2345678910", Address.removePhoneSymbols(phoneNumber));
  }

  @Test
  public void testRemovePhoneSymbols_NumberWithAdditionalSpaceWithoutSpaces() {
    //Check symbols are removed but spaces are preserved
    //Though this shouldn't be the behavior but we get that from method doc
    String phoneNumberWithExtraSpaces = "+1 (234) - 5  67  -8  910  ";
    assertEquals("1 234  5  67  8  910  ",
        Address.removePhoneSymbols(phoneNumberWithExtraSpaces));
  }

  @Test
  public void testRemovePhoneSymbols_EmptyPhoneNumber() {
    assertEquals("", Address.removePhoneSymbols(""));
  }

  @Test
  public void testRemovePhoneSymbols_NotRemoveOtherSymbols() {
    //Check only said symbols are removed
    //Though this is not a number but the code doesn't handle it
    String phoneNumberWithVariousSymbols = "+1*2&3%4$3@3()!_14";
    assertEquals("1*2&3%4$3@3!_14",
        Address.removePhoneSymbols(phoneNumberWithVariousSymbols));
  }

  @Test
  public void testRemovePhoneSymbols_RemovePlusSymbol() {
    //Test removal of only '+'
    assertEquals("", Address.removePhoneSymbols("+"));
  }

  @Test
  public void testRemovePhoneSymbols_RemoveDashSymbol() {
    //Test removal of only '-'
    assertEquals("", Address.removePhoneSymbols("-"));
  }

  @Test
  public void testRemovePhoneSymbols_RemoveOpenBracketSymbol() {
    //Test removal of only '('
    assertEquals("", Address.removePhoneSymbols("("));
  }

  @Test
  public void testRemovePhoneSymbols_RemoveClosedBracketSymbol() {
    //Test removal of only ')'
    assertEquals("", Address.removePhoneSymbols(")"));
  }

  // Neither handled in code nor documented that nullPointerException is thrown or null argument is
  // not allowed. This test case would hence fail as it throws NullPointerException.
  @Test
  public void testRemovePhoneSymbols_NullStringAsArgument() {
    assertEquals(null, Address.removePhoneSymbols(null));
  }

  @Test
  public void testRemoveLineBreaksAndTrim() {
    //Test base case
    String stringWithNoLeadingAndTrailingSpaceAndNewLines = "abc";
    assertEquals("abc",
        Address.removeLineBreaksAndTrim(stringWithNoLeadingAndTrailingSpaceAndNewLines));
  }

  @Test
  public void testRemoveLineBreaksAndTrim_EmptyString() {
    //Test empty string
    assertEquals(Address.removeLineBreaksAndTrim(""), "");
  }

  @Test
  public void testRemoveLineBreaksAndTrim_StringWithOnlySpaces() {
    //Test string with only spaces
    assertEquals(Address.removeLineBreaksAndTrim("       "), "");
  }

  @Test
  public void testRemoveLineBreaksAndTrim_StringWithOnlyLineBreaks() {
    //Test string with only line breaks
    assertEquals(Address.removeLineBreaksAndTrim("\n\n\n"), "");
  }

  @Test
  public void testRemoveLineBreaksAndTrim_StringWithOnlySpaceAndLineBreaks() {
    assertEquals(Address.removeLineBreaksAndTrim("  \n   \n\n"), "");
  }

  @Test
  public void testRemoveLineBreaksAndTrim_StringWithTrailingAndLeadingSpace() {
    //Trim Spaces
    String stringWithLeadingAndTrailingSpace = "    abc    ";
    assertEquals(Address.removeLineBreaksAndTrim(stringWithLeadingAndTrailingSpace), "abc");
  }

  @Test
  public void testRemoveLineBreaksAndTrim_StringWithLineBreaksAndCharacters() {
    //Remove Newline
    //Doesn't specify that leading and trailing line breaks are entirely removed and
    // other line breaks are replaced by spaces.
    String stringWithNewLines = "\nab\n\nc\n\n";
    assertEquals(Address.removeLineBreaksAndTrim(stringWithNewLines), "ab  c");
  }

  @Test
  public void testRemoveLineBreaksAndTrim_StringWithLineBreaksAndSpacesAndCharacters() {
    //Remove leading and trailing space and new line
    String stringWithLeadingAndTrailingSpaceAndNewLines = "   abc  Abc  \n a \n  \n     ";
    assertEquals(Address.removeLineBreaksAndTrim(stringWithLeadingAndTrailingSpaceAndNewLines),
        "abc  Abc    a");
  }

  //Should specify that one would get a null pointer exception on passing null string
  //Neither handled in code nor documented that nullPointerException is thrown
  @Test(expected = NullPointerException.class)
  public void testRemoveLineBreaksAndTrim_NullStringAsArgument() {
    assertEquals(null, Address.removeLineBreaksAndTrim(null));
  }
}