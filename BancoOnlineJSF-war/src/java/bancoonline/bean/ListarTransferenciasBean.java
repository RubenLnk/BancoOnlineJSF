/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoonline.bean;

import bancoonline.ejb.CuentaFacade;
import bancoonline.ejb.TransferenciaFacade;
import bancoonline.entity.Cuenta;
import bancoonline.entity.Transferencia;
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
@Named(value = "listarTransferenciasBean")
@RequestScoped
public class ListarTransferenciasBean {

    @EJB
    private CuentaFacade cuentaFacade;

    @EJB
    private TransferenciaFacade transferenciaFacade;
    
    @Inject
    private ErrorBean errorBean;
    
    private List<Transferencia> listaTransferencias;
    private List<Cuenta> listaCuentas;

    /**
     * Creates a new instance of ListarTransferenciasBean
     */
    public ListarTransferenciasBean() {
    }

    public List<Transferencia> getListaTransferencias() {
        return listaTransferencias;
    }

    public void setListaTransferencias(List<Transferencia> listaTransferencias) {
        this.listaTransferencias = listaTransferencias;
    }

    public List<Cuenta> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(List<Cuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }
    
    public Cuenta buscarUsuarioCuenta(Usuario user){
        return cuentaFacade.buscarCuentaPorIdUsuario(user);
    }
    
    @PostConstruct
    public void init(){
        this.listaTransferencias = this.transferenciaFacade.findAll();
    }
}
