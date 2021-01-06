package bank.infraestructure;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static LobbyMaster Lobby = new LobbyMaster();


    public static void main(String[] args) {
        boolean connected = false;
        String cash;
        String[] cashArr;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenidos a TestBank");

        while (true) {
            System.out.println("1 - Conectarse.");
            System.out.println("2 - Crear nueva cuenta");
            System.out.println("3 - Salir");
            System.out.print("Ingrese una opcion: ");
            String action = scanner.nextLine();

            //Conectarme
            if (action.equals("1")) {
                System.out.print("Ingrese su nombre de usuario: ");
                String usernameScan = scanner.nextLine();
                System.out.print("Ingrese su contraseña: ");
                String passwordScan = scanner.nextLine();

                Account connectedAccount = Lobby.authentication(usernameScan, passwordScan);
                if (connectedAccount != null){
                    connected = true;
                }

                while (connected){

                    if (connectedAccount.getSessions() > 4){
                        System.out.println("Ha excedido el maximo de operaciones por sesion y se ha desconectado.");
                        connected = false;
                    }

                    System.out.println("");
                    System.out.println("Bienvenido a su cuenta " + usernameScan + ", operaciones disponibles: " + (4 - connectedAccount.getSessions()));
                    System.out.println("SALDOS USD " + connectedAccount.getUSDCash() + " - CLP " + connectedAccount.getCLPCash());
                    System.out.println("1 - Realizar un Deposito");
                    System.out.println("2 - Realizar un Retiro");
                    System.out.println("3 - Revisar Transacciones");
                    System.out.println("4 - Cerrar Sesion");
                    System.out.println("Ingrese una opcion: ");
                    action = scanner.nextLine();

                    switch (action){
                        //Deposito
                        case "1":
                            System.out.println("Ingrese Moneda (USD, CLP) y monto");
                            System.out.println("Ej. USD 300");
                            cash = scanner.nextLine();
                            cashArr = cash.split(" ");
                            if (cashArr[0].equals("USD")){
                                boolean depositEnd = connectedAccount.deposit(Integer.parseInt(cashArr[1]), Currency.USD, "");
                            }
                            else if (cashArr[0].equals("CLP")){
                                boolean depositEnd = connectedAccount.deposit(Integer.parseInt(cashArr[1]), Currency.CLP, "");

                            }
                            else{
                                System.out.println("Moneda invalida");
                            }
                            break;

                        case "2":
                            System.out.println("Ingrese Moneda (USD, CLP) y monto");
                            System.out.println("Ej. USD 300");
                            cash = scanner.nextLine();
                            cashArr = cash.split(" ");
                            if (cashArr[0].equals("USD")){
                                boolean depositEnd = connectedAccount.withdrawal(Integer.parseInt(cashArr[1]), Currency.USD, "");
                            }
                            else if (cashArr[0].equals("CLP")){
                                boolean depositEnd = connectedAccount.withdrawal(Integer.parseInt(cashArr[1]), Currency.CLP, "");

                            }
                            else{
                                System.out.println("Moneda invalida");
                            }
                            break;

                        case "3":
                            connectedAccount.listTransactions();
                            break;

                        case "4":
                            cash = null;
                            cashArr = null;
                            connectedAccount = null;
                            connected = false;

                        default:
                            System.out.println("Comando invalido.");
                    }

                }
            }

            //Crear nueva cuenta
            else if (action.equals("2")) {
                System.out.print("Ingrese su nombre de usuario deseado: ");
                String usernameScan = scanner.nextLine();
                System.out.print("Ingrese su contraseña deseada: ");
                String passwordScan = scanner.nextLine();
                if (Lobby.newAccount(usernameScan, passwordScan)) {
                    System.out.println("Su cuenta ha sido creada con exito.");
                } else {
                    System.out.println("Su cuenta no pudo ser creada");
                }

            }

            //Cerrar aplicacion
            else if (action.equals("3")) {
                System.out.println("Cerrando Aplicacion");
                break;
            } else {
                System.out.println("Opcion invalida, ingrese otra opcion.");
            }

        }
    }





/*
        Account account = new Account(1, "tester", "1234");
        account.deposit(25, Currency.USD, "Some test Deposit");
        account.deposit(5000, Currency.CLP, "La pension");
        account.listTransactions();
*/



}
