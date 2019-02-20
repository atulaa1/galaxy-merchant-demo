package com.dungpx.galaxy.merchant.processor;

import com.dungpx.galaxy.merchant.error.ErrorMessage;
import com.dungpx.galaxy.merchant.error.InvalidInputException;
import com.dungpx.galaxy.merchant.input.InputType;
import com.dungpx.galaxy.merchant.storage.ConversionRateStorage;

/**
 * <b>File Created</b>: Feb 19, 2019
 *
 * <b>Author</b>: dungpx
 */
class AssignmentRomanInputProcessor extends InputProcessor {

    AssignmentRomanInputProcessor(InputProcessor inputProcessor) {
        super(inputProcessor);
    }

    @Override
    void doProcessInternal(String inputText) throws Exception {
        String[] assignmentParts = inputText.trim().split("\\s+is\\s+");
        if (assignmentParts.length != 2) {
            throw new InvalidInputException(ErrorMessage.INVALID_INPUT_FORMAT);
        }

        String intergalacticUnit = assignmentParts[0].trim();
        String romanNumber = assignmentParts[1].trim();
        ConversionRateStorage.store(intergalacticUnit, romanNumber);
    }

    @Override
    public boolean support(InputType inputType) {
        return inputType == InputType.ASSIGNMENT_ROMAN;
    }
}
