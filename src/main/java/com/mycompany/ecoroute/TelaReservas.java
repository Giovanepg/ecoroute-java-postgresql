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

public class TelaReservas extends JFrame {

    private JTable tabela;
    private DefaultTableModel modelo;
    private JButton botaoFechar;

    public TelaReservas() {
        setTitle("EcoRoute - Lista de Reservas");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel();
        modelo.addColumn("ID Reserva");
        modelo.addColumn("Passageiro");
        modelo.addColumn("Origem");
        modelo.addColumn("Destino");
        modelo.addColumn("Status");

        tabela = new JTable(modelo);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        botaoFechar = new JButton("Fechar");
        botaoFechar.addActionListener(e -> dispose());

        JPanel painelBotao = new JPanel();
        painelBotao.add(botaoFechar);

        add(painelBotao, BorderLayout.SOUTH);

        carregarReservas();

        setVisible(true);
    }

    private void carregarReservas() {
        String sql =
            "SELECT r.id_reserva, u.nome AS passageiro, c.origem, c.destino, r.status_reserva " +
            "FROM reserva r " +
            "INNER JOIN usuario u ON r.id_usuario = u.id_usuario " +
            "INNER JOIN carona c ON r.id_carona = c.id_carona";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("id_reserva"),
                    rs.getString("passageiro"),
                    rs.getString("origem"),
                    rs.getString("destino"),
                    rs.getString("status_reserva")
                });
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar reservas: " + ex.getMessage());
        }
    }
}
