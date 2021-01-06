package bank.infraestructure;

import java.util.ArrayList;

public class Account {

    private long accountId;
    private String username;
    private String password;
    private int sessionOperations;

    private Integer USDCash;
    private Integer CLPCash;

    private ArrayList<Transaction> transactions;

    public Account(long accountId, String username, String password){
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.sessionOperations = 0;
        this.USDCash = 0;
        this.CLPCash = 0;
        this.transactions = new ArrayList<Transaction>();
    }

    //Aprobacion de Deposito
    //Si currency = USD (0) & amount >= 10
    //     ---> return true
    //Su Currency = CLP (1) & amount >= 2000
    //     ---> return true
    //else
    //     ---> return false
    private boolean depositApproval(Currency currency, Integer amount) {
        if (sessionOperations > 4){
            System.out.println("Limite de operaciones por Sesion.");
            return false;
        }

        if (currency.equals(Currency.USD) && amount >= 10){
            System.out.println("Deposit of USD $ " + amount.toString() + " approved.");
            return true;
        }
        if (currency.equals(Currency.CLP) && amount >= 2000){
            System.out.println("Deposito de CLP $" + amount.toString() + " aprobado.");
            return true;
        }
        System.out.println("Solicitud rechazada.");
        return false;
    }
    //Aprobacion de Retiro
    //Si currency = USD & (10 <= amount <= 100) & amount <= USDCash
    //     ---> return True
    //Si currency = CLP & 2000 <= amount <= 200000 & amount <= CLPCash
    //     ---> return True
    //else
    //     ---> False;
    private boolean withdrawalApproval(Currency currency, Integer amount){
        if (sessionOperations > 4){
            System.out.println("Limite de operaciones por Sesion.");
            return false;
        }
        //USD
        if (currency.equals(Currency.USD)){
            boolean validAmount = (10 <= amount && amount <= 100) && amount <= USDCash;
            if (validAmount){
                System.out.println("Withdrawal of USD $ " + amount.toString() + " approved.");
                return true;
            }
        }
        //CLP
        if (currency.equals(Currency.CLP)){
            boolean validAmount = (2000 <= amount && amount <= 200000) && amount <= CLPCash;
            if (validAmount){
                System.out.println("Retiro de CLP $" + amount.toString() + " aprobado.");
                return true;
            }
        }
        System.out.println("Solicitud rechazada.");
        return false;
    }
    // Transaccion de Deposito
    // Si deposit_approval() = true
    //     ---> new Transaction deposit, agregada a transactions,
    //     ---> disminuye amount de Cash segun currency
    //     ===> return true.
    // else
    //     ===> return false.
    public boolean deposit(Integer amount, Currency currency, String detail){

        if (depositApproval(currency, amount)) {
            Transaction new_deposit = new Transaction(this.accountId, "deposit", currency, amount, detail);
            transactions.add(new_deposit);

            switch (currency) {
                case USD -> USDCash += amount;
                case CLP -> CLPCash += amount;
            }
            sessionOperations += 1;
            System.out.println("Deposito finalizado");
            
            return true;
        }
        return false;

    }
    // Transaccion de retiro
    public boolean withdrawal(Integer amount, Currency currency, String detail){
        if (withdrawalApproval(currency,amount)){
            Transaction new_withdrawal = new Transaction(this.accountId,"withdrawal", currency, amount, detail);
            transactions.add(new_withdrawal);

            switch (currency) {
                case USD -> USDCash -= amount;
                case CLP -> CLPCash -= amount;
            }
            sessionOperations += 1;
            System.out.println("Retiro finalizado");
            return true;
        }
        return false;
    }

    public void listTransactions(){
        sessionOperations += 1;
        for (Transaction current : transactions) {
            System.out.println(current.transactionString());
        }
    }


    public double getAccountId(){
        return this.accountId;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return this.password;
    }

    public void resetSession(){
        sessionOperations = 0;
    }

    public int getSessions(){
        return sessionOperations;
    }

    public int getUSDCash(){
        return USDCash;
    }

    public int getCLPCash(){
        return CLPCash;
    }
}
