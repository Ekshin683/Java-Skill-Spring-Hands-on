package com.exercises;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private Map<String, Double> items = new HashMap<>();

    public void addItem(String name, double price) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Item name cannot be empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        items.put(name, price);
    }

    public void removeItem(String name) {
        items.remove(name);
    }

    public double calculateTotal() {
        return items.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    public double calculateTotalWithDiscount(double discountPercent) {
        double total = calculateTotal();
        return total - (total * discountPercent / 100);
    }

    public int getItemCount() {
        return items.size();
    }

    public boolean containsItem(String name) {
        return items.containsKey(name);
    }

    public void clear() {
        items.clear();
    }
}