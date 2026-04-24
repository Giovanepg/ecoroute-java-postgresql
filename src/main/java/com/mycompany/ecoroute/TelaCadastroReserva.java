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

public class TelaCadastroReserva extends JFrame {

    private JTextField campoDataReserva;
    private JTextField campoStatusReserva;
    private JTextField campoIdUsuario;
    private JTextField campoIdCarona;
    private JButton botaoSalvar;

    public TelaCadastroReserva() {
        setTitle("EcoRoute - Cadastro de Reserva");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        campoDataReserva = new JTextField();
        campoStatusReserva = new JTextField();
        campoIdUsuario = new JTextField();
        campoIdCarona = new JTextField();

        botaoSalvar = new JButton("Salvar");

        add(new JLabel("Data da Reserva (AAAA-MM-DD):"));
        add(campoDataReserva);

        add(new JLabel("Status da Reserva:"));
        add(campoStatusReserva);

        add(new JLabel("ID do Passageiro:"));
        add(campoIdUsuario);

        add(new JLabel("ID da Carona:"));
        add(campoIdCarona);

        add(new JLabel(""));
        add(botaoSalvar);

        botaoSalvar.addActionListener(e -> salvarReserva());

        setVisible(true);
    }

    private void salvarReserva() {
        String sql = "INSERT INTO reserva (data_reserva, status_reserva, id_usuario, id_carona) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, java.sql.Date.valueOf(campoDataReserva.getText()));
            stmt.setString(2, campoStatusReserva.getText());
            stmt.setInt(3, Integer.parseInt(campoIdUsuario.getText()));
            stmt.setInt(4, Integer.parseInt(campoIdCarona.getText()));

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Reserva cadastrada com sucesso!");

            campoDataReserva.setText("");
            campoStatusReserva.setText("");
            campoIdUsuario.setText("");
            campoIdCarona.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar reserva: " + ex.getMessage());
        }
    }
}
