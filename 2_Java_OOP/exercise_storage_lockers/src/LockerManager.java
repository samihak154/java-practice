import java.util.ArrayList;

public class LockerManager {
    private ArrayList<Locker> lockers;

    public LockerManager() {
        this.lockers = new ArrayList<>();
    }

    public void addLocker(String lockerID) {

        lockers.add(new Locker(lockerID));
    }

    public Locker getLocker(String lockerID) {
        for (Locker locker : lockers) {
            if (locker.getLockerID().equals(lockerID)) {
                return locker;
            }
        }
        return null;
    }

    public void removeLocker(String lockerID) {
        Locker toRemove = getLocker(lockerID);  // Reuse existing method
        if (toRemove != null) {
            lockers.remove(toRemove);
        }
    }

    public void storeItemInLocker(String lockerID) {
        Locker locker = getLocker(lockerID);
        if (locker == null) {
            System.out.println("Error: Locker not found.");
            return;
        }
        try {
            String item = IO.getInputItem();
            locker.storeItem(item);
            System.out.println("Item stored successfully!");
        } catch (IllegalStateException e) {
            System.out.println("Error: Locker is already occupied.");
        }
    }

    public void removeItemFromLocker(String lockerID) {
        Locker locker = getLocker(lockerID);
        if (locker == null) {
            System.out.println("Error: Locker not found.");
            return;
        }
        try {
            locker.removeItem();
            System.out.println("Item retrieved from " + locker.getLockerID());
        } catch (IllegalStateException e) {
            System.out.println("Error: No items were found.");
        }
    }

    public void displayAllLockers() {
        for (Locker locker : lockers) {
            String displayLockers = locker.toString();
            System.out.println(displayLockers);
        }
    }

    public boolean isEmpty() {
        return lockers.isEmpty();  // Delegates to the ArrayList's method
    }
}
