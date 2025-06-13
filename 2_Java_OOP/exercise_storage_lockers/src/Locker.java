public class Locker {
    protected String lockerID;
    protected boolean isOccupied;
    protected String contents;

    //Constructor
    public Locker(String lockerID) {
        this.lockerID = lockerID;
        this.isOccupied = false;
        this.contents = "";
    }

    public String getLockerID() {
        return lockerID;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public String getContents() {
        return contents;
    }

    public void storeItem(String item) {
        contents = item;
        isOccupied = true;
    }

    public void removeItem() {
        contents = "";
        isOccupied = false;
    }

    @Override
    public String toString() {
        return String.format("Locker ID: " + lockerID + ", Occupied: " + isOccupied +  ", Contents: " + contents);
    }
}
