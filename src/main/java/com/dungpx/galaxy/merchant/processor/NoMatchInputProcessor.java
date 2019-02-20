package com.dungpx.galaxy.merchant.processor;

import com.dungpx.galaxy.merchant.error.ErrorMessage;
import com.dungpx.galaxy.merchant.input.InputType;
import com.dungpx.galaxy.merchant.storage.OutputStorage;

/**
 * <b>File Created</b>: Feb 19, 2019
 *
 * <b>Author</b>: dungpx
 */
class NoMatchInputProcessor extends InputProcessor {
    NoMatchInputProcessor(InputProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    void doProcessInternal(String inputText) {
        OutputStorage.store(ErrorMessage.NO_IDEA.toString());
    }

    @Override
    boolean support(InputType inputType) {
        return inputType == InputType.NO_MATCH;
    }
}
