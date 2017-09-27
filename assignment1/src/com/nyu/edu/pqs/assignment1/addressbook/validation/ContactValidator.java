package com.nyu.edu.pqs.assignment1.addressbook.validation;

import com.nyu.edu.pqs.assignment1.addressbook.pojo.ContactPojo;

/**
 * A contract for contact validator.
 * This will be useful if several or different versions/implementations of the validation are needed.
 * The developer developing this library can develop a new implementation for validator without
 * affecting other components.
 *
 * @author Naman Kumar
 * @version 1.0
 * @since 09/26/2017
 */
public interface ContactValidator {

    /**
     * This method is used to validate the user provided data.
     * Currently the checks are limited to number and name but it could be extended in the future.
     *
     * @param contactPojo A user created contact like object to add a new contact to address book.
     * @return true if the data provided is valid, false otherwise.
     */
    boolean isValidContact(ContactPojo contactPojo);
}
