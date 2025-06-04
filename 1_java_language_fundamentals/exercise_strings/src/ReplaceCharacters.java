public class ReplaceCharacters {
    public static void main(String[] args) {
        // 1. Declare a string
        String sentence = "The quick brown fox.";
        // 2. Replace "quick" with "slow", and replace all spaces with underscores (_)
        String newSentence = sentence.replace("quick","slow");
        String finalSentence = newSentence.replace(" ","_");
        // 3. Print
        System.out.println("Modified sentence: " + finalSentence);
    }
}
