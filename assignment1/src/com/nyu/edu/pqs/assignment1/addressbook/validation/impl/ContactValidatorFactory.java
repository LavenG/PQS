package com.nyu.edu.pqs.assignment1.addressbook.validation.impl;

import com.nyu.edu.pqs.assignment1.addressbook.version.Version;
import com.nyu.edu.pqs.assignment1.addressbook.validation.ContactValidator;

/**
 * This is a factory class for {@link ContactValidator} interface
 * This gives a method that checks for version and accordingly returns correct implementation for the interface.
 * As more versions of the address book library are released one can change the code here to determine the
 * correct validation implementation to use without changing the initialization in all classes.
 */
public final class ContactValidatorFactory {

    /**
     * This constructor prevents the default parameter-less constructor from being used elsewhere.
     */
    private ContactValidatorFactory() {}

    /**
     * This methods returns an instance of Contact Validator depending on the version provided by user.
     * The library developer could over-write the version provided here if he wants the code to be defaulted to
     * a particular version.
     *
     * @param version The version information provided by user.
     * @return An instance of implementation of COntact Validator interface.
     */
    public static ContactValidator getConatctValidator(final Version version) {
        if (version == Version.Version1) {
            return new VersionOneDataValidation();
        }
        return null;
    }
}
