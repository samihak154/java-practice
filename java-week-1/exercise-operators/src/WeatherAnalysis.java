public class WeatherAnalysis {
    public static void main(String[] args) {
        double temperatureCelsius = 25.0;
        boolean isRaining = false;
        int windSpeedKmh = 10;

        temperatureCelsius += 5;
        windSpeedKmh =+ 5;

        double temperatureFahrenheit = (temperatureCelsius*(9/5)) + 32;

        boolean highTemp = temperatureFahrenheit > 85.0;
        boolean highWindSpeed = windSpeedKmh > 20;

        boolean goodWeather = !isRaining && temperatureFahrenheit >= 60.0 && temperatureFahrenheit <= 85.0;
        boolean weatherWarning = windSpeedKmh > 30 | temperatureFahrenheit < 0.0;


        // Print Results
        System.out.println("\n=== Weather Summary ===");
        System.out.println("Temperature: " + temperatureCelsius + "°C or " + temperatureFahrenheit + "°F");
        System.out.println("Wind speed: " + windSpeedKmh + "km/h");

        if (highTemp) {
            System.out.println("\nThe temperature in Fahrenheit exceeds 85°F");
        } else {
            System.out.println("\nThe temperature in Fahrenheit doesn't exceed 85°F");
        }
        if (highWindSpeed) {
            System.out.println("The wind speed is greater than 20 km/h..");
        } else {
            System.out.println("The wind speed isn't greater than 20 km/h.");
        }

        if (goodWeather) {
            System.out.println("It's a good day to go outside.");
        } else {
            System.out.println("It's not a good day to go outside.");
        }
        if (weatherWarning) {
            System.out.println("There is a weather warning today.");
        } else {
            System.out.println("There are no weather warnings today.");
        }
    }
}
