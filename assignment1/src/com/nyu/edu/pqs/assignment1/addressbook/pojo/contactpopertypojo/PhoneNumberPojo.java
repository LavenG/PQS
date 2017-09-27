package com.nyu.edu.pqs.assignment1.addressbook.pojo.contactpopertypojo;

import com.nyu.edu.pqs.assignment1.addressbook.pojo.ContactPojo;

/**
 * This is the model email object which user uses to create to {@link ContactPojo}.
 * Use the builder {@link PhoneNumberPojoBuilder} class to create an instance of this method.
 */
public class PhoneNumberPojo {
    private final PhoneNumberFormatPojo mobileNumber;
    private final PhoneNumberFormatPojo workNumber;
    private final PhoneNumberFormatPojo homeNumber;

    private PhoneNumberPojo(PhoneNumberFormatPojo mobileNumber, PhoneNumberFormatPojo workNumber, PhoneNumberFormatPojo homeNumber) {
        this.mobileNumber = mobileNumber;
        this.workNumber = workNumber;
        this.homeNumber = homeNumber;
    }

    public PhoneNumberFormatPojo getMobileNumber() {
        return mobileNumber;
    }

    public PhoneNumberFormatPojo getHomeNumber() {
        return homeNumber;
    }

    public PhoneNumberFormatPojo getWorkNumber() {
        return workNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Mobile Number:\t" + mobileNumber.toString() + "\nHome Number:\t" + homeNumber.toString() + "\nWork Number:\t" + workNumber.toString();
    }

    /**
     * This is the builder class to create an object of type {@link PhoneNumberPojo}
     */
    public static class PhoneNumberPojoBuilder {
        private PhoneNumberFormatPojo mobileNumber;
        private PhoneNumberFormatPojo workNumber;
        private PhoneNumberFormatPojo homeNumber;

        public PhoneNumberPojoBuilder setMobileNumber(PhoneNumberFormatPojo mobileNumber) {
            this.mobileNumber = mobileNumber;
            return this;
        }

        public PhoneNumberPojoBuilder setWorkNumber(PhoneNumberFormatPojo workNumber) {
            this.workNumber = workNumber;
            return this;
        }

        public PhoneNumberPojoBuilder setHomeNumber(PhoneNumberFormatPojo homeNumber) {
            this.homeNumber = homeNumber;
            return this;
        }

        public PhoneNumberPojo build() {
            return new PhoneNumberPojo(mobileNumber, workNumber, homeNumber);
        }
    }
}
