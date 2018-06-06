/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoonline.bean;

import bancoonline.ejb.CuentaFacade;
import bancoonline.ejb.EmpleadoFacade;
import bancoonline.ejb.UsuarioFacade;
import bancoonline.entity.Cuenta;
import bancoonline.entity.Empleado;
import bancoonline.entity.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author Frank
 */
@Named(value = "login")
@SessionScoped
public class Login implements Serializable{

    @EJB
    private CuentaFacade cuentaFacade;

    @EJB
    private EmpleadoFacade empleadoFacade;

    @EJB
    private UsuarioFacade usuarioFacade;

    private String nombre = "";
    private String contrasenya = "";
    
    private Usuario usuario;
    private Cuenta cuenta;
    private Empleado empleado;
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    
    
    public String validar(){
        String pagina = "login";
        Character c;
     
        if(nombre != null && !nombre.equals("")){
            c = nombre.charAt(0);
             System.out.println("0");//*************
            if(c == 'e'){
                nombre = nombre.substring(1, nombre.length());
                empleado = empleadoFacade.buscarEmpleadoPorDniYPassword(nombre, contrasenya);
                System.out.println("1");//*************
                if(empleado != null){
                    pagina = "indexEmpleado";
                    System.out.println("2");//*************
                }
            }else{
                System.out.println("3");//*************
               usuario = usuarioFacade.buscarUsuarioPorDniYPassword(nombre, contrasenya);
               if(usuario != null){
                    cuenta = cuentaFacade.buscarCuentaPorIdUsuario(usuario);
                    pagina = "indexUsuario";
                    System.out.println("4");//*************
                }             
            }
        }
        
        System.out.println(nombre);
        System.out.println(contrasenya);
                
        return pagina;
    }
    
    @PostConstruct
    public void init(){
        
    }
    
}
