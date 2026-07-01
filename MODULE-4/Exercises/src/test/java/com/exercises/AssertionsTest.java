package com.exercises;

import org.junit.Test;
import static org.junit.Assert.*;

public class AssertionsTest {

    StringUtils stringUtils = new StringUtils();

    // ─── assertEquals ───────────────────────────────────────────────────────
    // Checks that two values are equal
    @Test
    public void testAssertEquals() {
        // int equality
        assertEquals(5, 2 + 3);

        // String equality
        assertEquals("HELLO", stringUtils.toUpperCase("hello"));

        // double equality — third argument is the acceptable delta (tolerance)
        assertEquals(3.14, Math.PI, 0.01);
    }

    // ─── assertTrue ─────────────────────────────────────────────────────────
    // Checks that a condition is true
    @Test
    public void testAssertTrue() {
        assertTrue(5 > 3);
        assertTrue("java".startsWith("j"));
        assertTrue(stringUtils.isEmpty(""));
        assertTrue(stringUtils.isEmpty(null));
    }

    // ─── assertFalse ────────────────────────────────────────────────────────
    // Checks that a condition is false
    @Test
    public void testAssertFalse() {
        assertFalse(5 < 3);
        assertFalse("hello".isEmpty());
        assertFalse(stringUtils.isEmpty("hello"));
    }

    // ─── assertNull ─────────────────────────────────────────────────────────
    // Checks that the value is null
    @Test
    public void testAssertNull() {
        assertNull(null);
        assertNull(stringUtils.toUpperCase(null));  // our method returns null for null input
        assertNull(stringUtils.reverse(null));
    }

    // ─── assertNotNull ──────────────────────────────────────────────────────
    // Checks that the value is NOT null
    @Test
    public void testAssertNotNull() {
        assertNotNull(new Object());
        assertNotNull("hello");
        assertNotNull(stringUtils.toUpperCase("world"));
    }

    // ─── assertSame / assertNotSame ─────────────────────────────────────────
    // Checks that two references point to the SAME object in memory (==)
    // (different from assertEquals which checks .equals())
    @Test
    public void testAssertSame() {
        String a = "hello";
        String b = a;    // b and a point to the same object
        assertSame(a, b);

        String c = new String("hello");  // c is a different object even though value is same
        assertNotSame(a, c);

        // assertEquals passes (same value), assertSame would fail (different object)
        assertEquals(a, c);
        assertNotSame(a, c);
    }

    // ─── assertArrayEquals ──────────────────────────────────────────────────
    // Checks that two arrays have the same length and elements
    @Test
    public void testAssertArrayEquals() {
        int[] expected = {1, 2, 3, 4, 5};
        int[] actual   = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, actual);

        String[] expectedStr = {"a", "b", "c"};
        String[] actualStr   = {"a", "b", "c"};
        assertArrayEquals(expectedStr, actualStr);
    }

    // ─── Assertion with custom failure message ───────────────────────────────
    // Every assertion can take a String message as the first argument
    // This message is shown when the test fails — makes debugging easier
    @Test
    public void testAssertionsWithMessages() {
        int vowelCount = stringUtils.countVowels("Hello World");

        assertEquals("Expected 3 vowels in 'Hello World'", 3, vowelCount);
        assertTrue("Vowel count should be greater than 0", vowelCount > 0);
        assertNotNull("Result should never be null", stringUtils.reverse("test"));
    }

    // ─── Combined real-world example ─────────────────────────────────────────
    @Test
    public void testReverseString() {
        String original = "JUnit";
        String reversed = stringUtils.reverse(original);

        assertNotNull(reversed);
        assertEquals("tinUJ", reversed);
        assertNotSame(original, reversed);  // reverse returns a new String
        assertEquals(original.length(), reversed.length());
    }

    // ─── fail() — explicitly fail a test ────────────────────────────────────
    // Useful inside try/catch when you EXPECT an exception
    @Test
    public void testExceptionUsingFail() {
        try {
            int result = 10 / 0;
            fail("Expected ArithmeticException but no exception was thrown");
        } catch (ArithmeticException e) {
            // Test passes — exception was correctly thrown
            assertEquals("/ by zero", e.getMessage());
        }
    }
}