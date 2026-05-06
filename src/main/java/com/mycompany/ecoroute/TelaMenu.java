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
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TelaMenu extends JFrame {

    public TelaMenu() {
        setTitle("EcoRoute - Menu Principal");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configurar cores modernas
        Color corFundo = new Color(248, 249, 250); // Cinza muito claro
        Color corCard = Color.WHITE;
        Color corBotaoPrimario = new Color(30, 144, 255); // Azul dodger
        Color corBotaoSecundario = new Color(70, 130, 180); // Azul aço
        Color corTexto = new Color(50, 50, 50);

        getContentPane().setBackground(corFundo);

        // Layout principal
        JPanel painelPrincipal = new JPanel(new BorderLayout(20, 20));
        painelPrincipal.setBorder(new EmptyBorder(30, 30, 30, 30));
        painelPrincipal.setBackground(corFundo);

        // Painel do título
        JPanel painelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelTitulo.setBackground(corFundo);
        JLabel titulo = new JLabel("Menu Principal");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titulo.setForeground(new Color(30, 144, 255));
        painelTitulo.add(titulo);
        painelPrincipal.add(painelTitulo, BorderLayout.NORTH);

        // Painel central com cards
        JPanel painelCards = new JPanel(new GridLayout(2, 3, 20, 20));
        painelCards.setBackground(corFundo);

        // Criar cards para cada funcionalidade
        adicionarCard(painelCards, "👥 Usuários", "Gerenciar usuários do sistema", corBotaoPrimario,
                     e -> new TelaListarUsuarios());
        adicionarCard(painelCards, "🚗 Veículos", "Cadastrar e listar veículos", corBotaoSecundario,
                     e -> new TelaListarVeiculos());
        adicionarCard(painelCards, "🚘 Caronas", "Visualizar caronas disponíveis", new Color(60, 179, 113),
                     e -> new TelaCaronas());
        adicionarCard(painelCards, "📋 Reservas", "Consultar reservas ativas", new Color(255, 140, 0),
                     e -> new TelaReservas());
        adicionarCard(painelCards, "➕ Cadastrar Carona", "Oferecer uma nova carona", new Color(138, 43, 226),
                     e -> new TelaCadastroCarona());
        adicionarCard(painelCards, "➕ Cadastrar Reserva", "Reservar uma carona", new Color(220, 20, 60),
                     e -> new TelaCadastroReserva());

        painelPrincipal.add(painelCards, BorderLayout.CENTER);

        // Painel inferior com botões adicionais
        JPanel painelInferior = new JPanel(new GridLayout(1, 3, 20, 0));
        painelInferior.setBackground(corFundo);

        adicionarBotao(painelInferior, "✏️ Atualizar Reserva", new Color(255, 165, 0),
                      e -> new TelaAtualizarReserva());
        adicionarBotao(painelInferior, "🗑️ Excluir Reserva", new Color(255, 69, 0),
                      e -> new TelaExcluirReserva());
        adicionarBotao(painelInferior, "❌ Fechar", new Color(128, 128, 128),
                      e -> dispose());

        painelPrincipal.add(painelInferior, BorderLayout.SOUTH);

        add(painelPrincipal);
        setVisible(true);
    }

    private void adicionarCard(JPanel painel, String titulo, String descricao, Color cor, java.awt.event.ActionListener acao) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Título do card
        JLabel labelTitulo = new JLabel(titulo);
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        labelTitulo.setForeground(new Color(50, 50, 50));
        card.add(labelTitulo, BorderLayout.NORTH);

        // Descrição
        JLabel labelDesc = new JLabel("<html><div style='text-align: center;'>" + descricao + "</div></html>");
        labelDesc.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        labelDesc.setForeground(new Color(100, 100, 100));
        labelDesc.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(labelDesc, BorderLayout.CENTER);

        // Botão de ação
        JButton botao = new JButton("Acessar");
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efeito hover
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(cor.brighter());
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(cor, 2),
                    BorderFactory.createEmptyBorder(20, 20, 20, 20)
                ));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(cor);
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
                    BorderFactory.createEmptyBorder(20, 20, 20, 20)
                ));
            }
        });

        JPanel painelBotao = new JPanel();
        painelBotao.setBackground(Color.WHITE);
        painelBotao.add(botao);
        card.add(painelBotao, BorderLayout.SOUTH);

        // Ação do card inteiro
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                acao.actionPerformed(new java.awt.event.ActionEvent(card, 0, ""));
            }
        });

        botao.addActionListener(acao);

        painel.add(card);
    }

    private void adicionarBotao(JPanel painel, String texto, Color cor, java.awt.event.ActionListener acao) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(cor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(cor);
            }
        });

        botao.addActionListener(acao);
        painel.add(botao);
    }
}