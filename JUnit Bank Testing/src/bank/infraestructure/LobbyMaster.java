package bank.infraestructure;

import java.util.ArrayList;

public class LobbyMaster {
    public ArrayList<Account> accountList = new ArrayList<Account>();
    private long lastAccountId = 0;


    //Si username y password en accountList
    //      ---> return true
    //si no
    //      ---> false
    public Account authentication(String username, String password){
        for(Account account : accountList){
            if (account.getUsername().equals(username) && account.getPassword().equals(password)){
                account.resetSession();
                System.out.println("Conectando a su cuenta");
                return account;
            }
        }
        System.out.println("Cuenta no encontrada.");
        return null;
    }

    //Recibe nombre de usuario y password
    //  ---> new Account(user,pass)
    //  ---> save on accountList
    //  ---> return true (nueva cuenta)
    public boolean newAccount(String username, String password){
        //Por ahora es simple, y permite cuentas identicas, diferenciadas por accountId.
        Account newAccount = new Account(lastAccountId, username, password);
        lastAccountId += 1;
        accountList.add(newAccount);
        return true;
    }
}
