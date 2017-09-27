package com.nyu.edu.pqs.assignment1.addressbook.api;

/**
 * This is a VERSION control enum for {@link AddressBook} library.
 * As more versions of the library are developed they could be added to the version enum
 * switching between versions should be easier and clearer due to this enum.
 */
public enum Version {
    Version1,
    Version2;

    @Override
    public String toString() {
        return "Version" + name();
    }
}
