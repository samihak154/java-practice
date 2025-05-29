public class StadiumSeating {
    // 1. Create the enum
    enum SeatSection {
        GENERAL,
        PREMIUM,
        VIP
    }

    public static void main(String[] args) {
        // 2. Print each section's ordinal value
        System.out.println("GENERAL is assigned value: " + SeatSection.GENERAL.ordinal());
        System.out.println("PREMIUM is assigned value: " + SeatSection.PREMIUM.ordinal());
        System.out.println("VIP is assigned value: " + SeatSection.VIP.ordinal());
    }
}
