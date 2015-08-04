/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tms.web;

import com.tms.ejb.SeguridadEJB;
import com.tms.entity.Area;
import com.tms.entity.Menu;
import com.tms.entity.Tipodocumento;
import com.tms.entity.Usuario;
import com.tms.web.util.BaseJSFBean;
import com.tms.web.util.ReglaNavegacionEnum;
import com.tms.web.util.Seguridad;
import com.tms.web.util.TipoMensajeEnum;
import javax.faces.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.el.ELContext;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("usuarioJSFBean")
@SessionScoped
public class UsuarioJSFBean extends BaseJSFBean implements Serializable {

//<editor-fold defaultstate="collapsed" desc="variable">
    private String user = null;
    private String clave = null;
    private Usuario usuarioLog = null;
    private Usuario usuario = new Usuario();
    private String textoConsulta = "";

    private Tipodocumento tipodocumento = new Tipodocumento();
    private List<Tipodocumento> lstTipodocumento = new ArrayList<>();

    private Area area = new Area();
    private List<Area> lstArea = new ArrayList<>();

    private List<Usuario> lstUsuarios = new ArrayList<>();

    private String numerodocumento;
    private String primernombre;

    //lista de usuarios para asignar
    private List<Usuario> lstUsuariosAsignar = new ArrayList<>();

    private String segundonombre;
    private String primerapellido;
    private String segundoapellido;

    //variable para el manejo del titulo
    private String titulo = "Inicio";

    private List<Menu> listaMenu = new ArrayList<>();

    private Menu menuSel = null;
    private String beanDest = "";
    private String beanAnt = "";

    public UsuarioJSFBean() {
    }
    
    @EJB
    private SeguridadEJB seguridadEJB;

    public String validarUsuario() {
        usuarioLog = seguridadEJB.validarUsuarioLogin(user, Seguridad.hashPasswordSha512(clave));
        if (usuarioLog == null) {
            mostrarMensaje("Usuario o contraseña incorrectos", TipoMensajeEnum.ERROR);
        } else {
            if (!usuarioLog.getEstado()) {
                mostrarMensaje("Usuario inactivo", TipoMensajeEnum.ERROR);
            } else {
                mostrarMensaje("Bienvenido " + usuarioLog.getUsuario(), TipoMensajeEnum.INFO);
                cargarMenu();
                return "/welcomePrimefaces.xhtml";
                //  return ReglaNavegacionEnum.INICIO.getRegla();
            }
        }

        return null;
    }

    
    public void cargarMenu() {
        listaMenu.clear();
        listaMenu = seguridadEJB.consultarMenuXUsuario(usuarioLog.getIdusuario(), true, true);

    }

    public List<Menu> getListaMenu() {
        return listaMenu;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Metodos y funciones">
    public String navegar() {

        ejecutarInit();
        titulo = menuSel.getMenuLabel();
        return menuSel.getMenuReglaNavegacion();

    }
    
    @Override
    public void init() {
        numPanel = 1;
        lstUsuarios.clear();
        consultarTipodocumento();
        consultarArea();
        usuario = new Usuario();
        
     
    }

    @Override
    public void limpiarVariables() {
        lstUsuarios.clear();
    }

    /**
     * ActionListener para la barra de menu principal
     */
    public void ejecutarInit() {

        try {
            beanAnt = beanDest;
            beanDest = menuSel.getMenuController();
            ELContext elc = FacesContext.getCurrentInstance().getELContext();
            Object objDestJSFBean = elc.getELResolver().getValue(elc, null, beanDest);
            Class cls = objDestJSFBean.getClass();

            if (beanAnt != null && !beanAnt.isEmpty()) {
                Object objAntJSFBean = elc.getELResolver().getValue(elc, null, beanAnt);
                Class clsAnt = objAntJSFBean.getClass();
                Method mthdLimpiarVar = clsAnt.getDeclaredMethod("limpiarVariables", new Class<?>[0]);
                mthdLimpiarVar.invoke(objAntJSFBean, new Object[0]);
            }

            Method mthdInit = cls.getDeclaredMethod("init", new Class<?>[0]);
            mthdInit.invoke(objDestJSFBean, new Object[0]);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException |
                NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(UsuarioJSFBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    

    public void guardarUsuario() {
        try {
            if (seguridadEJB.validarUsuarioXUsuario(usuario.getUsuario(), usuario.getIdusuario()) == 0L) {

                usuario.setContrasena(Seguridad.hashPasswordSha512(usuario.getFuncionario().getPersona().getNumerodocumento()));

                usuario.getFuncionario().getPersona().setTipodocumento(seguridadEJB.consultarTipoDocumentoXId(usuario.getFuncionario().getPersona().getTipodocumento().getIdtipodocumento()));
                usuario.getFuncionario().setArea(seguridadEJB.consultarAreaXId(usuario.getFuncionario().getArea().getIdarea()));
                usuario = seguridadEJB.crearUsuario(usuario);
                mostrarMensaje("Usuario  " + usuario.getUsuario() + " guardado con exito", TipoMensajeEnum.INFO);
                numPanel = 1;
            } else {
                mostrarMensaje("Error al guardar usuario", TipoMensajeEnum.FATAL);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            mostrarMensaje("Error al guardar usuario", TipoMensajeEnum.FATAL);
        }

    }
    
     public void logout() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        ec.redirect(ec.getRequestContextPath() + "/faces/index.xhtml");
    }
     
     /*
    Método para direccionar a página de recordar clave
    */
    public String olvidoContrasena() {

        return ReglaNavegacionEnum.FORGOT.getRegla();
    }
    
    /*
    Retorna al login si no desea hacer el cambio de clave
    */
    public String cancelarCambio() {

        return ReglaNavegacionEnum.INICIO.getRegla();
    }
    
    //crear usuario

    public String crearUsuario() {
        usuario = null;
        usuario = new Usuario();
        numPanel = 2;
        return null;

    }

    //regresar al panel 1
    public void regresar() {

        usuario = null;
        usuario = new Usuario();

        numPanel = 1;

    }

    //Editar Usuario
    
    public void editarUsuario(ActionEvent ae) {

        usuario = null;
        usuario = (Usuario) ae.getComponent().getAttributes().get("usuario");
        
        numPanel = 2;

    }


    public void consultarUsuario() {

        lstUsuarios = seguridadEJB.consultarUsuario(numerodocumento, primernombre);
    }

    private void consultarTipodocumento() {

        lstTipodocumento = seguridadEJB.consultarTipodocumento();
    }

    private void consultarArea() {

        lstArea = seguridadEJB.consultarArea();
    }

    public void consutarUsuarioAsignar() {

        lstUsuariosAsignar = seguridadEJB.consutarUsuarioAsignar(this.textoConsulta);

    }

    
//</editor-fold>

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Usuario getUsuarioLog() {
        return usuarioLog;
    }

    public void setUsuarioLog(Usuario usuarioLog) {
        this.usuarioLog = usuarioLog;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTextoConsulta() {
        return textoConsulta;
    }

    public void setTextoConsulta(String textoConsulta) {
        this.textoConsulta = textoConsulta;
    }

    public Tipodocumento getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(Tipodocumento tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public List<Tipodocumento> getLstTipodocumento() {
        return lstTipodocumento;
    }

    public void setLstTipodocumento(List<Tipodocumento> lstTipodocumento) {
        this.lstTipodocumento = lstTipodocumento;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public List<Area> getLstArea() {
        return lstArea;
    }

    public void setLstArea(List<Area> lstArea) {
        this.lstArea = lstArea;
    }

    public List<Usuario> getLstUsuarios() {
        return lstUsuarios;
    }

    public void setLstUsuarios(List<Usuario> lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }

    public String getNumerodocumento() {
        return numerodocumento;
    }

    public void setNumerodocumento(String numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public String getPrimernombre() {
        return primernombre;
    }

    public void setPrimernombre(String primernombre) {
        this.primernombre = primernombre;
    }

    public List<Usuario> getLstUsuariosAsignar() {
        return lstUsuariosAsignar;
    }

    public void setLstUsuariosAsignar(List<Usuario> lstUsuariosAsignar) {
        this.lstUsuariosAsignar = lstUsuariosAsignar;
    }

    public String getSegundonombre() {
        return segundonombre;
    }

    public void setSegundonombre(String segundonombre) {
        this.segundonombre = segundonombre;
    }

    public String getPrimerapellido() {
        return primerapellido;
    }

    public void setPrimerapellido(String primerapellido) {
        this.primerapellido = primerapellido;
    }

    public String getSegundoapellido() {
        return segundoapellido;
    }

    public void setSegundoapellido(String segundoapellido) {
        this.segundoapellido = segundoapellido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Menu getMenuSel() {
        return menuSel;
    }

    public void setMenuSel(Menu menuSel) {
        this.menuSel = menuSel;
    }
    

}
