/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Jesus Donaldo
 * @param <T> El tipo de la clase persistente
 * @param <ID> El tipo del id de la clase persistente
 */
public abstract class GenericDAO<T, ID extends Serializable> {
    
    private final Class<T> persistentClass;
    
    public GenericDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    protected Session getSession() {
        Session session = null;
        
        if(HibernateUtil.getSessionFactory() != null) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
        
        return session;
    }
    
    public Class<T> getPersistentClass() {
        return persistentClass;
    }
    
    /**
     * Obtener la entidad con todas sus relaciones, es decir, completa (default)
     * Es necesario implementar este método en los daos específicos, solo para
     * las entidades que tienen relaciones, claro
     * @param id el id de la entidad
     * @return la entidad completa
     */
    public abstract T obtener(ID id);
    
    public List<T> obtenerTodos() {
        
        Session s = getSession();
        Transaction tx = null;
        List<T> entidades;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }
        
        try {
            tx = s.beginTransaction();
            //Obtiene todos los objetos de la clase, sin sus relaciones
            Criteria c = s.createCriteria(getPersistentClass());
            entidades = c.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        
        return entidades;
    }
    
    public ID insertar(T entidad) {  
        
        Session s = getSession();
        Transaction tx = null;
        ID id = null;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return null;
        }
        
        try {
            tx = s.beginTransaction();
            //Persiste la entidad en la base de datos
            id = (ID) s.save(entidad);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
        
        return id;
    }
    
    public void modificar(T entidad) {  
        
        Session s = getSession();
        Transaction tx = null;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return;
        }
        
        try {
            tx = s.beginTransaction();
            //Modifica la entidad en la base de datos
            s.update(entidad);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
    }
    
    public void eliminar(T entidad) {  
        
        Session s = getSession();
        Transaction tx = null;
        
        if(s == null) {
            System.out.println("Session nula, regresando null....");
            return;
        }
        
        try {
            tx = s.beginTransaction();
            //Elimina la entidad de la base de datos
            s.delete(entidad);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            s.close();
            System.out.println("Session cerrada");
        }
    }
    
}