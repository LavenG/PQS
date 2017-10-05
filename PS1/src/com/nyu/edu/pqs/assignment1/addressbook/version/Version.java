package com.nyu.edu.pqs.assignment1.addressbook.version;

import com.nyu.edu.pqs.assignment1.addressbook.api.AddressBook;

/**
 * This is a VERSION control enum for {@link AddressBook} library.
 * As more versions of the library are developed they could be added to the version enum
 * switching between versions should be easier and clearer due to this enum.
 */
public enum Version {
    /**
     * For supporting version 1 of the program.
     */
    Version1,
    /**
     * For supporting version 2 of the program.
     * Not used in current implementation.
     */
    Version2;

    /**
     * To return the string version of an enum object.
     *
     * @return the string version of the enum object calling the method.
     */
    @Override
    public String toString() {
        return "Version" + name();
    }
}
