package com.dungpx.galaxy.merchant.input;

public enum InputType {
    ASSIGNMENT_ROMAN("^([A-Za-z]+) is ([I|V|X|L|C|D|M])$"),
    ASSIGNMENT_CREDIT("^(([A-Za-z\\s])+) is ([0-9]+) ([c|C]redits)$"),
    QUESTION_HOW_MUCH("^([h|H]ow much is) (([A-Za-z\\s])+)\\?$"),
    QUESTION_HOW_MANY("^([h|H]ow many [c|C]redits is) (([A-Za-z\\s])+)\\?$"),
    QUESTION_COMPARISON_INTERGALACTIC_UNITS("^([i|I]s) (([A-Za-z\\s])+) larger than (([A-Za-z\\s])+)\\?$"),
    QUESTION_COMPARISON_CREDIT("^([d|D]oes) (([A-Za-z\\s])+) has more [c|C]redits than (([A-Za-z\\s])+)\\?$"),
    NO_MATCH("");

    private String pattern;

    InputType(String pattern) {
        this.pattern = pattern;
    }

    public static InputType determineInputType(String inputText) {
        for (InputType inputType : values()) {
            if (inputText.matches(inputType.pattern)) {
                return inputType;
            }
        }
        return NO_MATCH;
    }
}
