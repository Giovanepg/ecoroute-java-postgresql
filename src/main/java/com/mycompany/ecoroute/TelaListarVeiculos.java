/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecoroute;

/**
 *
 * @author blacklegen
 */
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TelaListarVeiculos extends JFrame {

    private JTable tabela;
    private DefaultTableModel modelo;
    private JButton botaoFechar;

    public TelaListarVeiculos() {
        setTitle("EcoRoute - Lista de Veículos");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Modelo");
        modelo.addColumn("Placa");
        modelo.addColumn("Cor");
        modelo.addColumn("Capacidade");
        modelo.addColumn("Motorista");

        tabela = new JTable(modelo);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        botaoFechar = new JButton("Fechar");
        botaoFechar.addActionListener(e -> dispose());

        JPanel painelBotao = new JPanel();
        painelBotao.add(botaoFechar);

        add(painelBotao, BorderLayout.SOUTH);

        carregarVeiculos();

        setVisible(true);
    }

    private void carregarVeiculos() {
        String sql =
            "SELECT v.id_veiculo, v.modelo, v.placa, v.cor, v.capacidade, u.nome AS motorista " +
            "FROM veiculo v " +
            "INNER JOIN usuario u ON v.id_usuario = u.id_usuario";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("id_veiculo"),
                    rs.getString("modelo"),
                    rs.getString("placa"),
                    rs.getString("cor"),
                    rs.getInt("capacidade"),
                    rs.getString("motorista")
                });
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar veículos: " + ex.getMessage());
        }
    }
}
