/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tms.web.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Administrador
 */
public abstract class BaseJSFBean {

       
    
    
    protected int numPanel = 1;
    
    public abstract void init();

    public abstract void limpiarVariables();


    public void mostrarMensaje(String mensaje, TipoMensajeEnum mensajeEnum) {

        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        switch (mensajeEnum.getTipo()) {
            case 1:

                message.setDetail(mensaje);
                message.setSummary("Error");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                break;
            case 2:

                message.setDetail(mensaje);
                message.setSummary("Advertencia");
                message.setSeverity(FacesMessage.SEVERITY_WARN);
                break;
            case 3:

                message.setDetail(mensaje);
                message.setSummary("Información");
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                break;
            default:
                message.setDetail(mensaje);
                message.setSummary("Fatal");
                message.setSeverity(FacesMessage.SEVERITY_FATAL);

        }

       context.addMessage(null, message);

        
    }

    public int getNumPanel() {
        return numPanel;
    }

    public void setNumPanel(int numPanel) {
        this.numPanel = numPanel;
    }
    
    

}
