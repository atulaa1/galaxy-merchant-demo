package com.dungpx.galaxy.merchant.processor;

import com.dungpx.galaxy.merchant.input.InputType;
import com.dungpx.galaxy.merchant.storage.OutputStorage;

abstract class InputProcessor {
    private InputProcessor nextProcessor;

    InputProcessor(InputProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    void process(String inputText) {
        InputType inputType = InputType.determineInputType(inputText);
        if (support(inputType)) {
            try {
                doProcessInternal(inputText);
            } catch (Exception exception) {
                OutputStorage.store(exception.getMessage());
            }
        } else if (nextProcessor != null) {
            nextProcessor.process(inputText);
        }
    }

    abstract void doProcessInternal(String inputText) throws Exception;

    abstract boolean support(InputType inputType);
}
