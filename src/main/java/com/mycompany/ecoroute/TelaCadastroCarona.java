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

public class TelaCadastroCarona extends JFrame {

    private JTextField campoOrigem;
    private JTextField campoDestino;
    private JTextField campoDataSaida;
    private JTextField campoHorarioSaida;
    private JTextField campoVagas;
    private JTextField campoValor;
    private JTextField campoStatus;
    private JTextField campoIdUsuario;
    private JTextField campoIdVeiculo;
    private JButton botaoSalvar;

    public TelaCadastroCarona() {
        setTitle("EcoRoute - Cadastro de Carona");
        setSize(450, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(10, 2, 10, 10));

        campoOrigem = new JTextField();
        campoDestino = new JTextField();
        campoDataSaida = new JTextField();
        campoHorarioSaida = new JTextField();
        campoVagas = new JTextField();
        campoValor = new JTextField();
        campoStatus = new JTextField();
        campoIdUsuario = new JTextField();
        campoIdVeiculo = new JTextField();

        botaoSalvar = new JButton("Salvar");

        add(new JLabel("Origem:"));
        add(campoOrigem);

        add(new JLabel("Destino:"));
        add(campoDestino);

        add(new JLabel("Data Saída (AAAA-MM-DD):"));
        add(campoDataSaida);

        add(new JLabel("Horário Saída (HH:MM:SS):"));
        add(campoHorarioSaida);

        add(new JLabel("Vagas Disponíveis:"));
        add(campoVagas);

        add(new JLabel("Valor:"));
        add(campoValor);

        add(new JLabel("Status:"));
        add(campoStatus);

        add(new JLabel("ID do Motorista:"));
        add(campoIdUsuario);

        add(new JLabel("ID do Veículo:"));
        add(campoIdVeiculo);

        add(new JLabel(""));
        add(botaoSalvar);

        botaoSalvar.addActionListener(e -> salvarCarona());

        setVisible(true);
    }

    private void salvarCarona() {
        String sql = "INSERT INTO carona (origem, destino, data_saida, horario_saida, vagas_disponiveis, valor, status, id_usuario, id_veiculo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, campoOrigem.getText());
            stmt.setString(2, campoDestino.getText());
            stmt.setDate(3, java.sql.Date.valueOf(campoDataSaida.getText()));
            stmt.setTime(4, java.sql.Time.valueOf(campoHorarioSaida.getText()));
            stmt.setInt(5, Integer.parseInt(campoVagas.getText()));
            stmt.setBigDecimal(6, new java.math.BigDecimal(campoValor.getText()));
            stmt.setString(7, campoStatus.getText());
            stmt.setInt(8, Integer.parseInt(campoIdUsuario.getText()));
            stmt.setInt(9, Integer.parseInt(campoIdVeiculo.getText()));

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Carona cadastrada com sucesso!");

            campoOrigem.setText("");
            campoDestino.setText("");
            campoDataSaida.setText("");
            campoHorarioSaida.setText("");
            campoVagas.setText("");
            campoValor.setText("");
            campoStatus.setText("");
            campoIdUsuario.setText("");
            campoIdVeiculo.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar carona: " + ex.getMessage());
        }
    }
}
