package com.nyu.edu.pqs.assignment1.addressbook.pojo.contactpopertypojo;

import com.nyu.edu.pqs.assignment1.addressbook.pojo.ContactPojo;

/**
 * This is the model email object which user uses to create to {@link ContactPojo}.
 * Use the builder {@link EmailPojoBuilder} class to create an instance of this method.
 */
public class EmailPojo {
    private final EmailFormatPojo personalEmail;
    private final EmailFormatPojo workEmail;

    private EmailPojo(EmailFormatPojo personalEmail, EmailFormatPojo workEmail) {
        this.personalEmail = personalEmail;
        this.workEmail = workEmail;
    }

    public EmailFormatPojo getPersonalEmail() {
        return personalEmail;
    }

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

        public EmailPojoBuilder setPersonalEmail(EmailFormatPojo personalEmail) {
            this.personalEmail = personalEmail;
            return this;
        }

        public EmailPojoBuilder setWorkEmail(EmailFormatPojo workEmail) {
            this.workEmail = workEmail;
            return this;
        }

        public EmailPojo build() {
            return new EmailPojo(personalEmail, workEmail);
        }
    }
}
