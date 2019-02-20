package com.dungpx.galaxy.merchant.processor;

import com.dungpx.galaxy.merchant.error.InvalidInputException;
import com.dungpx.galaxy.merchant.storage.ConversionRateStorage;
import com.dungpx.galaxy.merchant.storage.OutputStorage;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * <b>File Created</b>: Feb 20, 2019
 *
 * <b>Author</b>: dungpx
 */
public class QuestionHowMuchInputProcessorTest {

    private static final String QUESTION_HOW_MUCH_INPUT_VALID = "how much is pish tegj glob glob ?";
    private static final String QUESTION_HOW_MUCH_INPUT_UNKNOWN_UNIT = "how much is pish tegj glob prok ?";

    @Before
    public void setup() {
        AssignmentRomanInputProcessor assignmentRomanInputProcessor = new AssignmentRomanInputProcessor(null);
        assignmentRomanInputProcessor.process("glob is I");
        assignmentRomanInputProcessor.process("pish is X");
        assignmentRomanInputProcessor.process("tegj is L");
    }

    @After
    public void teardown() {
        ConversionRateStorage.clearAll();
        OutputStorage.clearAll();
    }

    @Test
    public void givenValidInput_thenProcessSuccessful() throws Exception {
        QuestionHowMuchInputProcessor questionHowMuchInputProcessor = new QuestionHowMuchInputProcessor(null);
        questionHowMuchInputProcessor.doProcessInternal(QUESTION_HOW_MUCH_INPUT_VALID);
        assertEquals(1, OutputStorage.getAll().length);
        assertEquals("pish tegj glob glob is 42", OutputStorage.getAll()[0]);
    }

    @Test(expected = InvalidInputException.class)
    public void givenInvalidInput_unknownUnit_thenThrowInvalidInputException() throws Exception {
        QuestionHowMuchInputProcessor questionHowMuchInputProcessor = new QuestionHowMuchInputProcessor(null);
        questionHowMuchInputProcessor.doProcessInternal(QUESTION_HOW_MUCH_INPUT_UNKNOWN_UNIT);
    }
}
