public class WeatherAnalysis {
    public static void main(String[] args) {
        double temperatureCelsius = 25.0;
        boolean isRaining = false;
        int windSpeedKmh = 10;

        double temperatureFahrenheit = (temperatureCelsius*(9/5)) + 32;

        temperatureCelsius += 5;
        windSpeedKmh =+ 5;

        boolean highTemp = temperatureFahrenheit > 85.0;
        boolean highWindSpeed = windSpeedKmh > 20;
    }
}
