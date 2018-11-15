package com.sunhill.demo.account;

import java.math.BigDecimal;

public interface AccountInterface {

    void deposit(BigDecimal amount);
    void withdrawal(BigDecimal amount);
    boolean checkPositive(BigDecimal amount);

}
