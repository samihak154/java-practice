/*PART 3: SPLITTING STRINGS
Task: Split a String and Process its Parts
1. Declare a string:
    a. String csvData = "apple,banana,grape,orange";
2. Use split() to break it into an array.
3. Loop through the array and print each item.

Example Output:
Fruit 1: apple
Fruit 2: banana
Fruit 3: grape
Fruit 4: orange*/
public class SplitStrings {
    public static void main(String[] args) {
        // 1. Declare a string
        String csvData = "apple,banana,grape,orange";

        // 2. Use split() to break it into an array
        String[] fruits = csvData.split(",");
    }
}

