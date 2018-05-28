/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoonline.ejb;

import bancoonline.entity.Cuenta;
import bancoonline.entity.Usuario;
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
public class CuentaFacade extends AbstractFacade<Cuenta> {

    @PersistenceContext(unitName = "BancoOnline-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentaFacade() {
        super(Cuenta.class);
    }
    
    public Cuenta buscarCuentaPorIdUsuario(Usuario usuario) {
        Query q = this.em.createQuery("select c from Cuenta c where c.idUsuario = :usuario")
                .setParameter("usuario", usuario);
        
        List<Cuenta> cuentas = q.getResultList();
        
        if(cuentas.size() <= 0) {
            return null;
        } else {
            return cuentas.get(0);
        }
    }
    
    public Cuenta buscarCuentaPorCuentaCorriente(String cuentaCorriente) {
        Query q = this.em.createQuery("select c from Cuenta c where c.cuentaCorriente like :cuentaCorriente")
                .setParameter("cuentaCorriente", cuentaCorriente);
        
        List<Cuenta> cuentas = q.getResultList();
        
        if(cuentas.size() <= 0) {
            return null;
        } else {
            return cuentas.get(0);
        }
    }
    
}
