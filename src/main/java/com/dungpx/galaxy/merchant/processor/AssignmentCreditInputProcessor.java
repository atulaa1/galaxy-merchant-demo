package com.dungpx.galaxy.merchant.processor;

import com.dungpx.galaxy.merchant.converter.IntergalacticUnitsToDecimalAmountConverter;
import com.dungpx.galaxy.merchant.error.ErrorMessage;
import com.dungpx.galaxy.merchant.error.InvalidInputException;
import com.dungpx.galaxy.merchant.input.InputType;
import com.dungpx.galaxy.merchant.storage.ConversionRateStorage;

import java.util.Arrays;

/**
 * <b>File Created</b>: Feb 19, 2019
 *
 * <b>Author</b>: dungpx
 */
class AssignmentCreditInputProcessor extends InputProcessor {
    AssignmentCreditInputProcessor(InputProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    void doProcessInternal(String inputText) throws Exception {
        inputText = inputText.replaceAll("\\s*[c|C]redits\\s*", "");
        String[] assignmentParts = inputText.split("\\s+is\\s+");
        if (assignmentParts.length < 2) {
            throw new InvalidInputException(ErrorMessage.INVALID_INPUT_FORMAT);
        }

        String[] intergalacticMetalParts = assignmentParts[0].split("\\s+");
        double value = Double.valueOf(assignmentParts[1]);

        String metal = intergalacticMetalParts[intergalacticMetalParts.length - 1];
        String[] intergalacticUnits = Arrays.copyOfRange(intergalacticMetalParts, 0, intergalacticMetalParts.length - 1);
        long decimalAmount = IntergalacticUnitsToDecimalAmountConverter.convert(intergalacticUnits);

        double creditPerUnit = value / decimalAmount;

        ConversionRateStorage.store(metal, String.format("%.2f", creditPerUnit));
    }

    @Override
    boolean support(InputType inputType) {
        return inputType == InputType.ASSIGNMENT_CREDIT;
    }
}
