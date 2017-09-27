package com.nyu.edu.pqs.assignment1.addressbook.iomanager.impl;

import com.nyu.edu.pqs.assignment1.addressbook.api.Version;
import com.nyu.edu.pqs.assignment1.addressbook.iomanager.IOManager;

/**
 * This is a factory class for {@link IOManager} interface.
 * This gives a method that checks for version and accordingly returns correct implementation for the interface.
 * As more versions of the address book library are released one can change the code here to determine the
 * correct validation implementation to use without changing the initialization in all classes.
 */
public class IOManagerFactory {

    /**
     * This methods returns an instance of IO Manager interface depending on the version provided by user.
     * The library developer could over-write the version provided here if he wants the code to be defaulted to
     * a particular version.
     *
     * @param version The version information provided by user.
     * @return An instance of implementation of IOManager interface.
     */
    public static IOManager getIOManager(Version version) {
        if(version == Version.Version1) {
            return new JsonFormatIOManager();
        }
        return null;
    }
}
