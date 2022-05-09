import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
       consultaDados();
//        insereDados();
//        deletaDados();
//        atualizaDados();
    }

    public static void consultaDados() {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/teste",
                    "root",
                    ""
            );

            PreparedStatement preparedStatement = connection.prepareStatement("select * from notas");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.printf(
                        "%d = %s\n",
                        resultSet.getInt("id"),
                        resultSet.getString("descricao")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void insereDados() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Insira o ID da anotação: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Insira a anotação: ");
        String text = scanner.nextLine();

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/teste",
                    "root",
                    ""
            );

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into notas (id, descricao) values (?, ?)"
            );
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, text);

            int rowsaffected = preparedStatement.executeUpdate();

            if (rowsaffected > 0) {
                System.out.println("Anotação cadastrada com sucesso");
            } else {
                System.out.println("Erro na inserção. Tente novamente");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deletaDados() {
        consultaDados();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o ID a ser deletado: ");
        int id = scanner.nextInt();

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/teste",
                    "root",
                    ""
            );

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from notas where id=?"
            );
            preparedStatement.setInt(1, id);

            int rowsaffected = preparedStatement.executeUpdate();

            if (rowsaffected > 0) {
                System.out.println("Anotação excluída com sucesso");
            } else {
                System.out.println("Erro na exclusão. Tente novamente");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void atualizaDados() {
        consultaDados();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o ID a ser alterado: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Insira a nova anotação: ");
        String text = scanner.nextLine();

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/teste",
                    "root",
                    ""
            );

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update notas set descricao=? where id=?"
            );
            preparedStatement.setString(1, text);
            preparedStatement.setInt(2, id);

            int rowsaffected = preparedStatement.executeUpdate();

            if (rowsaffected > 0) {
                System.out.println("Anotação alterada com sucesso");
            } else {
                System.out.println("Erro na alteração. Tente novamente");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}