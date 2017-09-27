package com.nyu.edu.pqs.assignment1.addressbook.contact.conatctproperty;

import com.nyu.edu.pqs.assignment1.addressbook.contact.Contact;

/**
 * This is the model email object which user uses to create to {@link Contact}.
 * Use the builder {@link NoteBuilder} class to create an instance of this method.
 */
public class Note {
    private final String note;

    private Note(String note) {
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
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note1 = (Note) o;

        return note != null ? note.equals(note1.note) : note1.note == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return note != null ? note.hashCode() : 0;
    }

    /**
     * This is the builder class to create an object of type {@link Note}
     */
    public static class NoteBuilder {
        private String note;

        public NoteBuilder setNote(String note) {
            this.note = note;
            return this;
        }

        public Note build() {
            return new Note(note);
        }
    }
}
