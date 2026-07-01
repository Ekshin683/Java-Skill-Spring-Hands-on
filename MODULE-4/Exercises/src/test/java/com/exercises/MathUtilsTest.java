// package MODULE-4.Exercises.src.test.java.com.exercises;

package com.exercises;

import org.junit.Test;
import static org.junit.Assert.*;

public class MathUtilsTest {

    // Create one instance of MathUtils used across all tests
    MathUtils mathUtils = new MathUtils();

    @Test
    public void testAdd() {
        int result = mathUtils.add(5, 3);
        assertEquals(8, result);
    }

    @Test
    public void testAddWithNegativeNumbers() {
        assertEquals(-2, mathUtils.add(-5, 3));
    }

    @Test
    public void testAddWithZero() {
        assertEquals(7, mathUtils.add(7, 0));
    }

    @Test
    public void testSubtract() {
        assertEquals(2, mathUtils.subtract(5, 3));
    }

    @Test
    public void testMultiply() {
        assertEquals(15, mathUtils.multiply(5, 3));
    }

    @Test
    public void testMultiplyByZero() {
        assertEquals(0, mathUtils.multiply(5, 0));
    }

    @Test
    public void testDivide() {
        assertEquals(2.5, mathUtils.divide(5, 2), 0.001);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZeroThrowsException() {
        mathUtils.divide(10, 0);
        // If ArithmeticException is thrown, test passes
        // If not thrown, test fails
    }

    @Test
    public void testIsEvenWithEvenNumber() {
        assertTrue(mathUtils.isEven(4));
    }

    @Test
    public void testIsEvenWithOddNumber() {
        assertFalse(mathUtils.isEven(7));
    }

    @Test
    public void testFindMax() {
        assertEquals(10, mathUtils.findMax(3, 10));
    }

    @Test
    public void testFindMaxWhenEqual() {
        assertEquals(5, mathUtils.findMax(5, 5));
    }
}