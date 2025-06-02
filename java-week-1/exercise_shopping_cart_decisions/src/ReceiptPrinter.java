public class ReceiptPrinter {
    public static void printReceipt(ShoppingCart cart, Product product) {
        String businessName = "Pumba's Pet Supplies";
        String contactNumber = "123-456-7890";

        System.out.println("\n---------------------------------------");
        System.out.println("Order Details");
        System.out.println("---------------------------------------");
        System.out.println("Product: " + product.getDescription());
        System.out.println("Tax-exempt: " + (cart.isTaxExempt() ? "yes" : "no"));
        System.out.println("Shipping: " + cart.getShippingType());
        System.out.println("Order quantity: " + cart.getQuantity());
        System.out.println("Promo code: " + cart.getPromoCode());
        System.out.println("---------------------------------------");
        System.out.printf("Subtotal: $%,.2f%n", cart.getSubtotal());
        System.out.printf("Discount applied: $%,.2f%n", cart.getDiscount());
        System.out.printf("Tax amount: $%,.2f%n", cart.getTaxAmount());
        System.out.printf("Shipping cost: $%,.2f%n", cart.getShippingCost());
        System.out.printf("Total cost: $%,.2f%n", cart.getTotal());
        System.out.printf("Profit per product: $%,.2f%n", cart.getProfitPerProduct());
        System.out.printf("Total potential profit: $%,.2f%n", cart.getTotalPotentialProfit());
        System.out.println("---------------------------------------");
        System.out.println("Business Info");
        System.out.println("---------------------------------------");
        System.out.println("Business name: " + businessName);
        System.out.println("Contact Number: " + contactNumber);
    }
}
