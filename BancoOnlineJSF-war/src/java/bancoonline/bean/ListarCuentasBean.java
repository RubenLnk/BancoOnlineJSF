/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoonline.bean;

import bancoonline.ejb.CuentaFacade;
import bancoonline.entity.Cuenta;
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
@Named(value = "listarCuentasBean")
@RequestScoped
public class ListarCuentasBean {

    @EJB
    private CuentaFacade cuentaFacade;

    @Inject
    private ErrorBean errorBean;
    
    private List<Cuenta> listaCuentas;
    /**
     * Creates a new instance of ListarCuentasBean
     */
    public ListarCuentasBean() {
    }

    public List<Cuenta> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(List<Cuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }
    
    @PostConstruct
    public void init (){
        this.listaCuentas = this.cuentaFacade.findAll();
    }
}
