package com.bl.addressbook.service;

import com.bl.addressbook.exception.AddressBookException;
import com.bl.addressbook.model.Contact;
import com.bl.addressbook.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository repository;

    public List<Contact> getAll() {
        return repository.findAll();
    }

    public Contact getContactById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AddressBookException("Not Found!"));
    }

    public Contact addContact(Contact contact) {
        return repository.save(contact);
    }

    public Contact updateContact(long id, Contact updated) {
        Contact old = repository.findById(id)
                .orElseThrow(() -> new AddressBookException("Not Found!"));
        old.setName(updated.getName());
        return old;
    }

    public void deleteContact(long id) {
        Contact contact = repository.findById(id)
                .orElseThrow(() -> new AddressBookException("Not Found!"));
        repository.delete(contact);
    }
}
