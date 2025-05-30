public class Wailist {
    private Reservation[] reservations;
    private int currentIndex = 0;

    public Waitlist(int defaultSize) {
        reservations = new Reservation[defaultSize];
    }

    public void add(Reservation r) {
        reservations[currentIndex] = r;
        currentIndex++;

        if (currentIndex >= reservations.length) {
            growWaitlist();
        }
    }

    private void growWaitlist() {
        Reservation[] temp = new Reservation[reservations.length * 2];

        for(int i = 0; i < reservations.length; i++) {
            temp[i] = reservations[i];
        }

        reservations = temp;
    }

    public void printList() {
        for(int i = 0; i < reservations.length; i++) {
            if (reservations[i] != null) {
                reservations[i].printInfo();
            }
        }
    }

    // 3 reason to create a class
    // 1. Workflow (main)
    // 2. Tasks (Waitlist, add, remove, callnext, etc.)
    // 3. Data Bucket (Reservation)
}
