package com.dungpx.galaxy.merchant.processor;

import com.dungpx.galaxy.merchant.converter.IntergalacticMetalToCreditAmountConverter;
import com.dungpx.galaxy.merchant.error.ErrorMessage;
import com.dungpx.galaxy.merchant.error.InvalidInputException;
import com.dungpx.galaxy.merchant.input.InputType;
import com.dungpx.galaxy.merchant.storage.OutputStorage;

class QuestionComparisonCreditInputProcessor extends InputProcessor {
    QuestionComparisonCreditInputProcessor(InputProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    void doProcessInternal(String inputText) throws Exception {
        inputText = inputText.replaceAll("([d|D]oes)\\s+|\\s*\\?", "").trim();
        String[] comparisonParts = inputText.split("\\s+has more [c|C]redits than\\s+");
        if (comparisonParts.length < 2) {
            throw new InvalidInputException(ErrorMessage.INVALID_INPUT_FORMAT);
        }

        final String comparisonPart1 = comparisonParts[0];
        double creditsAmountPart1 = IntergalacticMetalToCreditAmountConverter.convert(comparisonPart1);

        final String comparisonPart2 = comparisonParts[1];
        double creditsAmountPart2 = IntergalacticMetalToCreditAmountConverter.convert(comparisonPart2);

        if (creditsAmountPart1 > creditsAmountPart2) {
            OutputStorage.store(String.format("%s has more Credits than %s", comparisonPart1, comparisonPart2));
        } else if (creditsAmountPart1 == creditsAmountPart2) {
            OutputStorage.store(String.format("%s has as same Credits as %s", comparisonPart1, comparisonPart2));
        } else {
            OutputStorage.store(String.format("%s has less Credits than %s", comparisonPart1, comparisonPart2));
        }
    }

    @Override
    boolean support(InputType inputType) {
        return inputType == InputType.QUESTION_COMPARISON_CREDIT;
    }
}
