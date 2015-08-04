/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tms.web.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.NamedQuery;
import javax.sql.DataSource;
//import org.postgresql.ds.PGSimpleDataSource;

/**
 * Clase para el Manejo básico de la seguridad
 *
 * @author extmchlfchacon
 */
public class Seguridad {


    public static void main(String[] args) {
        String contrasena = "admin";
        System.out.println(hashPasswordSha512(contrasena));
    }

    /**
     * Constante para utilizar como base en la conversión de contraseñas
     */
    private static final char[] HEXADECIMAL = {'0', '1', '2', '3',
        '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};


    /**
     * Función para encriptar con el algoritmo sha2 con 512 bytes
     *
     * @param contrasena
     * @return
     */
    public static String hashPasswordSha512(String contrasena) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            String C6H5COONa = "C6H5COONa";
            StringBuilder strBClave = new StringBuilder(contrasena);
            strBClave.append(C6H5COONa);
            byte[] bytes = md.digest(strBClave.toString().getBytes());
            StringBuilder sb = new StringBuilder(2 * bytes.length);
            for (int i = 0; i < bytes.length; i++) {
                int low = (bytes[i] & 0x0f);
                int high = ((bytes[i] & 0xf0) >> 4);
                sb.append(HEXADECIMAL[high]);
                sb.append(HEXADECIMAL[low]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Seguridad.class.getName()).log(Level.SEVERE, null, ex);
            return "Error";
        }


    }

    public static String eliminarPuntosEspacios(String cadena) {
        String cadenatratada = cadena;
        cadenatratada = cadenatratada.replace(" ", "");
        cadenatratada = cadenatratada.replace(".", "");
        cadenatratada = cadenatratada.replace(",", "");
        return cadenatratada;
    }

    /**
     * Elimina símbolos especiales que se pueden utilizar para inyección SQL
     *
     * @param cadena Cadena a tratar
     * @return Cadena con símbolos especiales eliminados
     */
    public static String eliminarSE(String cadena) {
        String cadenatratada = cadena;
        cadenatratada = cadenatratada.replace("-", "");
        cadenatratada = cadenatratada.replace("<", "");
        cadenatratada = cadenatratada.replace(">", "");
        cadenatratada = cadenatratada.replace("=", "");
        cadenatratada = cadenatratada.replace("?", "");
        cadenatratada = cadenatratada.replace("/", "");
        cadenatratada = cadenatratada.replace("#", "");
        cadenatratada = cadenatratada.replace("$", "");
        cadenatratada = cadenatratada.replace("%", "");
        cadenatratada = cadenatratada.replace("(", "");
        cadenatratada = cadenatratada.replace(")", "");
        cadenatratada = cadenatratada.replace("\"", "");
        cadenatratada = cadenatratada.replace("'", "");
        cadenatratada = cadenatratada.replace("|", "");
        cadenatratada = cadenatratada.replace("*", "");
        cadenatratada = cadenatratada.replace("\\", "");


        return cadenatratada;
    }
}

