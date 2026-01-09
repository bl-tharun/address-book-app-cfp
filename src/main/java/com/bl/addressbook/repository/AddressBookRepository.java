package com.bl.addressbook.repository;

import com.bl.addressbook.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressBookRepository extends JpaRepository<Contact, Long> {
}
