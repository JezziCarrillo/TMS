/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tms.dao;

import com.tms.entity.Tipoequipo;
import com.tms.util.UtilidadDaoBean;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Administrador
 */
@Stateless
@LocalBean
public class TipoEquipoDAO extends UtilidadDaoBean<Tipoequipo, Integer>{

    public TipoEquipoDAO() {
        setMiClase(Tipoequipo.class);
    }

    public Tipoequipo findUsingId(Integer id) {
        return em.find(Tipoequipo.class, id);
    }
    
}
