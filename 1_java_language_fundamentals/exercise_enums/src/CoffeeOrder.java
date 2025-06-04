public class CoffeeOrder {
    // 1. Create the enum
    enum CoffeeSize {
        SMALL,
        MEDIUM,
        LARGE
    }

    public static void main(String[] args) {
        // 2. Declare and initialize a CoffeeSize variable
        CoffeeSize myCoffee = CoffeeSize.MEDIUM;

        // 3. Print the selected size
        System.out.println("Selected coffee size: " + myCoffee);
    }
}
