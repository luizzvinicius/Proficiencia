package proficiencia.connection;

import java.nio.file.*;
import java.sql.*;
import java.io.IOException;

public class ConnectionFactory implements AutoCloseable {
    private static Connection conn = null;

    private ConnectionFactory() { }

    public static Connection getConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost", "root", "root");
            criarBanco(conn);
        } catch (SQLException e) {
            System.out.println("Erro ao se conectar: " + e.getMessage());
        }
        return conn;
    }

    private static void criarBanco(Connection conn) {
        var path = Paths.get("src/main/java/proficiencia/connection/tables.sql");
        try (var stmt = conn.createStatement()) {
            var file = Files.readString(path).replace("\r\n", "").split(";");
            for (String comand : file) {
                stmt.addBatch(comand + ";");
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            System.out.println("Erro ao criar preparedStatement: " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }

    @Override
    public void close() throws Exception {
        conn.close();
    }
}