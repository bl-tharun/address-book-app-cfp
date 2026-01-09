package com.bl.addressbook.service;

import com.bl.addressbook.model.Contact;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressBookService {
    public static List<Contact> contactList = new ArrayList<>();          // In-memory

    public List<Contact> getAll() {
        return contactList;
    }

    public Contact getContactById(long id) {
        return contactList.get((int) (id - 1));
    }

    public Contact addContact(Contact contact) {
        contactList.add(contact);
        return contact;
    }

    public Contact updateContact(long id, Contact updated) {
        Contact old = contactList.get((int) (id - 1));
        old.setName(updated.getName());
        return old;
    }

    public void deleteContact(long id) {
        Contact contact = contactList.get((int) (id - 1));
        contactList.remove(contact);
    }
}
