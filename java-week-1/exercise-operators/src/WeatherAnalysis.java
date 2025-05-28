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

        boolean goodWeather = !isRaining && temperatureFahrenheit >= 60.0 && temperatureFahrenheit <= 85.0;
        boolean weatherWarning = windSpeedKmh > 30 | temperatureFahrenheit <= 0.0;

    }
}
