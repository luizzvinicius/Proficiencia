package proficiencia;

import java.sql.*;
import java.util.Locale;
import proficiencia.connection.ConnectionFactory;
import proficiencia.utils.Entrada;

public class Main {
    static Locale brasil = Locale.of("pt", "BR");

    public static void main(String[] args) {
        var listaOpcoes = new String[] {
        };
        var methods = new methods[] {
        };

        try (var conn = ConnectionFactory.getConnection(); var scan = new Entrada()) {
            for (int i = 0; i < listaOpcoes.length; i++) {
                System.out.printf("[ %d ] %s%n", i + 1, listaOpcoes[i]);
            }

            var opt = 0;
            while (opt != listaOpcoes.length - 1) {
                opt = scan.lerOption("Opção: ", 1, listaOpcoes.length, "Opção inválida") - 1;
                methods[opt].accept(conn, scan);
            }
        } catch (SQLException e) {
            System.out.println("Conexão vazia: " + e.getMessage());
        }
    }

    @FunctionalInterface
    public interface methods {
        void accept(Connection conn, Entrada scan);
    }
}