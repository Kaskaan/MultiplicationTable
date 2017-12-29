import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

// Author: Konrad Lesiak
// Last update: 29.12.2017
// Multiplication Table (Console Game) - Give the correct answer and earn points!

public class MultiplicationTable {
    private int randomBounds;
    private int firstNumber;
    private int secondNumber;
    private int multiplicationResult;
    private int userResponse;
    private int userPoints;
    private boolean isGameOver = false;
    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);

    // Constructor with one Integer parameter (bounds of generated numbers).
    // f.e. If this parameter will be "10", than max generated Quest will be: "10 X 10"
    MultiplicationTable(int randomBounds) {
        this.randomBounds = randomBounds + 1;
        // Game is running until "isGameOver" will be false
        for (int i = 0; isGameOver == false ; i++) {
            playGame();
        }
    }

    // This method generating two random numbers.
    private void generateNumbers() {
        firstNumber = random.nextInt(randomBounds);
        secondNumber = random.nextInt(randomBounds);
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
            userResponse = scanner.nextInt();
            checkConditions();
        } catch (InputMismatchException e) {
            System.out.println("Wpisano nieprawidłowy typ danych!");
            System.out.println("KONIEC GRY!");
            isGameOver = true;
        }
    }

    // This method provide User Response.
    private void firstResponseScan() {
        System.out.println("Wpisz wynik: ");
        scanUserResponse();
    }

    // This method giving User Response after the first answer is wrong.
    private void secondResponseScan() {
        System.out.println("Ponownie wpisz wynik wyrażenia: " + firstNumber + " X " + secondNumber);
        scanUserResponse();
    }

    // This method adding one point to User.
    private void addUserPoint() {
        userPoints += 1;
    }

    // This method subtract one point to User.
    private void subtractUserPoint() {
        if (userPoints > 0) {
            userPoints -= 1;
        } else
            userPoints -= 0;
    }

    // This method display user points.
    private void showUserPoints() {
        System.out.println("Twoje punkty: " + userPoints);
    }

    // This method checking and comparing User Response
    private void checkConditions() {
        if (userResponse == multiplicationResult) {
            addUserPoint();
            System.out.println("To się zgadza! \n");
            showUserPoints();
            System.out.println("----------------------------------------");
        } else {
            subtractUserPoint();
            System.out.println("Błędny wynik! \n");
            showUserPoints();
            System.out.println("----------------------------------------");
            secondResponseScan();
        }
    }

    // This is all-in-one method to start this game
    private void playGame() {
        generateNumbers();
        establishMultiplicationResult();
        showQuest();
        firstResponseScan();
    }
}
