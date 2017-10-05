package com.nyu.edu.pqs.assignment1.addressbook.contact.conatctproperty;

/**
 * This is the model email object which user uses to create to {@link Email}.
 * Use the builder {@link EmailFormatBuilder} class to create an instance of this method.
 */
public class EmailFormat {
    private final String userName;
    private final String domain;

    private EmailFormat(final String userName, final String domain) {
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
     * This method is used to check if the current object starts with a given text.
     *
     * @param searchTerm the string which has to be verified against.
     * @return true if object starts with the provided text, false otherwise.
     */
    public boolean startsWith(String searchTerm) {
        String consolidatedString = actualEmail();
        return userName.startsWith(searchTerm) || domain.startsWith(searchTerm) || consolidatedString.startsWith(searchTerm);
    }

    private String actualEmail() {
        return userName + '@' + domain;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return actualEmail();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailFormat that = (EmailFormat) o;

        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        return domain != null ? domain.equals(that.domain) : that.domain == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (domain != null ? domain.hashCode() : 0);
        return result;
    }

    /**
     * This is the builder class to create an object of type {@link EmailFormat}
     */
    public static class EmailFormatBuilder {
        private String userName;
        private String domain;

        public EmailFormatBuilder setUserName(final String userName) {
            this.userName = userName;
            return this;
        }

        public EmailFormatBuilder setDomain(final String domain) {
            this.domain = domain;
            return this;
        }

        public EmailFormat build() {
            return new EmailFormat(userName, domain);
        }
    }
}
