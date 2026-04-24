/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecoroute;

/**
 *
 * @author blacklegen
 */
import javax.swing.SwingUtilities;

public class EcoRoute {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaLogin();
        });
    }
}