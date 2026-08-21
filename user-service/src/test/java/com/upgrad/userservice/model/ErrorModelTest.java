package com.upgrad.userservice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Error Model Tests")
class ErrorModelTest {

    private ErrorModel errorModel;

    @BeforeEach
    void setUp() {
        errorModel = new ErrorModel();
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
        errorModel.setErrorMessage("Invalid input");
        assertEquals("Invalid input", errorModel.getErrorMessage());
    }

    @Test
    @DisplayName("Should set and get errorFieldsList")
    void testSetGetErrorFieldsList() {
        List<String> fields = new ArrayList<>();
        fields.add("firstName");
        fields.add("email");
        errorModel.setErrorFieldsList(fields);
        assertEquals(2, errorModel.getErrorFieldsList().size());
    }

    @Test
    @DisplayName("Should build error model with builder")
    void testErrorModelBuilder() {
        List<String> fields = Arrays.asList("field1", "field2");
        ErrorModel error = ErrorModel.builder()
                .errorCode("ERR_400")
                .errorMessage("Bad Request")
                .errorFieldsList(fields)
                .build();

        assertEquals("ERR_400", error.getErrorCode());
        assertEquals("Bad Request", error.getErrorMessage());
        assertEquals(2, error.getErrorFieldsList().size());
    }

    @Test
    @DisplayName("Should populate all fields")
    void testPopulateAllFields() {
        List<String> fieldsList = Arrays.asList("username", "password");
        errorModel.setErrorCode("AUTH_001");
        errorModel.setErrorMessage("Authentication failed");
        errorModel.setErrorFieldsList(fieldsList);

        assertEquals("AUTH_001", errorModel.getErrorCode());
        assertEquals("Authentication failed", errorModel.getErrorMessage());
        assertEquals(fieldsList, errorModel.getErrorFieldsList());
    }

    @Test
    @DisplayName("Should handle null values")
    void testNullValues() {
        errorModel.setErrorCode(null);
        errorModel.setErrorMessage(null);
        errorModel.setErrorFieldsList(null);
        assertNull(errorModel.getErrorCode());
        assertNull(errorModel.getErrorMessage());
        assertNull(errorModel.getErrorFieldsList());
    }

    @Test
    @DisplayName("Should handle empty error fields list")
    void testEmptyErrorFieldsList() {
        errorModel.setErrorFieldsList(new ArrayList<>());
        assertNotNull(errorModel.getErrorFieldsList());
        assertEquals(0, errorModel.getErrorFieldsList().size());
    }

    @Test
    @DisplayName("Should modify error details")
    void testModifyErrorDetails() {
        errorModel.setErrorCode("ERR_001");
        errorModel.setErrorMessage("Initial error");

        errorModel.setErrorCode("ERR_002");
        errorModel.setErrorMessage("Updated error");

        assertEquals("ERR_002", errorModel.getErrorCode());
        assertEquals("Updated error", errorModel.getErrorMessage());
    }

    @Test
    @DisplayName("Should add multiple error fields")
    void testMultipleErrorFields() {
        List<String> fields = Arrays.asList("field1", "field2", "field3", "field4");
        errorModel.setErrorFieldsList(fields);
        assertEquals(4, errorModel.getErrorFieldsList().size());
    }

    @Test
    @DisplayName("Should use builder pattern correctly")
    void testBuilderPattern() {
        ErrorModel error = ErrorModel.builder()
                .errorCode("404")
                .errorMessage("Not Found")
                .build();

        assertNotNull(error);
        assertEquals("404", error.getErrorCode());
        assertEquals("Not Found", error.getErrorMessage());
    }

    @Test
    @DisplayName("Should handle special characters in error message")
    void testSpecialCharactersInMessage() {
        String specialMessage = "Error: Invalid @#$% input!";
        errorModel.setErrorMessage(specialMessage);
        assertEquals(specialMessage, errorModel.getErrorMessage());
    }
}
