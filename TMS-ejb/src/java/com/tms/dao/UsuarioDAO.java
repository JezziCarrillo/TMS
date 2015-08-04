/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tms.dao;

import com.tms.entity.Usuario;
import com.tms.util.UtilidadDaoBean;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

/**
 *
 * @author Administrador
 */
@Stateless
@LocalBean
public class UsuarioDAO extends UtilidadDaoBean<Usuario, Integer> {

    public UsuarioDAO() {
        setMiClase(Usuario.class);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void persist(Usuario object) {
        em.persist(object);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Usuario validarUsuarioLogin(String usuario, String contrasena) {
        try {
            Query q = em.createNamedQuery("Usuario.findByUsuarioAutenticacion");
            q.setParameter("usuario", usuario);
            q.setParameter("contrasena", contrasena);
            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            return null;

        }
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Usuario> getListUsuario() {
        Query query = em.createNamedQuery("Usuario.findAll");
        return query.getResultList();
    }

    public List<Usuario> ListUsuario(String numerodocumento , String primernombre) {
        StringBuilder sql = new StringBuilder("SELECT p FROM Usuario p JOIN p.funcionario.persona per WHERE 1 = 1 ");
        if (numerodocumento != null && !numerodocumento.trim().isEmpty()) {
            sql.append("AND per.numerodocumento = :numerodocumento ");
        }
        if (primernombre != null && !primernombre.trim().isEmpty()) {
            sql.append("AND per.primernombre = :primernombre ");
        }
        sql.append(" ORDER BY per.numerodocumento");

        Query q = em.createQuery(sql.toString());

        if (numerodocumento != null && !numerodocumento.trim().isEmpty()) {
            q.setParameter("numerodocumento", numerodocumento);
        }
        if (primernombre != null  && !primernombre.trim().isEmpty()) {
            q.setParameter("primernombre", primernombre);
        }

        return q.getResultList();
    }
    //DPORTO
    //validar usuario por usuario
    public Long validarUsuarioXUsuario(String usuario, Integer idusuario) {
        Query q = null;
        if (idusuario == null) {
            q = em.createQuery("SELECT COUNT(c.idusuario) FROM Usuario c WHERE c.usuario=:usuario");
            q.setParameter("usuario", usuario);
            return (Long) q.getSingleResult();

        } else {
            q = em.createQuery("SELECT COUNT(c.idusuario) FROM Usuario c WHERE c.usuario=:usuario AND c.idusuario<>:idusuario");
            q.setParameter("usuario", usuario);
            q.setParameter("idusuario", idusuario);
            return (Long) q.getSingleResult();
        }
    }

    //consutar usuario con like
    public List<Usuario> consutarUsuarioAsignar(String texto) {
        StringBuilder sql = new StringBuilder("SELECT p FROM Usuario p JOIN p.funcionario.persona per WHERE 1 = 1 ");

        sql.append("OR UPPER(per.numerodocumento) like UPPER(:texto) ");
        sql.append("OR UPPER(per.primernombre) like UPPER(:texto) ");
        sql.append("OR UPPER(per.segundonombre) like UPPER(:texto) ");
        sql.append("OR UPPER(per.primerapellido) like UPPER(:texto) ");
        sql.append("OR UPPER(per.segundoapellido) like UPPER(:texto) ");

        sql.append(" ORDER BY per.numerodocumento");

        Query q = em.createQuery(sql.toString());
        q.setParameter("texto", "%" + texto + "%");
        return q.getResultList();
    }

    

}
