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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TelaReservas extends JFrame {

    private JTable tabela;
    private DefaultTableModel modelo;
    private JButton botaoFechar;

    public TelaReservas() {
        setTitle("EcoRoute - Lista de Reservas");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Configurar cores
        Color corFundo = new Color(248, 249, 250);
        Color corCabecalho = new Color(30, 144, 255);
        Color corTexto = new Color(50, 50, 50);

        getContentPane().setBackground(corFundo);

        // Layout principal
        JPanel painelPrincipal = new JPanel(new BorderLayout(20, 20));
        painelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
        painelPrincipal.setBackground(corFundo);

        // Painel título
        JPanel painelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelTitulo.setBackground(corFundo);
        JLabel titulo = new JLabel("📋 Lista de Reservas");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(corCabecalho);
        painelTitulo.add(titulo);
        painelPrincipal.add(painelTitulo, BorderLayout.NORTH);

        // Configurar modelo da tabela
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabela somente leitura
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Passageiro");
        modelo.addColumn("Origem");
        modelo.addColumn("Destino");
        modelo.addColumn("Status");

        // Criar tabela estilizada
        tabela = new JTable(modelo);
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tabela.setRowHeight(35);
        tabela.setShowGrid(true);
        tabela.setGridColor(new Color(230, 230, 230));
        tabela.setSelectionBackground(new Color(30, 144, 255, 100));
        tabela.setSelectionForeground(Color.BLACK);
        tabela.setIntercellSpacing(new Dimension(0, 1));

       

        // Renderizador personalizado para células
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Colocar texto no centro
                setHorizontalAlignment(SwingConstants.CENTER);

                // Colorir status
                if (column == 4 && value != null) {
                    String status = value.toString();
                    if (status.equalsIgnoreCase("Confirmada")) {
                        setForeground(new Color(60, 179, 113)); // Verde
                    } else if (status.equalsIgnoreCase("Pendente")) {
                        setForeground(new Color(255, 140, 0)); // Laranja
                    } else if (status.equalsIgnoreCase("Cancelada")) {
                        setForeground(new Color(220, 20, 60)); // Vermelho
                    } else {
                        setForeground(corTexto);
                    }
                } else {
                    setForeground(corTexto);
                }

                // Alternar cores das linhas
                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(Color.WHITE);
                    } else {
                        c.setBackground(new Color(248, 248, 248));
                    }
                }

                return c;
            }
        };

        // Aplicar renderizador a todas as colunas
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        // Scroll pane com borda
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        scrollPane.getViewport().setBackground(Color.WHITE);

        painelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Painel de controles
        JPanel painelControles = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        painelControles.setBackground(corFundo);

        // Botão atualizar
        JButton botaoAtualizar = new JButton("🔄 Atualizar");
        botaoAtualizar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botaoAtualizar.setBackground(new Color(70, 130, 180));
        botaoAtualizar.setForeground(Color.WHITE);
        botaoAtualizar.setFocusPainted(false);
        botaoAtualizar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        botaoAtualizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoAtualizar.addActionListener(e -> atualizarTabela());

        // Botão fechar
        botaoFechar = new JButton("❌ Fechar");
        botaoFechar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botaoFechar.setBackground(new Color(128, 128, 128));
        botaoFechar.setForeground(Color.WHITE);
        botaoFechar.setFocusPainted(false);
        botaoFechar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        botaoFechar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoFechar.addActionListener(e -> dispose());

        // Adicionar efeitos hover
        adicionarEfeitoHover(botaoAtualizar, new Color(70, 130, 180));
        adicionarEfeitoHover(botaoFechar, new Color(128, 128, 128));

        painelControles.add(botaoAtualizar);
        painelControles.add(botaoFechar);
        painelPrincipal.add(painelControles, BorderLayout.SOUTH);

        add(painelPrincipal);
        carregarReservas();
        setVisible(true);
    }

    private void adicionarEfeitoHover(JButton botao, Color corBase) {
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(corBase.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(corBase);
            }
        });
    }

    private void atualizarTabela() {
        modelo.setRowCount(0); // Limpar tabela
        carregarReservas();
        JOptionPane.showMessageDialog(this, "Tabela atualizada com sucesso!", "Atualização",
                                      JOptionPane.INFORMATION_MESSAGE);
    }

    private void carregarReservas() {
        String sql =
            "SELECT r.id_reserva, u.nome AS passageiro, c.origem, c.destino, r.status_reserva " +
            "FROM reserva r " +
            "INNER JOIN usuario u ON r.id_usuario = u.id_usuario " +
            "INNER JOIN carona c ON r.id_carona = c.id_carona";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("id_reserva"),
                    rs.getString("passageiro"),
                    rs.getString("origem"),
                    rs.getString("destino"),
                    rs.getString("status_reserva")
                });
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar reservas: " + ex.getMessage());
        }
    }
}
