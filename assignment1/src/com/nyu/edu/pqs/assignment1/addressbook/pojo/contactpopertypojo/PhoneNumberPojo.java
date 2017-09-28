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

    private PhoneNumberPojo(final PhoneNumberFormatPojo mobileNumber, final PhoneNumberFormatPojo workNumber,
                            final PhoneNumberFormatPojo homeNumber) {
        this.mobileNumber = mobileNumber;
        this.workNumber = workNumber;
        this.homeNumber = homeNumber;
    }

    /**
     *
     * @return All numerical mobile number. It could also be null.
     */
    public PhoneNumberFormatPojo getMobileNumber() {
        return mobileNumber;
    }

    /**
     *
     * @return All numerical home number. It could also be null.
     */
    public PhoneNumberFormatPojo getHomeNumber() {
        return homeNumber;
    }

    /**
     *
     * @return All numerical mobile number. It could also be null.
     */
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

        /**
         * To set a mobile number which can only be a string og numbers or null.
         *
         * @param mobileNumber The mobile number entered by the user.
         * @return current instance of {@link PhoneNumberPojoBuilder}
         */
        public PhoneNumberPojoBuilder setMobileNumber(final PhoneNumberFormatPojo mobileNumber) {
            this.mobileNumber = mobileNumber;
            return this;
        }

        /**
         * To set a work number which can only be a string og numbers or null.
         *
         * @param workNumber The work number entered by the user.
         * @return current instance of {@link PhoneNumberPojoBuilder}
         */
        public PhoneNumberPojoBuilder setWorkNumber(final PhoneNumberFormatPojo workNumber) {
            this.workNumber = workNumber;
            return this;
        }

        /**
         * To set a home number which can only be a string og numbers or null.
         *
         * @param homeNumber The home number entered by the user.
         * @return current instance of {@link PhoneNumberPojoBuilder}
         */
        public PhoneNumberPojoBuilder setHomeNumber(final PhoneNumberFormatPojo homeNumber) {
            this.homeNumber = homeNumber;
            return this;
        }

        public PhoneNumberPojo build() {
            return new PhoneNumberPojo(mobileNumber, workNumber, homeNumber);
        }
    }
}
