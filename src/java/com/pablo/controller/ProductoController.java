package com.pablo.controller;

import com.pablo.dao.ProductoDao;
import com.pablo.model.Producto;
import com.pablo.services.IVentas;
import com.pablo.util.AccionUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ProductoController implements Serializable {

    private List<Producto> productos;
    private Producto producto;

    private String accion;

    @PostConstruct
    public void init() {
        producto = new Producto();
        doFindAll();
    }

    public void doFindAll() {
        FacesMessage msg = null;
        IVentas dao = new ProductoDao();

        try {
            final String query = "SELECT p FROM Producto p ORDER BY p.codigo ASC";
            productos = dao.findBy(query);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Ocurrio un error en el proceso");
        }

        if (msg != null) {
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void doCreate() {
        FacesMessage msg = null;
        IVentas dao = new ProductoDao();

        try {
            boolean result = dao.create(producto);

            if (result) {
                productos.add(productos.size(), producto);
                doFindAll();
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Los datos fueron guardados correctamente");
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Fail", "Los datos no se pudieron guardar");
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", e.getMessage());
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void doUpdate(Producto p) {
        FacesMessage msg = null;
        IVentas dao = new ProductoDao();

        try {
            boolean result = dao.update(producto);

            if (result) {
                productos.clear();
                doFindAll();
                producto = new Producto();
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Los datos fueron guardados correctamente");
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Fail", "Los datos no se pudieron guardar");
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", e.getMessage());
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void doDelete() {
        FacesMessage msg = null;
        IVentas dao = new ProductoDao();

        try {
            boolean result = dao.delete(producto);

            if (result) {
                productos.clear();
                doFindAll();
                producto = new Producto();
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Los datos fueron guardados correctamente");
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Fail", "Los datos no se pudieron guardar");
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", e.getMessage());
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void doExecute() {
        switch (accion) {
            case AccionUtil.CREATE:
                doCreate();
                break;
            case AccionUtil.NUEVA:
                doCreate();
                break;
            case AccionUtil.UPDATE:
                doUpdate(producto);
                break;
        }
    }

    public void setNew() {
        accion = AccionUtil.CREATE;
        producto = new Producto();
        doFindAll();
    }

    public void setUpdate(Producto p) {
        accion = AccionUtil.UPDATE;
        producto = p;
        doFindAll();
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

}
