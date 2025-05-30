public class Car {
    private String brand;
    private String model;
    private int year;

    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public void displayInfo(String prefix) {
        System.out.println(prefix + brand + " " + model + " (" + year + ")");
    }
}
