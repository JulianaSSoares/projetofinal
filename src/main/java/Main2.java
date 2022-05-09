import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) throws SQLException {
//        consultaDados();
//        insereDados();
//        deletaDados();
//        atualizaDados();
    }

    public static void consultaDados() {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/cantina",
                    "root",
                    ""
            );

            PreparedStatement preparedStatement = connection.prepareStatement("select * from produtos");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.printf(
                        "%d = %s %.2f\n",
                        resultSet.getInt("id"),
                        resultSet.getString("descricao"),
                        resultSet.getBigDecimal("preco")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void insereDados() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Insira o ID do produto: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Insira a descriçao do produto: ");
        String descricao = scanner.nextLine();
        System.out.println("Insira o preço do produto: ");
        double preco = scanner.nextDouble();

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/cantina",
                    "root",
                    ""
            );

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into produtos (id, descricao, preco) values (?, ?, ?)"
            );
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, descricao);
            preparedStatement.setDouble(3, preco);

            int rowsaffected = preparedStatement.executeUpdate();

            if (rowsaffected > 0) {
                System.out.println("Produto cadastrado com sucesso");
            } else {
                System.out.println("Erro na inserção. Tente novamente");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        consultaDados();
    }

    public static void deletaDados() {
        consultaDados();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o ID a ser deletado: ");
        int id = scanner.nextInt();

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/cantina",
                    "root",
                    ""
            );

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from produtos where id=?"
            );
            preparedStatement.setInt(1, id);

            int rowsaffected = preparedStatement.executeUpdate();

            if (rowsaffected > 0) {
                System.out.println("Produto excluído com sucesso");
            } else {
                System.out.println("Erro na exclusão. Tente novamente");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        consultaDados();
    }

    public static void atualizaDados() {
        consultaDados();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o ID a ser alterado: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Insira a nova descrição: ");
        String descricao = scanner.nextLine();
        System.out.println("Insira a novo preço: ");
        double preco = scanner.nextDouble();

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/cantina",
                    "root",
                    ""
            );

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update produtos set descricao=?, preco=? where id=?"
            );
            preparedStatement.setString(1, descricao);
            preparedStatement.setDouble(2, preco);
            preparedStatement.setInt(3, id);

            int rowsaffected = preparedStatement.executeUpdate();

            if (rowsaffected > 0) {
                System.out.println("Produto alterado com sucesso");
            } else {
                System.out.println("Erro na alteração. Tente novamente");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}