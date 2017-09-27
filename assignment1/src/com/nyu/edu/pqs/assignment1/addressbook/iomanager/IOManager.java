package com.nyu.edu.pqs.assignment1.addressbook.iomanager;

 import com.nyu.edu.pqs.assignment1.addressbook.contact.Contact;
 import com.nyu.edu.pqs.assignment1.addressbook.pojo.ContactPojo;

 import java.io.FileNotFoundException;
 import java.io.IOException;
 import java.util.List;

/**
 * A contract for IO manager.
 * This will be useful if several or different versions/implementations of the io management are needed.
 * The developer developing this library can develop a new implementation for managing input and output operations
 * (eg. different save format or object format) without affecting other components.
 *
 * @author Naman Kumar
 * @version 1.0
 * @since 09/24/2017
 */
public interface IOManager {

    /**
     * This method is used to searlize and write the content of the address book to list.
     * This method helps with data persistence.
     *
     * @param filePath the root directory where contacts need to be saved.
     * @param contactList The list of contacts to be written to the file.
     * @return true if the file was saved to passes directory, false otherwise.
     */
    boolean saveAddressBook(String filePath, List<Contact> contactList);

    /**
     * This method is used to read from a saved version of data.
     * This method helps with retrieval of persisted data.
     *
     * @param filePath The file path for file where contacts are read from.
     * @return the list of contacts created on reading the saved data.
     */
    List<Contact> readAddressBook(String filePath);
}
