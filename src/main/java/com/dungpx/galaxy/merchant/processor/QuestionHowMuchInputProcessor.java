package com.dungpx.galaxy.merchant.processor;

import com.dungpx.galaxy.merchant.converter.IntergalacticUnitsToDecimalAmountConverter;
import com.dungpx.galaxy.merchant.error.InvalidInputException;
import com.dungpx.galaxy.merchant.input.InputType;
import com.dungpx.galaxy.merchant.storage.OutputStorage;

/**
 * <b>File Created</b>: Feb 19, 2019
 *
 * <b>Author</b>: dungpx
 */
class QuestionHowMuchInputProcessor extends InputProcessor {
    QuestionHowMuchInputProcessor(InputProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    void doProcessInternal(String inputText) throws Exception {
        String intergalacticUnits = inputText.replaceAll("([h|H]ow much is)\\s+|\\s*\\?", "");
        long decimalResult = IntergalacticUnitsToDecimalAmountConverter.convert(intergalacticUnits);
        OutputStorage.store(intergalacticUnits + " is " + decimalResult);
    }

    @Override
    boolean support(InputType inputType) {
        return inputType == InputType.QUESTION_HOW_MUCH;
    }
}
