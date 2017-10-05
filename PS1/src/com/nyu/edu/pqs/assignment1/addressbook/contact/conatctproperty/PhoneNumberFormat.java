package com.nyu.edu.pqs.assignment1.addressbook.contact.conatctproperty;

/**
 * This is the model email object which user uses to create to {@link PhoneNumber}.
 * Use the builder {@link PhoneNumberFormatBuilder} class to create an instance of this method.
 */
public class PhoneNumberFormat {
    private final String countryCode;
    private final String areaCode;
    private final String firstThreeDigit;
    private final String lastFourDigit;

    private PhoneNumberFormat(final String countryCode, final String areaCode,
                              final String firstThreeDigit, final String lastFourDigit) {
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

    /**
     * This method is used to check if the current object starts with a given text.
     *
     * @param searchTerm the string which has to be verified against.
     * @return true if object starts with the provided text, false otherwise.
     */
    public boolean startsWith(final String searchTerm) {
        if (countryCode.startsWith(searchTerm)) {
            return true;
        } else if (areaCode.startsWith(searchTerm)) {
            return true;
        } else if (firstThreeDigit.startsWith(searchTerm)) {
            return true;
        } else if (actualPhoneNumber().startsWith(searchTerm)) {
            return true;
        } else if (completeWithoutFormatting().startsWith(searchTerm)) {
            return true;
        } else if (withoutCountryCodeAndFormatting().startsWith(searchTerm)) {
            return true;
        } else if (actualPhoneNumberWithoutCountryCode().startsWith(searchTerm)) {
            return true;
        }
        return false;
    }

    private String actualPhoneNumber() {
        return "+" + countryCode + actualPhoneNumberWithoutCountryCode();
    }

    private String  actualPhoneNumberWithoutCountryCode() {
        return " (" + areaCode + ") " +
                firstThreeDigit + "-" + lastFourDigit;
    }

    private String completeWithoutFormatting() {
        return countryCode + withoutCountryCodeAndFormatting();
    }

    private String withoutCountryCodeAndFormatting() {
        return areaCode + firstThreeDigit + lastFourDigit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneNumberFormat that = (PhoneNumberFormat) o;

        if (countryCode != null ? !countryCode.equals(that.countryCode) : that.countryCode != null) return false;
        if (areaCode != null ? !areaCode.equals(that.areaCode) : that.areaCode != null) return false;
        if (firstThreeDigit != null ? !firstThreeDigit.equals(that.firstThreeDigit) : that.firstThreeDigit != null)
            return false;
        return lastFourDigit != null ? lastFourDigit.equals(that.lastFourDigit) : that.lastFourDigit == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = countryCode != null ? countryCode.hashCode() : 0;
        result = 31 * result + (areaCode != null ? areaCode.hashCode() : 0);
        result = 31 * result + (firstThreeDigit != null ? firstThreeDigit.hashCode() : 0);
        result = 31 * result + (lastFourDigit != null ? lastFourDigit.hashCode() : 0);
        return result;
    }

    /**
     * This is the builder class to create an object of type {@link PhoneNumberFormat}
     */
    public static class PhoneNumberFormatBuilder {
        private String countryCode;
        private String areaCode;
        private String firstThreeDigit;
        private String lastFourDigit;

        public PhoneNumberFormatBuilder setCountryCode(final String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public PhoneNumberFormatBuilder setAreaCode(final String areaCode) {
            this.areaCode = areaCode;
            return this;
        }

        public PhoneNumberFormatBuilder setFirstThreeDigit(final String firstThreeDigit) {
            this.firstThreeDigit = firstThreeDigit;
            return this;
        }

        public PhoneNumberFormatBuilder setLastFourDigit(final String lastFourDigit) {
            this.lastFourDigit = lastFourDigit;
            return this;
        }

        public PhoneNumberFormat build() {
            return new PhoneNumberFormat(countryCode, areaCode, firstThreeDigit, lastFourDigit);
        }
    }
}
