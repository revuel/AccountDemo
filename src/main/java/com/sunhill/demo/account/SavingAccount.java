package com.sunhill.demo.account;

import java.math.BigDecimal;

public class SavingAccount extends Account implements AccountInterface {

    // Atributes (Big Decimal are recommended to avoid double or float pitfalls)
    private BigDecimal balance = BigDecimal.valueOf(0); // Initialize attributes to avoid null pointers
    private BigDecimal interestRate = BigDecimal.valueOf(0);

    private static final String INSUFFICIENT_FUNDS = "WARNING: Insufficient funds for withdrawal. Operation cancelled.";
    private static final String NEGATIVE_INTEREST = "WARNING: Interest of equal or under 0% makes no sense";

    // Constructor
    public SavingAccount(String owner) {
        super.setOwner(owner); // Force to set up owner, as it works as Account identifier in this demo
    }

    // Getters & Setters
    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        // To avoid "negative" interest rates
        if (checkPositive(interestRate) && (interestRate.compareTo(BigDecimal.valueOf(0)) > 0)) {
            this.interestRate = interestRate;
        } else {
            System.out.println(NEGATIVE_INTEREST);
        }
    }

    public BigDecimal getBalance() {
        return balance;
    }

    private void setBalance(BigDecimal balance) {
        this.balance = balance; // Private method, to encapsulate balance actions within deposits and withdrawals
    }

    // Business exclusive methods for SavingAccount
    public void interestPayment(){
        BigDecimal amount = getBalance().multiply(getInterestRate()); // Calculate payment by interest
        setBalance(getBalance().add(amount)); // Add to balance
    }

    // Methods from the interface AccountInterface
    public boolean checkPositive(BigDecimal amount){
        if (amount.signum() != (-1) && amount.compareTo(BigDecimal.valueOf(0)) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void deposit(BigDecimal amount) {
        // Value must be positive
        if (checkPositive(amount)) {
            setBalance(getBalance().add(amount));
        }
    }

    public void withdrawal(BigDecimal amount) {
        // Value must be positive
        if (checkPositive(amount)) {
            // As it is a SavingAccount account, its balance should not be negative
            if (getBalance().compareTo(amount) < 0)  {
                System.out.println(INSUFFICIENT_FUNDS);
            } else {
                this.balance = this.balance.subtract(amount);
            }
        }
    }

    // Debugging purposes only
    public String toString(){
        String response = "Saving Account data:\n" +
                "\tOwner: " + getOwner() +
                "\tBalance: " + getBalance() +
                "\tInterest Rate: " + getInterestRate();

        return response;
    }
}
