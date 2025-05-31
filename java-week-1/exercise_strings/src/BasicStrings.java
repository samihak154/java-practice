/*Instructions
This exercise will help you practice and reinforce various string operations, including
concatenation, length, substring, indexOf, split, charAt, and replace.

PART 1: BASIC STRING OPERATIONS
Task: Declare, Assign, and Manipulate Strings
1. Declare and assign the following string variables:
    ○ String firstName = "Harry";
    ○ String lastName = "Potter";
    ○ String fullName (concatenate firstName and lastName with a space).
2. Print fullName.
3. Find the length of fullName and print it.
4. Extract and print the first character using charAt(0).
5. Find the position of the letter 'r' in fullName using indexOf().

Example Output:
Full name: Harry Potter
Length: 12
First character: H
Index of 'r': 2*/
public class BasicStrings {
    public static void main(String[] args) {
        //1. Declare and assign variables
        String firstName = "Harry";
        String lastName = "Potter";
        String fullName = firstName + " " + lastName;

        //2. Print full name
        System.out.println("Full name: " + fullName);

    }
}
