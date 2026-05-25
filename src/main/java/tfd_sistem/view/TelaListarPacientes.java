package tfd_sistem.view;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TelaListarPacientes extends JFrame {

    JTable tabela;

    DefaultTableModel modelo;

    public TelaListarPacientes() {

        setTitle("Lista de Pacientes - TFD+");

        setSize(700, 400);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("CPF");
        modelo.addColumn("Telefone");
        modelo.addColumn("Cidade");

        tabela = new JTable(modelo);

        JScrollPane painel =
                new JScrollPane(tabela);

        add(painel, BorderLayout.CENTER);

        carregarPacientes();

        setVisible(true);
    }

    public void carregarPacientes() {

        try {

            Connection conexao =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/tfd_aviso_mais",
                            "root",
                            "320526"
                    );

            Statement stmt =
                    conexao.createStatement();

            ResultSet rs =
                    stmt.executeQuery(
                            "SELECT * FROM paciente"
                    );

            while (rs.next()) {

                modelo.addRow(new Object[]{

                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("cidade")
                });
            }

            conexao.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new TelaListarPacientes();
    }
}