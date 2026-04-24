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

public class TelaCaronas extends JFrame {

    private JTable tabela;
    private DefaultTableModel modelo;
    private JButton botaoFechar;

    public TelaCaronas() {
        setTitle("EcoRoute - Lista de Caronas");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Motorista");
        modelo.addColumn("Veículo");
        modelo.addColumn("Origem");
        modelo.addColumn("Destino");
        modelo.addColumn("Data");
        modelo.addColumn("Valor");

        tabela = new JTable(modelo);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        botaoFechar = new JButton("Fechar");
        botaoFechar.addActionListener(e -> dispose());

        JPanel painelBotao = new JPanel();
        painelBotao.add(botaoFechar);

        add(painelBotao, BorderLayout.SOUTH);

        carregarCaronas();

        setVisible(true);
    }

    private void carregarCaronas() {
        String sql =
            "SELECT c.id_carona, u.nome AS motorista, v.modelo AS veiculo, " +
            "c.origem, c.destino, c.data_saida, c.valor " +
            "FROM carona c " +
            "INNER JOIN usuario u ON c.id_usuario = u.id_usuario " +
            "INNER JOIN veiculo v ON c.id_veiculo = v.id_veiculo";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("id_carona"),
                    rs.getString("motorista"),
                    rs.getString("veiculo"),
                    rs.getString("origem"),
                    rs.getString("destino"),
                    rs.getDate("data_saida"),
                    rs.getBigDecimal("valor")
                });
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar caronas: " + ex.getMessage());
        }
    }
}