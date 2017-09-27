package com.nyu.edu.pqs.assignment1.addressbook.contact.conatctproperty;

import com.nyu.edu.pqs.assignment1.addressbook.contact.Contact;

/**
 * This is the model email object which user uses to create to {@link Contact}.
 * Use the builder {@link PhoneNumberBuilder} class to create an instance of this method.
 */
public class PhoneNumber {
    private final PhoneNumberFormat mobileNumber;
    private final PhoneNumberFormat workNumber;
    private final PhoneNumberFormat homeNumber;

    private PhoneNumber(PhoneNumberFormat mobileNumber, PhoneNumberFormat workNumber, PhoneNumberFormat homeNumber) {
        this.mobileNumber = mobileNumber;
        this.workNumber = workNumber;
        this.homeNumber = homeNumber;
    }

    public PhoneNumberFormat getMobileNumber() {
        return mobileNumber;
    }

    public PhoneNumberFormat getHomeNumber() {
        return homeNumber;
    }

    public PhoneNumberFormat getWorkNumber() {
        return workNumber;
    }

    /**
     * This method is used to check if the current object starts with a given text.
     *
     * @param searchTerm the string which has to be verified against.
     * @return true if object starts with the provided text, false otherwise.
     */
    public boolean startsWith(String searchTerm) {
        if (mobileNumber.startsWith(searchTerm)) {
            return true;
        } else if (homeNumber.startsWith(searchTerm)) {
            return true;
        } else if (workNumber.startsWith(searchTerm)) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Mobile Number:\t" + mobileNumber.toString() + "\nHome Number:\t" + homeNumber.toString() + "\nWork Number:\t" + workNumber.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneNumber that = (PhoneNumber) o;

        if (mobileNumber != null ? !mobileNumber.equals(that.mobileNumber) : that.mobileNumber != null) return false;
        if (workNumber != null ? !workNumber.equals(that.workNumber) : that.workNumber != null) return false;
        return homeNumber != null ? homeNumber.equals(that.homeNumber) : that.homeNumber == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = mobileNumber != null ? mobileNumber.hashCode() : 0;
        result = 31 * result + (workNumber != null ? workNumber.hashCode() : 0);
        result = 31 * result + (homeNumber != null ? homeNumber.hashCode() : 0);
        return result;
    }

    /**
     * This is the builder class to create an object of type {@link PhoneNumber}
     */
    public static class PhoneNumberBuilder {
        private PhoneNumberFormat mobileNumber;
        private PhoneNumberFormat workNumber;
        private PhoneNumberFormat homeNumber;

        public PhoneNumberBuilder setMobileNumber(PhoneNumberFormat mobileNumber) {
            this.mobileNumber = mobileNumber;
            return this;
        }

        public PhoneNumberBuilder setWorkNumber(PhoneNumberFormat workNumber) {
            this.workNumber = workNumber;
            return this;
        }

        public PhoneNumberBuilder setHomeNumber(PhoneNumberFormat homeNumber) {
            this.homeNumber = homeNumber;
            return this;
        }

        public PhoneNumber build() {
            return new PhoneNumber(mobileNumber, workNumber, homeNumber);
        }
    }
}
