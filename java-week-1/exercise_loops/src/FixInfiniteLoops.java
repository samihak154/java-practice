import java.util.Scanner;

public class FixInfiniteLoops {
    public static void main(String[] args) {
        // Fix the Infinite Loop
        System.out.println("Infinite loop fixed: ");
        int i = 0;
        while (i <= 10) {
            System.out.println(i);
            i++;
        }

        // Even Number Checker (Preventing Infinite Loops)
        Scanner io = new Scanner(System.in);
        int userNumber;
        while (true) {  // Infinite loop until break
            System.out.print("\nEnter an even number: ");
            userNumber = Integer.parseInt(io.nextLine());
            if (userNumber % 2 == 0) {
                break;
            }
            System.out.println("That's an odd number, please enter an even number!");
        }
        System.out.println("Exiting program...");
    }
}

