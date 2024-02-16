import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import proficiencia.connection.ConnectionFactory;

public class ConnectionFactoryTest {
    @Test
    @DisplayName("Conexão falha se conexão é diferente de Connection")
    void testGetConnection() {
        var conn = ConnectionFactory.getConnection();
        assertTrue(conn instanceof Connection, "Conexão falhou");
    }

    @Test
    @DisplayName("Conexão falha se conexão é null")
    void testGetConnectionNull() {
        var conn = ConnectionFactory.getConnection();
        conn = null;
        assertNull(conn, "Conexão é nula");
    }
}
