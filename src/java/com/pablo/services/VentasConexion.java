package com.pablo.services;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;

/**
 *
 * @author Pablo
 */

public class VentasConexion {

    private static VentasConexion instancia=null;
    private EntityManagerFactory factory;
    
    public static VentasConexion getInstancia(){
        if (instancia==null){
            instancia =new VentasConexion();
        }
        return instancia;
    }
    
    public EntityManagerFactory getFactory(){
        try {
            if(factory==null){
                factory=Persistence.createEntityManagerFactory("ProyectoVentasPU");
            }
        } catch (Exception e) {
            throw e;
        }return factory;
        
    }
}
