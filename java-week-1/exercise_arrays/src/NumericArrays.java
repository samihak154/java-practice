public class NumericArrays {
    public static void main(String[] args) {
        // 7. Sum of Numbers in an Array
        double[] testScores = {89.7, 96.0, 93.5, 88.0, 82.9};
        double sum = 0.0;
        for (int i = 0; i < testScores.length; i++) {
            sum += testScores[i];
        }
        System.out.println("Sum of all test scores: " + sum);
        // 8. Find the Maximum & Minimum
        double highest = testScores[0];
        double lowest = testScores[0];
        for (int i = 0; i < testScores.length; i++) {
            if(testScores[i] > highest) {
                highest = testScores[i];
            }
        }
        for (int i = 0; i < testScores.length; i++) {
            if(testScores[i] < lowest) {
                lowest = testScores[i];
            }
        }
        System.out.println("Highest score: " + highest);
        System.out.println("Lowest score: " + lowest);
        // 9. Calculate the Average
        double average = sum / testScores.length;
        System.out.printf("Average score: %.1f%n", average);

    }
}
