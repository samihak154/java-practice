/*
Attributes
● lockerId (String): A unique identifier for the locker.
● isOccupied (boolean): Indicates whether the locker is currently in use.
● contents (String): Description of the contents in the locker (default is an empty
string).

Methods
● Locker(String lockerId): Constructor that initializes the locker with a given ID, sets
isOccupied to false, and contents to an empty string.
● void storeItem(String item): Stores an item in the locker, sets isOccupied to true,
and updates the contents.
● void removeItem(): Empties the locker, sets isOccupied to false, and clears the
contents.
● String toString(): Returns a string representation of the locker, including its ID,
occupancy status, and contents.
*/

public class Locker {
    String lockerID;
    boolean isOccupied;
    String contents;

    //Constructor
    public Locker(String lockerID) {
        this.lockerID = lockerID;
        this.isOccupied = false;
        this.contents = "";
    }
}
