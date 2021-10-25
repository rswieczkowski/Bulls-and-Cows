package com.cowsandbulls;

public class Gameplay {



    private CodeGenerator codeGenerator;
    private int turnCounter;
    private int bullCounter;
    private int cowCounter;
    private int secretCodeLength;

    public Gameplay() {
        codeGenerator = new CodeGenerator();
        secretCodeLength = codeGenerator.getCodeLength();
        runGame();
    }

    public String inputAnswer() {
        String userCode = CodeGenerator.SCANNER.nextLine();
        while (userCode.length() != secretCodeLength) {
            userCode = CodeGenerator.SCANNER.nextLine();
        }
        return userCode;
    }

    private void runGame() {
        

        while (bullCounter < secretCodeLength) {
            cowCounter = 0;
            bullCounter = 0;
            turnCounter++;
            System.out.printf("Turn %s. Answer:\n", turnCounter);
            String userCode = inputAnswer();

            for (int i = 0; i < secretCodeLength; i++) {
                for (int j = 0; j < secretCodeLength; j++) {
                    if (userCode.charAt(i) == codeGenerator.getSecretCode().getCode().charAt(j)) {
                        cowCounter++;
                    }
                }
                if (userCode.charAt(i) == codeGenerator.getSecretCode().getCode().charAt(i)) {
                    bullCounter++;
                    cowCounter--;
                }
            }
            String grade = checkGrade();
            System.out.println(grade);
        }
        System.out.print("Congratulations! You guessed the secret code.");
    }

    private String checkGrade() {
        String grade = "Grade: ";
        String bull = bullCounter == 1 ? "bull" : "bulls";
        String cow = cowCounter == 1 ? "cow" : "cows";
        if (bullCounter > 0 && cowCounter > 0) {
            grade += bullCounter + " " + bull + " and " + cowCounter + " " + cow;
        } else if (bullCounter > 0) {
            grade += bullCounter + " " + bull;
        } else if (cowCounter > 0) {
            grade += cowCounter + " " + cow;
        } else {
            grade += "None";
        }
        return grade + '.';
    }
}

