// In charge of managing the lockers: rent, access, release
import java.util.Random;

public class LockerService {
    private Locker[] lockers;

    //Constructor
    public LockerService (int numberofLockers) {
        lockers = new Locker[numberofLockers];

        for (int i = 0; i < lockers.length; i++) {
            lockers[i] = new Locker();
        }
    }

    private String generateRandomPin() {
        Random rand = new Random();
        return String.format("%04d", rand.nextInt(10000));  // 4-digit PIN with leading zeros
    }

    public Result rentLocker() {
        // find an available locker, if pin number is null then locker is available
        for (int i = 0; i < lockers.length; i++) {
            if (lockers[i].getPinNumber() == null) {
                // Generate a random pin
                String pin = generateRandomPin();
                // Assign the pin to an empty locker
                lockers[i].setPinNumber(pin);
                int lockerNumber = i + 1;
                lockers[i].setLockerNumber(lockerNumber);
                // Success message
                return new Result(true, "You have rented a locker, your locker number: " + lockerNumber +
                                                        "\nAnd here is your pin: " + pin + "\n");
            }
        }
        return new Result(false, "Sorry, all lockers are full!");
    }

    // for testing rentLocker()
    public void printLockers() {
        for (int i = 0; i < lockers.length; i++) {
            Locker locker = lockers[i];
            System.out.println(
                    "Array Index: " + i +
                            " | Locker Number: " + locker.getLockerNumber() +
                            " | PIN: " + locker.getPinNumber()
            );
        }
    }
}

