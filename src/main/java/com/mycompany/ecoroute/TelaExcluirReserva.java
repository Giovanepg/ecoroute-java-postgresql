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

public class TelaExcluirReserva extends JFrame {

    private JTextField campoIdReserva;
    private JButton botaoExcluir;

    public TelaExcluirReserva() {
        setTitle("EcoRoute - Excluir Reserva");
        setSize(400, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(2, 2, 10, 10));

        campoIdReserva = new JTextField();
        botaoExcluir = new JButton("Excluir");

        add(new JLabel("ID da Reserva:"));
        add(campoIdReserva);
        add(new JLabel(""));
        add(botaoExcluir);

        botaoExcluir.addActionListener(e -> excluirReserva());

        setVisible(true);
    }

    private void excluirReserva() {
        String sql = "DELETE FROM reserva WHERE id_reserva = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, Integer.parseInt(campoIdReserva.getText()));

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                JOptionPane.showMessageDialog(this, "Reserva excluída com sucesso!");
                campoIdReserva.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Nenhuma reserva encontrada com esse ID.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir reserva: " + ex.getMessage());
        }
    }
}
