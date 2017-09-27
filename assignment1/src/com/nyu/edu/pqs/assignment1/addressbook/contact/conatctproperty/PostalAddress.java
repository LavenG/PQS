package com.nyu.edu.pqs.assignment1.addressbook.contact.conatctproperty;

import com.nyu.edu.pqs.assignment1.addressbook.contact.Contact;

/**
 * This is the model email object which user uses to create to {@link Contact}.
 * Use the builder {@link PostalAddressBuilder} class to create an instance of this method.
 */
public class PostalAddress {
    private final String addressLine1;
    private final String addressLine2;
    private final String city;
    private final String state;
    private final String country;
    private final String zipCode;

    private PostalAddress(String addressLine1, String addressLine2, String city, String state, String country, String zipCode) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getZipCode() {
        return zipCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return addressLine1 + ",\n" + addressLine2 + ",\n" + city + ",\t" + state + ",\t" + country + ",\n zipcode:\t" + zipCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostalAddress that = (PostalAddress) o;

        if (addressLine1 != null ? !addressLine1.equals(that.addressLine1) : that.addressLine1 != null) return false;
        if (addressLine2 != null ? !addressLine2.equals(that.addressLine2) : that.addressLine2 != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        return zipCode != null ? zipCode.equals(that.zipCode) : that.zipCode == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = addressLine1 != null ? addressLine1.hashCode() : 0;
        result = 31 * result + (addressLine2 != null ? addressLine2.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        return result;
    }

    /**
     * This is the builder class to create an object of type {@link PostalAddress}
     */
    public static class PostalAddressBuilder {
        private String addressLine1;
        private String addressLine2;
        private String city;
        private String state;
        private String country;
        private String zipCode;

        public PostalAddressBuilder setAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
            return this;
        }

        public PostalAddressBuilder setAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
            return this;
        }

        public PostalAddressBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public PostalAddressBuilder setState(String state) {
            this.state = state;
            return this;
        }

        public PostalAddressBuilder setCountry(String country) {
            this.country = country;
            return this;
        }

        public PostalAddressBuilder setZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public PostalAddress build() {
            return new PostalAddress(addressLine1, addressLine2, city, state, country, zipCode);
        }
    }
}
