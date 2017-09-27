package com.nyu.edu.pqs.assignment1.addressbook.pojo;

import com.nyu.edu.pqs.assignment1.addressbook.pojo.contactpopertypojo.*;

/**
 * This is the model contact object which user passes to address book to process.
 * To not expose the internal model implementation our address book uses we have this pojo which
 * interacts with our address book library.
 * Use the builder {@link ContactPojoBuilder} class to create an instance of this method.
 */
public class ContactPojo {
    private final NamePojo name;
    private final PhoneNumberPojo phoneNumber;
    private final EmailPojo email;
    private final NotePojo note;
    private final PostalAddressPojo postalAddress;

    private ContactPojo(NamePojo name, PhoneNumberPojo phoneNumber, EmailPojo email, NotePojo note,
                        PostalAddressPojo postalAddressPojo) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.note = note;
        this.postalAddress = postalAddressPojo;
    }

    public NamePojo getName() {
        return name;
    }

    public PhoneNumberPojo getPhoneNumber() {
        return phoneNumber;
    }

    public EmailPojo getEmail() {
        return email;
    }

    public NotePojo getNote() {
        return note;
    }

    public PostalAddressPojo getPostalAddress() {
        return postalAddress;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Name:\t" + name.toString() + "\nPhone Number:\t" + phoneNumber.toString() + "\nEmail:\t" +
                email.toString() + "\nNote:\t" + note.toString() + "\nPostal Address:\t" + postalAddress.toString();
    }

    /**
     * This is the builder class to create an object of type {@link ContactPojo}
     */
    public static class ContactPojoBuilder {
        private NamePojo name;
        private PhoneNumberPojo phoneNumber;
        private EmailPojo email;
        private NotePojo note;
        private PostalAddressPojo postalAddress;

        public ContactPojoBuilder setName(NamePojo name) {
            this.name = name;
            return this;
        }

        public ContactPojoBuilder setPhoneNumber(PhoneNumberPojo phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public ContactPojoBuilder setEmail(EmailPojo email) {
            this.email = email;
            return this;
        }

        public ContactPojoBuilder setNote(NotePojo note) {
            this.note = note;
            return this;
        }

        public ContactPojoBuilder setPostalAddress(PostalAddressPojo postalAddress) {
            this.postalAddress = postalAddress;
            return this;
        }

        public ContactPojo build() {
            return new ContactPojo(name, phoneNumber, email, note, postalAddress);
        }
    }
}
