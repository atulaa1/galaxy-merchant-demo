package com.dungpx.galaxy.merchant.processor;

import com.dungpx.galaxy.merchant.error.InvalidInputException;
import com.dungpx.galaxy.merchant.storage.ConversionRateStorage;
import com.dungpx.galaxy.merchant.storage.OutputStorage;
import org.junit.*;

import static junit.framework.TestCase.assertEquals;

/**
 * <b>File Created</b>: Feb 20, 2019
 *
 * <b>Author</b>: dungpx
 */
public class QuestionHowManyInputProcessorTest {
    private static final String QUESTION_HOW_MANY_INPUT_VALID = "how many Credits is glob prok Silver ?";
    private static final String QUESTION_HOW_MANY_INPUT_INVALID_UNKNOWN_UNIT = "how many Credits is glob pish Silver ?";

    @Before
    public void setup() {
        AssignmentRomanInputProcessor assignmentRomanInputProcessor = new AssignmentRomanInputProcessor(null);
        assignmentRomanInputProcessor.process("glob is I");
        assignmentRomanInputProcessor.process("prok is V");

        AssignmentCreditInputProcessor assignmentCreditInputProcessor = new AssignmentCreditInputProcessor(null);
        assignmentCreditInputProcessor.process("glob glob Silver is 34 Credits");
    }

    @After
    public void teardown() {
        ConversionRateStorage.clearAll();
        OutputStorage.clearAll();
    }

    @Test
    public void givenValidInput_thenProcessSuccessful() throws Exception {
        QuestionHowManyInputProcessor questionHowManyInputProcessor = new QuestionHowManyInputProcessor(null);
        questionHowManyInputProcessor.doProcessInternal(QUESTION_HOW_MANY_INPUT_VALID);

        assertEquals(1, OutputStorage.getAll().length);
        assertEquals("glob prok Silver is 68.00 Credits", OutputStorage.getAll()[0]);
    }

    @Test(expected = InvalidInputException.class)
    public void givenInvalidInput_unknownUnit_thenThrowsInvalidInputException() throws Exception {
        QuestionHowManyInputProcessor questionHowManyInputProcessor = new QuestionHowManyInputProcessor(null);
        questionHowManyInputProcessor.doProcessInternal(QUESTION_HOW_MANY_INPUT_INVALID_UNKNOWN_UNIT);
    }
}
