package com.nyu.edu.pqs.assignment1.addressbook.api.impl;

import com.nyu.edu.pqs.assignment1.addressbook.api.AddressBook;
import com.nyu.edu.pqs.assignment1.addressbook.version.Version;

/**
 * This is a factory class for {@link AddressBook} interface.
 * This gives a method that checks for version and accordingly returns correct implementation for the interface.
 * As more versions of the address book library are released one can change the code here to determine the
 * correct address book implementation to use without changing the initialization in all classes.
 */
public final class AddressBookFactory {

    /**
     * This constructor prevents the default parameter-less constructor from being used elsewhere.
     */
    private AddressBookFactory() {}

    /**
     * This methods returns an instance of AddressBook interface depending on the version provided by user.
     * The library developer could over-write the version provided here if he wants the code to be defaulted to
     * a particular version.
     *
     * @param version The version information provided by user.
     * @return An instance of implementation of Address Book interface.
     */
    public static AddressBook getAddressBook(final Version version) {
        if (version == Version.Version1) {
            return new AddressBookImpl(version);
        }
        return null;
    }
}
