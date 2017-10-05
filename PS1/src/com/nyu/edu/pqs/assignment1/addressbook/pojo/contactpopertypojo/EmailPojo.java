package com.nyu.edu.pqs.assignment1.addressbook.pojo.contactpopertypojo;

import com.nyu.edu.pqs.assignment1.addressbook.pojo.ContactPojo;

/**
 * This is the model email object which user uses to create to {@link ContactPojo}.
 * Use the builder {@link EmailPojoBuilder} class to create an instance of this method.
 */
public class EmailPojo {
    private final EmailFormatPojo personalEmail;
    private final EmailFormatPojo workEmail;

    private EmailPojo(final EmailFormatPojo personalEmail, final EmailFormatPojo workEmail) {
        this.personalEmail = personalEmail;
        this.workEmail = workEmail;
    }

    /**
     * Used to get the personal email address of a user.
     *
     * @return The personal email which is an object of type {@link EmailFormatPojo}
     */
    public EmailFormatPojo getPersonalEmail() {
        return personalEmail;
    }

    /**
     * Used to get the work email address of a user
     *
     * @return The work email which is an object of type {@link EmailFormatPojo}
     */
    public EmailFormatPojo getWorkEmail() {
        return workEmail;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Personal e-mail:\t" + personalEmail.toString() + "\n Work e-mail:\t" + workEmail.toString();
    }

    /**
     * This is the builder class to create an object of type {@link EmailPojo}
     */
    public static class EmailPojoBuilder {
        private EmailFormatPojo personalEmail;
        private EmailFormatPojo workEmail;

        /**
         * To initialize value for personal email for an email object.
         *
         * @param personalEmail An object of type {@link EmailFormatPojo} for personal email
         *                      which contains username and domain.
         * @return the current instance of the builder.
         */
        public EmailPojoBuilder setPersonalEmail(final EmailFormatPojo personalEmail) {
            this.personalEmail = personalEmail;
            return this;
        }

        /**
         * To initialize value for work email for an email object.
         *
         * @param workEmail An object of type {@link EmailFormatPojo} for work email
         *                  which contains username and domain.
         * @return the current instance of the builder.
         */
        public EmailPojoBuilder setWorkEmail(final EmailFormatPojo workEmail) {
            this.workEmail = workEmail;
            return this;
        }

        /**
         * To build a Email object.
         *
         * @return An object of type {@link EmailPojo}.
         */
        public EmailPojo build() {
            return new EmailPojo(personalEmail, workEmail);
        }
    }
}
