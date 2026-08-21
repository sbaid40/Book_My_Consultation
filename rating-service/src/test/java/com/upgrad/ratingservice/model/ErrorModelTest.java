package com.upgrad.ratingservice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Error Model Tests")
class ErrorModelTest {

    private ErrorModel errorModel;

    @BeforeEach
    void setUp() {
        errorModel = new ErrorModel("code","message");
    }

    @Test
    @DisplayName("Should set and get errorCode")
    void testSetGetErrorCode() {
        errorModel.setErrorCode("ERR_001");
        assertEquals("ERR_001", errorModel.getErrorCode());
    }

    @Test
    @DisplayName("Should set and get errorMessage")
    void testSetGetErrorMessage() {
        errorModel.setErrorMessage("Invalid rating value");
        assertEquals("Invalid rating value", errorModel.getErrorMessage());
    }

    @Test
    @DisplayName("Should build error model with builder")
    void testErrorModelBuilder() {
        ErrorModel error = ErrorModel.builder()
                .errorCode("ERR_400")
                .errorMessage("Bad Request")
                .build();

        assertEquals("ERR_400", error.getErrorCode());
        assertEquals("Bad Request", error.getErrorMessage());
    }

    @Test
    @DisplayName("Should populate all error fields")
    void testPopulateAllFields() {
        errorModel.setErrorCode("ERR_500");
        errorModel.setErrorMessage("Internal Server Error");

        assertEquals("ERR_500", errorModel.getErrorCode());
        assertEquals("Internal Server Error", errorModel.getErrorMessage());
    }

    @Test
    @DisplayName("Should handle null values")
    void testNullValues() {
        errorModel.setErrorCode(null);
        errorModel.setErrorMessage(null);
        assertNull(errorModel.getErrorCode());
        assertNull(errorModel.getErrorMessage());
    }

    @Test
    @DisplayName("Should handle empty strings")
    void testEmptyStrings() {
        errorModel.setErrorCode("");
        errorModel.setErrorMessage("");
        assertEquals("", errorModel.getErrorCode());
        assertEquals("", errorModel.getErrorMessage());
    }

    @Test
    @DisplayName("Should modify error details")
    void testModifyErrorDetails() {
        errorModel.setErrorCode("ERR_100");
        errorModel.setErrorMessage("Initial error");

        errorModel.setErrorCode("ERR_200");
        errorModel.setErrorMessage("Updated error");

        assertEquals("ERR_200", errorModel.getErrorCode());
        assertEquals("Updated error", errorModel.getErrorMessage());
    }

    @Test
    @DisplayName("Should create new instance with builder")
    void testNewInstanceWithBuilder() {
        ErrorModel error1 = ErrorModel.builder().errorCode("E1").errorMessage("Error 1").build();
        ErrorModel error2 = ErrorModel.builder().errorCode("E2").errorMessage("Error 2").build();

        assertNotEquals(error1.getErrorCode(), error2.getErrorCode());
    }

    @Test
    @DisplayName("Should handle long error messages")
    void testLongErrorMessage() {
        String longMessage = "This is a very long error message that describes what went wrong in detail";
        errorModel.setErrorMessage(longMessage);
        assertEquals(longMessage, errorModel.getErrorMessage());
    }

    @Test
    @DisplayName("Should handle special characters in error code")
    void testSpecialCharactersInErrorCode() {
        errorModel.setErrorCode("ERR-001_TEST");
        assertEquals("ERR-001_TEST", errorModel.getErrorCode());
    }

    @Test
    @DisplayName("Should use builder pattern correctly")
    void testBuilderPattern() {
        ErrorModel error = ErrorModel.builder()
                .errorCode("404")
                .errorMessage("Resource not found")
                .build();

        assertNotNull(error);
        assertEquals("404", error.getErrorCode());
        assertEquals("Resource not found", error.getErrorMessage());
    }
}
