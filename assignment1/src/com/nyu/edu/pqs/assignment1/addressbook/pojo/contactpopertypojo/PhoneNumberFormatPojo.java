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

    private PhoneNumberFormatPojo(String countryCode, String areaCode, String firstThreeDigit, String lastFourDigit) {
        this.countryCode = countryCode;
        this.areaCode = areaCode;
        this.firstThreeDigit = firstThreeDigit;
        this.lastFourDigit = lastFourDigit;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public String getFirstThreeDigit() {
        return firstThreeDigit;
    }

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

        public PhoneNumberEntryPojoBuilder setCountryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public PhoneNumberEntryPojoBuilder setAreaCode(String areaCode) {
            this.areaCode = areaCode;
            return this;
        }

        public PhoneNumberEntryPojoBuilder setFirstThreeDigit(String firstThreeDigit) {
            this.firstThreeDigit = firstThreeDigit;
            return this;
        }

        public PhoneNumberEntryPojoBuilder setLastFourDigit(String lastFourDigit) {
            this.lastFourDigit = lastFourDigit;
            return this;
        }

        public PhoneNumberFormatPojo build() {
            return new PhoneNumberFormatPojo(countryCode, areaCode, firstThreeDigit, lastFourDigit);
        }
    }
}
