// App workflow

public class Main {
    public static void main(String[] args) {
        // 1. Welcome user
        // 2. Display menu
        // 3. Get menu choice
        // 4. Based on choice do task, if choice is:
            // a) Rent:
                // Get next available locker
                // Get random pin number
                // Store locker number and pin number in arrays
                // Show thank you message and instructions
            // b) Access:
                // Prompt user for locker number and PIN
                // Validate input against stored data
                // Grant access if match is found, else show error
            // c) Release:
                // Prompt for locker number and PIN
                // Confirm action: “Are you sure?” (Yes/No)
                // Clear data from array if confirmed
                // Show error for incorrect PIN or locker
            // d) Quit:
                // User can quit main menu anytime by pressing any key
                // other than the choices (1, 2, or 3)
        // Loop back to main menu after doing tasks and showing messages


        LockerService service = new LockerService(10);
        boolean runningMenu = true;

        System.out.println("=== Welcome to Locker Manager! ===");

        while (runningMenu) {
            IO.displayMenu();
            String choice = IO.getMenuChoice();

            switch (choice) {
                case "1":
                    Result rentResult = service.rentLocker();
                    IO.displayResult(rentResult);
                    break;
                case "2":
                    Result accessResult = service.accessLocker();
                    IO.displayResult(accessResult);
                    break;
                case "3":
                    Result releaseResult = service.releaseLocker();
                    IO.displayResult(releaseResult);
                    break;
                default:
                    System.out.println("Exiting locker manager.\n");
                    runningMenu = false;
                    break;
            }
        }
    }
}
