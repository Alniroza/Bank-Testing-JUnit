package bank.infraestructure;

import java.util.Date;

//Las transacciones representan un retiro o deposito de dinero. Iran guardando toda la info
//importante que ocurra en una transaccion.
public class Transaction {
    private long accountId;
    private Date date;
    private String type;
    private String detail;
    private Currency currency;
    private Integer movement;

    //
    public Transaction(long accountId, String type, Currency currency, Integer movement, String detail){
        this.accountId = accountId;
        this.date = new Date();
        this.type = type;
        this.detail = detail;
        this.currency = currency;
        this.movement = movement;
    }

    public String transactionString(){
        return "[" + date.toString() + "] " + type +": " + currency.toString() + " $" + movement.toString() + " - " + detail;
    }


}
