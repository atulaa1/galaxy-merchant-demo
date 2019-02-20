package com.dungpx.galaxy.merchant.processor;

import com.dungpx.galaxy.merchant.error.InvalidInputException;
import com.dungpx.galaxy.merchant.storage.ConversionRateStorage;
import com.dungpx.galaxy.merchant.storage.OutputStorage;
import org.junit.After;
import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * <b>File Created</b>: Feb 20, 2019
 *
 * <b>Author</b>: dungpx
 */
public class AssignmentRomanInputProcessorTest {
    private static final String ASSIGNMENT_ROMAN_INPUT_VALID = "glob is I";
    private static final String ASSIGNMENT_ROMAN_INPUT_INVALID_FORMAT = "glob I";

    @After
    public void teardown() {
        ConversionRateStorage.clearAll();
        OutputStorage.clearAll();
    }

    @Test
    public void givenValidInput_thenProcessSuccessful() throws Exception {
        AssignmentRomanInputProcessor assignmentRomanInputProcessor = new AssignmentRomanInputProcessor(null);

        assignmentRomanInputProcessor.doProcessInternal(ASSIGNMENT_ROMAN_INPUT_VALID);
        assertNotNull(ConversionRateStorage.get("glob"));
        assertEquals("I", ConversionRateStorage.get("glob"));
    }

    @Test(expected = InvalidInputException.class)
    public void givenInvalidInput_missingIsKeyword_thenThrowInvalidInputException() throws Exception {
        AssignmentRomanInputProcessor assignmentRomanInputProcessor = new AssignmentRomanInputProcessor(null);
        assignmentRomanInputProcessor.doProcessInternal(ASSIGNMENT_ROMAN_INPUT_INVALID_FORMAT);
        assertNull(ConversionRateStorage.get("glob"));
    }
}
