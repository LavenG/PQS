package com.nyu.edu.pqs.assignment1.addressbook.api;

import com.nyu.edu.pqs.assignment1.addressbook.pojo.ContactPojo;

import java.io.IOException;
import java.util.List;

/**
 * An API contract for address book.
 * This API will come in handy if several versions/implementations of the phone book are needed.
 * The developer using this library can change the implementation imported and switch to a newer version.
 *
 * @author Naman Kumar
 * @version 1.0
 * @since 09/19/2017
 */
// Any future developer who wants to change the whole implementation can
// create a new implementation for the interface.
public interface AddressBook {
    /**
     * This method is used to create a contact and add it to contact list.
     * The developer passes a plain old java object and a contact object is added.
     *
     * @param addressBookEntry user contact object to be converted to actual data object and added to the list.
     * @return true if the user object was successfully added, false otherwise.
     */
    boolean createEntry(ContactPojo addressBookEntry);

    /**
     * This method is used to remove a contact from a list.
     * The developer passes a plain old java object and the contact corresponding to that object is removed.
     *
     * @param addressBookEntry user contact object to be converted to actual data object and removed from the list.
     * @return true if the contact was found and removed, false otherwise.
     */
    boolean removeEntry(ContactPojo addressBookEntry);

    /**
     * This method is used to search for a contact from the list of contacts.
     *
     * @param searchTerm a string which is the search term input by the user.
     * @return a list of all the contacts that match the search term.
     */
    List<ContactPojo> searchEntry(String searchTerm);

    /**
     * This method is used to searlize and write the content of the address book to list.
     * This method helps with data persistence and creates back up for data.
     *
     * @param filePath the root directory where contacts need to be saved.
     * @return true if the file was saved to passes directory, false otherwise.
     * @throws  IOException If read from persisted file fails at the specified file path.
     */
    void saveAddressBook(String filePath) throws IOException;

    /**
     * This method is used to read from a saved version of data.
     * This method helps with retrieval of persisted data.
     * Reads data from the back-up and over writes current data.
     *
     * @param filePath The file path of file where contacts are read from.
     * @return true if the data was read from file and set to contact list, false otherwise.
     * @throws  IOException If the read from file object fails due to Input output error.
     */
    boolean readAddressBook(String filePath) throws IOException;
}
