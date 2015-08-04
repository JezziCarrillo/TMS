/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tms.dao;

import com.tms.entity.So;
import com.tms.util.UtilidadDaoBean;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Administrador
 */
@Stateless
@LocalBean
public class SoDAO extends UtilidadDaoBean<So, Integer>{

    public SoDAO() {
        setMiClase(So.class);
    }
    //DPORTO
     public So findUsingId(Integer id) {
        return em.find(So.class, id);
    }

    
}
