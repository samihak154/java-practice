import java.util.List;
import java.util.Scanner;

public class TerminalUtils {
    private static Scanner scanner = new Scanner(System.in);

    public static void displayMenu() {
        String menu = "=== Media List Application ===\n" +
                            "1. Add Media\n" +
                            "2. Remove Media\n" +
                            "3. Play Media\n" +
                            "4. List All Media\n" +
                            "5. Quit\n" +
                            "==============================\n";
        System.out.print(menu);
    }

    public static String getMenuChoice() {
        String menuChoice = "";
        while (true) {
            System.out.print("Choose an option: ");
            menuChoice = scanner.nextLine();
            if (menuChoice.matches("[1-5]")) {
                break;
            }
            System.out.print("Please choose a valid menu option!\n");
        }
        return menuChoice;
    }

    public static String getString(String prompt) {
        System.out.print(prompt);
        String stringInput = scanner.nextLine();
        return stringInput;
    }

    public static int getInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer. parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            }
        }
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public static void displayMediaList(List<Media> mediaList) {
        for (int i = 0; i < mediaList.size(); i++) {
            Media media = mediaList.get(i);
            System.out.println((i+1) + ". " + media.getClass().getSimpleName() + ": " + media.getName());
            System.out.println("   " + media.getDescription());
        }
        System.out.println("\n");
    }
}

