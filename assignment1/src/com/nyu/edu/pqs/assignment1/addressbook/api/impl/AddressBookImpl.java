package com.nyu.edu.pqs.assignment1.addressbook.api.impl;

import com.nyu.edu.pqs.assignment1.addressbook.api.AddressBook;
import com.nyu.edu.pqs.assignment1.addressbook.api.Version;
import com.nyu.edu.pqs.assignment1.addressbook.contact.Contact;
import com.nyu.edu.pqs.assignment1.addressbook.contact.contactmanager.ContactManager;
import com.nyu.edu.pqs.assignment1.addressbook.contact.contactmanager.impl.ContactManagerFactory;
import com.nyu.edu.pqs.assignment1.addressbook.iomanager.IOManager;
import com.nyu.edu.pqs.assignment1.addressbook.iomanager.impl.IOManagerFactory;
import com.nyu.edu.pqs.assignment1.addressbook.pojo.ContactPojo;

import java.util.LinkedList;
import java.util.List;

/**
 * This is one implementation/prototype of the {@link AddressBook} library.
 * A developer using the library can use this prototype to execute operations.
 */
public class AddressBookImpl implements AddressBook {
    // A future developer of the library wanting to use just a new IOManagement or contact management technique can
    // create a new implemetation for the same and change version and factory methods of implementation. This helps
    // with quick future development without making much change to older implementation.
    private final Version version;
    private List<Contact> contactList;
    private final ContactManager contactManager;
    private final IOManager ioManager;

    /**
     * The default constructor which initates objects with desired values.
     */
    AddressBookImpl(Version version) {
        this.version = version;
        contactList = new LinkedList<>();
        contactManager = ContactManagerFactory.getContactManager(this.version);
        ioManager = IOManagerFactory.getIOManager(this.version);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean createEntry(ContactPojo addressBookEntry) {
        return contactManager.createEntry(addressBookEntry, contactList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeEntry(ContactPojo addressBookEntry) {
        return contactManager.removeEntry(addressBookEntry, contactList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ContactPojo> searchEntry(String searchTerm) {
        return contactManager.searchEntry(searchTerm, contactList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean saveAddressBook(String filePath) {
        return ioManager.saveAddressBook(filePath, contactList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean readAddressBook(String filePath) {
            contactList = ioManager.readAddressBook(filePath);
            return contactList != null;
    }
}
