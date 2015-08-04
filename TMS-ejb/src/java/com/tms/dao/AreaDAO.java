/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tms.dao;

import com.tms.entity.Area;
import com.tms.util.UtilidadDaoBean;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Administrador
 */
@Stateless
@LocalBean
public class AreaDAO extends UtilidadDaoBean<Area, Integer> {

    public AreaDAO() {
        setMiClase(Area.class);
        
    }
    //DPORTO
    public Area findUsingId(Integer id) {
        return em.find(Area.class, id);
    }

   
}
