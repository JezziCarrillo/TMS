/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tms.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrador
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findByIdpersona", query = "SELECT p FROM Persona p WHERE p.idpersona = :idpersona"),
    @NamedQuery(name = "Persona.findByPrimernombre", query = "SELECT p FROM Persona p WHERE p.primernombre = :primernombre"),
    @NamedQuery(name = "Persona.findByPrimerapellido", query = "SELECT p FROM Persona p WHERE p.primerapellido = :primerapellido"),
    @NamedQuery(name = "Persona.findByNumerodocumento", query = "SELECT p FROM Persona p WHERE p.numerodocumento = :numerodocumento"),
    @NamedQuery(name = "Persona.findByDireccionpersona", query = "SELECT p FROM Persona p WHERE p.direccionpersona = :direccionpersona"),
    @NamedQuery(name = "Persona.findByTelefonopersona", query = "SELECT p FROM Persona p WHERE p.telefonopersona = :telefonopersona"),
    @NamedQuery(name = "Persona.findByEmailpersona", query = "SELECT p FROM Persona p WHERE p.emailpersona = :emailpersona"),
    @NamedQuery(name = "Persona.findBySegundonombre", query = "SELECT p FROM Persona p WHERE p.segundonombre = :segundonombre"),
    @NamedQuery(name = "Persona.findBySegundoapellido", query = "SELECT p FROM Persona p WHERE p.segundoapellido = :segundoapellido")})
public class Persona implements Serializable {
    private static final long serialVersionUID = 177L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idpersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String primernombre = getPrimernombre();
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String primerapellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String numerodocumento;
    @Size(max = 100)
    private String direccionpersona;
    @Size(max = 25)
    private String telefonopersona;
    @Size(max = 100)
    private String emailpersona;
    @Basic(optional = false)
    @Size(min = 1, max = 100)
    private String segundonombre;
    @Size(max = 100)
    private String segundoapellido;
    @JoinColumn(name = "idtipodocumento", referencedColumnName = "idtipodocumento")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tipodocumento tipodocumento;

    public Persona() {
        this.tipodocumento = new Tipodocumento();
        
        
    }

    public Persona(Integer idpersona) {
        this.idpersona = idpersona;
        
    }

    public Persona(Integer idpersona, String primernombre, String primerapellido, String numerodocumento, String segundonombre) {
        this.idpersona = idpersona;
        this.primernombre = primernombre;
        this.primerapellido = primerapellido;
        this.numerodocumento = numerodocumento;
        this.segundonombre = segundonombre;
    }

    public Integer getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Integer idpersona) {
        this.idpersona = idpersona;
    }

    public String getPrimernombre() {
        return primernombre;
    }

    public void setPrimernombre(String primernombre) {
        this.primernombre = primernombre;
    }

    public String getPrimerapellido() {
        return primerapellido;
    }

    public void setPrimerapellido(String primerapellido) {
        this.primerapellido = primerapellido;
    }

    public String getNumerodocumento() {
        return numerodocumento;
    }

    public void setNumerodocumento(String numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public String getDireccionpersona() {
        return direccionpersona;
    }

    public void setDireccionpersona(String direccionpersona) {
        this.direccionpersona = direccionpersona;
    }

    public String getTelefonopersona() {
        return telefonopersona;
    }

    public void setTelefonopersona(String telefonopersona) {
        this.telefonopersona = telefonopersona;
    }

    public String getEmailpersona() {
        return emailpersona;
    }

    public void setEmailpersona(String emailpersona) {
        this.emailpersona = emailpersona;
    }

    public String getSegundonombre() {
        return segundonombre;
    }

    public void setSegundonombre(String segundonombre) {
        this.segundonombre = segundonombre;
    }

    public String getSegundoapellido() {
        return segundoapellido;
    }

    public void setSegundoapellido(String segundoapellido) {
        this.segundoapellido = segundoapellido;
    }

    public Tipodocumento getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(Tipodocumento tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpersona != null ? idpersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.idpersona == null && other.idpersona != null) || (this.idpersona != null && !this.idpersona.equals(other.idpersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tms.entity.Persona[ idpersona=" + idpersona + " ]";
    }
    
    public String getNombres(){
        
        
            return this.primernombre + " "+ this.primerapellido;
    }
    
}
