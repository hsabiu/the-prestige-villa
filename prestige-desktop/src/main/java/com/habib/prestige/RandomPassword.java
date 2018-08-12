package com.habib.prestige;

import java.security.SecureRandom;
import java.util.Random;

class RandomPassword {
    private static final Random RANDOM = new SecureRandom();
    private static final int PASSWORD_LENGTH = 8;

    static String generateRandomPassword() {
        String letters = "abcdefghjklmnpqrstuvwxyzABCDEFJHJKLMNPQRSTUVWXYZ23456789";
        StringBuilder pw = new StringBuilder();
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = (int) (RANDOM.nextDouble() * letters.length());
            pw.append(letters, index, index + 1);
        }
        return pw.toString();
    }
}