package com.company;
import java.util.Scanner;
import java.util.Random;

public class BullsAndCows {
    final static String GREETINGS = "The secret code is prepared: ****.\nLet's begin!";
    final static int NUMBER_OF_DIGITS = 4; //You try to guess 4-digit number

    public Scanner obj = new Scanner(System.in); //For inputting
    private static int turnCount = 1; //Count of tries
    private int secretNum; //The number you try to guess
    private int cows; //Count of cows
    private int bulls; //Count of bulls

    //Constructor with parameter
    BullsAndCows(int secretNum) {
        this.secretNum = secretNum;
    }

    //Default constructor
    BullsAndCows() {
        generateSecretNum();
    }

    //secretNum setter
    public void setSecretNum(int secretNum) {
        this.secretNum = secretNum;
    }

    //secretNum getter
    public int getSecretNum() {
        return this.secretNum;
    }

    //Incrementation of turnCount
    public void turnCountIncrementation() {
        turnCount ++;
    }

    //cows setter
    public void setCows(int cows) {
        this.cows = cows;
    }

    //cows getter
    public int getCows() {
        return this.cows;
    }

    //Function which drop cows to 0
    private void dropCows() {
        this.cows = 0;
    }

    //bulls setter
    public void setBulls(int bulls) {
        this.bulls = bulls;
    }

    //bulls getter
    public int getBulls() {
        return this.bulls;
    }

    //Function which drop bulls to 0
    private void dropBulls() {
        this.bulls = 0;
    }

    //Function which check amount of bulls in the answer
    private int checkBulls(String secret, String answer) {
        int bullsCount = 0;
        for (int i = 0; i < NUMBER_OF_DIGITS; i++) {
            if (secret.charAt(i) == answer.charAt(i)) {
                bullsCount++;
            }
        }

        return bullsCount;
    }

    //Function which check amount of cows in the answer
    private int checkCows(String secret, String answer) {
        int cowsCount = 0;

        for (int i = 0; i < NUMBER_OF_DIGITS; i++) {
            for (int j = 0; j < NUMBER_OF_DIGITS; j++) {
                if (secret.charAt(i) == answer.charAt(j) && i != j) {
                    cowsCount++;
                }
            }
        }

        return cowsCount;
    }

    //Function which show amount of cows and bulls in the answer
    private void showBullsAndCows() {
        if (getBulls() == 0 && getCows() == 0) {
            System.out.println("None.");
        } else if (getBulls() == 4) {
            System.out.print(getBulls() + " bulls. \nCongrats! The secret code is " + getSecretNum() + '.');
        } else if (getBulls() == 0 && getCows() == 1) {
            System.out.println(getCows() + " cow.");
        } else if (getBulls() == 0 && getCows() != 0) {
            System.out.println(getCows() + " cows.");
        } else if (getCows() == 0 && getBulls() == 1) {
            System.out.println(getBulls() + " bull.");
        } else if (getCows() == 0 && getBulls() != 0) {
            System.out.println(getBulls() + " bulls.");
        } else if (getCows() == 1 && getBulls() == 1){
            System.out.println(getBulls() + " bull and " + getCows() + " cow.");
        } else if (getCows() == 1 && getBulls() != 1) {
            System.out.println(getBulls() + " bulls and " + getCows() + " cow.");
        } else if (getCows() != 1 && getBulls() == 1) {
            System.out.println(getBulls() + " bull and " + getCows() + " cows.");
        } else {
            System.out.println(getBulls() + " bulls and " + getCows() + " cows.");
        }
    }

    //Main function which starts the game
    public void game() {
        showRules();
        System.out.println(GREETINGS);
        System.out.println("");
        int answer;
        String sec = Integer.toString(getSecretNum());

        do {
            dropCows();
            dropBulls();
            System.out.println("Turn " + turnCount + ". Answer:");
            turnCountIncrementation();
            answer = correctInput();
            String ans = Integer.toString(answer);
            setBulls(checkBulls(sec, ans));
            if (getBulls() != 4) {
                setCows(checkCows(sec, ans));
            }
            System.out.print("Grade: ");
            showBullsAndCows();
            System.out.println("");
        } while (getBulls() != 4);
    }

    //Function which randomly generate secretNum
    private void generateSecretNum() {
        Random rand = new Random();
        int rand1 = 0;
        int rand2 = 0;
        int rand3 = 0;
        int rand4 = 0;
        while(rand1 == 0 || rand1 == rand2 || rand1 == rand3 || rand1 == rand4 || rand2 == rand3 || rand2 == rand4 || rand3 == rand4) {
            rand1 = rand.nextInt(10);
            rand2 = rand.nextInt(10);
            rand3 = rand.nextInt(10);
            rand4 = rand.nextInt(10);
        }
        int result = rand1 * 1000 + rand2 * 100 + rand3 * 10 + rand4;
        setSecretNum(result);
    }

    //Function for correct input answer which include checking all possible exception
    private int correctInput() {
        int n = 0;
        int result;
        boolean flag = false;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                n = sc.nextInt();
                flag = false;
            } catch (Exception e) {
                System.out.println("Enter only integer value.(" + e + ") Try again: ");
                flag = true;
            }
        } while (flag);


        result = n;

        while (!flag) {
            if (result < 1000 || result > 9999) {
                System.out.println("Incorrect input! Try again: ");
                correctInput();
            }
            flag = true;
        }

        return result;
    }

    //Function which show rules of the game
    private void showRules() {
        System.out.println("\nOn a sheet of paper, the players each write a 4-digit secret number. The digits must be all different. Then, in turn, the players \n" +
                "try to guess their opponent's number who gives the number of matches. If the matching digits are in their right positions, they are\n" +
                "\"bulls\", if in different positions, they are \"cows\". Example:\n" +
                "Secret number: 4271\n" +
                "Opponent's try: 1234\n" +
                "Answer: 1 bull and 2 cows. (The bull is \"2\", the cows are \"4\" and \"1\".)");
        System.out.println("Press any key+Enter to start");
        obj.nextLine();
    }

}