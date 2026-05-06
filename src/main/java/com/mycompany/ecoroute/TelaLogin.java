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
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TelaLogin extends JFrame {

    private final Color corPrimaria = new Color(34, 139, 87);
    private final Color corFundo = new Color(243, 247, 245);
    private final Color corPainel = Color.WHITE;
    private final Color corTexto = new Color(34, 40, 49);
    private final Color corTextoSuave = new Color(101, 113, 123);
    private final Color corBorda = new Color(211, 220, 216);
    private final Color corErro = new Color(210, 55, 70);
    private final Color corSucesso = new Color(34, 139, 87);

    private JTextField campoLogin;
    private JPasswordField campoSenha;
    private JButton botaoEntrar;
    private JButton botaoCadastrar;
    private JLabel labelMensagem;

    public TelaLogin() {
        setTitle("EcoRoute - Login");
        setSize(720, 480);
        setMinimumSize(new Dimension(620, 430));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(corFundo);

        JPanel painelPrincipal = new JPanel(new BorderLayout(0, 20));
        painelPrincipal.setBorder(new EmptyBorder(30, 34, 28, 34));
        painelPrincipal.setBackground(corFundo);

        painelPrincipal.add(criarCabecalho(), BorderLayout.NORTH);
        painelPrincipal.add(criarFormulario(), BorderLayout.CENTER);
        painelPrincipal.add(criarRodape(), BorderLayout.SOUTH);

        add(painelPrincipal);
        getRootPane().setDefaultButton(botaoEntrar);
        setVisible(true);
    }

    private JPanel criarCabecalho() {
        JPanel painelCabecalho = new JPanel(new BorderLayout(14, 4));
        painelCabecalho.setBackground(corFundo);

        JLabel titulo = new JLabel("EcoRoute");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 34));
        titulo.setForeground(corTexto);

        JLabel subtitulo = new JLabel("Acesse o sistema de caronas compartilhadas.");
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
        icone.setPreferredSize(new Dimension(58, 58));
        icone.setFont(new Font("Segoe UI", Font.BOLD, 19));
        icone.setForeground(Color.WHITE);
        icone.setBackground(corPrimaria);
        icone.setBorder(BorderFactory.createLineBorder(new Color(25, 120, 74), 1));

        painelCabecalho.add(icone, BorderLayout.WEST);
        painelCabecalho.add(textos, BorderLayout.CENTER);

        return painelCabecalho;
    }

    private JPanel criarFormulario() {
        JPanel painelForm = new JPanel(new GridBagLayout());
        painelForm.setBackground(corPainel);
        painelForm.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(225, 232, 228), 1),
            new EmptyBorder(28, 30, 24, 30)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        int linha = 0;
        adicionarCampo(painelForm, gbc, "Login", "Informe seu usuário de acesso", campoLogin = criarCampoTexto(), linha++);
        adicionarCampo(painelForm, gbc, "Senha", "Informe sua senha", campoSenha = criarCampoSenha(), linha++);

        gbc.gridx = 0;
        gbc.gridy = linha++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(12, 8, 2, 8);
        labelMensagem = new JLabel(" ");
        labelMensagem.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelMensagem.setHorizontalAlignment(SwingConstants.CENTER);
        painelForm.add(labelMensagem, gbc);

        gbc.gridx = 0;
        gbc.gridy = linha;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 8, 0, 8);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        painelBotoes.setBackground(corPainel);

        botaoCadastrar = criarBotao("Criar conta", new Color(58, 128, 164));
        botaoEntrar = criarBotao("Entrar", corPrimaria);

        botaoCadastrar.addActionListener(e -> new TelaCadastroUsuario());
        botaoEntrar.addActionListener(e -> autenticar());

        painelBotoes.add(botaoCadastrar);
        painelBotoes.add(botaoEntrar);
        painelForm.add(painelBotoes, gbc);

        return painelForm;
    }

    private JPanel criarRodape() {
        JPanel painelRodape = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        painelRodape.setBackground(corFundo);

        JLabel rodape = new JLabel("Sistema de Caronas Compartilhadas");
        rodape.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        rodape.setForeground(corTextoSuave);
        painelRodape.add(rodape);

        return painelRodape;
    }

    private JTextField criarCampoTexto() {
        JTextField campo = new JTextField(25);
        configurarCampo(campo);
        return campo;
    }

    private JPasswordField criarCampoSenha() {
        JPasswordField campo = new JPasswordField(25);
        configurarCampo(campo);
        return campo;
    }

    private void configurarCampo(JTextField campo) {
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setForeground(corTexto);
        campo.setBackground(new Color(252, 253, 252));
        campo.setCaretColor(corPrimaria);
        campo.setBorder(criarBordaCampo(corBorda, 1));
        campo.setPreferredSize(new Dimension(300, 42));
        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campo.setBorder(criarBordaCampo(corPrimaria, 2));
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                campo.setBorder(criarBordaCampo(corBorda, 1));
            }
        });
    }

    private JButton criarBotao(String texto, Color cor) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(12, 22, 12, 22));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setPreferredSize(new Dimension(180, 46));

        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (botao.isEnabled()) {
                    botao.setBackground(cor.brighter());
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(cor);
            }
        });

        return botao;
    }

    private void adicionarCampo(JPanel painel, GridBagConstraints gbc, String label, String dica, JTextField campo, int linha) {
        gbc.gridx = 0;
        gbc.gridy = linha;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(8, 8, 8, 8);

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

    private void autenticar() {
        labelMensagem.setText("");
        labelMensagem.setForeground(corErro);
        resetarBordas();

        String login = campoLogin.getText().trim();
        String senha = new String(campoSenha.getPassword()).trim();

        if (login.isEmpty()) {
            exibirErroCampo(campoLogin, "Por favor, informe o login.");
            return;
        }

        if (login.length() < 3) {
            exibirErroCampo(campoLogin, "Login deve ter no mínimo 3 caracteres.");
            return;
        }

        if (login.length() > 50) {
            exibirErroCampo(campoLogin, "Login deve ter no máximo 50 caracteres.");
            return;
        }

        if (senha.isEmpty()) {
            exibirErroCampo(campoSenha, "Por favor, informe a senha.");
            return;
        }

        if (senha.length() < 6) {
            exibirErroCampo(campoSenha, "Senha deve ter no mínimo 6 caracteres.");
            return;
        }

        if (senha.length() > 100) {
            exibirErroCampo(campoSenha, "Senha deve ter no máximo 100 caracteres.");
            return;
        }

        botaoEntrar.setEnabled(false);
        botaoEntrar.setText("Entrando...");
        labelMensagem.setForeground(corTextoSuave);
        labelMensagem.setText("Validando credenciais.");

        String sql = "SELECT id_usuario, nome, email FROM usuario WHERE login = ? AND senha = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, limitarTexto(login, 50));
            stmt.setString(2, limitarTexto(senha, 100));

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nomeUsuario = rs.getString("nome");

                campoLogin.setBorder(criarBordaCampo(corSucesso, 2));
                campoSenha.setBorder(criarBordaCampo(corSucesso, 2));
                labelMensagem.setForeground(corSucesso);
                labelMensagem.setText("Bem-vindo, " + nomeUsuario + ".");

                Timer timer = new Timer(900, e -> {
                    dispose();
                    new TelaMenu();
                });
                timer.setRepeats(false);
                timer.start();

            } else {
                campoLogin.setBorder(criarBordaCampo(corErro, 2));
                campoSenha.setBorder(criarBordaCampo(corErro, 2));
                labelMensagem.setForeground(corErro);
                labelMensagem.setText("Login ou senha inválidos.");
                campoSenha.setText("");
                botaoEntrar.setEnabled(true);
                botaoEntrar.setText("Entrar");
                campoSenha.requestFocus();
            }

        } catch (Exception ex) {
            labelMensagem.setForeground(corErro);
            labelMensagem.setText("Erro de conexão: " + ex.getMessage());
            botaoEntrar.setEnabled(true);
            botaoEntrar.setText("Entrar");
            System.err.println("Erro ao autenticar: " + ex.getMessage());
        }
    }

    private void exibirErroCampo(JTextField campo, String mensagem) {
        campo.setBorder(criarBordaCampo(corErro, 2));
        labelMensagem.setForeground(corErro);
        labelMensagem.setText(mensagem);
        campo.requestFocus();
    }

    private String limitarTexto(String texto, int maxLength) {
        if (texto.length() > maxLength) {
            return texto.substring(0, maxLength);
        }
        return texto;
    }

    private void resetarBordas() {
        campoLogin.setBorder(criarBordaCampo(corBorda, 1));
        campoSenha.setBorder(criarBordaCampo(corBorda, 1));
        botaoEntrar.setEnabled(true);
        botaoEntrar.setText("Entrar");
    }
}
