package com.bl.addressbook.controller;

import com.bl.addressbook.model.Contact;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {
    public static List<Contact> contactList = new ArrayList<>();          // In-memory

    @GetMapping("/getall")
    public ResponseEntity<List<Contact>> getAll() {
        return new ResponseEntity<>(contactList, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable long id) {
        Contact contact;
        try {
            contact = contactList.get((int) (id - 1));
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        contactList.add(contact);
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Contact> updateContact(
            @PathVariable long id,
            @RequestBody Contact updated
    ) {
        Contact old;
        try {
            old = contactList.get((int) (id - 1));
            old.setName(updated.getName());
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(old);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable long id) {
        try {
            Contact contact = contactList.get((int) (id - 1));
            contactList.remove(contact);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>("Not Found!", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Deleted Successfully!");
    }

}
