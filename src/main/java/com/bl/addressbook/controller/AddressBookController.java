package com.bl.addressbook.controller;

import com.bl.addressbook.dto.ResponseDto;
import com.bl.addressbook.model.Contact;
import com.bl.addressbook.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookService service;

    @GetMapping("/getall")
    public ResponseEntity<ResponseDto> getAll() {
        ResponseDto responseDto = new ResponseDto("Here's the full list", service.getAll());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDto> getContactById(@PathVariable long id) {
        ResponseDto responseDto = new ResponseDto("Found!", service.getContactById(id));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> addContact(@RequestBody Contact contact) {
        ResponseDto responseDto = new ResponseDto("Added Successfully!", service.addContact(contact));
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto> updateContact(
            @PathVariable long id,
            @RequestBody Contact updated
    ) {
        ResponseDto responseDto = new ResponseDto("Updated!", service.updateContact(id, updated));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable long id) {
        service.deleteContact(id);
        return ResponseEntity.ok("Deleted Successfully!");
    }

}
