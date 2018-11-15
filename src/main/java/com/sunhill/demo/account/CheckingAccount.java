package com.sunhill.demo.account;

import java.math.BigDecimal;

public class CheckingAccount extends Account implements AccountInterface{

    // Atributes / Atributes (Big Decimal are recommended to avoid double or float pitfalls)
    private BigDecimal balance = BigDecimal.valueOf(0); // Initialize attributes to avoid null pointers
    private BigDecimal maxOverdraft = BigDecimal.valueOf(0);

    private static final String MAX_OVERDRAFT_REACHED = "WARNING: Surpassing overdraft. Operation cancelled.";
    private static final String SAME_ACCOUNT = "WARNING: Same account detected. Operation cancelled";
    private static final String NEGATIVE_AMOUNT = "WARNING: Deposits, withdrawals or transfers must be positive. Operation cancelled";

    // Constructor
    public CheckingAccount(String owner){
        super.setOwner(owner); // Force to set up owner, as it works as Account identifier in this demo
    }

    // Getters & Setters
    public BigDecimal getBalance() {
        return balance;
    }

    private void setBalance(BigDecimal balance) {
        this.balance = balance; // Private method, to encapsulate balance actions within deposits and withdrawals
    }

    public BigDecimal getMaxOverdraft() {
        return maxOverdraft;
    }

    public void setMaxOverdraft(BigDecimal maxOverdraft) {
        this.maxOverdraft = maxOverdraft;
    }

    // Exclusive business logic methods for Checking
    public void transferToAccount (CheckingAccount destiny, BigDecimal amount){
        // Check if the operation doesn't exceed the limit
        if (checkPositive(amount)) {
            if ( checkOverdraft(amount) < 0){
                System.out.println(MAX_OVERDRAFT_REACHED);
            } else {
                // Check if i/m trying to make a transfer to my account
                if (checkDifferentAccount(destiny)){
                    setBalance(getBalance().subtract(amount));
                    destiny.deposit(amount);
                } else {
                    System.out.println(SAME_ACCOUNT);
                }
            }
        } else {
            System.out.println(NEGATIVE_AMOUNT);
        }
    }

    // Checkers
    public Integer checkOverdraft (BigDecimal amount) {
        // Verify that balance + max overdraf doesn't exceed
        BigDecimal maxToSpend = getBalance().add(getMaxOverdraft());
        return maxToSpend.compareTo(amount);
        // compareTo return types (Integer)
        // 1 means that maxToSpend is higher than amount (we can proceed)
        // 0 means that maxToSpend is equal than amount (we can proceed)
        // -1 means that maxToSpend is lower than amount (we can't proceed)
    }

    private boolean checkDifferentAccount(CheckingAccount accountToCheck){
        // Need to avoid "auto transfer"
        if (accountToCheck.getOwner().equals(getOwner())) {
            return false;
        } else {
            return true;
        }
    }

    // Methods from the interface AccountInterface
    public boolean checkPositive(BigDecimal amount){
        if ((amount.signum() != -1) && (amount.compareTo(BigDecimal.valueOf(0)) > 0)) {
            return true;
        } else {
            return false;
        }
    }

    public void deposit(BigDecimal amount) {
        if (checkPositive(amount)) {
            setBalance(getBalance().add(amount));
        } else {
            System.out.println(NEGATIVE_AMOUNT);
        }
    }

    public void withdrawal(BigDecimal amount) {
        if (checkPositive(amount)){
            // Check if the operation doesn't exceed the limit
            if ( checkOverdraft(amount) < 0){
                System.out.println(MAX_OVERDRAFT_REACHED);
            } else {
                setBalance(getBalance().subtract(amount));
            }
        } else {
            System.out.println(NEGATIVE_AMOUNT);
        }
    }

    // Debugging purposes only
    public String toString(){
        String response = "Checking Account data:\n" +
                "\tOwner: " + getOwner() +
                "\tBalance: " + getBalance() +
                "\tMaximum Overdraft: " + getMaxOverdraft();

        return response;
    }
}
