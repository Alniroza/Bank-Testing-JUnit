package tests;

import bank.infraestructure.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



public class AccountTest
{
    Account testAccount = new Account(0,"Admin", "GreatestAdmin");;

    @Test
    @DisplayName("Corroborando deposito valido (CLP y USD)")
    public void validatingDepositTest(){
        testAccount.deposit(50000, Currency.CLP , "");
        Assertions.assertEquals(50000, testAccount.getCLPCash());

        testAccount.deposit(200, Currency.USD , "");
        Assertions.assertEquals(200, testAccount.getUSDCash());
    }

    @Test
    @DisplayName("Corroborando retiro Validando (CLP y USD)")
    public void validatingWithdrawalTest(){
        testAccount.deposit(50000, Currency.CLP , "");
        testAccount.withdrawal(25000, Currency.CLP, "");
        Assertions.assertEquals(25000, testAccount.getCLPCash());

        testAccount.deposit(200, Currency.USD , "");
        testAccount.withdrawal(100, Currency.USD, "");
        Assertions.assertEquals(100, testAccount.getUSDCash());

    }

    @Test
    @DisplayName("Rechazando deposito invalido (CLP y USD)")
    public void denyingDepositTest(){
        testAccount.deposit(1000, Currency.CLP , "");
        Assertions.assertEquals(0, testAccount.getCLPCash());

        testAccount.deposit(2, Currency.USD , "");
        Assertions.assertEquals(0, testAccount.getUSDCash());

    }

    @Test
    @DisplayName("Rechazando retiro invalido. Dinero insuficiente (CLP y USD)")
    public void denyingWithdrawalTest(){
        testAccount.deposit(10000, Currency.CLP , "");
        testAccount.withdrawal(25000, Currency.CLP, "");
        Assertions.assertEquals(10000, testAccount.getCLPCash());

        testAccount.deposit(50, Currency.USD , "");
        testAccount.withdrawal(100, Currency.USD, "");
        Assertions.assertEquals(50, testAccount.getUSDCash());
    }

    @Test
    @DisplayName("Rechazando por retiro mayor al permitido (CLP y USD)")
    public void denyingOverWithdrawalTest(){
        testAccount.deposit(300000, Currency.CLP , "");
        testAccount.withdrawal(250000, Currency.CLP, "");
        Assertions.assertEquals(300000, testAccount.getCLPCash());

        testAccount.deposit(300, Currency.USD , "");
        testAccount.withdrawal(200, Currency.USD, "");
        Assertions.assertEquals(300, testAccount.getUSDCash());
    }

    @Test
    @DisplayName("Corroborando dinero disponible (CLP y USD) tras deposito y retiro")
    public void validatingCashTest(){
        testAccount.deposit(10000, Currency.CLP , "");
        Assertions.assertEquals(10000, testAccount.getCLPCash());
        testAccount.withdrawal(5000, Currency.CLP, "");
        Assertions.assertEquals(5000, testAccount.getCLPCash());

        testAccount.deposit(200, Currency.USD , "");
        Assertions.assertEquals(200, testAccount.getUSDCash());
        testAccount.withdrawal(50, Currency.USD, "");
        Assertions.assertEquals(150, testAccount.getUSDCash());

    }
}
