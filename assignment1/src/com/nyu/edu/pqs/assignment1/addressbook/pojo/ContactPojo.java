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

    /**
     *
     * @return An object of type {@link NamePojo} containing contacts name information.
     */
    public NamePojo getName() {
        return name;
    }

    /**
     *
     * @return An object of type {@link PhoneNumberPojo} containing contacts phone number information.
     */
    public PhoneNumberPojo getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @return An object of type {@link EmailPojo} containing contacts email information.
     */
    public EmailPojo getEmail() {
        return email;
    }

    /**
     *
     * @return An object of type {@link NotePojo} containing contacts note information.
     */
    public NotePojo getNote() {
        return note;
    }

    /**
     *
     * @return An object of type {@link PostalAddressPojo} containing contacts postal address information.
     */
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

        /**
         * To set name object to contact builder.
         *
         * @param name An object of type {@link NamePojo} containing contacts name information.
         * @return an instance of the builder.
         */
        public ContactPojoBuilder setName(final NamePojo name) {
            this.name = name;
            return this;
        }

        /**
         * To set phoneNumber object to contact builder.
         *
         * @param phoneNumber An object of type {@link NamePojo} containing contacts name information.
         * @return an instance of the builder.
         */
        public ContactPojoBuilder setPhoneNumber(final PhoneNumberPojo phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        /**
         *
         * @param email An object of type {@link NamePojo} containing contacts name information.
         * @return an instance of the builder.
         */
        public ContactPojoBuilder setEmail(final EmailPojo email) {
            this.email = email;
            return this;
        }

        /**
         *
         * @param note An object of type {@link NamePojo} containing contacts name information.
         * @return an instance of the builder.
         */
        public ContactPojoBuilder setNote(final NotePojo note) {
            this.note = note;
            return this;
        }

        /**
         *
         * @param postalAddress An object of type {@link NamePojo} containing contacts name information.
         * @return an instance of the builder.
         */
        public ContactPojoBuilder setPostalAddress(final PostalAddressPojo postalAddress) {
            this.postalAddress = postalAddress;
            return this;
        }

        /**
         * Create an object instance of {@link ContactPojo} with desired information set using
         * {@link ContactPojoBuilder} set methods.
         *
         * @return an instance of type {@link ContactPojo}.
         */
        public ContactPojo build() {
            return new ContactPojo(name, phoneNumber, email, note, postalAddress);
        }
    }
}
