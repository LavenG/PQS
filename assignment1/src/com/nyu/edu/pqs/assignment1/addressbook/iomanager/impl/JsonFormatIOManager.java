package com.nyu.edu.pqs.assignment1.addressbook.iomanager.impl;

import com.google.gson.Gson;
import com.nyu.edu.pqs.assignment1.addressbook.contact.Contact;
import com.nyu.edu.pqs.assignment1.addressbook.contact.conatctproperty.*;
import com.nyu.edu.pqs.assignment1.addressbook.helper.Helper;
import com.nyu.edu.pqs.assignment1.addressbook.iomanager.IOManager;
import com.nyu.edu.pqs.assignment1.addressbook.pojo.ContactPojo;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
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
    public boolean saveAddressBook(String filePath, List<Contact> contactList) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filePath + "\\contactList.json"));
            String json = gson.toJson(contactList);
            out.write(json);
            out.close();
            return true;
        } catch (IOException ie) {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Contact> readAddressBook(String filePath) {
        try {
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
        } catch (IOException ie) {
            return null;
        }
    }
}
