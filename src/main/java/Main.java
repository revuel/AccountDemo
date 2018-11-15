import com.sunhill.demo.account.CheckingAccount;
import com.sunhill.demo.account.SavingAccount;

import java.math.BigDecimal;

public class Main {

    // This class is for demo purposes only

    public static void main(String[] args){

        // DEMO: ACCOUNTS FOR SUNHILL

        // 1) ------------------------------------------------------------------
        /* Your task is to develop a few classes that will consider two
        different types of bank accounts – savings accounts and checking accounts.
        Each account has an owner and a balance. Accounts also support
        deposits and withdrawals of money amounts. */

        // Saving accounts example of deposits and withdrawals
        System.out.println("Saving example of deposits and withdrawals");
        SavingAccount peterSavingAccount = new SavingAccount("Peter");
        peterSavingAccount.deposit(BigDecimal.valueOf(10000));
        // Adds to balance, see deposit() implementation for more info
        peterSavingAccount.withdrawal(BigDecimal.valueOf(5000));
        // Substracts from balance, see withdrawal() implementation for more info
        System.out.println( peterSavingAccount.toString() + "\n");
        // deposit and withdrawal arguments are always positive
        // (see checkPositive() implementation)

        // Checking accounts example of deposito and withdrawals
        System.out.println("Checking example of deposits and withdrawals");
        CheckingAccount tracyCheckingAccount = new CheckingAccount("Tracy");
        tracyCheckingAccount.deposit(BigDecimal.valueOf(20000));
        tracyCheckingAccount.withdrawal(BigDecimal.valueOf(5123.24));
        System.out.println( tracyCheckingAccount.toString() + "\n");

        // 2) ------------------------------------------------------------------
        /* A savings account additionally supports interest payments to its
           owner based on a certain interest rate tied to the account. There
           should be a possibility to calculate and pay the appropriate
           interest to a savings account. */
        System.out.println("Checking saving interests functionality");
        peterSavingAccount.setInterestRate(BigDecimal.valueOf(0.01)); // 1% of interest
        peterSavingAccount.interestPayment();
        System.out.println( peterSavingAccount.toString() + "\n");
        // Interest rate should be higher than 0 (it wouldn't be a Saving account
        // if lower). See setInterestRate() implementation for details
        // Suggestion: it should be also lower than a certain threshold, or had a maximum
        // based on the balance amount, but it is not a requirement...

        // 3) ------------------------------------------------------------------
        /* A checking account additionally supports balance overdrafts restricted by
           a limit tied to the account. There should be a possibility to do
           cash transfers between checking accounts. */
        System.out.println("Checking overdraft functionality");
        tracyCheckingAccount.setMaxOverdraft(BigDecimal.valueOf(1000));
        tracyCheckingAccount.withdrawal(BigDecimal.valueOf(16000)); // She cant (check console msg)
        System.out.println( tracyCheckingAccount.toString());
        tracyCheckingAccount.withdrawal(BigDecimal.valueOf(15000));
        System.out.println( tracyCheckingAccount.toString() + "\n"); // Now she can

        System.out.println("Checking transfer functionality");
        CheckingAccount oliviaCheckingAccount = new CheckingAccount("Olivia");
        oliviaCheckingAccount.deposit(BigDecimal.valueOf(1000));
        oliviaCheckingAccount.transferToAccount(tracyCheckingAccount, BigDecimal.valueOf(123.24));

        System.out.println( tracyCheckingAccount.toString()); // TRACY
        System.out.println( oliviaCheckingAccount.toString() + "\n"); // OLIVIA

        // #) ------------------------------------------------------------------
        // Trying a transference to the same account
        tracyCheckingAccount.transferToAccount(tracyCheckingAccount, BigDecimal.valueOf(1000)); // Same acc

        // Checking not to exeed limit when transfering
        tracyCheckingAccount.transferToAccount(oliviaCheckingAccount, BigDecimal.valueOf(1000)); // tracy
        System.out.println( tracyCheckingAccount.toString());
        tracyCheckingAccount.transferToAccount(oliviaCheckingAccount, BigDecimal.valueOf(1)); // Exceeding overdraft
        System.out.println( tracyCheckingAccount.toString());

        // Checking not to make negative transfers
        tracyCheckingAccount.transferToAccount(oliviaCheckingAccount, BigDecimal.valueOf(-1000));
        System.out.println( tracyCheckingAccount.toString());
        System.out.println( oliviaCheckingAccount.toString());

        // Checking not to make negative depostis or withdrawals
        peterSavingAccount.deposit(BigDecimal.valueOf(-5050)); // must fail
        System.out.println( peterSavingAccount.toString());
        peterSavingAccount.withdrawal(BigDecimal.valueOf(-5050)); // must fail
        System.out.println( peterSavingAccount.toString());

        // More comments about the implementation available at class files
    }

}
