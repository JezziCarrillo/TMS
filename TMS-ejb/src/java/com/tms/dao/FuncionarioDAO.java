/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tms.dao;

import com.tms.entity.Funcionario;
import com.tms.util.UtilidadDaoBean;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Administrador
 */
@Stateless
@LocalBean
public class FuncionarioDAO extends UtilidadDaoBean<Funcionario, Integer>{

    public FuncionarioDAO() {
        setMiClase(Funcionario.class);
    }
    //DPORTO
    public Funcionario findUsingId(Integer id) {
        return em.find(Funcionario.class, id);
    }

    public void deleteUsingObject(Funcionario object){
        object = findUsingId(object.getIdfuncionario());
        //el remove se utiliza para el delete
        em.remove(object);
    }

    
}
