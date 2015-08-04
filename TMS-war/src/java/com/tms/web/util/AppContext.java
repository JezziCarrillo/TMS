/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tms.web.util;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Administrador
 */
@Named(value = "appContext")
@SessionScoped
public class AppContext implements Serializable{

    /**
     * Creates a new instance of AppContext
     */
    public AppContext() {
    }
    
    public String getContext() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getContextPath();
    }

}
