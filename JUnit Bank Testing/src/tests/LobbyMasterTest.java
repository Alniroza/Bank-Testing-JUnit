package tests;

import bank.infraestructure.Account;
import bank.infraestructure.LobbyMaster;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LobbyMasterTest {
    LobbyMaster Lobby = new LobbyMaster();

    @Test
    @DisplayName("Crear una nueva cuenta y comprobar que se agrega")
    public void newAccountTest(){
        Lobby.newAccount("TestUser", "TestPass");
        Account connectionTest = Lobby.authentication("TestUser", "TestPass");
        Assertions.assertEquals("TestUser", connectionTest.getUsername());
        Assertions.assertEquals("TestPass", connectionTest.getPassword());
    }

    @Test
    @DisplayName("Intentar conectarse a una cuenta no existente.")
    public void failedConnectionTest(){
        Account connectionTest = Lobby.authentication("FakeUser", "FakePass");
        Assertions.assertNull(connectionTest);
    }

    @Test
    @DisplayName("Creando multiples cuentas y comprobandolas")
    public void multipleAccountTest(){
        Lobby.newAccount("TestUser1", "TestPass1");
        Account connectionTest = Lobby.authentication("TestUser1", "TestPass1");
        Assertions.assertNotNull(connectionTest);
        connectionTest = null;
        Lobby.newAccount("GodAccount", "GodDoesntNeedPasswords");
        connectionTest = Lobby.authentication("GodAccount", "GodDoesntNeedPasswords");
        Assertions.assertNotNull(connectionTest);
        Lobby.newAccount("BlaBla", "Bleble");
        connectionTest = Lobby.authentication("BlaBla", "Bleble");
        Assertions.assertNotNull(connectionTest);



        Assertions.assertEquals("TestUser1", Lobby.accountList.get(0).getUsername());
        Assertions.assertEquals("GodAccount", Lobby.accountList.get(1).getUsername());
        Assertions.assertEquals("BlaBla", Lobby.accountList.get(2).getUsername());

    }

}
