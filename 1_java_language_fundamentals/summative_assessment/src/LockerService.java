// In charge of managing the lockers: rent, access, release
import java.util.Random;

public class LockerService {
    private Locker[] lockers;
    private Locker validatedLocker;

    //Constructor
    public LockerService (int numberofLockers) {
        lockers = new Locker[numberofLockers];

        for (int i = 0; i < lockers.length; i++) {
            lockers[i] = new Locker();
        }
    }

    private String generateRandomPin() {
        Random rand = new Random();
        return String.format("%04d", rand.nextInt(10000));
    }

    private int findLockerIndex() {
        for (int i = 0; i < lockers.length; i++) {
            if (lockers[i].getPinNumber() == null) {
                return i;
            }
        }
        return -1;
    }

    public boolean lockersAvailable() {
        return findLockerIndex() != -1;
    }

    public Result getValidation() {
        int userLocker = IO.getInputLocker();
        if (userLocker < 1 || userLocker > lockers.length) {
            return new Result(false, "Error: Invalid locker number.\n");
        }
        Locker locker = lockers[userLocker - 1];
        if (locker.getPinNumber() == null) {
            return new Result(false, "Error: Locker hasn't been rented yet.\n");
        }
        String userPin = IO.getInputPin();
        if (!locker.getPinNumber().equals(userPin)) {
            return new Result(false, "Error: PIN number is incorrect. Please try again.\n");
        }
        validatedLocker = locker;
        return new Result(true, "");
    }

    public Result rentLocker() {
        int availableIndex = findLockerIndex();
        if (availableIndex != -1) {
            String pinNumber = generateRandomPin();
            lockers[availableIndex].setPinNumber(pinNumber);
            int lockerNumber = availableIndex + 1;
            lockers[availableIndex].setLockerNumber(lockerNumber);
            return new Result(true, "You have rented a locker, your locker number: " + lockerNumber +
                    "\nAnd here is your pinNumber: " + pinNumber + "\n");
        }
        return new Result(false, "Sorry, all lockers are full!\n");
    }

    public Result accessLocker() {
        Result validation = getValidation();
        if (!validation.getSuccess()) {
            return validation;
        }
        return new Result(true, "Access granted! You may open your locker.\n");
    }

    public Result releaseLocker() {
        Result validation = getValidation();
        if (!validation.getSuccess()) {
            return validation;
        } else if (!IO.getConfirmation()) {
            return new Result(false, "Release cancelled.\n");
        } else {
            validatedLocker.setPinNumber(null);
            return new Result(true, "Your locker has been released!\n");
        }
    }

}


