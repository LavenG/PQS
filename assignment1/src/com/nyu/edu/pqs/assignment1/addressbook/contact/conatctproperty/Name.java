package com.nyu.edu.pqs.assignment1.addressbook.contact.conatctproperty;

import com.nyu.edu.pqs.assignment1.addressbook.contact.Contact;

/**
 * This is the model email object which user uses to create to {@link Contact}.
 * Use the builder {@link NameBuilder} class to create an instance of this method.
 */
public class Name implements Comparable<Name> {
    private final String firstName;
    private final String middleName;
    private final String lastName;

    private Name(final String firstName, final String middleName, final String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final Name o) {
        if(!this.firstName.equals(o.firstName)) {
          return this.firstName.compareTo(o.firstName);
        }
        if(!this.middleName.equals(o.middleName)) {
            return this.middleName.compareTo(o.middleName);
        }
        if(!this.lastName.equals(o.lastName)) {
            return this.lastName.compareTo(o.lastName);
        }
        return 0;
    }

    /**
     * This method is used to check if the current object starts with a given text.
     *
     * @param nameText the string which has to be verified against
     * @return true if object starts with the provided text, false otherwise.
     */
    public boolean startsWith(final String nameText) {
        String consolidatedName = firstName + ' ' + lastName + ' ' + middleName;
        if (firstName.startsWith(nameText)) {
            return true;
        } else if (middleName.startsWith(nameText)) {
            return true;
        } else if (lastName.startsWith(nameText)) {
            return true;
        } else if (consolidatedName.startsWith(nameText)) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return firstName + " " + middleName + " " + lastName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Name name = (Name) o;

        if (firstName != null ? !firstName.equals(name.firstName) : name.firstName != null) return false;
        if (middleName != null ? !middleName.equals(name.middleName) : name.middleName != null) return false;
        return lastName != null ? lastName.equals(name.lastName) : name.lastName == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    /**
     * This is the builder class to create an object of type {@link Name}
     */
    public static class NameBuilder {
        private String firstName;
        private String middleName;
        private String lastName;

        /**
         * Used to set a first name which is either 60 or leass character long or null.
         *
         * @param firstName the first name.
         * @return an instance of {@link NameBuilder} to build an object of type {@link Name}
         */
        public NameBuilder setFirstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        /**
         * Used to set a middle name which is either 60 or leass character long or null.
         *
         * @param middleName the middle name.
         * @return an instance of {@link NameBuilder} to build an object of type {@link Name}
         */
        public NameBuilder setMiddleName(final String middleName) {
            this.middleName = middleName;
            return this;
        }

        /**
         * Used to set a last name which is either 60 or leass character long or null.
         *
         * @param lastName the last name.
         * @return an instance of {@link NameBuilder} to build an object of type {@link Name}
         */
        public NameBuilder setLastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Name build() {
            return new Name(firstName, middleName, lastName);
        }
    }
}
