package addressbook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Unit Test for {@link Contact}
 * Have individual test case for each scenario so that failure can be easily detected
 */
public class ContactTest {
  private Contact contact;
  private static final String presentInSearch = "Abc";
  private static final String firstName = "Abc";
  private static final String lastName = "Def";
  private static final String street = "8th Street";
  private static final Address postalAddress
      = new Address("8th Street","New York","NY","10012","US");
  private Address addressSpy;
  private static final Address emptyValuesPostalAddress
      = new Address("","","","","");
  private static final String phoneNumber = Address.removePhoneSymbols("+1 (234) 567-8910");
  private static final String emailAddress = "abc@efg.com";
  private static final String note = "This is a note.";

  @Before
  public void setup() {
    createPostalAddressMock();
    contact = new Contact.Builder(firstName)
        .lastName(lastName)
        .postalAddress(addressSpy)
        .phoneNumber(phoneNumber)
        .emailAddress(emailAddress)
        .note(note)
        .build();
  }

  private void createPostalAddressMock() {
    addressSpy = Mockito.spy(postalAddress);
    Mockito.when(addressSpy.searchAddressFields(street)).thenReturn(true);
    Mockito.when(addressSpy.searchAddressFields("")).thenReturn(false);
    Mockito.when(addressSpy.equals(postalAddress)).thenReturn(true);
    Mockito.when(addressSpy.equals(emptyValuesPostalAddress)).thenReturn(false);
    Mockito.when(addressSpy.toString()).thenReturn("8th Street\n"
    + "New York\n"
    + "NY\n"
    + "10012\n"
    + "US");
  }

  @Test
  public void testSearchContactFields() {
    assertTrue(contact.searchContactFields(presentInSearch));
  }

  @Test
  public void testSearchContactFields_SearchTermMatchesFirstName() {
    assertTrue(contact.searchContactFields(firstName));
  }

  @Test
  public void testSearchContactFields_SearchTermMatchesLastName() {
    assertTrue(contact.searchContactFields(lastName));
  }

  @Test
  public void testSearchContactFields_PostalAddressSearchReturnsTrue() {
    assertTrue(contact.searchContactFields(street));
  }

  @Test
  public void testSearchContactFields_PostalAddressSearchReturnsFalse() {
    assertFalse(contact.searchContactFields(""));
  }

  @Test
  public void testSearchContactFields_SearchTermMatchesPhoneNumber() {
    assertTrue(contact.searchContactFields(phoneNumber));
  }

  @Test
  public void testSearchContactFields_SearchTermMatchesEmail() {
    assertTrue(contact.searchContactFields(emailAddress));
  }

  @Test
  public void testSearchContactFields_SearchTermMatchesNote() {
    assertTrue(contact.searchContactFields(note));
  }

  @Test
  public void testSearchContactFields_SearchTermMatchesNothing() {
    assertFalse(contact.searchContactFields(""));
  }

  // Neither handled in code nor documented that nullPointerException is thrown or null search term
  // not allowed. This test case would hence fail as it throws NullPointerException.
  @Test
  public void testSearchContactFields_NullString() {
    assertFalse(contact.searchContactFields(null));
  }

  @Test
  public void testEquals() {
    //Check same
    Contact sameContact = new Contact.Builder(firstName)
        .lastName(lastName)
        .postalAddress(postalAddress)
        .emailAddress(emailAddress)
        .phoneNumber(phoneNumber)
        .note(note)
        .build();
    assertTrue(contact.equals(sameContact));
  }

  @Test
  public void testEquals_ForSymmetry() {
    // test equals is symmetric : that is x.equlas(y) then y.equals(x) is also true
    Contact sameContact = new Contact.Builder(firstName)
        .lastName(lastName)
        .postalAddress(postalAddress)
        .emailAddress(emailAddress)
        .phoneNumber(phoneNumber)
        .note(note)
        .build();
    assertTrue(contact.equals(sameContact));
    assertTrue(contact.equals(sameContact));
  }

  @Test
  public void testEquals_ForTransitivity() {
    // test equals is tranvisitive, taht is if x.equals(y) is true y.equals(z) is true
    // then x.equals(z) is true
    Contact sameContactOne = new Contact.Builder(firstName)
        .lastName(lastName)
        .postalAddress(postalAddress)
        .emailAddress(emailAddress)
        .phoneNumber(phoneNumber)
        .note(note)
        .build();
    Contact sameContactTwo = new Contact.Builder(firstName)
        .lastName(lastName)
        .postalAddress(postalAddress)
        .emailAddress(emailAddress)
        .phoneNumber(phoneNumber)
        .note(note)
        .build();
    assertTrue(contact.equals(sameContactOne));
    assertTrue(sameContactOne.equals(sameContactTwo));
    assertTrue(contact.equals(sameContactTwo));
  }

  @Test
  public void testEquals_ForConsistency() {
    // Test the code works the same if the statement is called multiple times
    int testTimes = new Random().nextInt(10);
    Contact sameContact = new Contact.Builder(firstName)
        .lastName(lastName)
        .postalAddress(postalAddress)
        .emailAddress(emailAddress)
        .phoneNumber(phoneNumber)
        .note(note)
        .build();
    for (int i=0; i<testTimes; i++) {
      assertTrue( contact.equals(sameContact));
    }
    Contact differentContact = new Contact.Builder("")
        .lastName("")
        .postalAddress(emptyValuesPostalAddress)
        .emailAddress("")
        .phoneNumber("")
        .note("")
        .build();
    for (int i=0; i<testTimes; i++) {
      assertFalse(contact.equals(differentContact));
    }
  }

  @Test
  public void testEquals_DifferentContact() {
    //Check different
    Contact differentContact = new Contact.Builder("")
        .lastName("")
        .postalAddress(emptyValuesPostalAddress)
        .emailAddress("")
        .phoneNumber("")
        .note("")
        .build();
    assertFalse(contact.equals(differentContact));
  }

  @Test
  public void testEquals_AllEmptyFieldSameContact() {
    //Test same with both completely empty
    Contact emptyContact = new Contact.Builder("")
        .lastName("")
        .postalAddress(emptyValuesPostalAddress)
        .emailAddress("")
        .phoneNumber("")
        .note("")
        .build();
    Contact sameEmptyContact = new Contact.Builder("")
        .lastName("")
        .postalAddress(emptyValuesPostalAddress)
        .emailAddress("")
        .phoneNumber("")
        .note("")
        .build();
    assertTrue(emptyContact.equals(sameEmptyContact));
  }

  @Test
  public void testEquals_ContactWithEmptyFirstName() {
    //Check empty firstName
    Contact emptyFirstName = new Contact.Builder("")
        .lastName(lastName)
        .postalAddress(postalAddress)
        .emailAddress(emailAddress)
        .phoneNumber(phoneNumber)
        .note(note)
        .build();
    assertFalse(contact.equals(emptyFirstName));
  }

  @Test
  public void testEquals_ContactWithEmptyLastName() {
    //Check empty lastName
    Contact emptyLastName = new Contact.Builder(firstName)
        .lastName("")
        .postalAddress(postalAddress)
        .emailAddress(emailAddress)
        .phoneNumber(phoneNumber)
        .note(note)
        .build();
    assertFalse(contact.equals(emptyLastName));
  }

  @Test
  public void testEquals_ConatctWithEmptyPostalAddress() {
    //Check empty postal address
    Contact emptyPostalAddress = new Contact.Builder(firstName)
        .lastName(lastName)
        .postalAddress(emptyValuesPostalAddress)
        .emailAddress(emailAddress)
        .phoneNumber(phoneNumber)
        .note(note)
        .build();
    assertFalse(contact.equals(emptyPostalAddress));
  }

  @Test
  public void testEquals_ConatctWithEmptyEmailAddress() {
    //Check empty email address
    Contact emptyEmailAddress = new Contact.Builder(firstName)
        .lastName(lastName)
        .postalAddress(postalAddress)
        .emailAddress("")
        .phoneNumber(phoneNumber)
        .note(note)
        .build();
    assertFalse(contact.equals(emptyEmailAddress));
  }

  @Test
  public void testEquals_ContactWithEmptyPhoneNumber() {
    //Check empty phone number
    Contact emptyPhoneNumber = new Contact.Builder(firstName)
        .lastName(lastName)
        .postalAddress(postalAddress)
        .emailAddress(emailAddress)
        .phoneNumber("")
        .note(note)
        .build();
    assertFalse(contact.equals(emptyPhoneNumber));
  }

  @Test
  public void testEquals_ContactWithEmptyNote() {
    //Check empty note
    Contact emptyNote = new Contact.Builder(firstName)
        .lastName(lastName)
        .postalAddress(postalAddress)
        .emailAddress(emailAddress)
        .phoneNumber(phoneNumber)
        .note("")
        .build();
    assertFalse(contact.equals(emptyNote));
   }

  @Test
  public void testEquals_ContactWithNullFirstName() {
    //Check null firstName
    Contact nullFirstName = new Contact.Builder(null)
        .lastName(lastName)
        .postalAddress(postalAddress)
        .emailAddress(emailAddress)
        .phoneNumber(phoneNumber)
        .note(note)
        .build();
    assertFalse(contact.equals(nullFirstName));
  }

  @Test
  public void testEquals_ContactWithNullLastName() {
    //Check null lastName
    Contact nullLastName = new Contact.Builder(firstName)
        .lastName(null)
        .postalAddress(postalAddress)
        .emailAddress(emailAddress)
        .phoneNumber(phoneNumber)
        .note(note)
        .build();
    assertFalse(contact.equals(nullLastName));
  }

  @Test
  public void testEquals_ConatctWithNullPostalAddress() {
    //Check empty postal address
  }

  @Test
  public void testEquals_ConatctWithNullEmailAddress() {
    //Check null email address
    Contact nullEmailAddress = new Contact.Builder(firstName)
        .lastName(lastName)
        .postalAddress(postalAddress)
        .emailAddress(null)
        .phoneNumber(phoneNumber)
        .note(note)
        .build();
    assertFalse(contact.equals(nullEmailAddress));
  }

  @Test
  public void testEquals_ContactWithNullPhoneNumber() {
    //Check null phone number
    Contact nullPhoneNumber = new Contact.Builder(firstName)
        .lastName(lastName)
        .postalAddress(postalAddress)
        .emailAddress(emailAddress)
        .phoneNumber(null)
        .note(note)
        .build();
    assertFalse(contact.equals(nullPhoneNumber));
  }

  @Test
  public void testEquals_ContactWithNullNote() {
    //Check null note
    Contact nullNote = new Contact.Builder(firstName)
        .lastName(lastName)
        .postalAddress(postalAddress)
        .emailAddress(emailAddress)
        .phoneNumber(phoneNumber)
        .note(null)
        .build();
    assertFalse(contact.equals(nullNote));
  }


  // Neither handled in code nor documented that NullPointerException is thrown or null contact
  // not allowed. This test case would hence fail as it throws NullPointerException.
  @Test
  public void testEquals_NullTest() {
    contact.equals(null);
  }

  @Test
  public void testToString() {
    //Test base toString
    assertEquals(firstName + "\n"
        + lastName + "\n"
        + addressSpy.toString() + "\n"
        + phoneNumber + "\n"
        + emailAddress + "\n"
        + note, contact.toString());
  }

  @Test
  public void testToString_ContactWithEmptyFirstName() {
    Contact emptyFirstName = new Contact.Builder("")
        .lastName(lastName)
        .postalAddress(postalAddress)
        .emailAddress(emailAddress)
        .phoneNumber(phoneNumber)
        .note(note)
        .build();
    assertEquals("\n"
        + lastName + "\n"
        + addressSpy.toString() + "\n"
        + phoneNumber + "\n"
        + emailAddress + "\n"
        + note, emptyFirstName.toString());
  }

  @Test
  public void testToString_ContactEmptyLastName() {
    Contact emptyLastName = new Contact.Builder(firstName)
        .lastName("")
        .postalAddress(postalAddress)
        .emailAddress(emailAddress)
        .phoneNumber(phoneNumber)
        .note(note)
        .build();
    assertEquals(firstName + "\n"
        + "\n"
        + addressSpy.toString() + "\n"
        + phoneNumber + "\n"
        + emailAddress + "\n"
        + note, emptyLastName.toString());
  }

  @Test
  public void testToString_ContactWithEmptyPhoneNumber() {
    Contact emptyPhoneNumber = new Contact.Builder(firstName)
        .lastName(lastName)
        .postalAddress(postalAddress)
        .emailAddress(emailAddress)
        .phoneNumber("")
        .note(note)
        .build();
    assertEquals(firstName + "\n"
        + lastName + "\n"
        + addressSpy.toString() + "\n"
        + "\n"
        + emailAddress + "\n"
        + note, emptyPhoneNumber.toString());
  }

  @Test
  public void testToString_ContactWithEmptyEmailAddress() {
    Contact emptyEmailAddress = new Contact.Builder(firstName)
        .lastName(lastName)
        .postalAddress(postalAddress)
        .emailAddress("")
        .phoneNumber(phoneNumber)
        .note(note)
        .build();
    assertEquals(firstName + "\n"
        + lastName + "\n"
        + addressSpy.toString() + "\n"
        + phoneNumber + "\n"
        + "\n"
        + note, emptyEmailAddress.toString());
  }

  @Test
  public void testToString_ContactWithEmptyNote() {
    Contact emptyNote = new Contact.Builder(firstName)
        .lastName(lastName)
        .postalAddress(postalAddress)
        .emailAddress(emailAddress)
        .phoneNumber(phoneNumber)
        .note("")
        .build();
    assertEquals(firstName + "\n"
        + lastName + "\n"
        + addressSpy.toString() + "\n"
        + phoneNumber + "\n"
        + emailAddress + "\n", emptyNote.toString());
  }

  // Neither handled in code nor documented that nullPointerException is thrown or null first name
  // not allowed. This test case would hence fail as it throws NullPointerException.
  @Test
  public void testToString_ContactWithNullFirstName() {
    Contact nullFirstName = new Contact.Builder(null)
        .lastName(lastName)
        .postalAddress(postalAddress)
        .emailAddress(emailAddress)
        .phoneNumber(phoneNumber)
        .note(note)
        .build();
    assertEquals("\n"
        + lastName + "\n"
        + addressSpy.toString() + "\n"
        + phoneNumber + "\n"
        + emailAddress + "\n"
        + note, nullFirstName.toString());
  }

  // Neither handled in code nor documented that nullPointerException is thrown or null last name
  // not allowed. This test case would hence fail as it throws NullPointerException.
  @Test
  public void testToString_ContactWithNullLastName() {
    Contact nullLastName = new Contact.Builder(firstName)
        .lastName(null)
        .postalAddress(postalAddress)
        .emailAddress(emailAddress)
        .phoneNumber(phoneNumber)
        .note(note)
        .build();
    assertEquals(firstName + "\n"
        + "\n"
        + addressSpy.toString() + "\n"
        + phoneNumber + "\n"
        + emailAddress + "\n"
        + note, nullLastName.toString());
  }

  // Neither handled in code nor documented that nullPointerException is thrown or null postal
  // address not allowed. This test case would hence fail as it throws NullPointerException.
  @Test
  public void testToString_ContactWithNullPostalAddress() {

  }

  // Neither handled in code nor documented that nullPointerException is thrown or null email
  // address not allowed. This test case would hence fail as it throws NullPointerException.
  @Test
  public void testToString_ContactWithNullEmailAddress() {
    Contact nullEmailAddress = new Contact.Builder(null)
        .lastName(lastName)
        .postalAddress(postalAddress)
        .emailAddress(emailAddress)
        .phoneNumber(phoneNumber)
        .note(note)
        .build();
    assertEquals("\n"
        + lastName + "\n"
        + addressSpy.toString() + "\n"
        + phoneNumber + "\n"
        + "\n"
        + note, nullEmailAddress.toString());
  }

  // Neither handled in code nor documented that nullPointerException is thrown or null phone
  // number not allowed. This test case would hence fail as it throws NullPointerException.
  @Test
  public void testToString_ContactWithNullPhoneNumber() {
    Contact nullPhoneNumber = new Contact.Builder(null)
        .lastName(lastName)
        .postalAddress(postalAddress)
        .emailAddress(emailAddress)
        .phoneNumber(phoneNumber)
        .note(note)
        .build();
    assertEquals("\n"
        + lastName + "\n"
        + addressSpy.toString() + "\n"
        + "\n"
        + emailAddress + "\n"
        + note, nullPhoneNumber.toString());
  }

  // Neither handled in code nor documented that nullPointerException is thrown or null note
  // is not allowed. This test case would hence fail as it throws NullPointerException.
  @Test
  public void testToString_ContactWithNullNote() {
    Contact nullNote = new Contact.Builder(null)
        .lastName(lastName)
        .postalAddress(postalAddress)
        .emailAddress(emailAddress)
        .phoneNumber(phoneNumber)
        .note(note)
        .build();
    assertEquals("\n"
        + lastName + "\n"
        + addressSpy.toString() + "\n"
        + phoneNumber + "\n"
        + emailAddress + "\n"
        + "\n", nullNote.toString());
  }
}