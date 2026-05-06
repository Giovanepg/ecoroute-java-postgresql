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

public class TelaCadastroUsuario extends JFrame {

    private final Color corPrimaria = new Color(34, 139, 87);
    private final Color corFundo = new Color(243, 247, 245);
    private final Color corPainel = Color.WHITE;
    private final Color corTexto = new Color(34, 40, 49);
    private final Color corTextoSuave = new Color(101, 113, 123);
    private final Color corBorda = new Color(211, 220, 216);
    private final Color corErro = new Color(210, 55, 70);
    private final Color corSucesso = new Color(34, 139, 87);

    private JTextField campoNome;
    private JTextField campoEmail;
    private JTextField campoTelefone;
    private JTextField campoLogin;
    private JPasswordField campoSenha;
    private JPasswordField campoConfirmarSenha;
    private JComboBox<String> comboTipoUsuario;
    private JButton botaoSalvar;
    private JButton botaoCancelar;
    private JLabel labelMensagem;

    public TelaCadastroUsuario() {
        setTitle("EcoRoute - Cadastro de Usuário");
        setSize(720, 650);
        setMinimumSize(new Dimension(620, 560));
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
        getRootPane().setDefaultButton(botaoSalvar);
        setVisible(true);
    }

    private JPanel criarCabecalho() {
        JPanel painelCabecalho = new JPanel(new BorderLayout(12, 4));
        painelCabecalho.setBackground(corFundo);

        JLabel titulo = new JLabel("Criar Conta");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titulo.setForeground(corTexto);

        JLabel subtitulo = new JLabel("Cadastre um novo usuário para acessar o EcoRoute.");
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
        adicionarCampo(painelForm, gbc, "Nome", "Nome completo do usuário", campoNome = criarCampoTexto(), linha++);
        adicionarCampo(painelForm, gbc, "Email", "Endereço de contato", campoEmail = criarCampoTexto(), linha++);
        adicionarCampo(painelForm, gbc, "Telefone", "Telefone com DDD", campoTelefone = criarCampoTexto(), linha++);
        adicionarCampo(painelForm, gbc, "Login", "Mínimo de 3 caracteres", campoLogin = criarCampoTexto(), linha++);
        adicionarCampo(painelForm, gbc, "Senha", "Mínimo de 6 caracteres", campoSenha = criarCampoSenha(), linha++);
        adicionarCampo(painelForm, gbc, "Confirmar senha", "Repita a senha informada", campoConfirmarSenha = criarCampoSenha(), linha++);
        adicionarCampoTipo(painelForm, gbc, linha++);

        gbc.gridx = 0;
        gbc.gridy = linha;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(12, 8, 0, 8);
        labelMensagem = new JLabel(" ");
        labelMensagem.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelMensagem.setHorizontalAlignment(SwingConstants.CENTER);
        painelForm.add(labelMensagem, gbc);

        JScrollPane scrollPane = new JScrollPane(painelForm);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBackground(corFundo);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(corFundo);
        wrapper.add(scrollPane, BorderLayout.CENTER);

        return wrapper;
    }

    private JPanel criarRodape() {
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        painelBotoes.setBackground(corFundo);

        botaoSalvar = criarBotao("Salvar usuário", corPrimaria);
        botaoCancelar = criarBotao("Cancelar", new Color(108, 117, 125));

        botaoSalvar.addActionListener(e -> salvarUsuario());
        botaoCancelar.addActionListener(e -> dispose());

        painelBotoes.add(botaoCancelar);
        painelBotoes.add(botaoSalvar);

        return painelBotoes;
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
        campo.setPreferredSize(new Dimension(280, 40));
        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campo.setBorder(criarBordaCampo(corPrimaria, 2));
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                campo.setBorder(criarBordaCampo(corBorda, 1));
            }
        });
    }

    private JComboBox<String> criarComboTipoUsuario() {
        JComboBox<String> combo = new JComboBox<>(new String[]{"passageiro", "motorista"});
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setForeground(corTexto);
        combo.setBackground(new Color(252, 253, 252));
        combo.setBorder(criarBordaCampo(corBorda, 1));
        combo.setPreferredSize(new Dimension(280, 40));
        return combo;
    }

    private JButton criarBotao(String texto, Color cor) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(12, 22, 12, 22));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setPreferredSize(new Dimension(160, 44));

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
        gbc.insets = new Insets(7, 8, 7, 8);

        JPanel painelLabel = criarPainelLabel(label, dica);
        painel.add(painelLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        painel.add(campo, gbc);
    }

    private void adicionarCampoTipo(JPanel painel, GridBagConstraints gbc, int linha) {
        gbc.gridx = 0;
        gbc.gridy = linha;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(7, 8, 7, 8);

        painel.add(criarPainelLabel("Tipo de usuário", "Perfil de acesso no sistema"), gbc);

        comboTipoUsuario = criarComboTipoUsuario();
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        painel.add(comboTipoUsuario, gbc);
    }

    private JPanel criarPainelLabel(String label, String dica) {
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
        return painelLabel;
    }

    private javax.swing.border.Border criarBordaCampo(Color cor, int largura) {
        return BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(cor, largura),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        );
    }

    private void salvarUsuario() {
        labelMensagem.setText("");
        labelMensagem.setForeground(corErro);
        resetarBordas();

        String nome = campoNome.getText().trim();
        String email = campoEmail.getText().trim();
        String telefone = campoTelefone.getText().trim();
        String login = campoLogin.getText().trim();
        String senha = new String(campoSenha.getPassword()).trim();
        String confirmarSenha = new String(campoConfirmarSenha.getPassword()).trim();
        String tipoUsuario = comboTipoUsuario.getSelectedItem().toString();

        if (nome.isEmpty()) {
            exibirErroCampo(campoNome, "Por favor, informe o nome.");
            return;
        }

        if (email.isEmpty() || !email.contains("@")) {
            exibirErroCampo(campoEmail, "Informe um email válido.");
            return;
        }

        if (telefone.isEmpty()) {
            exibirErroCampo(campoTelefone, "Por favor, informe o telefone.");
            return;
        }

        if (login.length() < 3 || login.length() > 50) {
            exibirErroCampo(campoLogin, "Login deve ter entre 3 e 50 caracteres.");
            return;
        }

        if (senha.length() < 6 || senha.length() > 100) {
            exibirErroCampo(campoSenha, "Senha deve ter entre 6 e 100 caracteres.");
            return;
        }

        if (!senha.equals(confirmarSenha)) {
            exibirErroCampo(campoConfirmarSenha, "As senhas informadas não conferem.");
            return;
        }

        if (loginJaExiste(login)) {
            exibirErroCampo(campoLogin, "Já existe um usuário com esse login.");
            return;
        }

        String sql = "INSERT INTO usuario (nome, email, telefone, login, senha, tipo_usuario) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, telefone);
            stmt.setString(4, login);
            stmt.setString(5, senha);
            stmt.setString(6, tipoUsuario);

            stmt.executeUpdate();

            labelMensagem.setForeground(corSucesso);
            labelMensagem.setText("Usuário cadastrado com sucesso.");
            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
            limparCampos();

        } catch (Exception ex) {
            labelMensagem.setForeground(corErro);
            labelMensagem.setText("Erro ao cadastrar usuário.");
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar usuário: " + ex.getMessage());
        }
    }

    private boolean loginJaExiste(String login) {
        String sql = "SELECT 1 FROM usuario WHERE login = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (Exception ex) {
            labelMensagem.setForeground(corErro);
            labelMensagem.setText("Erro ao verificar login.");
            System.err.println("Erro ao verificar login: " + ex.getMessage());
            return true;
        }
    }

    private void exibirErroCampo(JTextField campo, String mensagem) {
        campo.setBorder(criarBordaCampo(corErro, 2));
        labelMensagem.setForeground(corErro);
        labelMensagem.setText(mensagem);
        campo.requestFocus();
    }

    private void resetarBordas() {
        campoNome.setBorder(criarBordaCampo(corBorda, 1));
        campoEmail.setBorder(criarBordaCampo(corBorda, 1));
        campoTelefone.setBorder(criarBordaCampo(corBorda, 1));
        campoLogin.setBorder(criarBordaCampo(corBorda, 1));
        campoSenha.setBorder(criarBordaCampo(corBorda, 1));
        campoConfirmarSenha.setBorder(criarBordaCampo(corBorda, 1));
    }

    private void limparCampos() {
        campoNome.setText("");
        campoEmail.setText("");
        campoTelefone.setText("");
        campoLogin.setText("");
        campoSenha.setText("");
        campoConfirmarSenha.setText("");
        comboTipoUsuario.setSelectedIndex(0);
        resetarBordas();
        campoNome.requestFocus();
    }
}
