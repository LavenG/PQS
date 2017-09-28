package com.nyu.edu.pqs.assignment1.addressbook.contact.contactmanager.impl;

import com.nyu.edu.pqs.assignment1.addressbook.version.Version;
import com.nyu.edu.pqs.assignment1.addressbook.contact.Contact;
import com.nyu.edu.pqs.assignment1.addressbook.contact.contactmanager.ContactManager;
import com.nyu.edu.pqs.assignment1.addressbook.helper.Helper;
import com.nyu.edu.pqs.assignment1.addressbook.pojo.ContactPojo;
import com.nyu.edu.pqs.assignment1.addressbook.validation.ContactValidator;
import com.nyu.edu.pqs.assignment1.addressbook.validation.impl.ContactValidatorFactory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * This is one implementation/prototype of the {@link ContactManager} library.
 * A developer using the library can use this prototype to execute operations defined by the inerface.
 * This implementation has linear time complexity and uses an array list for storing values.
 * The implementation also uses the array add and remove operations.
 * It takes in a user defined object and converts it to model object to store to address book.
 */
class LinearTimeContactManager implements ContactManager {
    /**
     * An instance of a validator class to validate input data.
     */
    private final ContactValidator contactValidator;

    LinearTimeContactManager(Version version) {
        contactValidator = ContactValidatorFactory.getConatctValidator(version);
    }

    /**
     * This method
     *
     * {@inheritDoc}
     */
    @Override
    public boolean createEntry(ContactPojo addressBookEntry, List<Contact> contactList) {
        if(!contactValidator.isValidContact(addressBookEntry) && !contactList.add(Helper.convertContactPojoToContact(addressBookEntry))) {
            return false;
        }
        Collections.sort(contactList);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeEntry(ContactPojo addressBookEntry, List<Contact> contactList) {
        return contactList.remove(Helper.convertContactPojoToContact(addressBookEntry));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ContactPojo> searchEntry(String searchTerm, List<Contact> contactList) {
        List<ContactPojo> searchResults = new LinkedList<>();
        for (Contact contact : contactList) {
            if (contact.getName().startsWith(searchTerm)) {
                searchResults.add(Helper.convertContactToContactPojo(contact));
            } else if (contact.getPhoneNumber().startsWith(searchTerm)) {
                searchResults.add(Helper.convertContactToContactPojo(contact));
            } else if (contact.getEmail().startsWith(searchTerm)) {
                searchResults.add(Helper.convertContactToContactPojo(contact));
            }
        }
        return searchResults;
    }
}
