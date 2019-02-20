package com.dungpx.galaxy.merchant;

import com.dungpx.galaxy.merchant.input.InputReader;
import com.dungpx.galaxy.merchant.processor.InputProcessorChain;
import com.dungpx.galaxy.merchant.utils.ApplicationWorkFlowUtils;

public class Application {

    public static void main(String[] args) {

        ApplicationWorkFlowUtils.startup();

        InputReader inputReader = new InputReader();
        String input;
        while (!(input = inputReader.getInput()).isEmpty()) {
            InputProcessorChain.process(input);
        }

        ApplicationWorkFlowUtils.finish();
    }
}
