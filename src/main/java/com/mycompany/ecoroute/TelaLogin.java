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
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaLogin extends JFrame {

    private JTextField campoLogin;
    private JPasswordField campoSenha;
    private JButton botaoEntrar;

    public TelaLogin() {
        setTitle("EcoRoute - Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel labelLogin = new JLabel("Login:");
        campoLogin = new JTextField();

        JLabel labelSenha = new JLabel("Senha:");
        campoSenha = new JPasswordField();

        botaoEntrar = new JButton("Entrar");

        add(new JLabel(""));
        add(new JLabel(""));
        add(labelLogin);
        add(campoLogin);
        add(labelSenha);
        add(campoSenha);
        add(new JLabel(""));
        add(botaoEntrar);

        botaoEntrar.addActionListener(e -> autenticar());

        setVisible(true);
    }

    private void autenticar() {
        String login = campoLogin.getText();
        String senha = new String(campoSenha.getPassword());

        String sql = "SELECT * FROM usuario WHERE login = ? AND senha = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");
                dispose();
                new TelaMenu();
            } else {
                JOptionPane.showMessageDialog(this, "Login ou senha inválidos.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao autenticar: " + ex.getMessage());
        }
    }
}