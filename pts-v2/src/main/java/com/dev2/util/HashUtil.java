/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev2.util;

import java.util.UUID;

/**
 *
 * @author Bruno
 */
public class HashUtil {

    // ENCRIPTADOR HASH + SALT
    public static String generateHash(String senha, String salt) {

        String password;
        password = org.apache.commons.codec.digest.DigestUtils.sha512Hex(senha + salt);

        return password;
    }

    // GERADOR DE SALT
    public static String generateSalt() {
        // Gera um valor aleatório
        UUID uuid = UUID.randomUUID();
        String myRandom = uuid.toString();
        return myRandom.substring(0, 20); // Retorno os 20 primeiros caracteres.

    }

}
