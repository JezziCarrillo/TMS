/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tms.dao;

import com.tms.entity.Menu;
import com.tms.entity.MenuRol;
import com.tms.util.UtilidadDaoBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author Administrador
 */
@Stateless
@LocalBean
public class MenuRolDAO extends UtilidadDaoBean<MenuRol, Integer> {

    public MenuRolDAO() {
        setMiClase(MenuRol.class);
    }

    public List<Menu> consultarMenuXUsuario(Integer idusuario, boolean rolEstado, boolean usrxrolEstado) {
        Query query = em.createNamedQuery("MenuRol.findByUsuario");
        query.setParameter("idusuario", idusuario);
        query.setParameter("rolEstado", rolEstado);
        query.setParameter("usrxrolEstado", usrxrolEstado);

        List<Menu> salida = new ArrayList<>();
        List<MenuRol> consulta = query.getResultList();

        LinkedHashMap<Integer, Menu> mapa = new LinkedHashMap<>();
        for (MenuRol mr : consulta) {
            Menu padre = mapa.get(mr.getMenu().getMenuPadre().getMenuId());
            if (padre != null) {
                Menu insert = mr.getMenu();
                insert.setMenuList(new ArrayList<>());
                padre.getMenuList().add(insert);
                mapa.put(padre.getMenuId(), padre);

            } else {
                Menu insert = mr.getMenu().getMenuPadre();
                insert.setMenuList(new ArrayList<>());
                insert.getMenuList().add(mr.getMenu());
                mapa.put(insert.getMenuId(), insert);
            }
        }
        for (Map.Entry<Integer, Menu> m : mapa.entrySet()) {
            salida.add(m.getValue());
        }

        return salida;

    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
