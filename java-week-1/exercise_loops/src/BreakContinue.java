import java.util.Scanner;

public class BreakContinue {
    public static void main(String[] args) {
        Scanner io = new Scanner(System.in);

        // 1. Skip Negative Numbers (Continue Statement)
        System.out.println("Enter positive numbers (enter 0 to stop):");
        while (true) {
            System.out.print("> ");
            int number = io.nextInt();
            if (number == 0) {
                break;
            }
            if (number < 0) {
                System.out.println("Skipping number: " + number);
                continue;
            }
            System.out.println("Storing number: " + number);
        }

        // 2. Find First Prime Number (Break Statement)
        System.out.print("\nEnter the first number of range: ");
        int start = io.nextInt();
        System.out.print("Enter the last number of range: ");
        int end = io.nextInt();
        for (int i = start; i <= end; i++) {
            boolean isPrime = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (i > 1 && isPrime) {
                System.out.println("First prime number in range: " + i);
                break;
            }
        }
    }
}
