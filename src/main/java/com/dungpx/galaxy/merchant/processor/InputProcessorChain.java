package com.dungpx.galaxy.merchant.processor;

public class InputProcessorChain {
    private static final InputProcessor CHAIN_OF_PROCESSOR;

    static {
        InputProcessor defaultInputProcessor = new NoMatchInputProcessor(null);
        InputProcessor questionComparisonCreditInputProcessor = new QuestionComparisonCreditInputProcessor(defaultInputProcessor);
        InputProcessor questionComparisonRomanInputProcessor = new QuestionComparisonIntergalacticUnitsInputProcessor(questionComparisonCreditInputProcessor);
        InputProcessor questionHowManyInputProcessor = new QuestionHowManyInputProcessor(questionComparisonRomanInputProcessor);
        InputProcessor questionHowMuchInputProcessor = new QuestionHowMuchInputProcessor(questionHowManyInputProcessor);
        InputProcessor assignmentCreditInputProcessor = new AssignmentCreditInputProcessor(questionHowMuchInputProcessor);
        CHAIN_OF_PROCESSOR = new AssignmentRomanInputProcessor(assignmentCreditInputProcessor);
    }

    public static void process(String inputText) {
        CHAIN_OF_PROCESSOR.process(inputText);
    }
}
