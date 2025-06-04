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
        String mainMenu = "\nYou find yourself in the grand chamber of an ancient pyramid. The are 3 passages." +
                "\nWhich one do you choose?" +
                "\n1. Chamber of the Sun God" +
                "\n2. Hall of the Moon Priestess" +
                "\n3. Chamber of the Eclipse" +
                "\n4. Quit game" +
                "\nYou choose: ";
        String sunFirstMessage = """
        \nYou enter a circular chamber with walls adorned in intricate golden details.
        A strange glow illuminates half of a ceremonial key on a pedestal. 
        You take the half with you.
        """;
        String sunSecondMessage = "\nYou find yourself back in the golden chamber. You've already got the key.";
        String moonFirstMessage = """
        \nYou step into a hallway lined with beautiful murals of lunar cycles. 
        At the end, a glittering crescent-shaped altar holds half of a ceremonial key.
        You take the half with you.
        """;
        String moonSecondMessage = "\nYou find yourself back in the hallway. You've already got the key.";
        String eclipseMessage = """
        \nThe two key halves combine together to form a key that perfectly matches the keyhole on the stone slab. 
        Once you enter the key, the slab slowly moves aside, revealing a portal to a beautiful lake surrounded by
        mountains and frozen in time. You explore for what feels like weeks, yet return to find not a second has passed,
        your body untouched by age.
        You decide to leave the pyramid with the key, knowing you'll want to return.
        """;
        String eclipseLockedMessage = """
        \nThe chamber is sealed by a stone slab with the engraving: 
        "Only when light and shadow unite shall the path open."
        Right under the engraving there appears to be a distinct keyhole waiting to be filled. 
        You need to look for all parts of the key!
        """;
        String gameOverMessage = "\nGame over. Thank you for playing!";

        Scanner io = new Scanner(System.in);

        System.out.println("Welcome to the Text Adventure Game!\n");

        while (true) {
            System.out.print(mainMenu);
            String choice = io.nextLine();

            if (choice.equals("3")) {
                if (!hasSunKeyHalf && !hasMoonKeyHalf) {
                    System.out.println(eclipseLockedMessage);
                } else {
                    System.out.println(eclipseMessage);
                    System.out.println(gameOverMessage);
                    break;
                }
            } else if (choice.equals("1")) {
                if (!visitedSunChamber) {
                    System.out.println(sunFirstMessage);
                    visitedSunChamber = true;
                    hasSunKeyHalf = true;
                } else {
                    System.out.println(sunSecondMessage);
                }
            } else if (choice.equals("2")) {
                if (!visitedMoonHall) {
                    System.out.println(moonFirstMessage);
                    visitedMoonHall = true;
                    hasMoonKeyHalf = true;
                } else {
                    System.out.println(moonSecondMessage);
                }
            } else if (choice.equals("4")){
                System.out.println("\nYou chose to leave the pyramid, its secrets untouched.");
                break;
            } else {
                System.out.println("\nInvalid choice, please choose 1, 2, 3 or 4");
            }
        }
    }
}
