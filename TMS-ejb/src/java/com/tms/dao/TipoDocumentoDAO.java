/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tms.dao;

import com.tms.entity.Tipodocumento;
import com.tms.util.UtilidadDaoBean;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Administrador
 */
@Stateless
@LocalBean
public class TipoDocumentoDAO extends UtilidadDaoBean<Tipodocumento, Integer> {

    public TipoDocumentoDAO() {
        setMiClase(Tipodocumento.class);
    }
    public Tipodocumento findUsingId(Integer id) {
        return em.find(Tipodocumento.class, id);
    }

   
}
