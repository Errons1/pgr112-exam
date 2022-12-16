package jdbc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConnectionMySQLTest {

    @Test
    public void makeConnection(){
        ConnectionMySQL con = new ConnectionMySQL();
        assertNotNull(con);
    }

}
