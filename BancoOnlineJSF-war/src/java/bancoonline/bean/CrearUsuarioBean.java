/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoonline.bean;

import bancoonline.ejb.CuentaFacade;
import bancoonline.ejb.UsuarioFacade;
import bancoonline.entity.Cuenta;
import bancoonline.entity.Empleado;
import bancoonline.entity.Usuario;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author ruben
 */
@Named(value = "crearUsuarioBean")
@RequestScoped
public class CrearUsuarioBean {

    @EJB
    private CuentaFacade cuentaFacade;

    @EJB
    private UsuarioFacade usuarioFacade;
    
    @Inject
    private Login login;
    
    @Inject
    private ErrorBean errorBean;
    
    private Usuario usuario;
    private String idCuenta;
    /**
     * Creates a new instance of CrearUsuarioBean
     */
    public CrearUsuarioBean() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }
    
    public String crearUsuario() {
        
        String pagina = "indexEmpleado";
        
        Cuenta cuenta = this.cuentaFacade.buscarCuentaPorCuentaCorriente(idCuenta);
        
        if(cuenta == null) {
            Empleado empleado = login.getEmpleado();
            usuario.setFechaAlta(new Date());
            usuario.setEmpleado(empleado);
        
            this.usuarioFacade.create(usuario);
            
            cuenta = new Cuenta();
            cuenta.setCuentaCorriente(idCuenta);
            cuenta.setSaldo(BigDecimal.ZERO);
            cuenta.setIdUsuario(usuario);
            
            this.cuentaFacade.create(cuenta);
            
        } else {
            pagina = "error";
            errorBean.setTitle("El número de cuenta corriente introducido ya existe");
            errorBean.setSubtitle("Por favor, introduzca otro número de cuenta corriente.");
        }
        
        return pagina;
    }
    
    @PostConstruct
    public void init() {
        this.usuario = new Usuario();
    }
}
