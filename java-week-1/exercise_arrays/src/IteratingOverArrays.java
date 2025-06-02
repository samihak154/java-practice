import java.util.Scanner;

public class IteratingOverArrays {
    public static void main(String[] args) {
        String[] cities = {"Austin", "San Antonio", "Houston", "Dallas", "El Paso"};

        // 4. Print Array Elements Using a Loop
        for (int i = 0; i < cities.length; i++) {
            System.out.println(cities[i]);
        }
        // 5. Reverse the Array
        System.out.println("\nReversed Array:");
        for (int i = cities.length - 1; i >= 0; i--) {
            System.out.println(cities[i]);
        }
        // 6. Find a Specific Element
        Scanner input = new Scanner(System.in);
        System.out.println("\nEnter a city name: ");
        String inputCity = input.nextLine();
        boolean cityFound = false;
        for (String city : cities) {
            if (city.equalsIgnoreCase(inputCity)) {
                cityFound = true;
                break;
            }
        }
        if (cityFound) {
            System.out.println("City is in the array.");
        } else {
            System.out.println("Sorry, city is not in the array!");
        }
    }
}
