package com.nyu.edu.pqs.assignment1.addressbook.contact.contactmanager.impl;

import com.nyu.edu.pqs.assignment1.addressbook.api.Version;
import com.nyu.edu.pqs.assignment1.addressbook.contact.contactmanager.ContactManager;

/**
 * This is a factory class for {@link ContactManager} interface.
 * This gives a method that checks for version and accordingly returns correct implementation for the interface.
 * As more versions of the address book library are released one can change the code here to determine the
 * correct Contact Manager implementation to use without changing the initialization in all classes.
 */
public class ContactManagerFactory {

    /**
     * This methods returns an instance of ContactManager interface depending on the version provided by user.
     * The library developer could over-write the version provided here if he wants the code to be defaulted to
     * a particular version.
     *
     * @param version The version information provided by user.
     * @return An instance of implementation of Contact Manager interface.
     */
    public static ContactManager getContactManager(Version version) {
        if(version == Version.Version1) {
            return new LinearTimeContactManager(version);
        }
        return null;
    }
}
