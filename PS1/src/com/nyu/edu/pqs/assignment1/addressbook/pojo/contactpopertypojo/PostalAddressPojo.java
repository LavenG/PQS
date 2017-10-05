package com.nyu.edu.pqs.assignment1.addressbook.pojo.contactpopertypojo;

import com.nyu.edu.pqs.assignment1.addressbook.pojo.ContactPojo;

/**
 * This is the model email object which user uses to create to {@link ContactPojo}.
 * Use the builder {@link PostalAddressPojoBuilder} class to create an instance of this method.
 */
public class PostalAddressPojo {
    private final String addressLine1;
    private final String addressLine2;
    private final String city;
    private final String state;
    private final String country;
    private final String  zipCode;

    private PostalAddressPojo(final String addressLine1, final String addressLine2, final String city,
                              final String state, final String country, final String zipCode) {
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

    public String  getZipCode() {
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
     * This is the builder class to create an object of type {@link PostalAddressPojo}
     */
    public static class PostalAddressPojoBuilder {
        private String addressLine1;
        private String addressLine2;
        private String city;
        private String state;
        private String country;
        private String zipCode;

        public PostalAddressPojoBuilder setAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
            return this;
        }

        public PostalAddressPojoBuilder setAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
            return this;
        }

        public PostalAddressPojoBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public PostalAddressPojoBuilder setState(String state) {
            this.state = state;
            return this;
        }

        public PostalAddressPojoBuilder setCountry(String country) {
            this.country = country;
            return this;
        }

        public PostalAddressPojoBuilder setZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public PostalAddressPojo build() {
            return new PostalAddressPojo(addressLine1, addressLine2, city, state, country, zipCode);
        }
    }
}
