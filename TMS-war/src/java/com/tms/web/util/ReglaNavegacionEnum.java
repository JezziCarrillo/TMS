/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tms.web.util;

/**
 *
 * @author Administrador
 */
public enum ReglaNavegacionEnum {

    INICIO("login"),
    FORGOT("forgot");

    private final String regla;

    private ReglaNavegacionEnum(String regla) {
        this.regla = regla;
    }

    public String getRegla() {
        return this.regla;
    }
    

}
