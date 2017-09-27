package com.nyu.edu.pqs.assignment1.addressbook.contact.conatctproperty;

import com.nyu.edu.pqs.assignment1.addressbook.contact.Contact;

/**
 * This is the model email object which user uses to create to {@link Contact}.
 * Use the builder {@link EmailBuilder} class to create an instance of this method.
 */
public class Email {
    private final EmailFormat personalEmail;
    private final EmailFormat workEmail;

    private Email(EmailFormat personalEmail, EmailFormat workEmail) {
        this.personalEmail = personalEmail;
        this.workEmail = workEmail;
    }

    public EmailFormat getPersonalEmail() {
        return personalEmail;
    }

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
    public boolean startsWith(String searchTerm) {
        return personalEmail.startsWith(searchTerm) || workEmail.startsWith(searchTerm);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
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

        public EmailBuilder setPersonalEmail(EmailFormat personalEmail) {
            this.personalEmail = personalEmail;
            return this;
        }

        public EmailBuilder setWorkEmail(EmailFormat workEmail) {
            this.workEmail = workEmail;
            return this;
        }

        public Email build() {
            return new Email(personalEmail, workEmail);
        }
    }
}
