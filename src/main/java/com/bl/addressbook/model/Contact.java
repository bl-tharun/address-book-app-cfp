package com.bl.addressbook.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "test")
public @Data class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public Contact() {
    }
}
