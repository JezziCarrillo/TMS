/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tms.ejb;

import com.tms.dao.AreaDAO;
import com.tms.dao.FuncionarioDAO;
import com.tms.dao.MenuRolDAO;
import com.tms.dao.PersonaDAO;
import com.tms.dao.TipoDocumentoDAO;
import com.tms.dao.UsuarioDAO;
import com.tms.entity.Area;
import com.tms.entity.Funcionario;
import com.tms.entity.Menu;
import com.tms.entity.Tipodocumento;
import com.tms.entity.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Administrador
 */
@Stateless
@LocalBean
public class SeguridadEJB implements  Serializable{

   @EJB
    private UsuarioDAO usuarioDAO;
    
    @EJB
    private TipoDocumentoDAO tipodocumentoDAO;
    
     @EJB
    private AreaDAO areaDAO;
    
    @EJB
    private MenuRolDAO menuRolDAO;
    
    @EJB
    private PersonaDAO personaDAO;
    
    @EJB
    private FuncionarioDAO funcionarioDAO;

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Usuario validarUsuarioLogin(String usuario, String contrasena) {
        return usuarioDAO.validarUsuarioLogin(usuario, contrasena);
    }
    
      public List<Menu> consultarMenuXUsuario(Integer idusuario, boolean rolEstado, boolean usrxrolEstado) {
        return  menuRolDAO.consultarMenuXUsuario(idusuario, rolEstado, usrxrolEstado);
     }
    
    public List<Usuario> consultarUsuario(String numerodocumento , String primernombre){
      return usuarioDAO.ListUsuario(numerodocumento, primernombre);
      
    } 
    //lista usuarios para asignar
    public List<Usuario> consutarUsuarioAsignar(String texto){
      return usuarioDAO.consutarUsuarioAsignar(texto);
      
    } 
    
    public Usuario crearUsuario(Usuario usuario) {

        return usuarioDAO.update(usuario);
    }
    
    
    public void crear(Usuario usuario){
    
        usuarioDAO.crear(usuario);
    }
    
    public List<Usuario> consultarUsuario() {
        return usuarioDAO.getListUsuario();
    }
    
   
    public List<Tipodocumento> consultarTipodocumento(){
        return tipodocumentoDAO.getListAll();
    }
    
    public List<Area> consultarArea(){
        return areaDAO.getListAll();
    }
    
    public Area consultarAreaXId(Integer id){
        return areaDAO.findOne(id);
    }
    
    public Tipodocumento consultarTipoDocumentoXId(Integer id){
        return tipodocumentoDAO.findOne(id);
    }
    
   
    //editar usuario
    public Usuario editarUsuario(Usuario usuario){
        return usuarioDAO.update(usuario);
    }
    
    //Validacion Usuario
    public Long validarUsuarioXUsuario(String usuario, Integer id){
        return usuarioDAO.validarUsuarioXUsuario(usuario, id);
    }

    
     public Funcionario getFuncionarioPorId(Integer idFuncionario){
        return funcionarioDAO.findOne(idFuncionario);
    }
}
