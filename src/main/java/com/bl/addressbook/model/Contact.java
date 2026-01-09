package com.bl.addressbook.model;

import lombok.Data;

public @Data class Contact {
    private long id;
    private String name;

    public Contact() {
    }

    public Contact(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
