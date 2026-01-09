package com.bl.addressbook.controller;

import com.bl.addressbook.dto.ResponseDto;
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
    public ResponseEntity<ResponseDto> getAll() {
        ResponseDto responseDto;
        if (contactList.isEmpty())
            responseDto =  new ResponseDto("List is Empty", contactList);
        responseDto = new ResponseDto("Here's the full list", contactList);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDto> getContactById(@PathVariable long id) {
        ResponseDto responseDto;
        try {
            Contact contact = contactList.get((int) (id - 1));
            responseDto = new ResponseDto("Contact with id: " + id, contact);
        } catch (IndexOutOfBoundsException e) {
            responseDto = new ResponseDto("Not Found!", null);
            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> addContact(@RequestBody Contact contact) {
        contactList.add(contact);
        ResponseDto responseDto = new ResponseDto("Added Successfully!", contact);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto> updateContact(
            @PathVariable long id,
            @RequestBody Contact updated
    ) {
        ResponseDto responseDto;
        try {
            Contact old = contactList.get((int) (id - 1));
            old.setName(updated.getName());
            responseDto = new ResponseDto("Updated Successfully", old);
        } catch (IndexOutOfBoundsException e) {
            responseDto = new ResponseDto("Not Found!", null);
            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
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
