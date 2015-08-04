/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tms.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "usuario_rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioRol.findAll", query = "SELECT u FROM UsuarioRol u"),
    @NamedQuery(name = "UsuarioRol.findByUsrxrolId", query = "SELECT u FROM UsuarioRol u WHERE u.usrxrolId = :usrxrolId"),
    @NamedQuery(name = "UsuarioRol.findByUsrxrolEstado", query = "SELECT u FROM UsuarioRol u WHERE u.usrxrolEstado = :usrxrolEstado")})
public class UsuarioRol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usrxrol_id")
    private Integer usrxrolId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usrxrol_estado")
    private boolean usrxrolEstado;
    @JoinColumn(name = "rol_id", referencedColumnName = "rol_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rol rol;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;

    public UsuarioRol() {
    }

    public UsuarioRol(Integer usrxrolId) {
        this.usrxrolId = usrxrolId;
    }

    public UsuarioRol(Integer usrxrolId, boolean usrxrolEstado) {
        this.usrxrolId = usrxrolId;
        this.usrxrolEstado = usrxrolEstado;
    }

    public Integer getUsrxrolId() {
        return usrxrolId;
    }

    public void setUsrxrolId(Integer usrxrolId) {
        this.usrxrolId = usrxrolId;
    }

    public boolean getUsrxrolEstado() {
        return usrxrolEstado;
    }

    public void setUsrxrolEstado(boolean usrxrolEstado) {
        this.usrxrolEstado = usrxrolEstado;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usrxrolId != null ? usrxrolId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioRol)) {
            return false;
        }
        UsuarioRol other = (UsuarioRol) object;
        if ((this.usrxrolId == null && other.usrxrolId != null) || (this.usrxrolId != null && !this.usrxrolId.equals(other.usrxrolId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tms.entity.UsuarioRol[ usrxrolId=" + usrxrolId + " ]";
    }
    
}
