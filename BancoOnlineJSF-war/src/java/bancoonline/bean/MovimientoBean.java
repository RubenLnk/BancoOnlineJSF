/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoonline.bean;

import bancoonline.ejb.CuentaFacade;
import bancoonline.ejb.MovimientoFacade;
import bancoonline.entity.Cuenta;
import bancoonline.entity.Empleado;
import bancoonline.entity.Movimiento;
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
@Named(value = "movimientoBean")
@RequestScoped
public class MovimientoBean {

    @EJB
    private CuentaFacade cuentaFacade;

    @EJB
    private MovimientoFacade movimientoFacade;
    
    @Inject
    private Login login;
    
    @Inject
    private ErrorBean errorBean;
    
    private Movimiento movimiento;
    private String idCuenta;
    private String tipo;
    
    /**
     * Creates a new instance of MovimientoBean
     */
    public MovimientoBean() {
    }

    public Movimiento getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String crearMovimiento() {
        
        String pagina = "listarEmpleado";
        
        Cuenta cuenta = this.cuentaFacade.buscarCuentaPorCuentaCorriente(idCuenta);
        if(cuenta != null) {
            Empleado empleado = login.getEmpleado();
            movimiento.setFecha(new Date());
            movimiento.setIdCuenta(cuenta);
            movimiento.setIdEmpleado(empleado);
            if(tipo.equals("gasto")) {
                if(cuenta.getSaldo().subtract(movimiento.getCantidad()).longValue() < 0) {
                   pagina = "error";
                   errorBean.setTitle("Su cuenta no tiene suficiente saldo");
                   errorBean.setSubtitle("Por favor, introduzca un valor válido o ingrese más dinero en su cuenta.");
                } else {
                    this.movimientoFacade.create(movimiento);

                    cuenta.setSaldo(cuenta.getSaldo().subtract(movimiento.getCantidad()));
                    this.cuentaFacade.edit(cuenta);
                }
            } else {
               this.movimientoFacade.create(movimiento);
               
               cuenta.setSaldo(cuenta.getSaldo().add(movimiento.getCantidad()));
               this.cuentaFacade.edit(cuenta);
            }
        } else {
           pagina = "error"; 
           errorBean.setTitle("No existe la cuenta corriente especificada");
           errorBean.setSubtitle("Por favor, especifique una cuenta correcta.");
        }
        
        return pagina;
    }
    
    @PostConstruct
    public void init() {
        this.movimiento = new Movimiento();
    }
}
