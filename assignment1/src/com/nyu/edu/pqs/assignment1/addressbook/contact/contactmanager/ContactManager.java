package com.nyu.edu.pqs.assignment1.addressbook.contact.contactmanager;

import com.nyu.edu.pqs.assignment1.addressbook.contact.Contact;
import com.nyu.edu.pqs.assignment1.addressbook.pojo.ContactPojo;

import java.util.List;

/**
 * A contract for contact manager.
 * This will be useful if several or different versions/implementations of the contact management are needed.
 * The developer developing this library can develop a new implementation for managing contacts
 * (eg. faster implementation for managing contacts) without affecting other components.
 *
 * @author Naman Kumar
 * @version 1.0
 * @since 09/23/2017
 */
public interface ContactManager {

    /**
     * This method is used to create a contact and add it to contact list.
     * The developer passes a plain old java object and a contact object is added.
     *
     * @param addressBookEntry user contact object to be converted to actual data object and added to the list.
     * @param contactList
     * @return true if the user object was successfully added, false otherwise.
     */
    boolean createEntry(ContactPojo addressBookEntry, List<Contact> contactList);

    /**
     * This method is used to remove a contact from a list.
     * The developer passes a plain old java object and the contact corresponding to that object is removed.
     *
     * @param addressBookEntry user contact object to be converted to actual data object and removed from the list.
     * @param contactList
     * @return true if the contact was found and removed, false otherwise.
     */
    boolean removeEntry(ContactPojo addressBookEntry, List<Contact> contactList);

    /**
     * This method is used to search for a contact from the list of contacts.
     *
     * @param searchTerm
     * @param contactList
     * @return
     */
    List<ContactPojo> searchEntry(String searchTerm, List<Contact> contactList);
}
