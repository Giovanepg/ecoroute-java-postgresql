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

public class TelaExcluirReserva extends JFrame {

    private final Color corPrimaria = new Color(210, 55, 70);
    private final Color corFundo = new Color(243, 247, 245);
    private final Color corPainel = Color.WHITE;
    private final Color corTexto = new Color(34, 40, 49);
    private final Color corTextoSuave = new Color(101, 113, 123);
    private final Color corBorda = new Color(211, 220, 216);
    private final Color corSucesso = new Color(34, 139, 87);

    private JTextField campoIdReserva;
    private JButton botaoExcluir;
    private JButton botaoCancelar;
    private JLabel labelMensagem;

    public TelaExcluirReserva() {
        setTitle("EcoRoute - Excluir Reserva");
        setSize(620, 380);
        setMinimumSize(new Dimension(520, 340));
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

        JLabel titulo = new JLabel("Excluir Reserva");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titulo.setForeground(corTexto);

        JLabel subtitulo = new JLabel("Remova uma reserva existente informando o ID cadastrado.");
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
        icone.setBorder(BorderFactory.createLineBorder(new Color(175, 45, 58), 1));

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

        adicionarCampo(painelForm, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(12, 8, 0, 8);
        labelMensagem = new JLabel(" ");
        labelMensagem.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelMensagem.setHorizontalAlignment(SwingConstants.CENTER);
        painelForm.add(labelMensagem, gbc);

        return painelForm;
    }

    private void adicionarCampo(JPanel painel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(7, 8, 7, 8);

        JPanel painelLabel = new JPanel(new GridLayout(2, 1, 0, 1));
        painelLabel.setBackground(corPainel);

        JLabel label = new JLabel("ID da reserva");
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        label.setForeground(corTexto);

        JLabel dica = new JLabel("Informe apenas números");
        dica.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        dica.setForeground(corTextoSuave);

        painelLabel.add(label);
        painelLabel.add(dica);
        painel.add(painelLabel, gbc);

        campoIdReserva = criarCampoTexto();

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        painel.add(campoIdReserva, gbc);
    }

    private JPanel criarRodape() {
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        painelBotoes.setBackground(corFundo);

        botaoExcluir = criarBotao("Excluir reserva", corPrimaria);
        botaoCancelar = criarBotao("Cancelar", new Color(108, 117, 125));

        botaoExcluir.addActionListener(e -> excluirReserva());
        botaoCancelar.addActionListener(e -> dispose());

        painelBotoes.add(botaoCancelar);
        painelBotoes.add(botaoExcluir);

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

    private javax.swing.border.Border criarBordaCampo(Color cor, int largura) {
        return BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(cor, largura),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        );
    }

    private void excluirReserva() {
        labelMensagem.setText("");
        labelMensagem.setForeground(corPrimaria);
        campoIdReserva.setBorder(criarBordaCampo(corBorda, 1));

        int idReserva;
        try {
            idReserva = Integer.parseInt(campoIdReserva.getText().trim());
            if (idReserva <= 0) {
                exibirErro("O ID da reserva deve ser maior que zero.");
                return;
            }
        } catch (NumberFormatException ex) {
            exibirErro("Informe um ID de reserva válido em números.");
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(
            this,
            "Deseja realmente excluir a reserva " + idReserva + "?",
            "Confirmar exclusão",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirmacao != JOptionPane.YES_OPTION) {
            labelMensagem.setForeground(corTextoSuave);
            labelMensagem.setText("Exclusão cancelada.");
            return;
        }

        String sql = "DELETE FROM reserva WHERE id_reserva = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idReserva);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                labelMensagem.setForeground(corSucesso);
                labelMensagem.setText("Reserva excluída com sucesso.");
                JOptionPane.showMessageDialog(this, "Reserva excluída com sucesso!");
                campoIdReserva.setText("");
                campoIdReserva.requestFocus();
            } else {
                labelMensagem.setForeground(corPrimaria);
                labelMensagem.setText("Nenhuma reserva encontrada com esse ID.");
                JOptionPane.showMessageDialog(this, "Nenhuma reserva encontrada com esse ID.");
            }

        } catch (Exception ex) {
            labelMensagem.setForeground(corPrimaria);
            labelMensagem.setText("Erro ao excluir reserva.");
            JOptionPane.showMessageDialog(this, "Erro ao excluir reserva: " + ex.getMessage());
        }
    }

    private void exibirErro(String mensagem) {
        campoIdReserva.setBorder(criarBordaCampo(corPrimaria, 2));
        labelMensagem.setForeground(corPrimaria);
        labelMensagem.setText(mensagem);
        campoIdReserva.requestFocus();
    }
}
