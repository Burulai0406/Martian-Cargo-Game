import java.util.Scanner;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int[] cargoLocations = new int[3];
        resetCargoLocations(cargoLocations, random);

        boolean foundAll = false;
        int attempts = 0;

       // System.out.println("Welcome, Martians! Let's find your hidden cargo boxes.");
       // System.out.println("Enter 3 numbers (0 to 7) to guess the kilometers where the boxes are buried.");
       // System.out.println("You have 5 attempts before the locations change!");
        while (!foundAll) {
            int correctGuesses = 0;
            System.out.println("\nAttempt" + (attempts + 1) + ":");
            int[] guesses = new int[3];
            for (int i = 0; i < 3; i++) {
                System.out.print("Enter guess " + (i + 1) + ": ");
                guesses[i] = scanner.nextInt();
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if  (guesses[i] == cargoLocations[j]) {
                        correctGuesses++;
                        break;
                    }
                }
            }
            System.out.println("You guessed " + correctGuesses + " boxes correctly!");
            if (correctGuesses == 3) {
                System.out.println("Congratulation! You found all the boxes.");
                break;
            }else {
                attempts++;
                if (attempts == 5) {
                    System.out.println("The cargo boxes have moved.Try again!");
                    resetCargoLocations(cargoLocations, random);
                    attempts = 0;
                }
            }
        }
        scanner.close();
    }
    private static void resetCargoLocations(int[] locations, Random random) {
        for (int i = 0; i < locations.length; i++) {
            locations[i] = random.nextInt(8);
            System.out.print( locations[i] + " ");
        }

    }
}