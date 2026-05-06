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
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TelaCaronas extends JFrame {

    private final Color corPrimaria = new Color(34, 139, 87);
    private final Color corFundo = new Color(243, 247, 245);
    private final Color corPainel = Color.WHITE;
    private final Color corTexto = new Color(34, 40, 49);
    private final Color corTextoSuave = new Color(101, 113, 123);
    private final Color corBorda = new Color(225, 232, 228);
    private final NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"));
    private final DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private JTable tabela;
    private DefaultTableModel modelo;
    private JButton botaoFechar;
    private JLabel labelResumo;

    public TelaCaronas() {
        setTitle("EcoRoute - Lista de Caronas");
        setSize(980, 560);
        setMinimumSize(new Dimension(820, 480));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setBackground(corFundo);

        JPanel painelPrincipal = new JPanel(new BorderLayout(0, 18));
        painelPrincipal.setBorder(new EmptyBorder(24, 30, 24, 30));
        painelPrincipal.setBackground(corFundo);
        painelPrincipal.add(criarCabecalho(), BorderLayout.NORTH);

        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Motorista");
        modelo.addColumn("Veículo");
        modelo.addColumn("Origem");
        modelo.addColumn("Destino");
        modelo.addColumn("Data");
        modelo.addColumn("Valor");

        tabela = new JTable(modelo);
        configurarTabela();

        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(corBorda, 1),
            BorderFactory.createEmptyBorder(6, 6, 6, 6)
        ));
        scrollPane.getViewport().setBackground(corPainel);
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);

        JPanel painelRodape = new JPanel(new BorderLayout(16, 0));
        painelRodape.setBackground(corFundo);

        labelResumo = new JLabel(" ");
        labelResumo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelResumo.setForeground(corTextoSuave);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        painelBotoes.setBackground(corFundo);

        JButton botaoAtualizar = criarBotao("Atualizar", new Color(58, 128, 164));
        botaoAtualizar.addActionListener(e -> atualizarTabela());

        botaoFechar = criarBotao("Fechar", new Color(108, 117, 125));
        botaoFechar.addActionListener(e -> dispose());

        painelBotoes.add(botaoAtualizar);
        painelBotoes.add(botaoFechar);

        painelRodape.add(labelResumo, BorderLayout.WEST);
        painelRodape.add(painelBotoes, BorderLayout.EAST);
        painelPrincipal.add(painelRodape, BorderLayout.SOUTH);

        add(painelPrincipal);
        carregarCaronas();
        setVisible(true);
    }

    private JPanel criarCabecalho() {
        JPanel painelCabecalho = new JPanel(new BorderLayout(12, 4));
        painelCabecalho.setBackground(corFundo);

        JLabel titulo = new JLabel("Caronas Disponiveis");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titulo.setForeground(corTexto);

        JLabel subtitulo = new JLabel("Consulte rotas cadastradas, motorista, veiculo, data e valor.");
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

    private void configurarTabela() {
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabela.setRowHeight(38);
        tabela.setShowVerticalLines(false);
        tabela.setShowHorizontalLines(true);
        tabela.setGridColor(new Color(232, 238, 235));
        tabela.setSelectionBackground(new Color(208, 235, 222));
        tabela.setSelectionForeground(corTexto);
        tabela.setIntercellSpacing(new Dimension(0, 1));
        tabela.setFillsViewportHeight(true);

        tabela.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabela.getTableHeader().setForeground(corTexto);
        tabela.getTableHeader().setBackground(new Color(235, 242, 238));
        tabela.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, formatarValor(value, column),
                        isSelected, hasFocus, row, column);

                setBorder(new EmptyBorder(0, 10, 0, 10));
                setForeground(corTexto);

                if (column == 0 || column == 5 || column == 6) {
                    setHorizontalAlignment(SwingConstants.CENTER);
                } else {
                    setHorizontalAlignment(SwingConstants.LEFT);
                }

                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(248, 251, 249));
                }

                return c;
            }
        };

        for (int i = 0; i < tabela.getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        tabela.getColumnModel().getColumn(0).setPreferredWidth(60);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(160);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(150);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(170);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(170);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(110);
        tabela.getColumnModel().getColumn(6).setPreferredWidth(110);
    }

    private Object formatarValor(Object value, int column) {
        if (value == null) {
            return "-";
        }

        if (column == 5 && value instanceof Date) {
            return ((Date) value).toLocalDate().format(formatoData);
        }

        if (column == 6 && value instanceof BigDecimal) {
            return formatoMoeda.format(value);
        }

        return value;
    }

    private JButton criarBotao(String texto, Color cor) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(12, 22, 12, 22));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setPreferredSize(new Dimension(140, 44));

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

    private void atualizarTabela() {
        modelo.setRowCount(0);
        carregarCaronas();
    }

    private void carregarCaronas() {
        String sql =
            "SELECT c.id_carona, u.nome AS motorista, v.modelo AS veiculo, " +
            "c.origem, c.destino, c.data_saida, c.valor " +
            "FROM carona c " +
            "INNER JOIN usuario u ON c.id_usuario = u.id_usuario " +
            "INNER JOIN veiculo v ON c.id_veiculo = v.id_veiculo";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("id_carona"),
                    rs.getString("motorista"),
                    rs.getString("veiculo"),
                    rs.getString("origem"),
                    rs.getString("destino"),
                    rs.getDate("data_saida"),
                    rs.getBigDecimal("valor")
                });
            }

            atualizarResumo();

        } catch (Exception ex) {
            labelResumo.setForeground(new Color(210, 55, 70));
            labelResumo.setText("Erro ao carregar caronas: " + ex.getMessage());
            System.err.println("Erro ao carregar caronas: " + ex.getMessage());
        }
    }

    private void atualizarResumo() {
        int total = modelo.getRowCount();
        labelResumo.setForeground(corTextoSuave);
        labelResumo.setText(total == 1 ? "1 carona encontrada" : total + " caronas encontradas");
    }
}
