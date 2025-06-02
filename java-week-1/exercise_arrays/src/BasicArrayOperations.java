public class BasicArrayOperations {
    public static void main(String[] args) {
        // 1.  Declare & Assign an Array
        String[] cities = {"Austin", "San Antonio", "Houston", "Dallas", "El Paso"};
        for (int i = 0; i < cities.length; i++) {
            System.out.println(cities[i]);
        }
        // 2. Access & Modify Elements
        cities[2] = "Fort Worth";
        System.out.println("\nUpdated Cities:");
        System.out.println("---------------");
        for (int i = 0; i < cities.length; i++) {
            System.out.println(cities[i]);
        }
        // 3. Find the Length of an Array
        System.out.println("\nTotal cities: " + cities.length);
    }
}
