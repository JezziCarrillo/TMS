/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tms.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NamedQuery(name = "Tipoequipo.findAll", query = "SELECT t FROM Tipoequipo t"),
    @NamedQuery(name = "Tipoequipo.findByIdtipoequipo", query = "SELECT t FROM Tipoequipo t WHERE t.idtipoequipo = :idtipoequipo"),
    @NamedQuery(name = "Tipoequipo.findByTipoequipo", query = "SELECT t FROM Tipoequipo t WHERE t.tipoequipo = :tipoequipo")})
public class Tipoequipo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idtipoequipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String tipoequipo;
    @OneToMany(mappedBy = "tipoequipo", fetch = FetchType.LAZY)
    private List<Equipo> equipoList;

    public Tipoequipo() {
    }

    public Tipoequipo(Integer idtipoequipo) {
        this.idtipoequipo = idtipoequipo;
    }

    public Tipoequipo(Integer idtipoequipo, String tipoequipo) {
        this.idtipoequipo = idtipoequipo;
        this.tipoequipo = tipoequipo;
    }

    public Integer getIdtipoequipo() {
        return idtipoequipo;
    }

    public void setIdtipoequipo(Integer idtipoequipo) {
        this.idtipoequipo = idtipoequipo;
    }

    public String getTipoequipo() {
        return tipoequipo;
    }

    public void setTipoequipo(String tipoequipo) {
        this.tipoequipo = tipoequipo;
    }

    @XmlTransient
    public List<Equipo> getEquipoList() {
        return equipoList;
    }

    public void setEquipoList(List<Equipo> equipoList) {
        this.equipoList = equipoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoequipo != null ? idtipoequipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoequipo)) {
            return false;
        }
        Tipoequipo other = (Tipoequipo) object;
        if ((this.idtipoequipo == null && other.idtipoequipo != null) || (this.idtipoequipo != null && !this.idtipoequipo.equals(other.idtipoequipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tms.entity.Tipoequipo[ idtipoequipo=" + idtipoequipo + " ]";
    }
    
}
