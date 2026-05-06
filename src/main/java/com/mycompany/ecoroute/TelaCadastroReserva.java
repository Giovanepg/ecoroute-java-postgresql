/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecoroute;

/**
 *
 * @author blacklegen
 */
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TelaCadastroReserva extends JFrame {

    private final Color corPrimaria = new Color(34, 139, 87);
    private final Color corFundo = new Color(243, 247, 245);
    private final Color corPainel = Color.WHITE;
    private final Color corTexto = new Color(34, 40, 49);
    private final Color corTextoSuave = new Color(101, 113, 123);
    private final Color corBorda = new Color(211, 220, 216);
    private final Color corErro = new Color(210, 55, 70);

    private JTextField campoDataReserva;
    private JTextField campoStatusReserva;
    private JTextField campoIdUsuario;
    private JTextField campoIdCarona;
    private JButton botaoSalvar;
    private JButton botaoCancelar;
    private JLabel labelMensagem;

    public TelaCadastroReserva() {
        setTitle("EcoRoute - Cadastro de Reserva");
        setSize(650, 500);
        setMinimumSize(new Dimension(560, 460));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setBackground(corFundo);

        JPanel painelPrincipal = new JPanel(new BorderLayout(0, 18));
        painelPrincipal.setBorder(new EmptyBorder(24, 30, 24, 30));
        painelPrincipal.setBackground(corFundo);

        painelPrincipal.add(criarCabecalho(), BorderLayout.NORTH);

        JPanel painelForm = new JPanel(new GridBagLayout());
        painelForm.setBackground(corPainel);
        painelForm.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(225, 232, 228), 1),
            new EmptyBorder(24, 26, 18, 26)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 8, 7, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        int linha = 0;

        adicionarSecao(painelForm, gbc, "Dados da reserva", linha++);
        adicionarCampo(painelForm, gbc, "Data da reserva", "Formato: AAAA-MM-DD", campoDataReserva = criarCampoTexto(), linha++);
        adicionarCampo(painelForm, gbc, "Status da reserva", "Exemplo: Pendente ou Confirmada", campoStatusReserva = criarCampoTexto(), linha++);

        adicionarSecao(painelForm, gbc, "Vinculos", linha++);
        adicionarCampo(painelForm, gbc, "ID do passageiro", "Usuario que esta fazendo a reserva", campoIdUsuario = criarCampoTexto(), linha++);
        adicionarCampo(painelForm, gbc, "ID da carona", "Carona escolhida pelo passageiro", campoIdCarona = criarCampoTexto(), linha++);

        gbc.gridx = 0;
        gbc.gridy = linha++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(12, 8, 0, 8);
        labelMensagem = new JLabel(" ");
        labelMensagem.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelMensagem.setHorizontalAlignment(SwingConstants.CENTER);
        painelForm.add(labelMensagem, gbc);

        painelPrincipal.add(painelForm, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        painelBotoes.setBackground(corFundo);

        botaoSalvar = criarBotao("Salvar reserva", corPrimaria);
        botaoCancelar = criarBotao("Cancelar", new Color(108, 117, 125));

        botaoSalvar.addActionListener(e -> salvarReserva());
        botaoCancelar.addActionListener(e -> dispose());

        painelBotoes.add(botaoCancelar);
        painelBotoes.add(botaoSalvar);

        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        add(painelPrincipal);
        setVisible(true);
    }

    private JPanel criarCabecalho() {
        JPanel painelCabecalho = new JPanel(new BorderLayout(12, 4));
        painelCabecalho.setBackground(corFundo);

        JLabel titulo = new JLabel("Nova Reserva");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titulo.setForeground(corTexto);

        JLabel subtitulo = new JLabel("Registre a reserva de uma carona para um passageiro.");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitulo.setForeground(corTextoSuave);

        JPanel textos = new JPanel(new GridLayout(2, 1, 0, 2));
        textos.setBackground(corFundo);
        textos.add(titulo);
        textos.add(subtitulo);

        JLabel icone = new JLabel("EC");
        icone.setHorizontalAlignment(SwingConstants.CENTER);
        icone.setVerticalAlignment(SwingConstants.CENTER);
        icone.setOpaque(true);
        icone.setPreferredSize(new Dimension(54, 54));
        icone.setFont(new Font("Segoe UI", Font.BOLD, 18));
        icone.setForeground(Color.WHITE);
        icone.setBackground(corPrimaria);
        icone.setBorder(BorderFactory.createLineBorder(new Color(25, 120, 74), 1));

        painelCabecalho.add(icone, BorderLayout.WEST);
        painelCabecalho.add(textos, BorderLayout.CENTER);

        return painelCabecalho;
    }

    private JTextField criarCampoTexto() {
        JTextField campo = new JTextField(25);
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setForeground(corTexto);
        campo.setBackground(new Color(252, 253, 252));
        campo.setCaretColor(corPrimaria);
        campo.setBorder(criarBordaCampo(corBorda, 1));
        campo.setPreferredSize(new Dimension(260, 40));
        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campo.setBorder(criarBordaCampo(corPrimaria, 2));
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                campo.setBorder(criarBordaCampo(corBorda, 1));
            }
        });
        return campo;
    }

    private JButton criarBotao(String texto, Color cor) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(12, 22, 12, 22));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setPreferredSize(new Dimension(150, 44));

        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(cor.brighter());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(cor);
            }
        });

        return botao;
    }

    private void adicionarSecao(JPanel painel, GridBagConstraints gbc, String titulo, int linha) {
        gbc.gridx = 0;
        gbc.gridy = linha;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(linha == 0 ? 0 : 16, 8, 4, 8);

        JLabel labelSecao = new JLabel(titulo);
        labelSecao.setFont(new Font("Segoe UI", Font.BOLD, 16));
        labelSecao.setForeground(corPrimaria);
        painel.add(labelSecao, gbc);
    }

    private void adicionarCampo(JPanel painel, GridBagConstraints gbc, String label, String dica, JTextField campo, int linha) {
        gbc.gridx = 0;
        gbc.gridy = linha;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(7, 8, 7, 8);

        JPanel painelLabel = new JPanel(new GridLayout(2, 1, 0, 1));
        painelLabel.setBackground(corPainel);

        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbl.setForeground(corTexto);

        JLabel lblDica = new JLabel(dica);
        lblDica.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblDica.setForeground(corTextoSuave);

        painelLabel.add(lbl);
        painelLabel.add(lblDica);
        painel.add(painelLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        painel.add(campo, gbc);
    }

    private javax.swing.border.Border criarBordaCampo(Color cor, int largura) {
        return BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(cor, largura),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        );
    }

    private void salvarReserva() {
        labelMensagem.setText("");
        labelMensagem.setForeground(corErro);
        resetarBordas();

        String dataReserva = campoDataReserva.getText().trim();
        String statusReserva = campoStatusReserva.getText().trim();
        String idUsuario = campoIdUsuario.getText().trim();
        String idCarona = campoIdCarona.getText().trim();

        boolean erro = false;

        if (dataReserva.isEmpty()) {
            campoDataReserva.setBorder(criarBordaCampo(corErro, 2));
            labelMensagem.setText("Por favor, informe a data da reserva.");
            erro = true;
        } else {
            try {
                java.sql.Date.valueOf(dataReserva);
            } catch (IllegalArgumentException e) {
                campoDataReserva.setBorder(criarBordaCampo(corErro, 2));
                labelMensagem.setText("Data invalida! Use o formato AAAA-MM-DD.");
                erro = true;
            }
        }

        if (statusReserva.isEmpty()) {
            campoStatusReserva.setBorder(criarBordaCampo(corErro, 2));
            labelMensagem.setText("Por favor, informe o status da reserva.");
            erro = true;
        } else if (statusReserva.length() > 30) {
            campoStatusReserva.setBorder(criarBordaCampo(corErro, 2));
            labelMensagem.setText("Status deve ter no maximo 30 caracteres.");
            erro = true;
        }

        if (idUsuario.isEmpty()) {
            campoIdUsuario.setBorder(criarBordaCampo(corErro, 2));
            labelMensagem.setText("Por favor, informe o ID do passageiro.");
            erro = true;
        } else {
            try {
                int id = Integer.parseInt(idUsuario);
                if (id <= 0) {
                    campoIdUsuario.setBorder(criarBordaCampo(corErro, 2));
                    labelMensagem.setText("ID do passageiro deve ser maior que zero.");
                    erro = true;
                }
            } catch (NumberFormatException e) {
                campoIdUsuario.setBorder(criarBordaCampo(corErro, 2));
                labelMensagem.setText("ID do passageiro deve ser um numero inteiro.");
                erro = true;
            }
        }

        if (idCarona.isEmpty()) {
            campoIdCarona.setBorder(criarBordaCampo(corErro, 2));
            labelMensagem.setText("Por favor, informe o ID da carona.");
            erro = true;
        } else {
            try {
                int id = Integer.parseInt(idCarona);
                if (id <= 0) {
                    campoIdCarona.setBorder(criarBordaCampo(corErro, 2));
                    labelMensagem.setText("ID da carona deve ser maior que zero.");
                    erro = true;
                }
            } catch (NumberFormatException e) {
                campoIdCarona.setBorder(criarBordaCampo(corErro, 2));
                labelMensagem.setText("ID da carona deve ser um numero inteiro.");
                erro = true;
            }
        }

        if (erro) {
            return;
        }

        String sql = "INSERT INTO reserva (data_reserva, status_reserva, id_usuario, id_carona) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, java.sql.Date.valueOf(dataReserva));
            stmt.setString(2, statusReserva);
            stmt.setInt(3, Integer.parseInt(idUsuario));
            stmt.setInt(4, Integer.parseInt(idCarona));

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                labelMensagem.setForeground(corPrimaria);
                labelMensagem.setText("Reserva cadastrada com sucesso!");

                javax.swing.Timer timer = new javax.swing.Timer(1000, e -> {
                    campoDataReserva.setText("");
                    campoStatusReserva.setText("");
                    campoIdUsuario.setText("");
                    campoIdCarona.setText("");
                    labelMensagem.setText("");
                    resetarBordas();
                });
                timer.setRepeats(false);
                timer.start();
            }

        } catch (Exception ex) {
            labelMensagem.setForeground(corErro);
            labelMensagem.setText("Erro ao salvar reserva: " + ex.getMessage());
            System.err.println("Erro ao salvar reserva: " + ex.getMessage());
        }
    }

    private void resetarBordas() {
        campoDataReserva.setBorder(criarBordaCampo(corBorda, 1));
        campoStatusReserva.setBorder(criarBordaCampo(corBorda, 1));
        campoIdUsuario.setBorder(criarBordaCampo(corBorda, 1));
        campoIdCarona.setBorder(criarBordaCampo(corBorda, 1));
    }
}
