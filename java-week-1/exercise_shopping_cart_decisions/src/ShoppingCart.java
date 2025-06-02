public class ShoppingCart {
    private final Product product;
    private final int quantity;
    private final boolean isTaxExempt;
    private final String shippingType;
    private final String promoCode;

    // Constructor
    public ShoppingCart(Product product, int quantity, boolean isTaxExempt,
                        String shippingType, String promoCode) {
        this.product = product;
        this.quantity = quantity;
        this.isTaxExempt = isTaxExempt;
        this.shippingType = shippingType;
        this.promoCode = promoCode;
    }

    // Getters
    public boolean isTaxExempt() {
        return isTaxExempt;
    }

    public String getShippingType() {
        return shippingType;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPromoCode() {
        return promoCode;
    }

    // Methods for receipt data
    public double getSubtotal() {
        return product.getCost() * quantity;
    }

    public double getDiscount() {
        double subtotal = getSubtotal();
        if (subtotal > 500) return subtotal * 0.10;
        else if (subtotal > 100) return subtotal * 0.05;
        return 0;
    }

    public double getTaxAmount() {
        return isTaxExempt ? 0 : (getSubtotal() - getDiscount()) * 0.07;
    }

    public double getShippingCost() {
        if (shippingType.equalsIgnoreCase("2day")) return 5.00;
        else if (shippingType.equalsIgnoreCase("overnight")) return 10.00;
        else return promoCode.equalsIgnoreCase("FREE") ? 0 : 2.00;
    }

    public double getTotal() {
        return getSubtotal() - getDiscount() + getTaxAmount() + getShippingCost();
    }

    public double getProfitPerProduct() {
        return product.getPrice() - product.getCost();
    }

    public double getTotalPotentialProfit() {
        return getProfitPerProduct() * quantity;
    }
}
