public class Main {
    public static void main(String[] args) {
        LockerManager manager = new LockerManager();
        String inputLockerID;

        System.out.println("======================================" +
                "\nWelcome to the Storage Locker Manager!\n");
        IO.displayMenu();

        while (true) {
            System.out.print("Choose an option: ");
            String choice = IO.getMenuChoice();

            if (choice.equals("1")) {
                inputLockerID = IO.getInputLockerID();
                manager.addLocker(inputLockerID);
                System.out.print("\n");
            } else if (choice.equals("2")) {
                inputLockerID = IO.getInputLockerID();
                manager.removeLocker(inputLockerID);
                System.out.print("\n");
            } else if (choice.equals("3")) {
                inputLockerID = IO.getInputLockerID();
                manager.storeItemInLocker(inputLockerID);
                System.out.print("\n");
            } else if (choice.equals("4")){
                inputLockerID = IO.getInputLockerID();
                manager.removeItemFromLocker(inputLockerID);
                System.out.print("\n");
            } else if (choice.equals("5")) {
                if (!manager.isEmpty()) {
                    manager.displayAllLockers();
                    System.out.print("\n");
                } else {
                    System.out.print("No lockers in use!\n");
                }
            } else {
                System.out.print("Exiting program.\n");
                break;
            }
        }
    }
}
