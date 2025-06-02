import java.util.Random;

public class AdvancedChallenges {
    public static void main(String[] args) {
        // 10. Count Occurrences of a Value
        Random number = new Random();
        int[] randomNumbers = new int[10];
        int targetNumber = 3;
        int count = 0;

        System.out.println("Random Number Array:");
        for (int i = 0; i < randomNumbers.length; i++) {
            randomNumbers[i] = number.nextInt(5) + 1;
            System.out.println(randomNumbers[i]);
        }
        for (int num : randomNumbers) {
            if (num == targetNumber) {
                count ++;
            }
        }
        System.out.println("\nAmount of times 3 appears: " + count);
        System.out.println("----------------------------\n");
        
        // 11. Shift Elements in an Array
        int[] numbers = {1, 2, 3, 4, 5};
        int firstElement = numbers[0];

        for (int i = 0; i < numbers.length - 1; i++) {
            numbers[i] = numbers[i + 1];
        }
        numbers[numbers.length - 1] = firstElement;

        // 12. Check for Duplicates
        String[] names = {"SpongeBob", "Patrick", "Patrick", "Squidward", "Gary", "Sandy"};
        boolean duplicateFound = false;

        System.out.println("Student names:");
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }
        for (int i = 0; i < names.length; i++) {
            for (int j = i + 1; j < names.length; j++) {
                if (names[i].equals(names[j])) {
                    duplicateFound = true;
                    break;
                }
            }
        }
        if (duplicateFound) {
            System.out.println("\nDuplicates found!");
        } else {
            System.out.println("\nDuplicates not found.");
        }
    }
}
