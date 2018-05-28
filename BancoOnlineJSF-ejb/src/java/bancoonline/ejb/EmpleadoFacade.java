/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoonline.ejb;

import bancoonline.entity.Empleado;
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
public class EmpleadoFacade extends AbstractFacade<Empleado> {

    @PersistenceContext(unitName = "BancoOnline-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoFacade() {
        super(Empleado.class);
    }
    
    public Empleado buscarEmpleadoPorDniYPassword(String dni, String password) {
        Query q = this.em.createQuery("select e from Empleado e where e.dni like :dni" +
                " and e.password like :password");
        q.setParameter("dni", dni);
        q.setParameter("password", password);
        
        List<Empleado> empleados = q.getResultList();
        if(empleados.size() <= 0) {
            return null;
        } else {
            return empleados.get(0);
        }
    }
            
    
}
