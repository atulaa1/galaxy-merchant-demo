package com.dungpx.galaxy.merchant.converter.roman;

/**
 * <b>File Created</b>: Feb 20, 2019
 *
 * <b>Author</b>: dungpx
 */
class RomanNumberValidator {
    private static final String ROMAN_NUMBER_VALIDATOR = "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";

    static boolean isValidRomanNumber(String romanNumber) {
        return romanNumber.matches(ROMAN_NUMBER_VALIDATOR);
    }
}
