package com.pragma.biblioteca.Util;

public class ValidacionesUtil {

    public static boolean esNuloOVacio(String texto){
        if(texto.isBlank() || texto==null){
            return true;
        }
        return false;
    }
}
