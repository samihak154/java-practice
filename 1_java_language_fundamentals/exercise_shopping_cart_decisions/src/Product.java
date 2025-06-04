public class Product {
    private int id;
    private int category;
    private double cost;
    private double price;
    private String description;

    public Product(int id, int category, double cost, double price, String description) {
        this.id = id;
        this.category = category;
        this.cost = cost;
        this.price = price;
        this.description = description;
    }

    public double getCost() { return cost; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
}
