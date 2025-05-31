/*PART 2: EXTRACTING PARTS OF STRING
Task: Use substring() to Extract Words
1. Declare a string:
a. String sentence = "Learning Java is fun!";
2. Extract and print:
    ○ "Learning" (characters 0 to 8).
    ○ "Java" (characters 9 to 13).
    ○ "fun!" (last word using a single argument with substring()).

Example Output:
First word: Learning
Second word: Java
Last word: fun!*/
public class ExtractStrings {
    public static void main(String[] args) {
        // 1. Declare a string
        String sentence = "Learning Java is fun!";

        // 2. Extract parts of the string
        String firstWord = sentence.substring(0, 8);
        String secondWord = sentence.substring(9, 13);
        String lastWord = sentence.substring(17, 21);
    }
}












