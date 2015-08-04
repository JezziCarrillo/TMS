/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tms.ejb;

import com.tms.dao.EquipoDAO;
import com.tms.dao.EstadoDAO;
import com.tms.dao.FuncionarioDAO;
import com.tms.dao.ProveedorDAO;
import com.tms.dao.SoDAO;
import com.tms.dao.TipoEquipoDAO;
import com.tms.dao.UsuarioDAO;
import com.tms.entity.Equipo;
import com.tms.entity.Estado;
import com.tms.entity.Proveedor;
import com.tms.entity.So;
import com.tms.entity.Tipoequipo;
import com.tms.entity.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Administrador
 */
@Stateless
@LocalBean
public class InventarioEJB implements Serializable {

    @EJB
    private EquipoDAO equipoDAO;
    @EJB
    private TipoEquipoDAO tipoEquipoDAO;
    @EJB
    private EstadoDAO estadoDAO;
    @EJB
    private SoDAO soDAO;
     
     @EJB
    private ProveedorDAO proveedorDAO;
     
    @EJB
    private UsuarioDAO usuarioDAO;
    
    @EJB
    private FuncionarioDAO funcionarioDAO;
    
    //DPORTO
      //crear equipo
    public Equipo crearEquipo(Equipo equipo) {

        return equipoDAO.update(equipo);
    }
    
    // creear equipo desde utilidad
    public void crear(Equipo equipo){
    
        equipoDAO.crear(equipo);
    }
    
    //eliminar equipo
    public void eliminar(Integer id) {
        equipoDAO.eliminar(id);
    }
    
    

    //consultar equipo por id o serial
    public List<Equipo> consultarequipos(Integer id, String serial){
      return equipoDAO.ListEquipo(id, serial);
      
    }
    
    //consultar tipo de equipo
    public List<Tipoequipo> consultarTipoEquipo(){
      return tipoEquipoDAO.getListAll();
      
    }
    
    //consultar estado de equipo
    public List<Estado> consultarEstado(){
      return estadoDAO.getListAll();
      
    }
    
   //consultar sistema operativo
    public List<So> consultarSo(){
      return soDAO.getListAll();
    }
    
    //consultar proveedores
    public List<Proveedor> consultarProveedor(){
      return proveedorDAO.getListAll();
    }
    
    //Validacion Equipo
    public Long validarEquipoXEquipo(String equipo, Integer id){
        return equipoDAO.validarEquipoXEquipo(equipo, id);
    }
    public List<Usuario> consutarUsuarioAsignar(String texto){
      return usuarioDAO.consutarUsuarioAsignar(texto);
      
    } 

    public So getSoPorId(Integer idSo){
       return soDAO.findOne(idSo);
    }

    public Proveedor getProveedorPorId(Integer idProveedor){
        return proveedorDAO.findOne(idProveedor);
    }
    
    public Tipoequipo getTipoEquipoPorId(Integer idTipoEquipo){
        return tipoEquipoDAO.findOne(idTipoEquipo);
    }
    
    public Estado getEstadoPorId (Integer idEstado){
        return estadoDAO.findOne(idEstado);
    }
    
   public Usuario getUsuarioPorId (Integer idUsuario){
        return usuarioDAO.findOne(idUsuario);
    }
}
