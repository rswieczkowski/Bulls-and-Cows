package com.cowsandbulls;

import java.util.Random;
import java.util.Scanner;

public class CodeGenerator {
    public static final Scanner SCANNER = new Scanner(System.in);
    private final static String CHARACTERS_ALLOWED = "0123456789abcdefghijklmnopqrstuvwxyz";


    private SecretCode secretCode;
    private int codeLength;
    private int scope;

    public CodeGenerator() {
        runGenerator();
    }

    public SecretCode getSecretCode() {
        return secretCode;
    }

    public int getCodeLength() {
        return codeLength;
    }

    private void printScope() {
        StringBuilder message = new StringBuilder("The secret is prepared: ");
        message.append("*".repeat(Math.max(0, codeLength)));

        if (scope <= 10) {
            message.append(" (0-").append(CHARACTERS_ALLOWED.charAt(scope - 1)).append(").");
        } else if (scope <= 36) {
            message.append(" (0-9, a-").append(CHARACTERS_ALLOWED.charAt(scope - 1)).append(").");
        }

        message.append("\nOkay, let's start a game!");
        System.out.println(message);
    }

    private void runGenerator() {

        codeLength = validateLength();
        scope = validateScope();

        if (scope < codeLength) {
            System.out.println("Error: it's not possible to generate a code with a length of " + codeLength + " with " + scope + " unique symbols.");
            runGenerator();
        } else {
            secretCode = generateNumber();
            printScope();
        }
    }

    private int input() {
        String number = SCANNER.nextLine();
        int validNumber = 0;
        try {
            validNumber = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            System.out.println("Error: " + number + " isn't a valid number.");
        }
        return validNumber;
    }

    private int validateLength() {
        System.out.println("Input the length of the secret code:");
        int n = input();
        while (n < 1 || n > 36) {
            System.out.println("Error: the length of the secret code should be between 1-36. Try again!");
            n = input();
        }
        return n;
    }

    private int validateScope() {
        System.out.println("Input the number of possible symbols in the code:");
        int n = input();
        while (n < 1 || n > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z). Try again!");
            n = input();
        }
        return n;
    }

    private SecretCode generateNumber() {
        Random random = new Random();
        StringBuilder secretCode = new StringBuilder();
        StringBuilder charScope = new StringBuilder(CHARACTERS_ALLOWED.substring(0, scope));

        for (int i = 0; i < codeLength; i++) {
            int number = random.nextInt(charScope.length());
            secretCode.append(charScope.charAt(number));
            charScope.deleteCharAt(number);
        }

        if (secretCode.length() < codeLength) {
            generateNumber();
        }
        return new SecretCode(secretCode.toString());
    }
}
