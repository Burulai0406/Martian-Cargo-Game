import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initialize the scanner for user input and random for generating random cargo locations
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Array to hold the locations of the cargo boxes
        int[] cargoLocations = new int[3];
        // Generate initial random locations for the cargo boxes
        resetCargoLocations(cargoLocations, random);

        // List to store leaderboard information
        List<String> leaderboard = new ArrayList<>();

        // Flag to track if all boxes are found
        boolean foundAll = false;
        // Counter to track the number of attempts made
        int attempts = 0;
        int totalCorrectGuesses = 0;

        // Get the player's name
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();

        // Display introduction and game instructions
        System.out.println("\nWelcome, " + playerName + "! Let's find your hidden cargo boxes.");
        System.out.println("Enter 3 numbers (1 to 7) to guess the kilometers where the boxes are buried.");
        System.out.println("You have 5 attempts before the locations change!");

        // Main game loop
        while (!foundAll) {
            int correctGuesses = 0;
            System.out.println("\nAttempt " + (attempts + 1) + ":");

            // Array to store user's guesses
            int[] guesses = new int[3];
            for (int i = 0; i < 3; i++) {
                // Validate user input
                while (true) {
                    System.out.print("Enter guess " + (i + 1) + ": ");
                    guesses[i] = scanner.nextInt();
                    if (guesses[i] >= 1 && guesses[i] <= 7) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter a number between 1 and 7.");
                    }
                }
            }

            // Compare user's guesses with cargo locations
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (guesses[i] == cargoLocations[j]) {
                        correctGuesses++; // Increment if a guess matches a cargo location
                        break; // Avoid counting the same cargo box multiple times
                    }
                }
            }

            // Display correct and incorrect guesses
            System.out.println("Correct guesses: ");
            for (int i = 0; i < 3; i++) {
                boolean found = false;
                for (int j = 0; j < 3; j++) {
                    if (guesses[i] == cargoLocations[j]) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    System.out.println("Guess " + guesses[i] + " is correct!");
                } else {
                    System.out.println("Guess " + guesses[i] + " is incorrect.");
                }
            }

            // Update total correct guesses
            totalCorrectGuesses += correctGuesses;
            System.out.println("You guessed " + correctGuesses + " boxes correctly!");
            System.out.println("Total correct guesses so far: " + totalCorrectGuesses);

            // Check if the user has guessed all three locations
            if (correctGuesses == 3) {
                System.out.println("Congratulations, " + playerName + "! You found all the boxes.");
                leaderboard.add(playerName + " found all boxes in " + (attempts + 1) + " attempts!");
                break; // End the game
            } else {
                attempts++; // Increment the attempt counter
                // Reset cargo locations after 5 unsuccessful attempts
                if (attempts == 5) {
                    System.out.println("The cargo boxes have moved. Try again!");
                    resetCargoLocations(cargoLocations, random);
                    attempts = 0; // Reset the attempt counter
                }
            }
        }

        // Display leaderboard
        System.out.println("\nLeaderboard:");
        for (String entry : leaderboard) {
            System.out.println(entry);
        }

        // Close the scanner to release system resources
        scanner.close();
    }

    // Method to generate new random cargo locations
    private static void resetCargoLocations(int[] locations, Random random) {
        for (int i = 0; i < locations.length; i++) {
            // Generate a random number between 1 and 7 for each cargo box location
            locations[i] = random.nextInt(7) + 1;
        }
        System.out.println("New cargo locations have been set.");
    }
}

