package com.dungpx.galaxy.merchant.error;

/**
 * <b>File Created</b>: Feb 20, 2019
 *
 * <b>Author</b>: dungpx
 */
public class InvalidInputException extends Exception {
    public InvalidInputException(ErrorMessage message) {
        super(message.toString());
    }
}
