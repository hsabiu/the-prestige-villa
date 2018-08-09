package com.habib.prestige;

import java.security.SecureRandom;
import java.util.Random;

class RandomPassword {
	private static final Random RANDOM = new SecureRandom();
	public static final int PASSWORD_LENGH = 8;

	public static String generateRondomPassword() {
		String letters = "abcdefghjklmnpqrstuvwxyzABCDEFJHJKLMNPQRSTUVWXYZ23456789";
		String pw = "";
		for (int i = 0; i < PASSWORD_LENGH; i++) {
			int index = (int) (RANDOM.nextDouble() * letters.length());
			pw += letters.substring(index, index + 1);
		}
		return pw;
	}
}