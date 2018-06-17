package bancoonline.bean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bancoonline.ejb.CuentaFacade;
import bancoonline.ejb.MovimientoFacade;
import bancoonline.ejb.TransferenciaFacade;
import bancoonline.ejb.UsuarioFacade;
import bancoonline.entity.Cuenta;
import bancoonline.entity.Movimiento;
import bancoonline.entity.Transferencia;
import bancoonline.entity.Usuario;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Frank
 */
@Named(value = "usuarioIndex")
@RequestScoped
public class UsuarioIndex {

    @EJB
    private MovimientoFacade movimientoFacade;

    @EJB
    private UsuarioFacade usuarioFacade;

    @EJB
    private CuentaFacade cuentaFacade;

    @EJB
    private TransferenciaFacade transferenciaFacade;

    @Inject 
    private Login login;
    
    private String cuentaDestino;
    private String concepto;
    private BigDecimal cantidad;
    private List<Movimiento> movimientos;
    private List<Transferencia> transferenciasReal;
    private List<Transferencia> transferenciasReci;

    private boolean saldoInsuficiente = false;
    private boolean cuentaInexistente = false;
    private boolean mensajes = false;

    public boolean isMensajes() {
        return saldoInsuficiente || cuentaInexistente;
    }

    public void setMensajes(boolean mensajes) {
        this.mensajes = mensajes;
    }
    
    public List<Transferencia> getTransferenciasReal() {
        return transferenciasReal;
    }

    public CuentaFacade getCuentaFacade() {
        return cuentaFacade;
    }

    public void setCuentaFacade(CuentaFacade cuentaFacade) {
        this.cuentaFacade = cuentaFacade;
    }

    public boolean isSaldoInsuficiente() {
        return saldoInsuficiente;
    }

    public void setSaldoInsuficiente(boolean saldoInsuficiente) {
        this.saldoInsuficiente = saldoInsuficiente;
    }

    public void setTransferenciasReal(List<Transferencia> transferenciasReal) {
        this.transferenciasReal = transferenciasReal;
    }

    public List<Transferencia> getTransferenciasReci() {
        return transferenciasReci;
    }

    public void setTransferenciasReci(List<Transferencia> transferenciasReci) {
        this.transferenciasReci = transferenciasReci;
    }
    

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isCuentaInexistente() {
        return cuentaInexistente;
    }

    public void setCuentaInexistente(boolean cuentaInexistente) {
        this.cuentaInexistente = cuentaInexistente;
    }
    
    
    
    @PostConstruct
    public void init(){
        cargaMovimientos();
        cargaTransferenciasRealizadas();
        cargaTransferenciasRecibidas();
    }
    
    public void cargaMovimientos(){
        Cuenta cuenta = this.login.getCuenta();
        movimientos = this.movimientoFacade.buscarPorCuentaCorriente(cuenta);
    }
    
    public void cargaTransferenciasRealizadas(){
        Usuario usuario = this.login.getUsuario();
        this.transferenciasReal = this.transferenciaFacade.buscarTransferenciasPorUsuarioOrigen(usuario);
    }
    
     public void cargaTransferenciasRecibidas(){
        Usuario usuario = this.login.getUsuario();
        this.transferenciasReci = this.transferenciaFacade.buscarTransferenciasPorUsuarioDestino(usuario);
    }
     
    private void quitaMensajes(){
        cuentaInexistente = false;
        saldoInsuficiente = false;
    }
     
    public String desconectar(){
        this.login.init();
        return "login";
    }
   
    public void transferir(){
        quitaMensajes();

        Cuenta cuentaDes = this.cuentaFacade.buscarCuentaPorCuentaCorriente(cuentaDestino);
        if(cuentaDes != null){
            if(login.getCuenta().getSaldo().subtract(cantidad).longValue() >= 0){
                Date fecha = new Date();
                Usuario origen = this.login.getUsuario();

                Usuario destino = cuentaDes.getIdUsuario();

                Transferencia transferencia = new Transferencia();
                transferencia.setCantidad(cantidad);
                transferencia.setFecha(fecha);
                transferencia.setConcepto(concepto);
                transferencia.setOrigen(origen);
                transferencia.setDestino(destino);
                this.transferenciaFacade.create(transferencia);
                login.getCuenta().setSaldo(login.getCuenta().getSaldo().subtract(cantidad));
                this.cuentaFacade.edit(login.getCuenta());
                cuentaDes.setSaldo(cuentaDes.getSaldo().add(cantidad));
                this.cuentaFacade.edit(cuentaDes);

                cargaTransferenciasRealizadas();               
            }else{
                saldoInsuficiente = true;
            }       
        }else{
            cuentaInexistente = true;
             if(login.getCuenta().getSaldo().subtract(cantidad).longValue() < 0){
                saldoInsuficiente = true; 
             }
        }
    }
    
}
