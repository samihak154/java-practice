public class Methods {
    public static void main(String [] args) {
        printWelcomeMessage();

        System.out.println("\nThe sum of 9 and 6 is " + sum(9, 6));
        System.out.println("The sum of 27 and 68 is " + sum(27, 68));

        System.out.println("\n23°C in Fahrenheit: " + convertToFahrenheit(23));
        System.out.println("35°C in Fahrenheit: " + convertToFahrenheit(35));

        System.out.println("\nIs 4 even? " + isEven(4));
        System.out.println("Is 7 even? " + isEven(7));
    }

    // Task 1
    public static void printWelcomeMessage() {
        System.out.println("Welcome to the Java Methods Exercise!");
        System.out.println("-------------------------------------");
    }

    // Task 2
    public static int sum(int a, int b) {
        return a + b;
    }
    // Task 3
    public static double convertToFahrenheit(double celsius) {
        double fahrenheit = (celsius * 9/5) + 32;
        return fahrenheit;
    }
    // Task 4
    public static boolean isEven(int number) {
        if (number % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

}
