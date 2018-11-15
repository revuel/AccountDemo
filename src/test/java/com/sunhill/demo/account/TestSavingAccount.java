package com.sunhill.demo.account;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TestSavingAccount {

    SavingAccount testSaving;

    @Before
    public void setUp(){
        testSaving = new SavingAccount("test");
    }

    @Test
    public void validDeposit_mustIncreaseBalance(){

        BigDecimal expected = new BigDecimal("1000");

        testSaving.deposit(expected);

        assertEquals(expected, testSaving.getBalance());

    }

    @Test
    public void negativeDeposit_mustLeaveBalanceIntact(){

        BigDecimal expected = new BigDecimal("0");
        BigDecimal negativeValue = new BigDecimal("-1000");

        testSaving.deposit(negativeValue);

        assertEquals(expected, testSaving.getBalance());

    }

    @Test
    public void validWithdrawal_mustDecreaseBalance() {
        BigDecimal expected = new BigDecimal("1000");

        testSaving.deposit(BigDecimal.valueOf(2000)); // add 2 thousand
        testSaving.withdrawal(expected); // substract 1000

        assertEquals(expected, testSaving.getBalance());

    }

    @Test
    public void negativeWithdrawal_mustLeaveBalanceIntact() {
        BigDecimal expected = new BigDecimal("0");

        testSaving.withdrawal(BigDecimal.valueOf(-2000)); // Invalid

        assertEquals(expected, testSaving.getBalance());
    }

    @Test
    public void exceededWithdrawal_mustLeaveBalanceIntact(){
        // Asuming a Saving account can not have a balance
        // lower than zero, it's necessary to verify that a
        // withdrawal > balance can not be completed.

        BigDecimal expected = new BigDecimal("0");
        testSaving.withdrawal(BigDecimal.valueOf(1000));
        assertEquals(expected, testSaving.getBalance());

    }

    @Test
    public void validInterestRate_mustUpdateInterestRate () {
        BigDecimal expected = new BigDecimal("0.01"); // 1% percent

        testSaving.setInterestRate(BigDecimal.valueOf(0.01));

        assertEquals(expected, testSaving.getInterestRate());
    }

    @Test
    public void negativeInterestRate_mustLeaveInterestRateIntact () {
        BigDecimal expected = new BigDecimal("0");

        testSaving.setInterestRate(BigDecimal.valueOf(-0.01));

        assertEquals(expected, testSaving.getInterestRate());
    }

    @Test
    public void interestPayment_mustIncreaseBalanceAccordingToInterestRate () {
        BigDecimal expected = new BigDecimal("1010.00");

        testSaving.deposit(BigDecimal.valueOf(1000));
        testSaving.setInterestRate(BigDecimal.valueOf(0.01)); // 1 % percent
        testSaving.interestPayment();

        assertEquals(expected, testSaving.getBalance());
    }
}
