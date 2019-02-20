package com.dungpx.galaxy.merchant.converter.roman;

import com.dungpx.galaxy.merchant.error.ErrorMessage;
import com.dungpx.galaxy.merchant.error.InvalidInputException;
import com.dungpx.galaxy.merchant.converter.roman.RomanNumberValidator;
import com.dungpx.galaxy.merchant.converter.roman.RomanNumeral;

/**
 * <b>File Created</b>: Feb 19, 2019
 *
 * <b>Author</b>: dungpx
 */
public class RomanToDecimalConverter {
    public static long convert(String completeRomanceNumber) throws InvalidInputException {
        if (RomanNumberValidator.isValidRomanNumber(completeRomanceNumber)) {
            return doConvertInternal(completeRomanceNumber);
        } else {
            throw new InvalidInputException(ErrorMessage.INVALID_ROMAN_NUMBER);
        }
    }

    private static long doConvertInternal(String roman) {
        long decimalResult = 0;
        int prevDecimal = 0;

        for (int idx = roman.length() - 1; idx >= 0; idx--) {
            char currentRomanChar = roman.charAt(idx);
            int currentDecimal = RomanNumeral.getValueFromChar(currentRomanChar);
            decimalResult = recalculateDecimalResult(prevDecimal, currentDecimal, decimalResult);
            prevDecimal = currentDecimal;
        }

        return decimalResult;
    }

    private static long recalculateDecimalResult(int prevDecimal, int currentDecimal, long oldDecimalResult) {
        if (currentDecimal < prevDecimal) {
            return oldDecimalResult - currentDecimal;
        } else {
            return oldDecimalResult + currentDecimal;
        }
    }
}
