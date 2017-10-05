package com.nyu.edu.pqs.assignment1.addressbook.iomanager.impl;

import com.google.gson.Gson;
import com.nyu.edu.pqs.assignment1.addressbook.contact.Contact;
import com.nyu.edu.pqs.assignment1.addressbook.iomanager.IOManager;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * This is one implementation/prototype of the {@link IOManager} library.
 * A developer using the library can use this prototype to execute operations.
 * This implementation saves data as json object to a file and extracts it to a list.
 * This is an implementation for serializing data.
 */
class JsonFormatIOManager implements IOManager {
    /**
     * An instance of google Gson class to convert objects to json for serialization.
     */
    private final Gson gson;

    /**
     * Default constructor overwritten with initialization of variable.
     */
    JsonFormatIOManager() {
        gson = new Gson();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAddressBook(final String filePath, final List<Contact> contactList) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(filePath + "\\contactList.json"));
        String json = gson.toJson(contactList);
        out.write(json);
        out.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Contact> readAddressBook(final String filePath) throws IOException {
            InputStream is = new FileInputStream(filePath + "\\contactList.json");
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
            String line = buf.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }
            List<Contact> contactList = gson.fromJson(sb.toString(), List.class);
            return contactList;
    }
}
