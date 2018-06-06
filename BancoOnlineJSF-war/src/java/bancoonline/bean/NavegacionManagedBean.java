/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoonline.bean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author t1t0
 */
@Named(value = "navegacionManagedBean")
@SessionScoped
public class NavegacionManagedBean implements Serializable {
    
    
    @Inject
    private Login login;
    
    String pagina;
    /**
     * Creates a new instance of NavegacionManagedBean
     */
    
    public NavegacionManagedBean() {
        
    }
    
    public Login getLogin() {
        return login;
    }
    
    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }
    
    public String goHome(){
        pagina = "indexEmpleado";
        return pagina;
    }
    
    public String goShow(){
        pagina = "listarEmpleado";
        return pagina;
    }
}
