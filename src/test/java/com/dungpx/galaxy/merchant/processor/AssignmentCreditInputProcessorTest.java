package com.dungpx.galaxy.merchant.processor;

import com.dungpx.galaxy.merchant.error.InvalidInputException;
import com.dungpx.galaxy.merchant.storage.ConversionRateStorage;
import com.dungpx.galaxy.merchant.storage.OutputStorage;
import org.junit.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

/**
 * <b>File Created</b>: Feb 20, 2019
 *
 * <b>Author</b>: dungpx
 */
public class AssignmentCreditInputProcessorTest {
    private static final String ASSIGNMENT_CREDIT_INPUT_VALID = "glob prok Gold is 57800 Credits";
    private static final String ASSIGNMENT_CREDIT_INPUT_INVALID_MISSING_IS_KEYWORD = "glob glob Gold 34 Credits";
    private static final String ASSIGNMENT_CREDIT_INPUT_INVALID_MISSING_CREDIT_VALUE = "glob glob Gold is Credits";
    private static final String ASSIGNMENT_CREDIT_INPUT_INVALID_UNKNOWN_UNIT = "glob pish Gold is 57800 Credits";

    @Before
    public void setUp() {
        AssignmentRomanInputProcessor assignmentRomanInputProcessor = new AssignmentRomanInputProcessor(null);
        assignmentRomanInputProcessor.process("glob is I");
        assignmentRomanInputProcessor.process("prok is V");
    }

    @After
    public void teardown() {
        ConversionRateStorage.clearAll();
        OutputStorage.clearAll();
    }

    @Test
    public void givenValidInput_thenProcessSuccessful() throws Exception {
        AssignmentCreditInputProcessor assignmentCreditInputProcessor = new AssignmentCreditInputProcessor(null);

        assignmentCreditInputProcessor.doProcessInternal(ASSIGNMENT_CREDIT_INPUT_VALID);
        assertNotNull(ConversionRateStorage.get("Gold"));
        assertEquals(14450d, Double.parseDouble(ConversionRateStorage.get("Gold")));
    }

    @Test(expected = InvalidInputException.class)
    public void givenInvalidInput_missingIsKeyword_thenThrowsInvalidInputException() throws Exception {
        AssignmentCreditInputProcessor assignmentCreditInputProcessor = new AssignmentCreditInputProcessor(null);
        assignmentCreditInputProcessor.doProcessInternal(ASSIGNMENT_CREDIT_INPUT_INVALID_MISSING_IS_KEYWORD);
        assertNull(ConversionRateStorage.get("Gold"));
    }

    @Test(expected = InvalidInputException.class)
    public void givenInvalidInput_missingCreditValue_thenThrowsInvalidInputException() throws Exception {
        AssignmentCreditInputProcessor assignmentCreditInputProcessor = new AssignmentCreditInputProcessor(null);
        assignmentCreditInputProcessor.doProcessInternal(ASSIGNMENT_CREDIT_INPUT_INVALID_MISSING_CREDIT_VALUE);
        assertNull(ConversionRateStorage.get("Gold"));
    }

    @Test(expected = InvalidInputException.class)
    public void givenInvalidInput_unknownUnit_thenThrowsInvalidInputException() throws Exception {
        AssignmentCreditInputProcessor assignmentCreditInputProcessor = new AssignmentCreditInputProcessor(null);
        assignmentCreditInputProcessor.doProcessInternal(ASSIGNMENT_CREDIT_INPUT_INVALID_UNKNOWN_UNIT);
        assertNull(ConversionRateStorage.get("Gold"));
    }
}
