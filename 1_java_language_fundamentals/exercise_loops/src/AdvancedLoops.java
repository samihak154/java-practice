import java.util.Scanner;

public class AdvancedLoops {
    public static void main(String[] args) {
        // FizzBuzz Challenge (For Loop)
        for (int i=1; i<=100; i++){
            if (i % 15 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else {
                System.out.println("Buzz");
            }
        }

        // Reverse a String (For Loop)
        Scanner io = new Scanner(System.in);
        System.out.print("\nPlease enter a word: ");
        String userWord = io.nextLine();
        for (int i = userWord.length() - 1; i >= 0; i--) {
            System.out.print(userWord.charAt(i));;
        }

        // Prime Number Checker (While Loop)
        System.out.print("\nEnter a number: ");
        int number = Integer.parseInt(io.nextLine());
        boolean isPrime = number > 1;
        int divisor = 2;

        while (divisor <= Math.sqrt(number)) {
            if (number % divisor == 0) {
                isPrime = false;
                break;
            }
            divisor++;
        }
        if (isPrime) {
            System.out.println(number + " is a prime number");
        } else {
            System.out.println(number + " is not a prime number");
        }

        // Word Counter (For Loop with Split)
        System.out.print("\nPlease enter a sentence: ");
        String sentence = io.nextLine();

        int wordCount = 0;
        if (!sentence.isEmpty()) {
            wordCount = 1;
            for (int i = 0; i < sentence.length(); i++) {
                if (sentence.charAt(i) == ' ') {
                    wordCount++;
                }
            }
        }
        System.out.println("Number of words in sentence: " + wordCount);
    }
}
