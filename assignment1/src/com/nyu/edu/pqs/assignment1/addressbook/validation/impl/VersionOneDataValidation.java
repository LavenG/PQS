package com.nyu.edu.pqs.assignment1.addressbook.validation.impl;

import com.nyu.edu.pqs.assignment1.addressbook.pojo.ContactPojo;
import com.nyu.edu.pqs.assignment1.addressbook.pojo.contactpopertypojo.NamePojo;
import com.nyu.edu.pqs.assignment1.addressbook.pojo.contactpopertypojo.PhoneNumberFormatPojo;
import com.nyu.edu.pqs.assignment1.addressbook.pojo.contactpopertypojo.PhoneNumberPojo;
import com.nyu.edu.pqs.assignment1.addressbook.validation.ContactValidator;

/**
 * This is one implementation/prototype of the {@link ContactValidator} library.
 * A developer using the library can use this prototype to execute operations defined by the inerface.
 * This implementation is used to verify data send for a contact object of Version type version 1.
 */
public class VersionOneDataValidation implements ContactValidator {

    /**
     * This method checks if name and phone number entered is valid.
     *
     * {@inheritDoc}
     */
    @Override
    public boolean isValidContact(ContactPojo contactPojo) {
        return isNameValid(contactPojo.getName()) && isPhoneNumberValid(contactPojo.getPhoneNumber());
    }

    private boolean isPhoneNumberValid(PhoneNumberPojo phoneNumber) {
        return phoneNumber == null || (( phoneNumber.getHomeNumber() == null ||
                isValidPhoneNumberFormat(phoneNumber.getHomeNumber()))) &&
                (( phoneNumber.getMobileNumber() == null ||
                        isValidPhoneNumberFormat(phoneNumber.getMobileNumber()))) &&
                (( phoneNumber.getWorkNumber() == null ||
                        isValidPhoneNumberFormat(phoneNumber.getWorkNumber())));
    }

    private boolean isValidPhoneNumberFormat(PhoneNumberFormatPojo phoneNumberFormatPojo) {
        return isInteger(phoneNumberFormatPojo.getCountryCode()) &&
                isInteger(phoneNumberFormatPojo.getAreaCode()) &&
                isInteger(phoneNumberFormatPojo.getFirstThreeDigit()) &&
                isInteger(phoneNumberFormatPojo.getLastFourDigit());
    }

    private boolean isInteger(String number) {
        if(number == null) return true;
        try {
            Integer.parseInt(number);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isNameValid(NamePojo name) {
        return name == null || ((name.getFirstName() == null || name.getFirstName().length() < 60) &&
                (name.getMiddleName() == null || name.getMiddleName().length() < 60) &&
                (name.getLastName() == null || name.getLastName().length() < 60));
    }
}
