public class ShoppingCartApp {
    public static void main(String[] args) {
        //1. Assign variables
        int productId = 1;
        int productCategory = 2;
        double productCost = 2.56;
        double productPrice = 4.99;
        int productQuantity = 78;
        String businessName = "Pumba's Pet Supplies";
        String businessContactInfo = "123-456-7890";
        String productDescription = "Squeezable Treat Variety Pack";

        //2. Calculate cost and show results
        double totalCost = productCost * productQuantity;
        double profitPerProduct = productPrice - productCost;
        double totalProfit = profitPerProduct * productQuantity;

        //3. Print
        System.out.println("Welcome to the shopping cart app!");
        System.out.println("---------------------------------");
        System.out.println("Product: " + productDescription);
        System.out.printf("Total cost: $%,.2f%n", totalCost);
        System.out.printf("Profit per product: $%,.2f%n", profitPerProduct);
        System.out.printf("Total potential profit: $%,.2f%n", totalProfit);
        System.out.println("\n---------------------------------");
        System.out.println("Business Info");
        System.out.println("Business name: " + businessName);
        System.out.println("Contact Number: " + businessContactInfo);


    }
}
