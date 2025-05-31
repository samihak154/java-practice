public class ExtractStrings {
    public static void main(String[] args) {
        // 1. Declare a string
        String sentence = "Learning Java is fun!";

        // 2. Extract parts of the string
        String firstWord = sentence.substring(0, 8);
        String secondWord = sentence.substring(9, 13);
        String lastWord = sentence.substring(17, 21);

        // 3. Print
        System.out.println("First word: " + firstWord);
        System.out.println("Second word: " + secondWord);
        System.out.println("Last word: " + lastWord);
    }
}












