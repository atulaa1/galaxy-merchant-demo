package com.dungpx.galaxy.merchant.error;

/**
 * <b>File Created</b>: Feb 20, 2019
 *
 * <b>Author</b>: dungpx
 */
public enum ErrorMessage {
    NO_IDEA("I have no idea what you are talking about"),
    INVALID_ROMAN_NUMBER("Invalid roman number"),
    INVALID_INPUT_FORMAT("Invalid input line");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
