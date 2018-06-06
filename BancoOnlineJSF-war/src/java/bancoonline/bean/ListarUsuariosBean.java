/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoonline.bean;

import bancoonline.ejb.UsuarioFacade;
import bancoonline.entity.Usuario;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author t1t0
 */
@Named(value = "listarUsuariosBean")
@RequestScoped
public class ListarUsuariosBean {

    @EJB
    private UsuarioFacade usuarioFacade;

    @Inject
    private ErrorBean errorBean;
    
    private List<Usuario> listaUsuarios;
    /**
     * Creates a new instance of ListarUsuariosBean
     */
    public ListarUsuariosBean() {
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
    @PostConstruct
    public void init(){
        this.listaUsuarios = this.usuarioFacade.findAll();
    }
}
