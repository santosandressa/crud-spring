package com.crud.spring.enums;

public enum Status {

    INACTIVE("Inativo"),
    ACTIVE("Ativo");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

}
