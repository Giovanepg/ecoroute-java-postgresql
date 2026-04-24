/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecoroute;

/**
 *
 * @author blacklegen
 */

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TelaMenu extends JFrame {

    public TelaMenu() {
        setTitle("EcoRoute - Menu Principal");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 15, 15));

        JButton botaoUsuarios = new JButton("Usuários");
        JButton botaoVeiculos = new JButton("Veículos");
        JButton botaoCaronas = new JButton("Caronas");
        JButton botaoReservas = new JButton("Reservas");
        JButton botaoCadastrarCarona = new JButton("Cadastrar Carona");
        JButton botaoCadastrarReserva = new JButton("Cadastrar Reserva");
        JButton botaoAtualizarReserva = new JButton("Atualizar Reserva");
        JButton botaoExcluirReserva = new JButton("Excluir Reserva");
        JButton botaoFechar = new JButton("Fechar");

        add(botaoUsuarios);
        add(botaoVeiculos);
        add(botaoCaronas);
        add(botaoReservas);
        add(botaoCadastrarCarona);
        add(botaoCadastrarReserva);
        add(botaoAtualizarReserva);
        add(botaoExcluirReserva);
        add(new JLabel(""));
        add(botaoFechar);

        botaoUsuarios.addActionListener(e -> new TelaListarUsuarios());
        botaoVeiculos.addActionListener(e -> new TelaListarVeiculos());
        botaoCaronas.addActionListener(e -> new TelaCaronas());
        botaoReservas.addActionListener(e -> new TelaReservas());
        botaoCadastrarCarona.addActionListener(e -> new TelaCadastroCarona());
        botaoCadastrarReserva.addActionListener(e -> new TelaCadastroReserva());
        botaoAtualizarReserva.addActionListener(e -> new TelaAtualizarReserva());
        botaoExcluirReserva.addActionListener(e -> new TelaExcluirReserva());

        botaoFechar.addActionListener(e -> dispose());

        setVisible(true);
    }
}