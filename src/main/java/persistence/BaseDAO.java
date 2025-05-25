package persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public abstract class BaseDAO<T> {

    @PersistenceContext(unitName = "PostgrePU")
    protected EntityManager em;

    private final Class<T> entityClass;

    protected BaseDAO(Class<T> entityClass) {this.entityClass = entityClass;}

    public void persist(T entity) {em.persist(entity);}

    public void removeById(Object primaryKey) {
        T entityRemoved = em.find(entityClass, primaryKey);
        if (entityRemoved != null) {
            em.remove(entityRemoved);
        }
    }

    public T find(Object Id) {return em.find(entityClass, Id);}

    @Transactional
    public T update(T entity) {return em.merge(entity);}

    public List<T> findAll() {
        return em.createNamedQuery(entityClass.getSimpleName() + ".findAll", entityClass).getResultList();
    }
}
