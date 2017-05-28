
package com.pablo.dao;

import com.pablo.model.Usuario;
import com.pablo.services.VentasService;

public class UsuarioDao extends VentasService<Usuario>{

    @Override
    public Usuario findVenta(String q) {
        return super.findVenta(q); //To change body of generated methods, choose Tools | Templates.
    }
    
}
