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

public class TelaCadastroCarona extends JFrame {

    private final Color corPrimaria = new Color(34, 139, 87);
    private final Color corFundo = new Color(243, 247, 245);
    private final Color corPainel = Color.WHITE;
    private final Color corTexto = new Color(34, 40, 49);
    private final Color corTextoSuave = new Color(101, 113, 123);
    private final Color corBorda = new Color(211, 220, 216);
    private final Color corErro = new Color(210, 55, 70);

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
    private JButton botaoCancelar;
    private JLabel labelMensagem;

    public TelaCadastroCarona() {
        setTitle("EcoRoute - Cadastro de Carona");
        setSize(720, 690);
        setMinimumSize(new Dimension(620, 620));
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

        adicionarSecao(painelForm, gbc, "Rota", linha++);
        adicionarCampo(painelForm, gbc, "Origem", "Cidade ou ponto de partida", campoOrigem = criarCampoTexto(), linha++);
        adicionarCampo(painelForm, gbc, "Destino", "Cidade ou ponto de chegada", campoDestino = criarCampoTexto(), linha++);

        adicionarSecao(painelForm, gbc, "Data e disponibilidade", linha++);
        adicionarCampo(painelForm, gbc, "Data de saida", "Formato: AAAA-MM-DD", campoDataSaida = criarCampoTexto(), linha++);
        adicionarCampo(painelForm, gbc, "Horario de saida", "Opcional. Formato: HH:MM:SS", campoHorarioSaida = criarCampoTexto(), linha++);
        adicionarCampo(painelForm, gbc, "Vagas disponiveis", "Numero entre 1 e 99", campoVagas = criarCampoTexto(), linha++);
        adicionarCampo(painelForm, gbc, "Valor", "Opcional. Use ponto para centavos", campoValor = criarCampoTexto(), linha++);
        adicionarCampo(painelForm, gbc, "Status", "Padrao: Ativa", campoStatus = criarCampoTexto(), linha++);

        adicionarSecao(painelForm, gbc, "Vinculos", linha++);
        adicionarCampo(painelForm, gbc, "ID do motorista", "Usuario responsavel pela carona", campoIdUsuario = criarCampoTexto(), linha++);
        adicionarCampo(painelForm, gbc, "ID do veiculo", "Veiculo usado na viagem", campoIdVeiculo = criarCampoTexto(), linha++);

        gbc.gridx = 0;
        gbc.gridy = linha++;
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
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        painelBotoes.setBackground(corFundo);

        botaoSalvar = criarBotao("Salvar carona", corPrimaria);
        botaoCancelar = criarBotao("Cancelar", new Color(108, 117, 125));

        botaoSalvar.addActionListener(e -> salvarCarona());
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

        JLabel titulo = new JLabel("Nova Carona");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titulo.setForeground(corTexto);

        JLabel subtitulo = new JLabel("Cadastre a rota, os detalhes da viagem e os vinculos do motorista.");
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

    private void salvarCarona() {
        // Limpar mensagem anterior
        labelMensagem.setText("");
        labelMensagem.setForeground(Color.RED);

        // Validar campos obrigatórios
        boolean erro = false;

        // Validar e limpar campos
        String origem = campoOrigem.getText().trim();
        String destino = campoDestino.getText().trim();
        String dataSaida = campoDataSaida.getText().trim();
        String horarioSaida = campoHorarioSaida.getText().trim();
        String vagas = campoVagas.getText().trim();
        String valor = campoValor.getText().trim();
        String status = campoStatus.getText().trim();
        String idUsuario = campoIdUsuario.getText().trim();
        String idVeiculo = campoIdVeiculo.getText().trim();

        // Resetar bordas
        resetarBordas();

        // Validações de campos obrigatórios
        if (origem.isEmpty()) {
            campoOrigem.setBorder(criarBordaCampo(corErro, 2));
            labelMensagem.setText("Por favor, preencha o campo de origem.");
            erro = true;
        } else if (origem.length() > 100) {
            campoOrigem.setBorder(criarBordaCampo(corErro, 2));
            labelMensagem.setText("Origem deve ter no máximo 100 caracteres.");
            erro = true;
        }

        if (destino.isEmpty()) {
            campoDestino.setBorder(criarBordaCampo(corErro, 2));
            labelMensagem.setText("Por favor, preencha o campo de destino.");
            erro = true;
        } else if (destino.length() > 100) {
            campoDestino.setBorder(criarBordaCampo(corErro, 2));
            labelMensagem.setText("Destino deve ter no máximo 100 caracteres.");
            erro = true;
        }

        if (dataSaida.isEmpty()) {
            campoDataSaida.setBorder(criarBordaCampo(corErro, 2));
            labelMensagem.setText("Por favor, informe a data de saída.");
            erro = true;
        } else {
            try {
                java.sql.Date.valueOf(dataSaida);
            } catch (IllegalArgumentException e) {
                campoDataSaida.setBorder(criarBordaCampo(corErro, 2));
                labelMensagem.setText("Data inválida! Use o formato AAAA-MM-DD.");
                erro = true;
            }
        }

        if (!horarioSaida.isEmpty()) {
            try {
                java.sql.Time.valueOf(horarioSaida);
            } catch (IllegalArgumentException e) {
                campoHorarioSaida.setBorder(criarBordaCampo(corErro, 2));
                labelMensagem.setText("Horário inválido! Use o formato HH:MM:SS.");
                erro = true;
            }
        }

        if (vagas.isEmpty()) {
            campoVagas.setBorder(criarBordaCampo(corErro, 2));
            labelMensagem.setText("Por favor, informe o número de vagas.");
            erro = true;
        } else {
            try {
                int numVagas = Integer.parseInt(vagas);
                if (numVagas <= 0 || numVagas > 99) {
                    campoVagas.setBorder(criarBordaCampo(corErro, 2));
                    labelMensagem.setText("Vagas deve ser um número entre 1 e 99.");
                    erro = true;
                }
            } catch (NumberFormatException e) {
                campoVagas.setBorder(criarBordaCampo(corErro, 2));
                labelMensagem.setText("Número de vagas deve ser um número inteiro.");
                erro = true;
            }
        }

        if (!valor.isEmpty()) {
            try {
                new java.math.BigDecimal(valor);
            } catch (NumberFormatException e) {
                campoValor.setBorder(criarBordaCampo(corErro, 2));
                labelMensagem.setText("Valor inválido! Use ponto para casas decimais (ex: 25.50).");
                erro = true;
            }
        }

        if (status.length() > 20) {
            campoStatus.setBorder(criarBordaCampo(corErro, 2));
            labelMensagem.setText("Status deve ter no máximo 20 caracteres.");
            erro = true;
        }

        if (idUsuario.isEmpty()) {
            campoIdUsuario.setBorder(criarBordaCampo(corErro, 2));
            labelMensagem.setText("Por favor, informe o ID do motorista.");
            erro = true;
        } else {
            try {
                int numIdUsuario = Integer.parseInt(idUsuario);
                if (numIdUsuario <= 0) {
                    campoIdUsuario.setBorder(criarBordaCampo(corErro, 2));
                    labelMensagem.setText("ID do motorista deve ser maior que zero.");
                    erro = true;
                }
            } catch (NumberFormatException e) {
                campoIdUsuario.setBorder(criarBordaCampo(corErro, 2));
                labelMensagem.setText("ID do motorista deve ser um número inteiro.");
                erro = true;
            }
        }

        if (idVeiculo.isEmpty()) {
            campoIdVeiculo.setBorder(criarBordaCampo(corErro, 2));
            labelMensagem.setText("Por favor, informe o ID do veículo.");
            erro = true;
        } else {
            try {
                int numIdVeiculo = Integer.parseInt(idVeiculo);
                if (numIdVeiculo <= 0) {
                    campoIdVeiculo.setBorder(criarBordaCampo(corErro, 2));
                    labelMensagem.setText("ID do veículo deve ser maior que zero.");
                    erro = true;
                }
            } catch (NumberFormatException e) {
                campoIdVeiculo.setBorder(criarBordaCampo(corErro, 2));
                labelMensagem.setText("ID do veículo deve ser um número inteiro.");
                erro = true;
            }
        }

        if (erro) {
            return;
        }

        String sql = "INSERT INTO carona (origem, destino, data_saida, horario_saida, vagas_disponiveis, valor, status, id_usuario, id_veiculo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, limitarTexto(origem, 100));
            stmt.setString(2, limitarTexto(destino, 100));
            stmt.setDate(3, java.sql.Date.valueOf(dataSaida));

            // Horário pode ser vazio
            if (horarioSaida.isEmpty()) {
                stmt.setTime(4, null);
            } else {
                stmt.setTime(4, java.sql.Time.valueOf(horarioSaida));
            }

            stmt.setInt(5, Integer.parseInt(vagas));

            // Valor pode ser vazio
            if (valor.isEmpty()) {
                stmt.setBigDecimal(6, null);
            } else {
                stmt.setBigDecimal(6, new java.math.BigDecimal(valor));
            }

            // Status pode ser vazio
            if (status.isEmpty()) {
                stmt.setString(7, "Ativa");
            } else {
                stmt.setString(7, limitarTexto(status, 20));
            }

            stmt.setInt(8, Integer.parseInt(idUsuario));
            stmt.setInt(9, Integer.parseInt(idVeiculo));

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                // Feedback visual de sucesso
                labelMensagem.setForeground(new Color(60, 179, 113));
                labelMensagem.setText("✓ Carona cadastrada com sucesso!");

                // Limpar campos após sucesso com delay mais curto
                javax.swing.Timer timer = new javax.swing.Timer(1000, e -> {
                    campoOrigem.setText("");
                    campoDestino.setText("");
                    campoDataSaida.setText("");
                    campoHorarioSaida.setText("");
                    campoVagas.setText("");
                    campoValor.setText("");
                    campoStatus.setText("");
                    campoIdUsuario.setText("");
                    campoIdVeiculo.setText("");
                    labelMensagem.setText("");
                    resetarBordas(); // Restaurar todas as bordas
                });
                timer.setRepeats(false);
                timer.start();
            }

        } catch (Exception ex) {
            labelMensagem.setText("❌ Erro ao salvar: " + ex.getMessage());
            System.err.println("Erro ao salvar carona: " + ex.getMessage());
        }
    }

    private String limitarTexto(String texto, int maxLength) {
        if (texto.length() > maxLength) {
            return texto.substring(0, maxLength);
        }
        return texto;
    }

    private void resetarBordas() {
        Color corPadrao = new Color(200, 200, 200);
        campoOrigem.setBorder(criarBordaCampo(corPadrao, 1));
        campoDestino.setBorder(criarBordaCampo(corPadrao, 1));
        campoDataSaida.setBorder(criarBordaCampo(corPadrao, 1));
        campoHorarioSaida.setBorder(criarBordaCampo(corPadrao, 1));
        campoVagas.setBorder(criarBordaCampo(corPadrao, 1));
        campoValor.setBorder(criarBordaCampo(corPadrao, 1));
        campoStatus.setBorder(criarBordaCampo(corPadrao, 1));
        campoIdUsuario.setBorder(criarBordaCampo(corPadrao, 1));
        campoIdVeiculo.setBorder(criarBordaCampo(corPadrao, 1));
    }
}
