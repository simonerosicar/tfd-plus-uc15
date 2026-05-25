package tfd_sistem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PacienteDAO {

    private final String URL =
            "jdbc:mysql://localhost:3306/tfd_aviso_mais";

    private final String USER = "root";

    private final String PASSWORD = "320526";

    public void salvarPaciente(
            String nome,
            String cpf,
            String cns,
            String telefone,
            String cidade
    ) {

        String sql =
                "INSERT INTO paciente " +
                "(nome, cpf, cns, telefone, cidade, ativo) " +
                "VALUES (?, ?, ?, ?, ?, true)";

        try {

            Connection conexao =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            PreparedStatement stmt =
                    conexao.prepareStatement(sql);

            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, cns);
            stmt.setString(4, telefone);
            stmt.setString(5, cidade);

            stmt.executeUpdate();

            System.out.println("Paciente salvo com sucesso!");

            stmt.close();
            conexao.close();

        } catch (Exception e) {

            System.out.println("Erro ao salvar paciente");

            e.printStackTrace();
        }
    }
}