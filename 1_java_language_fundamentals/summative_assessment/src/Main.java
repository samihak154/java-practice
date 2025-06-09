public class Main {
    public static void main(String[] args) {
        LockerService service = new LockerService(10);
        boolean runningMenu = true;

        System.out.println("=== Welcome to Locker Manager! ===");

        while (runningMenu) {
            IO.displayMenu(service.lockersAvailable());
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
