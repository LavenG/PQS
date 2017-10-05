package com.nyu.edu.pqs.assignment1.addressbook.contact.conatctproperty;

import com.nyu.edu.pqs.assignment1.addressbook.contact.Contact;

/**
 * This is the model email object which user uses to create to {@link Contact}.
 * Use the builder {@link EmailBuilder} class to create an instance of this method.
 */
public class Email {
    private final EmailFormat personalEmail;
    private final EmailFormat workEmail;

    private Email(final EmailFormat personalEmail, final EmailFormat workEmail) {
        this.personalEmail = personalEmail;
        this.workEmail = workEmail;
    }

    /**
     * Used to get the personal email address of a user
     *
     * @return The personal email which is an object of type {@link EmailFormat}
     */
    public EmailFormat getPersonalEmail() {
        return personalEmail;
    }

    /**
     * Used to get the work email address of a user
     *
     * @return The work email which is an object of type {@link EmailFormat}
     */
    public EmailFormat getWorkEmail() {
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
     * This method is used to check if the current object starts with a given text.
     *
     * @param searchTerm the string which has to be verified against.
     * @return true if object starts with the provided text, false otherwise.
     */
    public boolean startsWith(final String searchTerm) {
        return personalEmail.startsWith(searchTerm) || workEmail.startsWith(searchTerm);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Email email = (Email) o;

        if (personalEmail != null ? !personalEmail.equals(email.personalEmail) : email.personalEmail != null)
            return false;
        return workEmail != null ? workEmail.equals(email.workEmail) : email.workEmail == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = personalEmail != null ? personalEmail.hashCode() : 0;
        result = 31 * result + (workEmail != null ? workEmail.hashCode() : 0);
        return result;
    }

    /**
     * This is the builder class to create an object of type {@link Email}
     */
    public static class EmailBuilder {
        private EmailFormat personalEmail;
        private EmailFormat workEmail;

        /**
         * To initialize value for personal email for an email pojo object.
         *
         * @param personalEmail An object of type {@link EmailFormat} for personal email
         *                      which contains username and domain.
         * @return the current instance of the builder.
         */
        public EmailBuilder setPersonalEmail(final EmailFormat personalEmail) {
            this.personalEmail = personalEmail;
            return this;
        }

        /**
         * To initialize value for work email for an email pojo object.
         *
         * @param workEmail An object of type {@link EmailFormat} for work email
         *                  which contains username and domain.
         * @return the current instance of the builder.
         */
        public EmailBuilder setWorkEmail(final EmailFormat workEmail) {
            this.workEmail = workEmail;
            return this;
        }

        /**
         * To build a EmailPojo object.
         *
         * @return An object of type {@link Email}
         */
        public Email build() {
            return new Email(personalEmail, workEmail);
        }
    }
}
