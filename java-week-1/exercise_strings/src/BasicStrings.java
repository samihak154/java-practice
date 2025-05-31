public class BasicStrings {
    public static void main(String[] args) {
        //1. Declare and assign variables
        String firstName = "Harry";
        String lastName = "Potter";
        String fullName = firstName + " " + lastName;

        //2. Print full name.
        System.out.println("Full name: " + fullName);

        // 3. Find the length of fullName and print it.
        System.out.println("Length: " + fullName.length());

        // 4. Extract and print the first character using charAt(0).
        System.out.println("First character: " + fullName.charAt(0));

        // 5. Find the position of the letter 'r' in fullName using indexOf().
        System.out.println("Index of 'r': " + fullName.indexOf("r"));
    }
}
