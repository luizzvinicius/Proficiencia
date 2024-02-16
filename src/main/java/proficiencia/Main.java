package proficiencia;

import java.sql.*;
import java.util.Locale;
import proficiencia.connection.ConnectionFactory;
import proficiencia.utils.Entrada;

public class Main {
    static Locale brasil = Locale.of("pt", "BR");

    public static void main(String[] args) {
        var listaOpcoes = new String[] {
                "Cadastra", "Ler", "Sair"
        };
        methods[] methods = new methods[] {
                Main::outro, Main::outro, Main::outro
        };

        try (var conn = ConnectionFactory.getConnection(); var scan = new Entrada()) {
            var opt = 0;
            var size = listaOpcoes.length;
            while (opt != size - 1) {
                for (var i = 0; i < size; i++) {
                    System.out.printf("[ %d ] %s%n", i + 1, listaOpcoes[i]);
                }
                opt = scan.lerOption("Opção: ", 1, size, "Opção inválida");
                methods[opt].accept(conn, scan);
            }
        } catch (Exception e) {
            System.out.printf("Conexão vazia: %s%n", e.getMessage());
        }
    }

    @FunctionalInterface
    public interface methods {
        void accept(Connection conn, Entrada scan);
    }

    public static void outro(Connection conn, Entrada scan) {
        System.out.println("Método");
    }
}