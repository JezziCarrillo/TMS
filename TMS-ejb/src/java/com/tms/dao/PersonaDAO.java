/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tms.dao;

import com.tms.entity.Persona;
import com.tms.util.UtilidadDaoBean;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Administrador
 */
@Stateless
@LocalBean
public class PersonaDAO extends UtilidadDaoBean<Persona, Integer> {

    public PersonaDAO() {
        setMiClase(Persona.class);
    }
    //DPORTO
     public Persona findUsingId(Integer id) {
        return em.find(Persona.class, id);
    }
    
    public void deleteUsingObject(Persona object){
        object = findUsingId(object.getIdpersona());
        //el remove se utiliza para el delete
        em.remove(object);
    }

    
}
