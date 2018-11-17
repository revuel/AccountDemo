# Overall information
## Project properties
· Java 8\
· Maven 3.3 (not bundled)\
· UTF-8 encoding\
· Google´s Java Coding Style\
· JUnit\
· Build from sources (jar is provided anyway):\
```
mvn clean package
```
## Features
· Saving Accounts & Checking Accounts with deposit and withdrawal methods\
· Saving Accounts support interest payments\
· Checking Accounts support transfers\
· Checking Accounts can't surpasse a defined overdraft\

## Asumptions
· Account unique identifier is the owner attribute\
· Saving accounts can´t have a negative balance\

## Pitfalls avoided
· Null pointers (object initialization)\
· Deposit a negative amount\
· Withdrawal a negative amount\
· Transfer to the same account\
· Negative interest rate\
· Transfer a negative amount\
· setBalance not private\

## Classes
· AccountInterface\
· Account (superclass)\
· SavingAccount (subclass, implements AccountInterface)\
· CheckingAccount (subclass, implements AccountInterface)\
· TestSavingAccount\
· TestCheckingAccount\

Review code comments for implementation details