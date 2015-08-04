/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tms.web;

import com.tms.ejb.InventarioEJB;
import com.tms.ejb.SeguridadEJB;
import com.tms.entity.Equipo;
import com.tms.entity.Estado;
import com.tms.entity.Funcionario;
import com.tms.entity.Proveedor;
import com.tms.entity.So;
import com.tms.entity.Tipoequipo;
import com.tms.entity.Usuario;
import com.tms.web.util.BaseJSFBean;
import com.tms.web.util.TipoMensajeEnum;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Administrador
 */
@Named(value = "equipoJSFBean")
@SessionScoped

public class EquipoJSFBean extends BaseJSFBean implements Serializable{

    //<editor-fold defaultstate="collapsed" desc="variable">    
    private Usuario usuario = new Usuario();
    private List<Usuario> lstUsuariosAsignar = new ArrayList<>();
    private String textoConsulta = "";

    private Equipo equipo = new Equipo();

    private Funcionario funcionario = new Funcionario();

    private List<Equipo> lstEquipos = new ArrayList<>();

    private String serial;
    private Integer id;

    private Tipoequipo tipoequipo = new Tipoequipo();
    private List<Tipoequipo> listTipoEquipo = new ArrayList<>();

    private Estado estado = new Estado();
    private List<Estado> lstEstado = new ArrayList<>();

    private So sistema = new So();
    private List<So> lstSos = new ArrayList<>();

    private Proveedor proveedor = new Proveedor();
    private List<Proveedor> lstProveedor = new ArrayList<>();

    public EquipoJSFBean() {
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Metodos y funciones">
    @EJB
    private InventarioEJB inventarioEJB;
    @EJB
    private SeguridadEJB seguridadEJB;

      /*
     Método Init
     */
    @Override
    public void init() {
        
        lstEquipos.clear();
        lstUsuariosAsignar.clear();
        consultarTipoEquipo();
        consultarEstado();
        consultarSo();
        consultarProveedor();
        consutarUsuarioAsignar();
        crearEquipo();
        
        textoConsulta = "";
        numPanel = 1;
        //consultarEquipo();
    }

    @Override
    public void limpiarVariables() {
        lstEquipos.clear();
        lstUsuariosAsignar.clear();

    }
    
    public void guardarEquipo() {
        try {
            if (inventarioEJB.validarEquipoXEquipo(equipo.getSerial(), equipo.getIdequipo()) == 0L) {
                equipo.setSo(inventarioEJB.getSoPorId(equipo.getSo().getIdso()));
                equipo.setProveedor(inventarioEJB.getProveedorPorId(equipo.getProveedor().getIdproveedor()));
                equipo.setTipoequipo(inventarioEJB.getTipoEquipoPorId(equipo.getTipoequipo().getIdtipoequipo()));
                equipo.setEstado(inventarioEJB.getEstadoPorId(equipo.getEstado().getIdestado()));
                equipo.setFuncionario(usuario == null ? null :(usuario.getFuncionario() == null ? null : usuario.getFuncionario()));

                              
                equipo = inventarioEJB.crearEquipo(equipo);
                mostrarMensaje("Equipo con N° Inventario " + equipo.getIdequipo() + " guardado con exito", TipoMensajeEnum.INFO);
                numPanel = 1;
            } 
        } catch (Exception ex) {
            mostrarMensaje("Error al guardar equipo", TipoMensajeEnum.FATAL);
        }

    }

    public void crearEquipo() {

        equipo = null;
        equipo = new Equipo();

        numPanel = 2;
    }

    public void regresar() {

        equipo = null;
        equipo = new Equipo();

        numPanel = 1;

    }
    
   

    public void asignarUsuario() {

        numPanel = 3;

    }

    public void asignarUsuarioaequipo(ActionEvent ae) {

        numPanel = 2;
        //usuario = ae.getComponent().get
        
    }

       
    
    public void editarEquipo(ActionEvent ae) {

        equipo = null;
        equipo = (Equipo) ae.getComponent().getAttributes().get("equipo");
        
        numPanel = 2;

    }

    public void consultarEquipo() {

        lstEquipos = inventarioEJB.consultarequipos(id, serial);

    }

    //Metodo Consulta Tipo Equipo
    private void consultarTipoEquipo() {

        listTipoEquipo = inventarioEJB.consultarTipoEquipo();

    }

    //Metodo Consulta Estado
    private void consultarEstado() {

        lstEstado = inventarioEJB.consultarEstado();

    }

    //Metodo Consulta SO
    private void consultarSo() {

        lstSos = inventarioEJB.consultarSo();

    }

    //Metodo Consultar Proveedor
    private void consultarProveedor() {

        lstProveedor = inventarioEJB.consultarProveedor();
    }

    //Método para traer los Usuarios para relacionar con equipo
    public void consutarUsuarioAsignar() {

        lstUsuariosAsignar = seguridadEJB.consutarUsuarioAsignar(this.textoConsulta);

    }
    //DPORTO

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getLstUsuariosAsignar() {
        return lstUsuariosAsignar;
    }

    public void setLstUsuariosAsignar(List<Usuario> lstUsuariosAsignar) {
        this.lstUsuariosAsignar = lstUsuariosAsignar;
    }

    public String getTextoConsulta() {
        return textoConsulta;
    }

    public void setTextoConsulta(String textoConsulta) {
        this.textoConsulta = textoConsulta;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Equipo> getLstEquipos() {
        return lstEquipos;
    }

    public void setLstEquipos(List<Equipo> lstEquipos) {
        this.lstEquipos = lstEquipos;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tipoequipo getTipoequipo() {
        return tipoequipo;
    }

    public void setTipoequipo(Tipoequipo tipoequipo) {
        this.tipoequipo = tipoequipo;
    }

    public List<Tipoequipo> getListTipoEquipo() {
        return listTipoEquipo;
    }

    public void setListTipoEquipo(List<Tipoequipo> listTipoEquipo) {
        this.listTipoEquipo = listTipoEquipo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Estado> getLstEstado() {
        return lstEstado;
    }

    public void setLstEstado(List<Estado> lstEstado) {
        this.lstEstado = lstEstado;
    }

    public So getSistema() {
        return sistema;
    }

    public void setSistema(So sistema) {
        this.sistema = sistema;
    }

    public List<So> getLstSos() {
        return lstSos;
    }

    public void setLstSos(List<So> lstSos) {
        this.lstSos = lstSos;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<Proveedor> getLstProveedor() {
        return lstProveedor;
    }

    public void setLstProveedor(List<Proveedor> lstProveedor) {
        this.lstProveedor = lstProveedor;
    }
    

    

    

}
