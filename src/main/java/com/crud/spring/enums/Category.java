package com.crud.spring.enums;

public enum Category {
    BACKEND("back-end"),
    FRONTEND("front-end"),
    MOBILE("mobile"),
    DEVOPS("dev-ops");

    private final String value;

    Category(String value) {
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


