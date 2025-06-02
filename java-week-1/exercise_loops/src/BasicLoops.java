import java.util.Scanner;
import java.util.Random;

public class BasicLoops {
    public static void main(String[] args) {
        // Count Up! (For Loop)
        for (int i=1; i<=100; i++){
            System.out.println(i);
        }
        System.out.println("\nEven numbers:");
        for (int i=1; i<=100; i++){
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
        // Countdown Timer (While Loop)
        Scanner io = new Scanner(System.in);
        System.out.println("\nPlease enter a number (1-20): ");
        int number = Integer.parseInt(io.nextLine());
        System.out.println("Starting countdown...");
        while (number > 0) {
            System.out.println(number--);
        }
        System.out.println("Blast off!");
        // Guess the Number (Do-While Loop)
        Random randomNumber = new Random();
        int secretNumber = randomNumber.nextInt(10) + 1;
        int guessNumber;
        int attempts = 0;
        System.out.println("\nI have a number in mind from 1-10. Can you guess the number?");
        do{
            System.out.println("\nPlease enter your guess: ");
            guessNumber = Integer.parseInt(io.nextLine());
            attempts++;
            if (guessNumber < secretNumber) {
                System.out.println("Too low! Try again.");
            } else if (guessNumber > secretNumber) {
                System.out.println("Too high! Try again.");
            }
        }while(guessNumber != secretNumber);
        System.out.println("Congratulations, you guessed the number!" +
                           "\nThe number was: " + secretNumber);
    }
}
