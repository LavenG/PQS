package addressbook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

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

  //Null contact is added which shouldn't be the case. Hence this test fails.
  @Test
  public void testAdd_AddNullContact() {
    addressBook.add(null);
    assertEquals(0,contactList.size());
  }

  @Test
  public void testAdd_AddSameContact() {
    addressBook.add(contact);
    addressBook.add(contact);
    assertEquals(2,contactList.size());
    assertTrue(compareContacts(contact, contactList.get(0)));
    assertTrue(compareContacts(contact, contactList.get(1)));
  }

  @Test
  public void testRemove() throws Exception {
    contactList.add(contact);
    addressBook.remove(contact);
    assertEquals(0, contactList.size());
  }

  @Test
  public void testRemove_RemoveAskedContactWhenMultipleContactsInList() {
    contactList.add(contact);
    contactList.add(contact2);
    addressBook.remove(contact2);
    assertEquals(1,contactList.size());
    assertTrue(compareContacts(contact, contactList.get(0)));
  }

  @Test
  public void testRemove_RemoveOneOfManySimilarContact() {
    contactList.add(contact);
    contactList.add(contact);
    addressBook.remove(contact);
    assertEquals(1,contactList.size());
    assertTrue(compareContacts(contact, contactList.get(0)));
  }

  @Test
  public void testRemove_RemoveContactThatDoesntExist() {
    contactList.add(contact);
    addressBook.remove(contact2);
    assertEquals(1,contactList.size());
    assertTrue(compareContacts(contact, contactList.get(0)));
  }

  @Test
  public void testRemove_RemoveNull() {
    contactList.add(contact);
    addressBook.remove(null);
    assertEquals(1,contactList.size());
    assertTrue(compareContacts(contact, contactList.get(0)));
  }

  @Test
  public void testRemove_RemoveContactTwiceWhileListOnlyHasLessThanTwo() {
    contactList.add(contact);
    addressBook.remove(contact);
    assertEquals(0,contactList.size());
    addressBook.remove(contact);
    assertEquals(0, contactList.size());
  }

  @Test
  public void testRemove_RemoveContactTwiceWhileListHasMoreThanTwo() {
    contactList.add(contact);
    contactList.add(contact);
    contactList.add(contact);
    addressBook.remove(contact);
    assertEquals(2,contactList.size());
    addressBook.remove(contact);
    assertEquals(1, contactList.size());
  }

  @Test
  public void testSearch() {
    Contact contact3 = new Contact.Builder(firstName)
        .lastName("")
        .postalAddress(emptyValuesPostalAddress)
        .phoneNumber("")
        .emailAddress("")
        .note("")
        .build();
    contactList.add(contact);
    contactList.add(contact2);
    contactList.add(contact3);
    List<Contact> searchResult = addressBook.search(firstName);
    assertEquals(3, searchResult.size());
  }

  @Test
  public void testSearch_VerifyCanReturnOnlyOne() {
    Contact contact3 = new Contact.Builder("OnlyOne")
        .lastName("")
        .postalAddress(emptyValuesPostalAddress)
        .phoneNumber("")
        .emailAddress("")
        .note("")
        .build();
    contactList.add(contact);
    contactList.add(contact2);
    contactList.add(contact3);
    List<Contact> searchResult = addressBook.search("OnlyOne");
    assertEquals(1, searchResult.size());
    assertTrue(compareContacts(contact3, searchResult.get(0)));
  }

  @Test
  public void testSearch_SearchNotExistingTerm() {
    Contact contact3 = new Contact.Builder(firstName)
        .lastName("")
        .postalAddress(emptyValuesPostalAddress)
        .phoneNumber("")
        .emailAddress("")
        .note("")
        .build();
    contactList.add(contact);
    contactList.add(contact2);
    contactList.add(contact3);
    List<Contact> searchResult = addressBook.search("Not existing");
    assertEquals(0, searchResult.size());
  }

  //NullPointerExeption not handled hence the search fails when search term is null.
  //Not specified in code to never search with null.
  @Test
  public void testSearch_SearchForNull() {
    Contact contact3 = new Contact.Builder(firstName)
        .lastName("")
        .postalAddress(emptyValuesPostalAddress)
        .phoneNumber("")
        .emailAddress("")
        .note("")
        .build();
    contactList.add(contact);
    contactList.add(contact2);
    contactList.add(contact3);
    List<Contact> searchResult = addressBook.search(null);
    assertEquals(0, searchResult.size());
  }

  @Test
  public void testWriteAddressBookToFile() throws IOException {
    contactList.add(contact);
    contactList.add(contact);
    String fileName = "testsrc/MockFileNameNew.txt";
    addressBook.writeAddressBookToFile(fileName);
    String writeString = "#\n"
        + "Abc\n"
        + "Def\n"
        + "8th Street\n"
        + "New York\n"
        + "NY\n"
        + "10012\n"
        + "US\n"
        + "1 234 5678910\n"
        + "abc@efg.com\n"
        + "This is a note.\n"
        + "#\n"
        + "Abc\n"
        + "Def\n"
        + "8th Street\n"
        + "New York\n"
        + "NY\n"
        + "10012\n"
        + "US\n"
        + "1 234 5678910\n"
        + "abc@efg.com\n"
        + "This is a note.";
    assertEquals(writeString, readFileToString(fileName));
    deleteFile(fileName);
  }

  //As it is documented that IOException is received hence I test if null throws an
  // IOException. This should have been better handled or documented.
  @Test (expected = IOException.class)
  public void testWriteAddressBookToFile_NullFile() throws IOException {
    contactList.add(contact);
    contactList.add(contact);
    addressBook.writeAddressBookToFile(null);
  }

  @Test
  public void testWriteAddressBookToFile_FileWithExistingData() throws IOException {
    String fileName = "testsrc/MockFileNameExisting.txt";
    contactList.add(contact);
    contactList.add(contact);
    addressBook.writeAddressBookToFile(fileName);
    contactList.add(contact);
    contactList.add(contact);
    addressBook.writeAddressBookToFile(fileName);
    String writeString = "#\n"
        + "Abc\n"
        + "Def\n"
        + "8th Street\n"
        + "New York\n"
        + "NY\n"
        + "10012\n"
        + "US\n"
        + "1 234 5678910\n"
        + "abc@efg.com\n"
        + "This is a note.\n"
        + "#\n"
        + "Abc\n"
        + "Def\n"
        + "8th Street\n"
        + "New York\n"
        + "NY\n"
        + "10012\n"
        + "US\n"
        + "1 234 5678910\n"
        + "abc@efg.com\n"
        + "This is a note.\n"
        + "#\n"
        + "Abc\n"
        + "Def\n"
        + "8th Street\n"
        + "New York\n"
        + "NY\n"
        + "10012\n"
        + "US\n"
        + "1 234 5678910\n"
        + "abc@efg.com\n"
        + "This is a note.\n"
        + "#\n"
        + "Abc\n"
        + "Def\n"
        + "8th Street\n"
        + "New York\n"
        + "NY\n"
        + "10012\n"
        + "US\n"
        + "1 234 5678910\n"
        + "abc@efg.com\n"
        + "This is a note.";
    assertEquals(writeString, readFileToString(fileName));
    deleteFile(fileName);
  }

  @Test
  public void testReadAddressBookFromFile() throws IOException {
    addressBook.readAddressBookFromFile("testsrc/ContactFile.txt");
    assertEquals(1,contactList.size());
    assertTrue(compareContacts(contact, contactList.get(0)));
  }

  @Test
  public void testReadAddressBookFromFile_IllegalContactFile() throws IOException {
    addressBook.readAddressBookFromFile("testsrc/IllegalContactFile.txt");
    assertEquals(0,contactList.size());
  }

  @Test
  public void testReadAddressBookFromFile_MultipleContactFile() throws IOException {
    addressBook.readAddressBookFromFile("testsrc/MultipleContactFile.txt");
    assertEquals(2,contactList.size());
    assertTrue(compareContacts(contact, contactList.get(0)));
    assertTrue(compareContacts(contact, contactList.get(1)));
  }

  @Test (expected = IOException.class)
  public void testReadAddressBookFromFile_NotExistingFile() throws IOException {
    addressBook.readAddressBookFromFile("ContactFile.txt");
  }

  @Test (expected = IOException.class)
  public void testReadAddressBookFromFile_NullFile() throws IOException {
    addressBook.readAddressBookFromFile(null);
  }

  @Test
  public void testClear() {
    Contact contact3 = new Contact.Builder(firstName)
        .lastName("")
        .postalAddress(emptyValuesPostalAddress)
        .phoneNumber("")
        .emailAddress("")
        .note("")
        .build();
    contactList.add(contact);
    contactList.add(contact2);
    contactList.add(contact3);
    addressBook.clear();
    assertEquals(0,contactList.size());
  }

  @Test
  public void testClear_EmptyContactList() {
    addressBook.clear();
    assertEquals(0,contactList.size());
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

  private String readFileToString(String filename) {
    try {
      if (filename == null) {
        return "";
      }
      BufferedReader br = new BufferedReader(new FileReader(filename));
      String line = br.readLine();
      StringBuffer sb = new StringBuffer();
      boolean firstContact = true;
      while (line != null) {
        if (line.equals("#")) {
          if(!firstContact) {
            sb.append("\n");
          }
          sb.append("#\n");
          String firstName = br.readLine();
          String lastName = br.readLine();
          String street = br.readLine();
          String city = br.readLine();
          String state = br.readLine();
          String zip = br.readLine();
          String country = br.readLine();
          String phoneNumber = br.readLine();
          String emailAddress = br.readLine();
          String note = br.readLine();
          Address a = new Address(street, city, state, zip, country);
          Contact c = new Contact.Builder(firstName).lastName(lastName)
              .postalAddress(a).phoneNumber(phoneNumber)
              .emailAddress(emailAddress).note(note).build();
          contactList.add(c);
          sb.append(contact.toString());
        }
        firstContact = false;
        line = br.readLine();
      }
      return sb.toString();
    } catch(IOException e) {
      return "";
    }
  }

  private void deleteFile(String fileName) {
    if (fileName == null) {
      return;
    }
    File file = new File(fileName);
    file.delete();
  }
}