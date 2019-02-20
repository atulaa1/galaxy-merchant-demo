package com.dungpx.galaxy.merchant.processor;

import com.dungpx.galaxy.merchant.converter.IntergalacticMetalToCreditAmountConverter;
import com.dungpx.galaxy.merchant.error.InvalidInputException;
import com.dungpx.galaxy.merchant.input.InputType;
import com.dungpx.galaxy.merchant.storage.OutputStorage;

/**
 * <b>File Created</b>: Feb 19, 2019
 *
 * <b>Author</b>: dungpx
 */
class QuestionHowManyInputProcessor extends InputProcessor {

    QuestionHowManyInputProcessor(InputProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    void doProcessInternal(String inputText) throws Exception {
        String intergalacticMetal = inputText.replaceAll("([h|H]ow many [c|C]redits is)\\s+|\\s*\\?", "");
        double credit = IntergalacticMetalToCreditAmountConverter.convert(intergalacticMetal);
        OutputStorage.store(String.format("%s is %.2f Credits", intergalacticMetal, credit));
    }

    @Override
    boolean support(InputType inputType) {
        return inputType == InputType.QUESTION_HOW_MANY;
    }
}
