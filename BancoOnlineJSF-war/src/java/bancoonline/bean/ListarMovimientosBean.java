/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoonline.bean;

import bancoonline.ejb.MovimientoFacade;
import bancoonline.entity.Movimiento;
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
@Named(value = "listarMovimientosBean")
@RequestScoped
public class ListarMovimientosBean {

    @EJB
    private MovimientoFacade movimientoFacade;
    
    @Inject
    private ErrorBean errorBean;
    
    private List<Movimiento> listaMovimientos;
    /**
     * Creates a new instance of ListarMovimientosBean
     */
    public ListarMovimientosBean() {
    }

    public List<Movimiento> getListaMovimientos() {
        return listaMovimientos;
    }

    public void setListaMovimientos(List<Movimiento> listaMovimientos) {
        this.listaMovimientos = listaMovimientos;
    }

    @PostConstruct
    public void init() {
        this.listaMovimientos = this.movimientoFacade.findAll();
    }
}
