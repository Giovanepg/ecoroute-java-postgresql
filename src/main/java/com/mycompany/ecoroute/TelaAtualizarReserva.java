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

public class TelaAtualizarReserva extends JFrame {

    private final Color corPrimaria = new Color(255, 140, 0);
    private final Color corFundo = new Color(243, 247, 245);
    private final Color corPainel = Color.WHITE;
    private final Color corTexto = new Color(34, 40, 49);
    private final Color corTextoSuave = new Color(101, 113, 123);
    private final Color corBorda = new Color(211, 220, 216);
    private final Color corErro = new Color(210, 55, 70);
    private final Color corSucesso = new Color(34, 139, 87);

    private JTextField campoIdReserva;
    private JTextField campoNovoStatus;
    private JButton botaoAtualizar;
    private JButton botaoCancelar;
    private JLabel labelMensagem;

    public TelaAtualizarReserva() {
        setTitle("EcoRoute - Atualizar Reserva");
        setSize(650, 450);
        setMinimumSize(new Dimension(560, 400));
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

        JLabel titulo = new JLabel("Atualizar Reserva");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titulo.setForeground(corTexto);

        JLabel subtitulo = new JLabel("Altere o status de uma reserva usando o ID cadastrado.");
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
        icone.setBorder(BorderFactory.createLineBorder(new Color(220, 120, 0), 1));

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
        adicionarCampo(painelForm, gbc, "ID da reserva", "Informe apenas números", campoIdReserva = criarCampoTexto(), linha++);
        adicionarCampo(painelForm, gbc, "Novo status", "Exemplo: Pendente, Confirmada ou Cancelada", campoNovoStatus = criarCampoTexto(), linha++);

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

        botaoAtualizar = criarBotao("Atualizar reserva", corPrimaria);
        botaoCancelar = criarBotao("Cancelar", new Color(108, 117, 125));

        botaoAtualizar.addActionListener(e -> atualizarReserva());
        botaoCancelar.addActionListener(e -> dispose());

        painelBotoes.add(botaoCancelar);
        painelBotoes.add(botaoAtualizar);

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
        botao.setPreferredSize(new Dimension(160, 44));

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

    private void atualizarReserva() {
        labelMensagem.setText("");
        labelMensagem.setForeground(corErro);
        resetarBordas();

        int idReserva;
        try {
            idReserva = Integer.parseInt(campoIdReserva.getText().trim());
            if (idReserva <= 0) {
                exibirErroCampo(campoIdReserva, "O ID da reserva deve ser maior que zero.");
                return;
            }
        } catch (NumberFormatException ex) {
            exibirErroCampo(campoIdReserva, "Informe um ID de reserva válido em números.");
            return;
        }

        String novoStatus = campoNovoStatus.getText().trim();
        if (novoStatus.isEmpty()) {
            exibirErroCampo(campoNovoStatus, "Por favor, informe o novo status da reserva.");
            return;
        }

        String sql = "UPDATE reserva SET status_reserva = ? WHERE id_reserva = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoStatus);
            stmt.setInt(2, idReserva);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                labelMensagem.setForeground(corSucesso);
                labelMensagem.setText("Reserva atualizada com sucesso.");
                JOptionPane.showMessageDialog(this, "Reserva atualizada com sucesso!");
                campoIdReserva.setText("");
                campoNovoStatus.setText("");
                resetarBordas();
                campoIdReserva.requestFocus();
            } else {
                labelMensagem.setForeground(corErro);
                labelMensagem.setText("Nenhuma reserva encontrada com esse ID.");
                JOptionPane.showMessageDialog(this, "Nenhuma reserva encontrada com esse ID.");
            }

        } catch (Exception ex) {
            labelMensagem.setForeground(corErro);
            labelMensagem.setText("Erro ao atualizar reserva.");
            JOptionPane.showMessageDialog(this, "Erro ao atualizar reserva: " + ex.getMessage());
        }
    }

    private void exibirErroCampo(JTextField campo, String mensagem) {
        campo.setBorder(criarBordaCampo(corErro, 2));
        labelMensagem.setForeground(corErro);
        labelMensagem.setText(mensagem);
        campo.requestFocus();
    }

    private void resetarBordas() {
        campoIdReserva.setBorder(criarBordaCampo(corBorda, 1));
        campoNovoStatus.setBorder(criarBordaCampo(corBorda, 1));
    }
}
