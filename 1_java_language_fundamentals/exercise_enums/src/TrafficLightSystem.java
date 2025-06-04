public class TrafficLightSystem {
    enum TrafficLight {
        RED,
        YELLOW,
        GREEN
    }

    public static void main(String[] args) {
        // 2. Store all values in an array
        TrafficLight[] lights = TrafficLight.values();

        int predefinedIndex = 1;
        TrafficLight currentSignal = lights[predefinedIndex];
        System.out.println("Traffic light signal: " + currentSignal);
    }
}
