/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tms.dao;

import com.tms.entity.Estado;
import com.tms.util.UtilidadDaoBean;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Administrador
 */
@Stateless
@LocalBean
public class EstadoDAO extends UtilidadDaoBean<Estado, Integer>{

    public EstadoDAO() {
        setMiClase(Estado.class);
    }

     public Estado findUsingId(Integer id) {
        return em.find(Estado.class, id);
    }
    
}
