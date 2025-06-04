public class SplitStrings {
    public static void main(String[] args) {
        // 1. Declare a string
        String csvData = "apple,banana,grape,orange";

        // 2. Use split() to break it into an array
        String[] fruits = csvData.split(",");

        //3. Loop through the array and print each item
        for (int i=0; i < fruits.length; i++) {
            System.out.println("Fruit " + (i+1) + ": " + fruits[i]);
        }
    }
}

