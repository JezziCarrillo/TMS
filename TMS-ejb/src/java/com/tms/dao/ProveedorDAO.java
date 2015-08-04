/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tms.dao;

import com.tms.entity.Proveedor;
import com.tms.util.UtilidadDaoBean;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Administrador
 */
@Stateless
@LocalBean
public class ProveedorDAO extends UtilidadDaoBean<Proveedor, Integer>{

    public ProveedorDAO() {
        setMiClase(Proveedor.class);
    }
    //DPORTO
    public Proveedor findUsingId(Integer id) {
        return em.find(Proveedor.class, id);
    }

    
}
