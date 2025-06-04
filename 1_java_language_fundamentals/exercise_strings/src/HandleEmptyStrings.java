public class HandleEmptyStrings {
    public static void main(String[] args) {
        // 1. Declare a string variable but set it to null
        String myString = null;
        // 2. Use an if statement to check if the string is null before printing its length
        if (myString == null) {
            System.out.println("The string is null, cannot compute length.");
        } else {
            System.out.println("Length of my string: " + myString.length());
        }
        // 3. Declare another string as an empty string ("") and print its length
        String emptyString = "";
        System.out.println("Empty string length: " + emptyString.length());
    }
}
