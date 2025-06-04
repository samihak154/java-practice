public class LoanCalculator {
    public static void main(String[] args) {
        double loanAmount = 25000.00; // in dollars
        double annualInterestRate = 5.5; // as a percentage
        int loanTermYears = 5;
        double monthlyPayment;



        // 1. Use Assignment Operators:
        loanAmount += 5000.00;
        annualInterestRate -= 1;
        loanTermYears++;


        // 2. Comparison Operators
        // Check if the loanAmount exceeds $30,000.
        boolean loanExceeds = loanAmount > 30000;
        // Check if the monthlyPayment is more than $500.
        monthlyPayment = (loanAmount*((annualInterestRate/100)/12));
        boolean paymentExceeds = monthlyPayment > 500;


        // 3. Use Logical Operators:
        // Determine if the loan is affordable (monthly payment is below $500 AND
        // term is over 3 years).
        boolean loanAffordable = monthlyPayment < 500 && loanTermYears > 3;


        // Determine if the loan is expensive (monthly payment is above $700 OR
        // interest rate is over 6%).*/
        boolean loanExpensive = monthlyPayment > 700 || annualInterestRate > 6.0;



        // 4. Print all results
        System.out.println("\n=== Loan Summary ===");
        System.out.printf("Loan amount: $%,.0f%n", loanAmount);
        System.out.println("Interest rate: " + annualInterestRate);
        System.out.println("Loan term (in yrs): " + loanTermYears);


        if (loanExceeds) {
            System.out.println("\nThe loan amount exceeds $30,000.");
        } else {
            System.out.println("\nThe loan amount doesn't exceed $30,000.");
        }
        if (paymentExceeds) {
            System.out.println("The monthly payment exceeds $500.");
        } else {
            System.out.println("The monthly payment doesn't exceed $500.");
        }


        if (loanAffordable) {
            System.out.println("The loan is affordable.");
        } else {
            System.out.println("The loan isn't affordable.");
        }
        if (loanExpensive) {
            System.out.println("The loan is expensive.");
        } else {
            System.out.println("The loan isn't expensive.");
        }

    }
}

