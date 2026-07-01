package com.exercises;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ShoppingCartTest {

    // ─── Shared fixture ──────────────────────────────────────────────────────
    // This is the "test fixture" — the object shared across all tests in this class
    private ShoppingCart cart;

    // ─── @BeforeClass ────────────────────────────────────────────────────────
    // Runs ONCE before any test in this class.
    // Must be static. Use for expensive one-time setup (DB connections, loading config).
    @BeforeClass
    public static void setUpClass() {
        System.out.println("=== ShoppingCartTest suite starting ===");
    }

    // ─── @AfterClass ─────────────────────────────────────────────────────────
    // Runs ONCE after all tests in this class finish.
    // Must be static. Use for one-time cleanup (closing DB connections, releasing resources).
    @AfterClass
    public static void tearDownClass() {
        System.out.println("=== ShoppingCartTest suite completed ===");
    }

    // ─── @Before ─────────────────────────────────────────────────────────────
    // Runs before EACH test method.
    // Gives every test a fresh, clean ShoppingCart — no shared state between tests.
    @Before
    public void setUp() {
        cart = new ShoppingCart();
        System.out.println("--- New cart created for test ---");
    }

    // ─── @After ──────────────────────────────────────────────────────────────
    // Runs after EACH test method.
    // Good place to clean up resources created during the test.
    @After
    public void tearDown() {
        cart.clear();
        System.out.println("--- Cart cleared after test ---");
    }

    // ─── Test 1: AAA Pattern — adding one item ────────────────────────────────
    @Test
    public void testAddItem() {
        // Arrange — cart is already set up by @Before (empty cart)
        String itemName = "Laptop";
        double itemPrice = 999.99;

        // Act
        cart.addItem(itemName, itemPrice);

        // Assert
        assertEquals(1, cart.getItemCount());
        assertTrue(cart.containsItem("Laptop"));
    }

    // ─── Test 2: AAA Pattern — calculating total ──────────────────────────────
    @Test
    public void testCalculateTotal() {
        // Arrange
        cart.addItem("Laptop", 999.99);
        cart.addItem("Mouse", 25.00);
        cart.addItem("Keyboard", 49.99);

        // Act
        double total = cart.calculateTotal();

        // Assert
        assertEquals(1074.98, total, 0.01);
    }

    // ─── Test 3: AAA Pattern — discount calculation ───────────────────────────
    @Test
    public void testCalculateTotalWithDiscount() {
        // Arrange
        cart.addItem("Laptop", 1000.00);
        cart.addItem("Mouse", 100.00);
        double discountPercent = 10.0;

        // Act
        double discountedTotal = cart.calculateTotalWithDiscount(discountPercent);

        // Assert — 1100 * 0.9 = 990
        assertEquals(990.00, discountedTotal, 0.01);
    }

    // ─── Test 4: AAA Pattern — removing an item ───────────────────────────────
    @Test
    public void testRemoveItem() {
        // Arrange
        cart.addItem("Laptop", 999.99);
        cart.addItem("Mouse", 25.00);

        // Act
        cart.removeItem("Mouse");

        // Assert
        assertEquals(1, cart.getItemCount());
        assertFalse(cart.containsItem("Mouse"));
        assertTrue(cart.containsItem("Laptop"));
    }

    // ─── Test 5: Empty cart total should be zero ──────────────────────────────
    @Test
    public void testEmptyCartTotal() {
        // Arrange — @Before already gives us an empty cart

        // Act
        double total = cart.calculateTotal();

        // Assert
        assertEquals(0.0, total, 0.001);
        assertEquals(0, cart.getItemCount());
    }

    // ─── Test 6: Exception for invalid item name ──────────────────────────────
    @Test(expected = IllegalArgumentException.class)
    public void testAddItemWithEmptyNameThrowsException() {
        // Arrange + Act + Assert (exception expected)
        cart.addItem("", 10.0);
    }

    // ─── Test 7: Exception for negative price ─────────────────────────────────
    @Test(expected = IllegalArgumentException.class)
    public void testAddItemWithNegativePriceThrowsException() {
        cart.addItem("Laptop", -100.0);
    }

    // ─── Test 8: Adding multiple items and verifying count ───────────────────
    @Test
    public void testItemCountAfterMultipleAdditions() {
        // Arrange
        String[] items   = {"Laptop", "Mouse", "Keyboard", "Monitor", "Webcam"};
        double[] prices  = {999.99,   25.00,   49.99,      299.99,    79.99};

        // Act
        for (int i = 0; i < items.length; i++) {
            cart.addItem(items[i], prices[i]);
        }

        // Assert
        assertEquals(5, cart.getItemCount());
    }
}