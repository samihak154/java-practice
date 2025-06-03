public class Methods {
    public static void main(String [] args) {
        printWelcomeMessage();

        System.out.println("\nThe sum of 9 and 6 is " + sum(9, 6));
        System.out.println("The sum of 27 and 68 is " + sum(27, 68));

        System.out.println("\n23°C in Fahrenheit: " + convertToFahrenheit(23));
        System.out.println("35°C in Fahrenheit: " + convertToFahrenheit(35));

        System.out.println("\nIs 4 even? " + isEven(4));
        System.out.println("Is 7 even? " + isEven(7));

        System.out.println("\n");
        printMultipleTimes("Hello", 3);
        printMultipleTimes("Pumba", 2);

        System.out.println("\nMax of 1, 14, 8: " + findMax(1, 14, 8));
        System.out.println("Max of -5, -25, -2: " + findMax(-5, -25, -2));

        System.out.println("\nFactorial of 7: " + factorial(7));
        System.out.println("Factorial of 2: " + factorial(2));

        System.out.println("\n");
        greet("Pumba");
        greet("Pumba", 9);

        System.out.println("\nVowels in Hello: " + countVowels("hello"));
        System.out.println("Vowels in World: " + countVowels("world"));

        System.out.println("\n");
        System.out.println("5 + 5 = " + calculator(5, 5, '+'));
        System.out.println("6 - 3 = " + calculator(6, 3, '-'));
        System.out.println("4 * 7 = " + calculator(4, 7, '*'));
        System.out.println("9 / 3 = " + calculator(9, 3, '/'));
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

    // Task 5
    public static void printMultipleTimes(String text, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(text);
        }
    }

    // Task 6
    public static int findMax(int a, int b, int c) {
        int max = a;
        if (b > max) {
            max = b;
        }
        if (c > max) {
            max = c;
        }
        return max;
    }

    // Task 7
    public static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    // Task 8
    public static void greet(String name) {
        System.out.println("Hello, " + name + "!");
    }
    public static void greet(String name, int age) {
        System.out.println("Hello, " + name + "! You are " + age + " years old.");
    }

    // Task 9
    public static int countVowels(String text) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++;
            }
        }
        return count;
    }

    // Task 10
    public static double calculator(int num1, int num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException("Cannot divide by zero!");
                }
                return (double) num1 / num2;
            default:
                throw new IllegalArgumentException("Invalid operator!");
        }
    }
}
