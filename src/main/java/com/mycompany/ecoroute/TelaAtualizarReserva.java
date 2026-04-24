/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecoroute;

/**
 *
 * @author blacklegen
 */
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TelaAtualizarReserva extends JFrame {

    private JTextField campoIdReserva;
    private JTextField campoNovoStatus;
    private JButton botaoAtualizar;

    public TelaAtualizarReserva() {
        setTitle("EcoRoute - Atualizar Reserva");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));

        campoIdReserva = new JTextField();
        campoNovoStatus = new JTextField();
        botaoAtualizar = new JButton("Atualizar");

        add(new JLabel("ID da Reserva:"));
        add(campoIdReserva);

        add(new JLabel("Novo Status:"));
        add(campoNovoStatus);

        add(new JLabel(""));
        add(botaoAtualizar);

        botaoAtualizar.addActionListener(e -> atualizarReserva());

        setVisible(true);
    }

    private void atualizarReserva() {
        String sql = "UPDATE reserva SET status_reserva = ? WHERE id_reserva = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, campoNovoStatus.getText());
            stmt.setInt(2, Integer.parseInt(campoIdReserva.getText()));

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                JOptionPane.showMessageDialog(this, "Reserva atualizada com sucesso!");
                campoIdReserva.setText("");
                campoNovoStatus.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Nenhuma reserva encontrada com esse ID.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar reserva: " + ex.getMessage());
        }
    }
}
