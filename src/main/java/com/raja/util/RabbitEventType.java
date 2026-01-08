package com.raja.util;

public enum RabbitEventType {
    USER_CREATED("user-created"),
    USER_UPDATED("user-updated");

    private final String key;

    RabbitEventType(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }
}
