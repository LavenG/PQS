package com.nyu.edu.pqs.assignment1.addressbook.pojo.contactpopertypojo;

/**
 * This is the model email object which user uses to create to {@link EmailPojo}.
 * Use the builder {@link EmailFormatPojoBuilder} class to create an instance of this method.
 */
public class EmailFormatPojo {
    private final String userName;
    private final String domain;

    private EmailFormatPojo(String userName, String domain) {
        this.userName = userName;
        this.domain = domain;
    }

    public String getUserName() {
        return userName;
    }

    public String getDomain() {
        return domain;
    }

    /**
     * This is the builder class to create an object of type {@link EmailFormatPojo}
     */
    public static class EmailFormatPojoBuilder {
        private String userName;
        private String domain;

        public EmailFormatPojoBuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public EmailFormatPojoBuilder setDomain(String domain) {
            this.domain = domain;
            return this;
        }

        public EmailFormatPojo build() {
            return new EmailFormatPojo(userName, domain);
        }
    }
}
