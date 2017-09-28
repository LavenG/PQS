package com.nyu.edu.pqs.assignment1.addressbook.helper;

import com.nyu.edu.pqs.assignment1.addressbook.api.AddressBook;
import com.nyu.edu.pqs.assignment1.addressbook.contact.Contact;
import com.nyu.edu.pqs.assignment1.addressbook.contact.conatctproperty.*;
import com.nyu.edu.pqs.assignment1.addressbook.pojo.ContactPojo;
import com.nyu.edu.pqs.assignment1.addressbook.pojo.contactpopertypojo.*;

/**
 * This is a helper class for {@link AddressBook} library.
 * This class is used to convert a passsed Pojo to actual Contact Model and vice-versa.
 */
public final class Helper {

    /**
     * This constructor prevents the default parameter-less constructor from being used elsewhere.
     */
    private Helper() {}

    /**
     * The function convers a pojo to a model object to use for our address book.
     *
     * @param contactPojo A contact object passed by user.
     * @return A contact object that can be used for model.
     */
    public static Contact convertContactPojoToContact(final ContactPojo contactPojo) {
        EmailFormat workEmailFormat = convertEmailFormatPojoToEmailFormat(
                contactPojo.getEmail().getWorkEmail());
        EmailFormat personalEmailFormat = convertEmailFormatPojoToEmailFormat(
                contactPojo.getEmail().getPersonalEmail());
        Email email = new Email.EmailBuilder()
                .setPersonalEmail(personalEmailFormat)
                .setWorkEmail(workEmailFormat)
                .build();
        PhoneNumberFormat mobilePhoneNumberFormat = convertPhoneNumberFormatPojoToPhoneNumberFormat(
                contactPojo.getPhoneNumber().getMobileNumber());
        PhoneNumberFormat workPhoneNumberFormat = convertPhoneNumberFormatPojoToPhoneNumberFormat(
                contactPojo.getPhoneNumber().getWorkNumber());
        PhoneNumberFormat homePhoneNumberFormat = convertPhoneNumberFormatPojoToPhoneNumberFormat(
                contactPojo.getPhoneNumber().getHomeNumber());
        PhoneNumber phoneNumber = new PhoneNumber.PhoneNumberBuilder()
                .setHomeNumber(homePhoneNumberFormat)
                .setMobileNumber(mobilePhoneNumberFormat)
                .setWorkNumber(workPhoneNumberFormat)
                .build();
        Name name = new Name.NameBuilder()
                .setFirstName(contactPojo.getName().getFirstName())
                .setMiddleName(contactPojo.getName().getMiddleName())
                .setLastName(contactPojo.getName().getLastName())
                .build();
        PostalAddress postalAddress = new PostalAddress.PostalAddressBuilder()
                .setAddressLine1(contactPojo.getPostalAddress().getAddressLine1())
                .setAddressLine2(contactPojo.getPostalAddress().getAddressLine2())
                .setCity(contactPojo.getPostalAddress().getCity())
                .setState(contactPojo.getPostalAddress().getState())
                .setCountry(contactPojo.getPostalAddress().getCountry())
                .setZipCode(contactPojo.getPostalAddress().getZipCode())
                .build();
        Note note = new Note.NoteBuilder()
                .setNote(contactPojo.getNote().getNote())
                .build();
        return new Contact.ContactBuilder()
                .setName(name)
                .setPhoneNumber(phoneNumber)
                .setEmail(email)
                .setPostalAddress(postalAddress)
                .build();
    }

    /**
     * The functions converts a model to a pojo object to pass back to user.
     *
     * @param contact A contact object used in the model.
     * @return A contact object familiar to user.
     */
    public static ContactPojo convertContactToContactPojo(final Contact contact) {
        EmailFormatPojo workEmailFormatPojo = convertEmailFormatToEmailFormatPojo(
                contact.getEmail().getWorkEmail());
        EmailFormatPojo personalEmailFormatPojo = convertEmailFormatToEmailFormatPojo(
                contact.getEmail().getPersonalEmail());
        EmailPojo emailPojo = new EmailPojo.EmailPojoBuilder()
                .setPersonalEmail(personalEmailFormatPojo)
                .setWorkEmail(workEmailFormatPojo)
                .build();
        PhoneNumberFormatPojo mobilePhoneNumberFormatPojo = convertPhoneNumberFormatToPhoneNumberFormatPojo(
                contact.getPhoneNumber().getMobileNumber());
        PhoneNumberFormatPojo workPhoneNumberFormatPojo = convertPhoneNumberFormatToPhoneNumberFormatPojo(
                contact.getPhoneNumber().getWorkNumber());
        PhoneNumberFormatPojo homePhoneNumberFormatPojo = convertPhoneNumberFormatToPhoneNumberFormatPojo(
                contact.getPhoneNumber().getHomeNumber());
        PhoneNumberPojo phoneNumberPojo = new PhoneNumberPojo.PhoneNumberPojoBuilder()
                .setHomeNumber(homePhoneNumberFormatPojo)
                .setMobileNumber(mobilePhoneNumberFormatPojo)
                .setWorkNumber(workPhoneNumberFormatPojo)
                .build();
        NamePojo namePojo = new NamePojo.NamePojoBuilder()
                .setFirstName(contact.getName().getFirstName())
                .setMiddleName(contact.getName().getMiddleName())
                .setLastName(contact.getName().getLastName())
                .build();
        PostalAddressPojo postalAddressPojo = new PostalAddressPojo.PostalAddressPojoBuilder()
                .setAddressLine1(contact.getPostalAddress().getAddressLine1())
                .setAddressLine2(contact.getPostalAddress().getAddressLine2())
                .setCity(contact.getPostalAddress().getCity())
                .setState(contact.getPostalAddress().getState())
                .setCountry(contact.getPostalAddress().getCountry())
                .setZipCode(contact.getPostalAddress().getZipCode())
                .build();
        NotePojo notePojo = new NotePojo.NotePojoBuilder()
                .setNote(contact.getNote().getNote())
                .build();
        return new ContactPojo.ContactPojoBuilder()
                .setName(namePojo)
                .setPhoneNumber(phoneNumberPojo)
                .setEmail(emailPojo)
                .setPostalAddress(postalAddressPojo)
                .setNote(notePojo)
                .build();
    }

    private static PhoneNumberFormat convertPhoneNumberFormatPojoToPhoneNumberFormat(final PhoneNumberFormatPojo phoneNumberFormatPojo) {
        return new PhoneNumberFormat.PhoneNumberFormatBuilder().setCountryCode(phoneNumberFormatPojo.getCountryCode())
                .setAreaCode(phoneNumberFormatPojo.getAreaCode())
                .setFirstThreeDigit(phoneNumberFormatPojo.getFirstThreeDigit())
                .setLastFourDigit(phoneNumberFormatPojo.getLastFourDigit())
                .build();
    }

    private static PhoneNumberFormatPojo convertPhoneNumberFormatToPhoneNumberFormatPojo(final PhoneNumberFormat phoneNumberFormat) {
        return new PhoneNumberFormatPojo.PhoneNumberEntryPojoBuilder().setCountryCode(phoneNumberFormat.getCountryCode())
                .setAreaCode(phoneNumberFormat.getAreaCode())
                .setFirstThreeDigit(phoneNumberFormat.getFirstThreeDigit())
                .setLastFourDigit(phoneNumberFormat.getLastFourDigit())
                .build();
    }

    private static EmailFormatPojo convertEmailFormatToEmailFormatPojo(final EmailFormat emailFormat) {
        return new EmailFormatPojo.EmailFormatPojoBuilder().setUserName(emailFormat.getUserName()).setDomain(emailFormat.getDomain()).build();
    }

    private static EmailFormat convertEmailFormatPojoToEmailFormat(final EmailFormatPojo emailFormatPojo) {
        return new EmailFormat.EmailFormatBuilder().setUserName(emailFormatPojo.getUserName()).setDomain(emailFormatPojo.getDomain()).build();
    }
}
