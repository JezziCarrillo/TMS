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
public enum TipoMensajeEnum {

    ERROR(1),
    WARM(2),
    INFO(3),
    FATAL(4);

    private final int tipo;

    private TipoMensajeEnum(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return this.tipo;
    }

}
