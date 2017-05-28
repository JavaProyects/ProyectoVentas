package com.pablo.services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class VentasService<T> implements IVentas<T> {

    protected EntityManager em;

    public VentasService() {
        em = VentasConexion.getInstancia().getFactory().createEntityManager();
    }

    @Override
    public boolean create(T t) {
        try {
            em = VentasConexion.getInstancia().getFactory().createEntityManager();
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean update(T t) {
        try {
            em = VentasConexion.getInstancia().getFactory().createEntityManager();
            em.getTransaction().begin();
            em.merge(t);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(T t) {
        try {
            em = VentasConexion.getInstancia().getFactory().createEntityManager();
            em.getTransaction().begin();
            em.remove(em.merge(t));
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public T findVenta(String q) {
        T t =null; 
        try {
            em = VentasConexion.getInstancia().getFactory().createEntityManager();
            em.getTransaction().begin();
            Query query =em.createQuery(q);
            t = (T) query.getSingleResult();
            em.close();
          
            
       } catch (Exception e) {
           throw e;
        }
        return t;
    }

    @Override
    public List<T> findBy(String q) {
        List<T> lista =null; 
        try {
            em = VentasConexion.getInstancia().getFactory().createEntityManager();
            em.getTransaction().begin();
            Query query =em.createQuery(q);
            lista= query.getResultList();
            em.close();
          
            
       } catch (Exception e) {
           throw e;
        }
        return lista;
    }
}


