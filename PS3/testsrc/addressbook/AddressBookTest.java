package addressbook;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.*;

/**
 * Unit Test for {@link AddressBook}
 * Have individual test case for each scenario so that failure can be easily detected
 * Since we are not allowed to change code and the class is not very test friendly hence I have
 * used reflection to get access to private contactList.
 */
public class AddressBookTest {
  private AddressBook addressBook;
  private Contact contact;
  private Contact contact2;
  private Field contactListField;
  private List<Contact> contactList;
  private static final String firstName = "Abc";
  private static final String lastName = "Def";
  private static final Address postalAddress
      = new Address("8th Street","New York","NY","10012","US");
  private static final Address emptyValuesPostalAddress
      = new Address("","","","","");
  private static final String phoneNumber = Address.removePhoneSymbols("+1 (234) 567-8910");
  private static final String emailAddress = "abc@efg.com";
  private static final String note = "This is a note.";

  /**
   *
   */
  @Before
  public void setup() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
    addressBook = new AddressBook();
    contact = new Contact.Builder(firstName)
        .lastName(lastName)
        .postalAddress(postalAddress)
        .phoneNumber(phoneNumber)
        .emailAddress(emailAddress)
        .note(note)
        .build();
    contact2 = new Contact.Builder(firstName)
        .lastName(lastName)
        .postalAddress(emptyValuesPostalAddress)
        .phoneNumber(phoneNumber)
        .emailAddress(emailAddress)
        .note(note)
        .build();
    Class addressBookClass = AddressBook.class;
    contactListField = addressBookClass.getDeclaredField("contactList");
    contactListField.setAccessible(true);
    contactList = (List<Contact>) contactListField.get(addressBook);
  }

  @Test
  public void testAdd() {
    addressBook.add(contact);
    assertEquals(1,contactList.size());
  }

  @Test
  public void testAdd_CheckCorrectContactAdded() {
    addressBook.add(contact);
    assertTrue(compareContacts(contact, contactList.get(0)));
  }

  @Test
  public void testAdd_TestMultipleContactsAreAddedAndAreSameAsTheArguments() {
    addressBook.add(contact);
    addressBook.add(contact2);
    assertEquals(2,contactList.size());
    assertTrue(compareContacts(contact, contactList.get(0)));
    assertTrue(compareContacts(contact2, contactList.get(1)));
  }

  @Test
  public void testRemove() throws Exception {
    addressBook.add(contact);
    if (contactList.size() == 0) {
      fail("Add method of addressbook failed.");
    }
    addressBook.remove(contact);
    assertEquals(0, contactList.size());
  }

  @Test
  public void testRemove_RemoveAskedContactFromMultiple() {
    addressBook.add(contact);
    if (contactList.size() < 1) {
      fail("Add method of addressbook failed.");
    }
    addressBook.add(contact2);
    if (contactList.size() < 2) {
      fail("Add method of addressbook failed on adding multiple.");
    }
    addressBook.remove(contact2);
    assertEquals(1,contactList.size());
    assertTrue(compareContacts(contact, contactList.get(0)));
  }

  @Test
  public void testSearch() throws Exception {
    Contact contact3 = new Contact.Builder(firstName)
        .lastName("")
        .postalAddress(emptyValuesPostalAddress)
        .phoneNumber("")
        .emailAddress("")
        .note("")
        .build();
    addressBook.add(contact);
    if (contactList.size() < 1) {
      fail("Add method of addressbook failed.");
    }
    addressBook.add(contact2);
    if (contactList.size() < 2) {
      fail("Add method of addressbook failed on adding multiple.");
    }
    addressBook.add(contact3);
    if (contactList.size() < 3) {
      fail("Add method of addressbook failed.");
    }
    List<Contact> searchResult = addressBook.search(firstName);
    assertEquals(3, searchResult.size());
  }

  @Test
  public void testWriteAddressBookToFile() throws Exception {

  }

  @Test
  public void testReadAddressBookFromFile() throws Exception {

  }

  @Test
  public void testClear() throws Exception {

  }

  private boolean compareContacts(Contact contact1, Contact contact2) {
    if (contact1 != null && contact2 != null) {
      return compareStringValues(contact1.firstName, contact2.firstName)
          && compareStringValues(contact1.lastName, contact2.lastName)
          && compareStringValues(contact1.phoneNumber, contact2.phoneNumber)
          && compareStringValues(contact1.emailAddress, contact2.emailAddress)
          && compareStringValues(contact1.note, contact2.note)
          && compareAddress(contact1.postalAddress, contact2.postalAddress);
    }
    return (contact1 == null && contact2 == null) ? true : false;
  }

  private boolean compareAddress(Address address1, Address address2) {
    if (address1 != null && address2 != null) {
      return compareStringValues(address1.street, address2.street)
          && compareStringValues(address1.city, address2.city)
          && compareStringValues(address1.state, address2.state)
          && compareStringValues(address1.zip, address2.zip)
          && compareStringValues(address1.country, address2.country);
    }
    return (address1 == null && address2 == null) ? true : false;
  }

  private boolean compareStringValues(String s1, String s2) {
    if (s1 != null && s2 != null) {
      return s1.equals(s2);
    }
    return (s1 == null && s2 == null) ? true : false;
  }
}