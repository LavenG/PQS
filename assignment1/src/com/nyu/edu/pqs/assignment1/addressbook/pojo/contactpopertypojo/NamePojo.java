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

    private NamePojo(String firstName, String middleName, String lastName) {
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

        public NamePojoBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public NamePojoBuilder setMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public NamePojoBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public NamePojo build() {
            return new NamePojo(firstName, middleName, lastName);
        }
    }

}
