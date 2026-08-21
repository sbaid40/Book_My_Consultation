package com.upgrad.ratingservice.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RatingValueInvalidException Tests")
class RatingValueInvalidExceptionTest {

    @Test
    @DisplayName("Should create exception with no message")
    void testExceptionCreationNoMessage() {
        RatingValueInvalidException exception = new RatingValueInvalidException();
        assertNotNull(exception);
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    @DisplayName("Should throw exception successfully")
    void testThrowException() {
        assertThrows(RatingValueInvalidException.class, () -> {
            throw new RatingValueInvalidException();
        });
    }

    @Test
    @DisplayName("Should catch exception as RuntimeException")
    void testCatchAsRuntimeException() {
        assertThrows(RuntimeException.class, () -> {
            throw new RatingValueInvalidException();
        });
    }

    @Test
    @DisplayName("Should have proper inheritance chain")
    void testInheritanceChain() {
        RatingValueInvalidException exception = new RatingValueInvalidException();
        assertTrue(exception instanceof RuntimeException);
        assertTrue(exception instanceof Exception);
        assertTrue(exception instanceof Throwable);
    }

    @Test
    @DisplayName("Should be throwable and catchable")
    void testThrowAndCatch() {
        try {
            throw new RatingValueInvalidException();
        } catch (RatingValueInvalidException e) {
            assertNotNull(e);
        }
    }

    @Test
    @DisplayName("Should work in try-catch-finally block")
    void testTryCatchFinally() {
        boolean exceptionCaught = false;
        try {
            throw new RatingValueInvalidException();
        } catch (RatingValueInvalidException e) {
            exceptionCaught = true;
        } finally {
            assertTrue(exceptionCaught);
        }
    }

    @Test
    @DisplayName("Should be multiple instances independently")
    void testMultipleInstances() {
        RatingValueInvalidException exception1 = new RatingValueInvalidException();
        RatingValueInvalidException exception2 = new RatingValueInvalidException();

        assertNotNull(exception1);
        assertNotNull(exception2);
        assertNotSame(exception1, exception2);
    }

    @Test
    @DisplayName("Should preserve exception type through throw")
    void testExceptionTypePreservation() {
        try {
            throw new RatingValueInvalidException();
        } catch (Exception e) {
            assertTrue(e instanceof RatingValueInvalidException);
        }
    }
}
