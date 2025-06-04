public class ShoppingCartApp {
    enum OrderStatus {
        PENDING,
        PROCESSING,
        SHIPPED,
        DELIVERED
    }

    enum ShippingStatus {
        STANDARD,
        TWO_DAY,
        OVERNIGHT
    }

    public static void main(String[] args) {
        OrderStatus orderStatus = OrderStatus.PROCESSING;
        ShippingStatus shipStatus = ShippingStatus.TWO_DAY;

        System.out.println("Possible Order Status Values:");
        System.out.println(OrderStatus.PENDING);
        System.out.println(OrderStatus.PROCESSING);
        System.out.println(OrderStatus.SHIPPED);
        System.out.println(OrderStatus.DELIVERED);

        System.out.println("\nPossible Shipping Status Values:");
        System.out.println(ShippingStatus.STANDARD);
        System.out.println(ShippingStatus.TWO_DAY);
        System.out.println(ShippingStatus.OVERNIGHT);

        System.out.println("\nOrder Status: " + orderStatus);
        System.out.println("Ship Status: " + shipStatus);
    }
}
