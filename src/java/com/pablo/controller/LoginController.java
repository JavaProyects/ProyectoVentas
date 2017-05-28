package com.pablo.controller;

import com.pablo.dao.UsuarioDao;
import com.pablo.model.Usuario;
import com.pablo.services.IVentas;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    private List<Usuario> usuarios;
    private Usuario usuario;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
    }

    public String login() {
        FacesMessage message = null;
        String ruta = null;
        final String query = "select u from Usuario u where u.username='" + usuario.getUsername() + "' and u.password='" + usuario.getPassword() + "'";
        IVentas<Usuario> dao = new UsuarioDao();

        try {
            Usuario u = dao.findVenta(query);
            if (u != null) {
                if (u.getUsername().equals(usuario.getUsername()) && u.getPassword().equals(usuario.getPassword())) {
                    ruta = "pages/producto?faces-redirect=true";
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", usuario);
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", "Acceso concedido");
                } else {
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacion", "Usuario o Pasword Incorrecto");
                    ruta = "index";
                }
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacion", "Usuario o Pasword Incorrecto");
                ruta = "index";
            }
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de Validacion", "Usuario o Pasword Incorrecto");
            ruta = "index";
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        return ruta;

    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
