package com.nyu.edu.pqs.assignment1.addressbook.pojo.contactpopertypojo;

import com.nyu.edu.pqs.assignment1.addressbook.pojo.ContactPojo;

/**
 * This is the model email object which user uses to create to {@link ContactPojo}.
 * Use the builder {@link NamePojoBuilder} class to create an instance of this method.
 */
public class NamePojo {
    private final String firstName;
    private final String middleName;
    private final String lastName;

    private NamePojo(final String firstName, final String middleName, final String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    /**
     * Return the first name which could be null or a string of length less than 60.
     *
     * @return the first name of the user.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Return the middle name which could be null or a string of length less than 60.
     *
     * @return the middle name of the user.
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Return the last name which could be null or a string of length less than 60.
     *
     * @return the last name of the user.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return firstName + " " + middleName + " " + lastName;
    }

    /**
     * This is the builder class to create an object of type {@link NamePojo}
     */
    public static class NamePojoBuilder {
        private String firstName;
        private String middleName;
        private String lastName;

        /**
         * Used to set a first name which is either 60 or leass character long or null.
         *
         * @param firstName the first name.
         * @return an instance of {@link NamePojoBuilder} to build an object of type {@link NamePojo}
         */
        public NamePojoBuilder setFirstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        /**
         * Used to set a middle name which is either 60 or leass character long or null.
         *
         * @param middleName the middle name.
         * @return an instance of {@link NamePojoBuilder} to build an object of type {@link NamePojo}
         */
        public NamePojoBuilder setMiddleName(final String middleName) {
            this.middleName = middleName;
            return this;
        }

        /**
         * Used to set a last name which is either 60 or leass character long or null.
         *
         * @param lastName the last name.
         * @return an instance of {@link NamePojoBuilder} to build an object of type {@link NamePojo}
         */
        public NamePojoBuilder setLastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        public NamePojo build() {
            return new NamePojo(firstName, middleName, lastName);
        }
    }

}
