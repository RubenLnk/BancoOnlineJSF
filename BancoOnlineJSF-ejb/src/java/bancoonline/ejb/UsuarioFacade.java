/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoonline.ejb;

import bancoonline.entity.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author t1t0
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "BancoOnline-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public List<Usuario> buscarUsuarioPorDniYPassword(String dni, String password) {
        Query q = this.em.createQuery("select u from Usuario u where u.dni like :dni " +
                "and u.password like :password");
        q.setParameter("dni", dni);
        q.setParameter("password", password);
        
        return q.getResultList();
    }
}
