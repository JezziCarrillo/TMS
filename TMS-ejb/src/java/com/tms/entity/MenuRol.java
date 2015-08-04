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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "menu_rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MenuRol.findByUsuario", query = "SELECT m FROM MenuRol m JOIN m.rol rol JOIN rol.usuarioRolList urol WHERE urol.usuario.idusuario = :idusuario AND rol.rolEstado = :rolEstado AND urol.usrxrolEstado = :usrxrolEstado AND m.menu.menuPadre IS NOT NULL ORDER BY m.menu.menuOrden,m.menu.menuPadre.menuOrden,m.menu.menuLabel  "),
    @NamedQuery(name = "MenuRol.findAll", query = "SELECT m FROM MenuRol m"),
    @NamedQuery(name = "MenuRol.findByMenuRolId", query = "SELECT m FROM MenuRol m WHERE m.menuRolId = :menuRolId")})
public class MenuRol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "menu_rol_id")
    private Integer menuRolId;
    @JoinColumn(name = "menu_id", referencedColumnName = "menu_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Menu menu;
    @JoinColumn(name = "rol_id", referencedColumnName = "rol_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rol rol;

    public MenuRol() {
    }

    public MenuRol(Integer menuRolId) {
        this.menuRolId = menuRolId;
    }

    public Integer getMenuRolId() {
        return menuRolId;
    }

    public void setMenuRolId(Integer menuRolId) {
        this.menuRolId = menuRolId;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (menuRolId != null ? menuRolId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuRol)) {
            return false;
        }
        MenuRol other = (MenuRol) object;
        if ((this.menuRolId == null && other.menuRolId != null) || (this.menuRolId != null && !this.menuRolId.equals(other.menuRolId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tms.entity.MenuRol[ menuRolId=" + menuRolId + " ]";
    }
    
}
