package com.dungpx.galaxy.merchant.processor;

import com.dungpx.galaxy.merchant.converter.IntergalacticUnitsToDecimalAmountConverter;
import com.dungpx.galaxy.merchant.error.ErrorMessage;
import com.dungpx.galaxy.merchant.error.InvalidInputException;
import com.dungpx.galaxy.merchant.input.InputType;
import com.dungpx.galaxy.merchant.storage.OutputStorage;

class QuestionComparisonIntergalacticUnitsInputProcessor extends InputProcessor {

    QuestionComparisonIntergalacticUnitsInputProcessor(InputProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    void doProcessInternal(String inputText) throws Exception {
        inputText = inputText.replaceAll("([i|I]s)\\s+|\\s*\\?", "").trim();
        String[] parts = inputText.split("\\s+larger than\\s+");
        if (parts.length < 2) {
            throw new InvalidInputException(ErrorMessage.INVALID_INPUT_FORMAT);
        }

        final String unitsPart1 = parts[0];
        long decimalAmountPart1 = IntergalacticUnitsToDecimalAmountConverter.convert(unitsPart1);

        final String unitPart2 = parts[1];
        long decimalAmountPart2 = IntergalacticUnitsToDecimalAmountConverter.convert(unitPart2);

        if (decimalAmountPart1 > decimalAmountPart2) {
            OutputStorage.store(String.format("%s is larger than %s", unitsPart1, unitPart2));
        } else if (decimalAmountPart1 == decimalAmountPart2) {
            OutputStorage.store(String.format("%s is equal to %s", unitsPart1, unitPart2));
        } else {
            OutputStorage.store(String.format("%s is smaller than %s", unitsPart1, unitPart2));
        }
    }

    @Override
    boolean support(InputType inputType) {
        return inputType == InputType.QUESTION_COMPARISON_INTERGALACTIC_UNITS;
    }
}
