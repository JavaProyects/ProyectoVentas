package com.pablo.services;

import java.util.List;

/**
 *
 * @author Pablo
 */
public interface IVentas<T> {
    public boolean create(T t);
    public boolean update(T t);
    public boolean delete(T t);
    
    public T findVenta (String q);
    public List<T> findBy (String q);
    
    
}
