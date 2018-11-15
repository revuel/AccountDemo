package com.sunhill.demo.account;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TestCheckingAccount {

    CheckingAccount testChecking;

    @Before
    public void setUp() {
        testChecking = new CheckingAccount("test");
    }

    @Test
    public void validDeposit_mustIncreaseBalance(){

        BigDecimal expected = new BigDecimal("1000");

        testChecking.deposit(expected);

        assertEquals(expected, testChecking.getBalance());

    }

    @Test
    public void negativeDeposit_mustLeaveBalanceIntact(){

        BigDecimal expected = new BigDecimal("0");
        BigDecimal negativeValue = new BigDecimal("-1000");

        testChecking.deposit(negativeValue);

        assertEquals(expected, testChecking.getBalance());

    }

    @Test
    public void negativeWithdrawal_mustLeaveBalanceIntact() {
        BigDecimal expected = new BigDecimal("0");

        testChecking.withdrawal(BigDecimal.valueOf(-2000)); // Invalid

        assertEquals(expected, testChecking.getBalance());
    }

    @Test
    public void validWithdrawal_mustDecreaseBalance_respectingOverdraft() {

        BigDecimal expected = new BigDecimal("-1000");

        testChecking.deposit(BigDecimal.valueOf(2000)); // add 2 thousand
        testChecking.setMaxOverdraft(BigDecimal.valueOf(1000)); // 1 thousand overdraft
        testChecking.withdrawal(BigDecimal.valueOf(3000)); // withdraw 3000 (max)

        assertEquals(expected, testChecking.getBalance());

    }

    @Test
    public void validWithdrawal_mustNotDecreaseBalance_WhenNotRespectingOverdraft() {

        BigDecimal expected = new BigDecimal("2000");

        testChecking.deposit(BigDecimal.valueOf(2000)); // add 2 thousand
        testChecking.setMaxOverdraft(BigDecimal.valueOf(500)); // five hundred overdraft
        testChecking.withdrawal(BigDecimal.valueOf(3000)); // withdraw 3000 (max)

        assertEquals(expected, testChecking.getBalance());

    }

    @Test
    public void validTransfers_mustNotDecreaseBalance_ifAccountsAreTheSame() {
        // Asuming "owner" is the account identifier
        BigDecimal expected = new BigDecimal("500");
        testChecking.deposit(BigDecimal.valueOf(500)); // add 2 thousand
        testChecking.transferToAccount(testChecking, BigDecimal.valueOf(10));

        assertEquals(expected, testChecking.getBalance());

    }

    @Test
    public void negativeTransfer_mustLeaveBalanceIntact() {
        BigDecimal expected = new BigDecimal("0");
        CheckingAccount destinyCheckingAccount = new CheckingAccount("destiny");

        testChecking.withdrawal(BigDecimal.valueOf(-2000)); // Invalid

        assertEquals(expected, testChecking.getBalance());

    }

    @Test
    public void validTransfer_mustDecreaseBalance_respectingOverdraft() {
        BigDecimal expected = new BigDecimal("500");
        CheckingAccount destinyCheckingAccount = new CheckingAccount("destiny");
        testChecking.deposit(BigDecimal.valueOf(1000));
        testChecking.transferToAccount(destinyCheckingAccount, BigDecimal.valueOf(500));

        assertEquals(expected, testChecking.getBalance());
    }

    @Test
    public void validTransfer_mustNotDecreaseBalance_WhenNotRespectingOverdraft() {
        BigDecimal expected = new BigDecimal("2000");
        CheckingAccount destinyCheckingAccount = new CheckingAccount("destiny");

        testChecking.deposit(BigDecimal.valueOf(2000)); // add 2 thousand
        testChecking.setMaxOverdraft(BigDecimal.valueOf(500)); // five hundred overdraft
        testChecking.transferToAccount(destinyCheckingAccount , BigDecimal.valueOf(3000)); // withdraw 3000 (max)

        assertEquals(expected, testChecking.getBalance());

    }



}
