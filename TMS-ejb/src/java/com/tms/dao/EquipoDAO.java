/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tms.dao;

import com.tms.entity.Equipo;
import com.tms.entity.Tipoequipo;
import com.tms.util.UtilidadDaoBean;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Administrador
 */
@Stateless
@LocalBean
public class EquipoDAO extends UtilidadDaoBean<Equipo, Integer> {
    

    public EquipoDAO() {
        setMiClase(Equipo.class);
    }

    public Equipo findUsingId(Integer id) {
        return em.find(Equipo.class, id);
    }

    public List<Equipo> ListEquipo(Integer id, String serial) {
        StringBuilder sql = new StringBuilder("SELECT p FROM Equipo p WHERE 1 = 1 ");
        if (id != null) {
            sql.append("AND p.idequipo = :idequipo ");
        }
        if (serial != null) {
            sql.append("AND p.serial = :serial ");
        }
        sql.append(" ORDER BY p.idequipo");

        Query q = em.createQuery(sql.toString());

        if (id != null) {
            q.setParameter("idequipo", id);
        }
        if (serial != null) {
            q.setParameter("serial", serial);
        }

        return q.getResultList();
    }
    //DPORTO
    //validar equipo por equipo
    public Long validarEquipoXEquipo(String serial, Integer idequipo) {
        Query q = null;
        if (idequipo == null) {
            q = em.createQuery("SELECT COUNT(c.idequipo) FROM Equipo c WHERE c.serial=:serial");
            q.setParameter("serial", serial);
            return (Long) q.getSingleResult();

        } else {
            q = em.createQuery("SELECT COUNT(c.idequipo) FROM Equipo c WHERE c.serial=:serial AND c.idequipo<>:idequipo");
            q.setParameter("serial", serial);
            q.setParameter("idequipo", idequipo);
            return (Long) q.getSingleResult();
        }
    }

   
    
    
}
