/* 1. Welcome the player and explain the scenario
    - Print an introduction message
    - Ask if they want to enter the cave
    - Get user input (yes/no)
 2. If they enter, present two path choices (left or right)
    - Use an if-else statement to process the decision
    - If they go left, introduce an obstacle or creature
    - If they go right, introduce a treasure room
 3. If they go left, ask if they want to fight or flee
    - Use a nested if statement to handle the fight scenario
    - If they fight, print a success/failure message
    - If they flee, print a message that they barely escaped
 4. If they go right, present a switch menu with artifact choices
    - Display three options (e.g., a gem, a key, a book)
    - Use a switch statement to determine the outcome of their choice
    - Each case should have a unique consequence
 5. Handle invalid inputs with a default response
    - If the user enters an unexpected value, print an error message
    - Optionally, loop back and ask them to re-enter their choice
 6. End the game with a final message
    - Thank the player for playing
    - Print their fate based on the decisions they made */
import java.util.Scanner;
public class AdventureGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Welcome the player and explain the scenario
        System.out.println("Welcome to the Cave Adventure Game!");
        System.out.println("-----------------------------------");
        System.out.println("You can choose to go left or right, the game will have different results " +
                "\ndepending on your choice.\n\n");
        // First decision with retry if choice is invalid
        String enterGame;
        while (true) {
            System.out.println("\nDo you want to enter the cave? (yes/no): ");
            enterGame = scanner.nextLine().toLowerCase();
            if (enterGame.equals("yes") || enterGame.equals("no")) {
                break;
            }
            System.out.println("Invalid choice! Please enter 'yes' or 'no'.");
        }
        // Continue game if player chooses "yes"
        if (enterGame.equals("yes")) {
            System.out.println("\nYou step into the dark cave, your torch flickering...");
            System.out.println("The path splits ahead.");
            // Path choice with retry if choice is invalid
            String enterPath;
            while (true) {
                System.out.println("Which way do you choose? (left/right): ");
                enterPath = scanner.nextLine().toLowerCase();
                if (enterPath.equals("left") || enterPath.equals("right")) {
                    break;
                }
                System.out.println("Invalid path! Please choose 'left' or 'right'.");
            }
            // Left path
            if (enterPath.equals("left")) {
                System.out.println("\nYou choose the left path and hear strange growling...it looks like " +
                        "\nthere is a creature blocking the way");
                // Fight or flee with retry if choice is invalid
                String leftChoice;
                while (true) {
                    System.out.println("What would you like to do? (fight/flee): ");
                    leftChoice = scanner.nextLine().toLowerCase();
                    if (leftChoice.equals("fight") || leftChoice.equals("flee")) {
                        break;
                    }
                    System.out.println("Invalid choice! Please choose 'fight' or 'flee'.");
                }
                if (leftChoice.equals("fight")) {
                    System.out.println("\nYou bravely attack the creature!");
                    System.out.println("You win the fight and find a small treasure on its remains!");
                    System.out.println("\nENDING: You leave the cave victorious and richer!");
                } else {
                    System.out.println("\nYou decide to run back the way you came!");
                    System.out.println("The creature was right on your tail but you barely managed to escape!");
                    System.out.println("\nENDING: You escape unharmed, but with empty pockets.");
                }
            } else { // Right path
                System.out.println("\nYou choose the right path and find a treasure room!");
                System.out.println("Before you are three artifacts:");
                System.out.println("1. A glowing gem");
                System.out.println("2. A golden key");
                System.out.println("3. An ancient book");
                // Artifact choice with retry if choice is invalid
                String artifactChoice;
                while (true) {
                    System.out.println("Which do you take? (1/2/3): ");
                    artifactChoice = scanner.nextLine();
                    if (artifactChoice.equals("1") || artifactChoice.equals("2") || artifactChoice.equals("3")) {
                        break;
                    }
                    System.out.println("Invalid choice! Please enter 1, 2, or 3.");
                }
                switch (artifactChoice) {
                    case "1":
                        System.out.println("\nThe gem bonds to your palm, filling you with incredible power!" +
                                "\nYou can suddenly see all the cave's hidden treasures glowing through " +
                                "\nthe walls. The knowledge of this place flows into your mind - its history, " +
                                "\nits secrets.");
                        System.out.println("\nENDING: You become the Cave's chosen champion, wealthy beyond measure " +
                                "\nand gifted with supernatural abilities.");
                        break;
                    case "2":
                        System.out.println("\nThe key vibrates in your hand, pulling you toward a previously " +
                                "\ninvisible door. Beyond it lies a vault containing enough gold to live comfortably " +
                                "\nfor the rest of your life. However, as you collect your riches, the door seals " +
                                "\nshut behind you.");
                        System.out.println("\nENDING: You escape with fabulous wealth, but the cave's greater " +
                                "\nmysteries remain forever closed to you.");
                        break;
                    case "3":
                        System.out.println("\nThe book opens to a page containing your name... written centuries ago. " +
                                "\nAs you read, you gain profound wisdom but lose all memory of your life " +
                                "\nbefore this moment. The cave becomes your home as you dedicate yourself to " +
                                "\nstudying its lore.");
                        System.out.println("\nENDING: You become the cave's immortal librarian - all-knowing but alone, " +
                                "\nforever adding to the endless book.");
                        break;
                }
            }
            System.out.println("\nThank you for playing the Cave Adventure Game!");
        } else { // End game if player chooses "no"
            System.out.println("\nYou decide not to enter the cave.");
            System.out.println("\nENDING: As you walk away, you wonder what treasures you might have found...");
        }
    }
}
