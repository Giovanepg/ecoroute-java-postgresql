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

public class TelaVeiculos extends JFrame {

    private final Color corPrimaria = new Color(58, 128, 164);
    private final Color corFundo = new Color(243, 247, 245);
    private final Color corPainel = Color.WHITE;
    private final Color corTexto = new Color(34, 40, 49);
    private final Color corTextoSuave = new Color(101, 113, 123);
    private final Color corBorda = new Color(211, 220, 216);
    private final Color corErro = new Color(210, 55, 70);
    private final Color corSucesso = new Color(34, 139, 87);

    private JTextField campoModelo;
    private JTextField campoPlaca;
    private JTextField campoCor;
    private JTextField campoCapacidade;
    private JTextField campoIdUsuario;
    private JButton botaoSalvar;
    private JButton botaoCancelar;
    private JLabel labelMensagem;

    public TelaVeiculos() {
        setTitle("EcoRoute - Cadastro de Veículo");
        setSize(660, 560);
        setMinimumSize(new Dimension(560, 500));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setBackground(corFundo);

        JPanel painelPrincipal = new JPanel(new BorderLayout(0, 18));
        painelPrincipal.setBorder(new EmptyBorder(24, 30, 24, 30));
        painelPrincipal.setBackground(corFundo);

        painelPrincipal.add(criarCabecalho(), BorderLayout.NORTH);
        painelPrincipal.add(criarFormulario(), BorderLayout.CENTER);
        painelPrincipal.add(criarRodape(), BorderLayout.SOUTH);

        add(painelPrincipal);
        setVisible(true);
    }

    private JPanel criarCabecalho() {
        JPanel painelCabecalho = new JPanel(new BorderLayout(12, 4));
        painelCabecalho.setBackground(corFundo);

        JLabel titulo = new JLabel("Novo Veículo");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titulo.setForeground(corTexto);

        JLabel subtitulo = new JLabel("Cadastre os dados do veículo e vincule-o ao motorista responsável.");
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
        icone.setBorder(BorderFactory.createLineBorder(new Color(42, 102, 134), 1));

        painelCabecalho.add(icone, BorderLayout.WEST);
        painelCabecalho.add(textos, BorderLayout.CENTER);

        return painelCabecalho;
    }

    private JPanel criarFormulario() {
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

        adicionarSecao(painelForm, gbc, "Dados do veículo", linha++);
        adicionarCampo(painelForm, gbc, "Modelo", "Exemplo: Gol, Civic, Onix", campoModelo = criarCampoTexto(), linha++);
        adicionarCampo(painelForm, gbc, "Placa", "Identificação do veículo", campoPlaca = criarCampoTexto(), linha++);
        adicionarCampo(painelForm, gbc, "Cor", "Cor principal do veículo", campoCor = criarCampoTexto(), linha++);
        adicionarCampo(painelForm, gbc, "Capacidade", "Quantidade de lugares disponíveis", campoCapacidade = criarCampoTexto(), linha++);

        adicionarSecao(painelForm, gbc, "Vínculo", linha++);
        adicionarCampo(painelForm, gbc, "ID do motorista", "Usuário responsável pelo veículo", campoIdUsuario = criarCampoTexto(), linha++);

        gbc.gridx = 0;
        gbc.gridy = linha;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(12, 8, 0, 8);
        labelMensagem = new JLabel(" ");
        labelMensagem.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelMensagem.setHorizontalAlignment(SwingConstants.CENTER);
        painelForm.add(labelMensagem, gbc);

        return painelForm;
    }

    private JPanel criarRodape() {
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        painelBotoes.setBackground(corFundo);

        botaoSalvar = criarBotao("Salvar veículo", corPrimaria);
        botaoCancelar = criarBotao("Cancelar", new Color(108, 117, 125));

        botaoSalvar.addActionListener(e -> salvarVeiculo());
        botaoCancelar.addActionListener(e -> dispose());

        painelBotoes.add(botaoCancelar);
        painelBotoes.add(botaoSalvar);

        return painelBotoes;
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

    private void salvarVeiculo() {
        labelMensagem.setText("");
        labelMensagem.setForeground(corErro);
        resetarBordas();

        String modelo = campoModelo.getText().trim();
        String placa = campoPlaca.getText().trim();
        String cor = campoCor.getText().trim();
        String capacidadeTexto = campoCapacidade.getText().trim();
        String idUsuarioTexto = campoIdUsuario.getText().trim();

        if (modelo.isEmpty()) {
            exibirErroCampo(campoModelo, "Por favor, informe o modelo do veículo.");
            return;
        }

        if (placa.isEmpty()) {
            exibirErroCampo(campoPlaca, "Por favor, informe a placa do veículo.");
            return;
        }

        if (cor.isEmpty()) {
            exibirErroCampo(campoCor, "Por favor, informe a cor do veículo.");
            return;
        }

        int capacidade;
        try {
            capacidade = Integer.parseInt(capacidadeTexto);
            if (capacidade <= 0) {
                exibirErroCampo(campoCapacidade, "A capacidade deve ser maior que zero.");
                return;
            }
        } catch (NumberFormatException ex) {
            exibirErroCampo(campoCapacidade, "Informe uma capacidade válida em números.");
            return;
        }

        int idUsuario;
        try {
            idUsuario = Integer.parseInt(idUsuarioTexto);
            if (idUsuario <= 0) {
                exibirErroCampo(campoIdUsuario, "O ID do motorista deve ser maior que zero.");
                return;
            }
        } catch (NumberFormatException ex) {
            exibirErroCampo(campoIdUsuario, "Informe um ID de motorista válido em números.");
            return;
        }

        String sql = "INSERT INTO veiculo (modelo, placa, cor, capacidade, id_usuario) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, modelo);
            stmt.setString(2, placa);
            stmt.setString(3, cor);
            stmt.setInt(4, capacidade);
            stmt.setInt(5, idUsuario);

            stmt.executeUpdate();

            labelMensagem.setForeground(corSucesso);
            labelMensagem.setText("Veículo cadastrado com sucesso.");
            JOptionPane.showMessageDialog(this, "Veículo cadastrado com sucesso!");
            limparCampos();

        } catch (Exception ex) {
            labelMensagem.setForeground(corErro);
            labelMensagem.setText("Erro ao salvar veículo.");
            JOptionPane.showMessageDialog(this, "Erro ao salvar veículo: " + ex.getMessage());
        }
    }

    private void exibirErroCampo(JTextField campo, String mensagem) {
        campo.setBorder(criarBordaCampo(corErro, 2));
        labelMensagem.setForeground(corErro);
        labelMensagem.setText(mensagem);
        campo.requestFocus();
    }

    private void resetarBordas() {
        campoModelo.setBorder(criarBordaCampo(corBorda, 1));
        campoPlaca.setBorder(criarBordaCampo(corBorda, 1));
        campoCor.setBorder(criarBordaCampo(corBorda, 1));
        campoCapacidade.setBorder(criarBordaCampo(corBorda, 1));
        campoIdUsuario.setBorder(criarBordaCampo(corBorda, 1));
    }

    private void limparCampos() {
        campoModelo.setText("");
        campoPlaca.setText("");
        campoCor.setText("");
        campoCapacidade.setText("");
        campoIdUsuario.setText("");
        resetarBordas();
        campoModelo.requestFocus();
    }
}
