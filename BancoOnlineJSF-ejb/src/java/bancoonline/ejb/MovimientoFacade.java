/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoonline.ejb;

import bancoonline.entity.Cuenta;
import bancoonline.entity.Movimiento;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author t1t0
 */
@Stateless
public class MovimientoFacade extends AbstractFacade<Movimiento> {

    @PersistenceContext(unitName = "BancoOnline-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovimientoFacade() {
        super(Movimiento.class);
    }
    
    public List<Movimiento> buscarPorCuentaCorriente(Cuenta cuenta) {
        Query q = this.em.createQuery("select m from Movimiento m where m.idCuenta = :cuenta")
                .setParameter("cuenta", cuenta);
        
        return q.getResultList();
    }
    
    public List<Movimiento> findByIdMovimiento(Integer id) {
        Query q = this.em.createQuery("SELECT m FROM Movimiento m WHERE m.idMovimiento = :id")
                .setParameter("id", id);
        
        return q.getResultList();
    }

    public List<Movimiento> findByConcepto(String concepto) {
        Query q = this.em.createQuery("SELECT m FROM Movimiento m WHERE m.concepto LIKE :str")
                .setParameter("str", "%" + concepto + "%");
        
        return q.getResultList();
    }
    
    public List<Movimiento> findByCantidad(BigDecimal cantidad) {
        Query q = this.em.createQuery("SELECT m FROM Movimiento m WHERE m.cantidad = :cantidad")
                .setParameter("cantidad", cantidad);
        
        return q.getResultList();
    }
    
    public List<Movimiento> findByFecha(Date fecha) {
        Query q = this.em.createQuery("SELECT m FROM Movimiento m WHERE CAST(m.fecha AS DATE) = :fecha")
                .setParameter("fecha", fecha);
        
        return q.getResultList();
    }
}
