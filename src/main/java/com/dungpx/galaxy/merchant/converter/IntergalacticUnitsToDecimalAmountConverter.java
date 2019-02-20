package com.dungpx.galaxy.merchant.converter;

import com.dungpx.galaxy.merchant.converter.roman.RomanToDecimalConverter;
import com.dungpx.galaxy.merchant.error.ErrorMessage;
import com.dungpx.galaxy.merchant.error.InvalidInputException;
import com.dungpx.galaxy.merchant.storage.ConversionRateStorage;

public class IntergalacticUnitsToDecimalAmountConverter {

    public static long convert(String intergalacticUnits) throws InvalidInputException {
        return convert(intergalacticUnits.split("\\s+"));
    }

    public static long convert(String[] intergalacticUnits) throws InvalidInputException {
        StringBuilder completeRomanceNumber = new StringBuilder();
        for (String unit : intergalacticUnits) {
            String roman = ConversionRateStorage.get(unit);
            if (roman == null) {
                throw new InvalidInputException(ErrorMessage.NO_IDEA);
            } else {
                completeRomanceNumber.append(roman);
            }
        }

        return RomanToDecimalConverter.convert(completeRomanceNumber.toString());
    }
}
