package com.nyu.edu.pqs.assignment1.addressbook.pojo.contactpopertypojo;

import com.nyu.edu.pqs.assignment1.addressbook.pojo.ContactPojo;

/**
 * This is the model email object which user uses to create to {@link ContactPojo}.
 * Use the builder {@link NotePojoBuilder} class to create an instance of this method.
 */
public class NotePojo {
    private final String note;

    private NotePojo(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return note;
    }

    /**
     * This is the builder class to create an object of type {@link NotePojo}
     */
    public static class NotePojoBuilder {
        private String note;

        public NotePojoBuilder setNote(String note) {
            this.note = note;
            return this;
        }

        public NotePojo build() {
            return new NotePojo(note);
        }
    }
}
