package com.tms.util;


import java.io.Serializable;
import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class UtilidadDaoBean<T, ID extends Serializable> {

 
    public Class<T> miClase;

    @PersistenceContext(unitName = "TMS-ejbPU")
    protected EntityManager em;

    public final void setMiClase(Class<T> miClase) {
        this.miClase = miClase;
    }

    public List<T> getListAll() {
        Query query = em.createNamedQuery(miClase.getSimpleName() + ".findAll");
        return query.getResultList();
    }
    
    
    public T findOne(ID id) {
        return em.find(miClase, id);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void eliminar(ID id) {
        T entity = findOne(id);
        em.remove (entity);
    

    }
    
    public void  crear (T entity){
       em.persist(entity);
    }
     

    public T update(T ent) {
        return em.merge(ent);
    }

}
