package com.nyu.edu.pqs.assignment1.addressbook.pojo.contactpopertypojo;

/**
 * This is the model email object which user uses to create to {@link PhoneNumberPojo}.
 * Use the builder {@link PhoneNumberEntryPojoBuilder} class to create an instance of this method.
 */
public class PhoneNumberFormatPojo {
    private final String countryCode;
    private final String areaCode;
    private final String firstThreeDigit;
    private final String lastFourDigit;

    private PhoneNumberFormatPojo(final String countryCode, final String areaCode,
                                  final String firstThreeDigit, final String lastFourDigit) {
        this.countryCode = countryCode;
        this.areaCode = areaCode;
        this.firstThreeDigit = firstThreeDigit;
        this.lastFourDigit = lastFourDigit;
    }

    /**
     *
     * @return numerical country code as string or null.
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     *
     * @return numerical area code as string or null.
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     *
     * @return numerical first three digit after area code of phone number as string or null.
     */
    public String getFirstThreeDigit() {
        return firstThreeDigit;
    }

    /**
     *
     * @return numerical last three digit of phone number as string or null.
     */
    public String getLastFourDigit() {
        return lastFourDigit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return actualPhoneNumber();
    }

    private String actualPhoneNumber() {
        return "+" + countryCode + actualPhoneNumberWithoutCountryCode();
    }


    private String  actualPhoneNumberWithoutCountryCode() {
        return " (" + areaCode + ") " +
                firstThreeDigit + "-" + lastFourDigit;
    }

    /**
     * This is the builder class to create an object of type {@link PhoneNumberFormatPojo}
     */
    public static class PhoneNumberEntryPojoBuilder {
        private String countryCode;
        private String areaCode;
        private String firstThreeDigit;
        private String lastFourDigit;

        /**
         * Used to set the country of phone number. They can only be numerical as string or null.
         *
         * @param countryCode the country code for the phone number.
         * @return current instance of {@link PhoneNumberEntryPojoBuilder}
         */
        public PhoneNumberEntryPojoBuilder setCountryCode(final String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        /**
         * Used to set the area code of phone number. They can only be numerical as string or null.
         *
         * @param areaCode the area code for a phone number.
         * @return current instance of {@link PhoneNumberEntryPojoBuilder}
         */
        public PhoneNumberEntryPojoBuilder setAreaCode(final String areaCode) {
            this.areaCode = areaCode;
            return this;
        }

        /**
         * Used to set the first three digits of phone number. They can only be numerical as string or null.
         *
         * @param firstThreeDigit the first three digit after area code.
         * @return current instance of {@link PhoneNumberEntryPojoBuilder}
         */
        public PhoneNumberEntryPojoBuilder setFirstThreeDigit(final String firstThreeDigit) {
            this.firstThreeDigit = firstThreeDigit;
            return this;
        }

        /**
         * Used to set the last four digits of phone number. They can only be numerical as string or null.
         *
         * @param lastFourDigit the last four digit of phone number
         * @return current instance of {@link PhoneNumberEntryPojoBuilder}
         */
        public PhoneNumberEntryPojoBuilder setLastFourDigit(final String lastFourDigit) {
            this.lastFourDigit = lastFourDigit;
            return this;
        }

        public PhoneNumberFormatPojo build() {
            return new PhoneNumberFormatPojo(countryCode, areaCode, firstThreeDigit, lastFourDigit);
        }
    }
}
