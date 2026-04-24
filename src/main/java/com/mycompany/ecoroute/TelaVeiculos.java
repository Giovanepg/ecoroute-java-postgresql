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

public class TelaVeiculos extends JFrame {

    private JTextField campoModelo;
    private JTextField campoPlaca;
    private JTextField campoCor;
    private JTextField campoCapacidade;
    private JTextField campoIdUsuario;
    private JButton botaoSalvar;

    public TelaVeiculos() {
        setTitle("EcoRoute - Cadastro de Veículo");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));

        campoModelo = new JTextField();
        campoPlaca = new JTextField();
        campoCor = new JTextField();
        campoCapacidade = new JTextField();
        campoIdUsuario = new JTextField();

        botaoSalvar = new JButton("Salvar");

        add(new JLabel("Modelo:"));
        add(campoModelo);

        add(new JLabel("Placa:"));
        add(campoPlaca);

        add(new JLabel("Cor:"));
        add(campoCor);

        add(new JLabel("Capacidade:"));
        add(campoCapacidade);

        add(new JLabel("ID do Motorista:"));
        add(campoIdUsuario);

        add(new JLabel(""));
        add(botaoSalvar);

        botaoSalvar.addActionListener(e -> salvarVeiculo());

        setVisible(true);
    }

    private void salvarVeiculo() {
        String sql = "INSERT INTO veiculo (modelo, placa, cor, capacidade, id_usuario) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, campoModelo.getText());
            stmt.setString(2, campoPlaca.getText());
            stmt.setString(3, campoCor.getText());
            stmt.setInt(4, Integer.parseInt(campoCapacidade.getText()));
            stmt.setInt(5, Integer.parseInt(campoIdUsuario.getText()));

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Veículo cadastrado com sucesso!");

            campoModelo.setText("");
            campoPlaca.setText("");
            campoCor.setText("");
            campoCapacidade.setText("");
            campoIdUsuario.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar veículo: " + ex.getMessage());
        }
    }
}
