public class BasicArrayOperations {
    public static void main(String[] args) {
        // 1.  Declare & Assign an Array
        String[] cities = {"Austin", "San Antonio", "Houston", "Dallas", "El Paso"};
        System.out.println("City 1: " + cities[0]);
        System.out.println("City 1: " + cities[1]);
        System.out.println("City 1: " + cities[2]);
        System.out.println("City 1: " + cities[3]);
        System.out.println("City 1: " + cities[4]);
        // 2. Access & Modify Elements
        cities[2] = "Fort Worth";
        System.out.println("\nUpdated Cities:");
        System.out.println("---------------");
        System.out.println("City 1: " + cities[0]);
        System.out.println("City 1: " + cities[1]);
        System.out.println("City 1: " + cities[2]);
        System.out.println("City 1: " + cities[3]);
        System.out.println("City 1: " + cities[4]);
        // 3. Find the Length of an Array
        System.out.println("\nTotal cities: " + cities.length);
    }
}
