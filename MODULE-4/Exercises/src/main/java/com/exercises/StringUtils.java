
package com.exercises;

public class StringUtils {

    public String toUpperCase(String input) {
        if (input == null) return null;
        return input.toUpperCase();
    }

    public boolean isEmpty(String input) {
        return input == null || input.isEmpty();
    }

    public String reverse(String input) {
        if (input == null) return null;
        return new StringBuilder(input).reverse().toString();
    }

    public int countVowels(String input) {
        if (input == null) return 0;
        int count = 0;
        for (char c : input.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) != -1) count++;
        }
        return count;
    }
}