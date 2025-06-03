/*
 * PLAN
 * -------------------------------------------------------------------------
 *
 * CORE MECHANICS:
 * Central Hub/Main Room:
 *    - Grand chamber of Ancient Pyramid with 3 passages:
 *      1. Chamber of the Sun God
 *      2. Hall of the Moon Priestess
 *      3. Chamber of the Eclipse
 *
 * Location Behavior:
 *    - First visit: Unique description and key piece (Rooms 1-2)
 *    - Repeat visits: Alternate description
 *    - Vault (Room 3): Locked until both key halves are combined
 *
 * VARIABLES:
 *    - boolean visitedSunChamber, visitedMoonHall
 *    - boolean hasSunKeyHalf, hasMoonKeyHalf
 *
 * PROGRAM FLOW:
 *    start loop:
 *        1. Display central hub description and passage options (1/2/3/quit)
 *        2. Player chooses a passage or quits early
 *            3. Room 1: Chamber of the Sun God
 *               - First visit: Obtain sun key half and first message
 *               - Repeat: Message about "already visiting" or something similar
 *            4. Room 2: Hall of the Moon Priestess
 *               - First visit: Obtain moon key half and first message
 *               - Repeat: Message about "already visiting" or something similar
 *            5. Room 3: Chamber of the Eclipse
 *               - Locked: Show locked message
 *               - Unlocked: Show unlocked message, then exit game
 *    end loop
 */
import java.util.Scanner;

public class AdventureGame {
    public static void main(String[] args) {
        boolean visitedSunChamber = false;
        boolean visitedMoonHall = false;
        boolean hasSunKeyHalf = false;
        boolean hasMoonKeyHalf = false;


        Scanner io = new Scanner(System.in);

        System.out.println("Welcome to the Text Adventure Game!");

        while (true) {
            System.out.print("\nYou find yourself in the grand chamber of an ancient pyramid. The are 3 passages." +
                            "\nWhich one do you choose?" +
                            "\n1. Chamber of the Sun God" +
                            "\n2. Hall of the Moon Priestess" +
                            "\n3. Chamber of the Eclipse" +
                            "\n4. Quit game" +
                            "\nYou chose: ");
            String choice = io.nextLine();
            if (choice.equals("3")) {
                if (!hasSunKeyHalf && !hasMoonKeyHalf) {
                    System.out.println("Sorry! You have to unite the Sun and Moon chambers by collecting " +
                                       "the keys first!");
                } else {
                    System.out.println("Unlock message for Eclipse chamber");
                    System.out.println("Thank you for playing the game!");
                    break;
                }
            } else if (choice.equals("1")) {
                if (!visitedSunChamber) {
                    System.out.println("Show first message for Sun chamber");
                    visitedSunChamber = true;
                    hasSunKeyHalf = true;
                } else {
                    System.out.println("Show second message for Sun chamber");
                    break;
                }
            } else if (choice.equals("2")) {
                if (!visitedMoonHall) {
                    System.out.println("Show first message Moon chamber");
                    visitedMoonHall = true;
                    hasMoonKeyHalf = true;
                } else {
                    System.out.println("Show second message for Moon chamber");
                    break;
                }
            } else {
                System.out.println("You've chosen to exit the game.");
                break;
            }
        }
    }
}
