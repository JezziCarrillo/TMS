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
    @NamedQuery(name = "So.findAll", query = "SELECT s FROM So s"),
    @NamedQuery(name = "So.findByIdso", query = "SELECT s FROM So s WHERE s.idso = :idso"),
    @NamedQuery(name = "So.findBySistemaoperativo", query = "SELECT s FROM So s WHERE s.sistemaoperativo = :sistemaoperativo")})
public class So implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String sistemaoperativo;
    @OneToMany(mappedBy = "so", fetch = FetchType.LAZY)
    private List<Equipo> equipoList;

    public So() {
    }

    public So(Integer idso) {
        this.idso = idso;
    }

    public So(Integer idso, String sistemaoperativo) {
        this.idso = idso;
        this.sistemaoperativo = sistemaoperativo;
    }

    public Integer getIdso() {
        return idso;
    }

    public void setIdso(Integer idso) {
        this.idso = idso;
    }

    public String getSistemaoperativo() {
        return sistemaoperativo;
    }

    public void setSistemaoperativo(String sistemaoperativo) {
        this.sistemaoperativo = sistemaoperativo;
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
        hash += (idso != null ? idso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof So)) {
            return false;
        }
        So other = (So) object;
        if ((this.idso == null && other.idso != null) || (this.idso != null && !this.idso.equals(other.idso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tms.entity.So[ idso=" + idso + " ]";
    }
    
}
