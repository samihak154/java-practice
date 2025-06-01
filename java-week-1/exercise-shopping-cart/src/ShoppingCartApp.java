public class ShoppingCartApp {
    public static void main(String[] args) {
        //1. Assign variables
        int productId = 1;
        int productCategory = 2;
        double productCost = 2.56;
        double productPrice = 4.99;
        int productQuantity = 78;
        

        System.out.println("Welcome to the shopping cart app!");
        System.out.println("---------------------------------");

        //2. Calculate cost and show results
        double totalCost = productCost * productQuantity;
        System.out.printf("\nTotal cost: $%,.2f%n", totalCost);

        double profitPerProduct = productPrice - productCost;
        System.out.printf("Profit per product: $%,.2f%n", profitPerProduct);

        double totalProfit = profitPerProduct * productQuantity;
        System.out.printf("Total potential profit: $%,.2f%n", totalProfit);
    }
}
