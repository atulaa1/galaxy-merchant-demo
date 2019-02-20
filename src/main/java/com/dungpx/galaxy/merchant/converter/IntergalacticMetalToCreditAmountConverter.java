package com.dungpx.galaxy.merchant.converter;

import com.dungpx.galaxy.merchant.error.ErrorMessage;
import com.dungpx.galaxy.merchant.error.InvalidInputException;
import com.dungpx.galaxy.merchant.storage.ConversionRateStorage;

import java.util.Arrays;

public class IntergalacticMetalToCreditAmountConverter {
    public static double convert(String intergalacticMetal) throws InvalidInputException {
        String[] intergalacticMetalParts = intergalacticMetal.split("\\s+");

        String metal = intergalacticMetalParts[intergalacticMetalParts.length - 1];
        String[] intergalacticUnits = Arrays.copyOfRange(intergalacticMetalParts, 0, intergalacticMetalParts.length - 1);

        long decimalAmount = IntergalacticUnitsToDecimalAmountConverter.convert(intergalacticUnits);

        if (ConversionRateStorage.get(metal) == null) {
            throw new InvalidInputException(ErrorMessage.NO_IDEA);
        }

        double creditPerUnit = Double.parseDouble(ConversionRateStorage.get(metal));

        return decimalAmount * creditPerUnit;
    }
}
