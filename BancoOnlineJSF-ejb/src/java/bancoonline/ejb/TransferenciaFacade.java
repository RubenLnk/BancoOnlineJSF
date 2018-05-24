/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoonline.ejb;

import bancoonline.entity.Cuenta;
import bancoonline.entity.Transferencia;
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
public class TransferenciaFacade extends AbstractFacade<Transferencia> {

    @PersistenceContext(unitName = "BancoOnline-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransferenciaFacade() {
        super(Transferencia.class);
    }
    
    public List<Transferencia> buscarTransferenciasPorUsuarioOrigen(Usuario usuario) {
        Query q = this.em.createQuery("select t from Transferencia t where t.origen = :origen")
                .setParameter("origen", usuario);
        
        return q.getResultList();
    }
    
    public List<Transferencia> buscarTransferenciasPorUsuarioDestino(Usuario usuario) {
        Query q = this.em.createQuery("select t from Transferencia t where t.destino = :destino")
                .setParameter("destino", usuario);
        
        return q.getResultList();
    }
}
