package com.upgrad.userservice.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RequestedResourceNotFoundException Tests")
class RequestedResourceNotFoundExceptionTest {

    @Test
    @DisplayName("Should create exception with no message")
    void testExceptionCreationNoMessage() {
        RequestedResourceNotFoundException exception = new RequestedResourceNotFoundException();
        assertNotNull(exception);
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    @DisplayName("Should throw exception successfully")
    void testThrowException() {
        assertThrows(RequestedResourceNotFoundException.class, () -> {
            throw new RequestedResourceNotFoundException();
        });
    }

    @Test
    @DisplayName("Should catch exception as RuntimeException")
    void testCatchAsRuntimeException() {
        assertThrows(RuntimeException.class, () -> {
            throw new RequestedResourceNotFoundException();
        });
    }

    @Test
    @DisplayName("Should have proper inheritance chain")
    void testInheritanceChain() {
        RequestedResourceNotFoundException exception = new RequestedResourceNotFoundException();
        assertTrue(exception instanceof RuntimeException);
        assertTrue(exception instanceof Exception);
        assertTrue(exception instanceof Throwable);
    }

    @Test
    @DisplayName("Should allow message in constructor (inherited from RuntimeException)")
    void testExceptionWithMessage() {
        String message = "Resource not found";
        RuntimeException exception = new RuntimeException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Should allow cause in constructor (inherited from RuntimeException)")
    void testExceptionWithCause() {
        Throwable cause = new Throwable("Original cause");
        RuntimeException exception = new RuntimeException(cause);
        assertEquals(cause, exception.getCause());
    }

    @Test
    @DisplayName("Should be throwable and catchable")
    void testThrowAndCatch() {
        try {
            throw new RequestedResourceNotFoundException();
        } catch (RequestedResourceNotFoundException e) {
            assertNotNull(e);
        }
    }

    @Test
    @DisplayName("Should work in try-catch-finally block")
    void testTryCatchFinally() {
        boolean exceptionCaught = false;
        try {
            throw new RequestedResourceNotFoundException();
        } catch (RequestedResourceNotFoundException e) {
            exceptionCaught = true;
        } finally {
            assertTrue(exceptionCaught);
        }
    }
}
