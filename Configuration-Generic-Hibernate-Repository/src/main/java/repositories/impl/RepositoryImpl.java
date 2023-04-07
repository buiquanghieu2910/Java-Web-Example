/**
 * BUI_QUANG_HIEU, 2023
 * RepositoryImpl.java
 */
package repositories.impl;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import repositories.Repository;
import utils.HibernateUtil;

import java.io.Serializable;
import java.util.List;

/**
 * @author BUI_QUANG_HIEU
 */
public class RepositoryImpl<T, ID extends Serializable> implements Repository<T, ID> {

    private final Class<T> persistentClass;
    private Session session;

    public RepositoryImpl(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    @Override
    public T save(T t) {
        try {
            this.session = HibernateUtil.getFACTORY().openSession();
            this.session.getTransaction().begin();
            this.session.persist(t);
            this.session.getTransaction().commit();
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            this.session.getTransaction().rollback();
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
        return null;
    }

    @Override
    public T delete(T t) {
        try {
            this.session = HibernateUtil.getFACTORY().openSession();
            this.session.getTransaction().begin();
            this.session.delete(t);
            this.session.getTransaction().commit();
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            this.session.getTransaction().rollback();
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
        return null;
    }

    @Override
    public T update(T t) {
        try {
            this.session = HibernateUtil.getFACTORY().openSession();
            this.session.getTransaction().begin();
            T mergedT = this.session.merge(t);
            this.session.flush();
            this.session.getTransaction().commit();
            return mergedT;
        } catch (Exception e) {
            e.printStackTrace();
            this.session.getTransaction().rollback();
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
        return null;
    }

    @Override
    public List<T> findAll() {
        try {
            this.session = HibernateUtil.getFACTORY().openSession();
            CriteriaQuery<T> cq = this.session.getCriteriaBuilder().createQuery(persistentClass);
            Root<T> rootEntry = cq.from(persistentClass);
            CriteriaQuery<T> all = cq.select(rootEntry);
            TypedQuery<T> allQuery = this.session.createQuery(all);
            return allQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
        return null;
    }

    @Override
    public T findById(ID id) {
        try {
            this.session = HibernateUtil.getFACTORY().openSession();
            return (T) this.session.find(persistentClass, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
        return null;
    }

    @Override
    public List<T> where(String query) {
        try {
            this.session = HibernateUtil.getFACTORY().openSession();
            TypedQuery<T> hql = this.session.createQuery(query);
            return hql.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
        return null;
    }
}
