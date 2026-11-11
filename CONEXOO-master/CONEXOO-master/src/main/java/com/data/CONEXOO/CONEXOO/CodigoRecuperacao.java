/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.data.CONEXOO.CONEXOO;

import java.security.SecureRandom;
/**
 *
 * @author liluis
 */
public class CodigoRecuperacao {
    private static final String CARACTERES =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ23456789";

    private static final SecureRandom random = new SecureRandom();

    public static String gerarCodigo() {

        StringBuilder codigo = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int indice = random.nextInt(CARACTERES.length());
            codigo.append(CARACTERES.charAt(indice));
        }

        return codigo.toString();
    }
}
