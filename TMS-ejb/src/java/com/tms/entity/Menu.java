/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tms.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m"),
    @NamedQuery(name = "Menu.findByMenuId", query = "SELECT m FROM Menu m WHERE m.menuId = :menuId"),
    @NamedQuery(name = "Menu.findByMenuLabel", query = "SELECT m FROM Menu m WHERE m.menuLabel = :menuLabel"),
    @NamedQuery(name = "Menu.findByMenuReglaNavegacion", query = "SELECT m FROM Menu m WHERE m.menuReglaNavegacion = :menuReglaNavegacion"),
    @NamedQuery(name = "Menu.findByMenuController", query = "SELECT m FROM Menu m WHERE m.menuController = :menuController"),
    @NamedQuery(name = "Menu.findByMenuOrden", query = "SELECT m FROM Menu m WHERE m.menuOrden = :menuOrden")})
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "menu_id")
    private Integer menuId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "menu_label")
    private String menuLabel;
    @Size(max = 2147483647)
    @Column(name = "menu_regla_navegacion")
    private String menuReglaNavegacion;
    @Size(max = 2147483647)
    @Column(name = "menu_controller")
    private String menuController;
    @Basic(optional = false)
    @NotNull
    @Column(name = "menu_orden")
    private int menuOrden;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menu", fetch = FetchType.LAZY)
    private List<MenuRol> menuRolList;
    @OneToMany(mappedBy = "menuPadre", fetch = FetchType.LAZY)
    private List<Menu> menuList;
    @JoinColumn(name = "menu_id_menu_padre", referencedColumnName = "menu_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menuPadre;

    public Menu() {
    }

    public Menu(Integer menuId) {
        this.menuId = menuId;
    }

    public Menu(Integer menuId, String menuLabel, int menuOrden) {
        this.menuId = menuId;
        this.menuLabel = menuLabel;
        this.menuOrden = menuOrden;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuLabel() {
        return menuLabel;
    }

    public void setMenuLabel(String menuLabel) {
        this.menuLabel = menuLabel;
    }

    public String getMenuReglaNavegacion() {
        return menuReglaNavegacion;
    }

    public void setMenuReglaNavegacion(String menuReglaNavegacion) {
        this.menuReglaNavegacion = menuReglaNavegacion;
    }

    public String getMenuController() {
        return menuController;
    }

    public void setMenuController(String menuController) {
        this.menuController = menuController;
    }

    public int getMenuOrden() {
        return menuOrden;
    }

    public void setMenuOrden(int menuOrden) {
        this.menuOrden = menuOrden;
    }

    @XmlTransient
    public List<MenuRol> getMenuRolList() {
        return menuRolList;
    }

    public void setMenuRolList(List<MenuRol> menuRolList) {
        this.menuRolList = menuRolList;
    }

    @XmlTransient
    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public Menu getMenuPadre() {
        return menuPadre;
    }

    public void setMenuPadre(Menu menuPadre) {
        this.menuPadre = menuPadre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (menuId != null ? menuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.menuId == null && other.menuId != null) || (this.menuId != null && !this.menuId.equals(other.menuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tms.entity.Menu[ menuId=" + menuId + " ]";
    }
    
    public boolean getTieneHijos(){
        if(this.menuList == null || this.menuList.isEmpty()){
            return false;
        }
        return true;
    }
    
}
