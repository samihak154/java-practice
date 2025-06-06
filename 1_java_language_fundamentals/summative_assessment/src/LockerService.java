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
                // Generate a random pinNumber
                String pinNumber = generateRandomPin();
                // Assign the pinNumber to an empty locker
                lockers[i].setPinNumber(pinNumber);
                int lockerNumber = i + 1;
                lockers[i].setLockerNumber(lockerNumber);
                // Success message
                return new Result(true, "You have rented a locker, your locker number: " + lockerNumber +
                                                        "\nAnd here is your pinNumber: " + pinNumber + "\n");
            }
        }
        return new Result(false, "Sorry, all lockers are full!");
    }

    public Result accessLocker() {
        // access the locker based on user input from methods in IO class

        // Validate locker number range
        int userLocker = IO.getInputLocker();
        if (userLocker < 1 || userLocker > lockers.length) {
            return new Result(false, "Error: Invalid locker number.");
        }
        // get locker and store it in a variable
        Locker locker = lockers[userLocker - 1];
        if (locker.getPinNumber() == null) {
            // if locker isn't rented
            return new Result(false, "Error: Locker hasn't been rented yet.");
        }
        // if user input of pin number matches
        // print true, Access granted or something similar
        String userPin = IO.getInputPin();
        if (locker.getPinNumber().equals(userPin)) {
            return new Result(true, "Access granted! You may open your locker.");
        } else {
            // otherwise print false, Error: pin number is incorrect!
            return new Result(false, "Error: PIN number is incorrect. Please try again.");
        }
    }
//    public Result releaseLocker() {
//        // release the locker given locker number and pin
//    }
}

