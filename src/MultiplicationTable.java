import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

// Author: Konrad Lesiak
// Created: 29.12.2017
// Updated: 26.12.2020
// Multiplication Table (Terminal Game) - Good way to practice multiplication table answers.
//
// Choose the bounds, and answer the multiplication questions.
// You have 3 attempts for every single answer. After good answer attempts are set up to 3.
// You loose point every wrong answer. You get one point every good answer.

public class MultiplicationTable {
    private final Random RANDOM_NUMBER = new Random();
    private final Scanner USER_ANSWER = new Scanner(System.in);
    private final int randomBounds;
    private int firstNumber;
    private int secondNumber;
    private int multiplicationResult;
    private int userResponse;
    private int userPoints;
    private int availableAttempts = 3;
    private boolean isGameOver = false;

    // If this parameter will be "10", than max generated quest will be: "10 X 10".
    public MultiplicationTable(int randomBounds) {
        this.randomBounds = randomBounds + 1;
        // Game is running until "isGameOver" is false, or user available attempts will be more than 0
        while (!isGameOver && availableAttempts > 0) {
            playGame();
        }
    }

    // Generating two random numbers.
    private void generateNumbers() {
        firstNumber = RANDOM_NUMBER.nextInt(randomBounds);
        secondNumber = RANDOM_NUMBER.nextInt(randomBounds);
    }

    // This method calculating multiplication result from random numbers.
    private void establishMultiplicationResult() {
        multiplicationResult = firstNumber * secondNumber;
    }

    // This method displaying first Quest Message.
    private void showQuest() {
        System.out.println("Jaki jest wynik wyrażenia: " + firstNumber + " X " + secondNumber + "?");
    }

    // This method scanning console to get User response.
    private void scanUserResponse() {
        try {
            if (availableAttempts > 0) {
                userResponse = USER_ANSWER.nextInt();
                checkConditions();
            } else setGameOver();

        } catch (InputMismatchException e) {
            System.out.println("Wpisano nieprawidłowy typ danych!");
            setGameOver();
        }
    }

    private void setGameOver() {
        System.out.println("KONIEC GRY!");
        isGameOver = true;
    }

    // This method provide User Response.
    private void firstResponseScan() {
        System.out.println("Wpisz wynik: ");
        scanUserResponse();
    }

    // This method giving User Response after the first answer is wrong.
    private void secondResponseScan() {
        if (availableAttempts > 0) {
            System.out.println("Ponownie wpisz wynik wyrażenia: " + firstNumber + " X " + secondNumber);
            scanUserResponse();
        } else setGameOver();
    }

    private void addUserPoint() {
        userPoints++;
    }

    private void subtractUserPoint() {
        if (userPoints > 0) {
            userPoints--;
        }
    }

    private void subtractUserLife() {
        this.availableAttempts--;
    }

    private void showUserPoints() {
        System.out.println("Twoje punkty: " + userPoints);
    }

    private void resetAttempts() {
        availableAttempts = 3;
    }

    // This method is checking and comparing user response with multiplication result
    private void checkConditions() {
        if (userResponse == multiplicationResult) {
            addUserPoint();
            resetAttempts();
            System.out.println("To się zgadza! \n");
            showUserPoints();
            System.out.println("----------------------------------------");
        } else {
            subtractUserPoint();
            subtractUserLife();
            System.out.println("Błędny wynik! \n");
            System.out.println("Zostało podejść: " + availableAttempts);
            showUserPoints();
            System.out.println("----------------------------------------");
            secondResponseScan();
        }
    }

    // Use this method to play the Game
    private void playGame() {
        generateNumbers();
        establishMultiplicationResult();
        showQuest();
        firstResponseScan();
    }
}
