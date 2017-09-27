package com.nyu.edu.pqs.assignment1.addressbook.contact;

import com.nyu.edu.pqs.assignment1.addressbook.contact.conatctproperty.*;

/**
 * This is the model contact object which is used as the actual data in the address book and is not expected by api
 * methods. Since, this is the internal
 * Use the builder {@link ContactBuilder} class to create an instance of this method.
 */
public class Contact implements Comparable<Contact> {
    private final Name name;
    private final PhoneNumber phoneNumber;
    private final Email email;
    private final Note note;
    private final PostalAddress postalAddress;

    private Contact(Name name, PhoneNumber phoneNumber, Email email, Note note, PostalAddress postalAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.note = note;
        this.postalAddress = postalAddress;
    }

    public Name getName() {
        return name;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public Email getEmail() {
        return email;
    }

    public Note getNote() {
        return note;
    }

    public PostalAddress getPostalAddress() {
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
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Contact o) {
        return this.name.compareTo(o.name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (name != null ? !name.equals(contact.name) : contact.name != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(contact.phoneNumber) : contact.phoneNumber != null) return false;
        if (email != null ? !email.equals(contact.email) : contact.email != null) return false;
        if (note != null ? !note.equals(contact.note) : contact.note != null) return false;
        return postalAddress != null ? postalAddress.equals(contact.postalAddress) : contact.postalAddress == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (postalAddress != null ? postalAddress.hashCode() : 0);
        return result;
    }

    /**
     * This is the builder class to create an object of type {@link Contact}
     */
    public static class ContactBuilder {
        private Name name;
        private PhoneNumber phoneNumber;
        private Email email;
        private Note note;
        private PostalAddress postalAddress;

        public ContactBuilder setName(Name name) {
            this.name = name;
            return this;
        }

        public ContactBuilder setPhoneNumber(PhoneNumber phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public ContactBuilder setEmail(Email email) {
            this.email = email;
            return this;
        }

        public ContactBuilder setNote(Note note) {
            this.note = note;
            return this;
        }

        public ContactBuilder setPostalAddress(PostalAddress postalAddress) {
            this.postalAddress = postalAddress;
            return this;
        }

        public Contact build() {
            return new Contact(name, phoneNumber, email, note, postalAddress);
        }
    }
}
