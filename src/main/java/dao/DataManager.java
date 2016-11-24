/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author LegendaryJohn
 * @param <T>
 * @param <PK>
 */
public abstract class DataManager<T, PK>
{
    protected EntityManager manager;
    protected EntityTransaction transaction;
    
    
    /**
     * The generic class we reference
     */
    Class<T> entityType;
    
    public DataManager() {
        manager = Persistence.createEntityManagerFactory("PU name").createEntityManager();
        transaction = manager.getTransaction();
        
        // We need this in order to use the generics. 
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityType = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }
    
    public EntityManager getManager() {
        return this.manager;
    }
    
    public EntityTransaction getTransaction() {
        return this.transaction;
    }
    
    /**
     * Saves an entity to the database.
     * @param entity    Entity to create
     * @return          The entity created
     */
    public T create(T entity) {

        transaction.begin();
        manager.persist(entity);
        transaction.commit();

        return entity;
    }
    
    /**
     * Saves a list of entities in the database.
     * @param entities    
     */
    public void createFromList(List<T> entities) {
      
        if (entities != null || entities.size() > 0) {
            transaction.begin();
            for (T entity : entities) 
                manager.persist(entity);
            transaction.commit();
        }
    }
    
    /**
     * Lookup an entity by entity id.
     * 
     * @param id
     * @return 
     */
    public T find(PK id) {
        T entity = manager.find(entityType, id);
        
        if(entity != null){
            return manager.find(entityType, id);
        }
        
        return null;
    }

    /**
     * Update entity.
     * @param entity
     * @return 
     */
    public T update(T entity) {

        transaction.begin();
        manager.merge(entity);
        transaction.commit();

        return entity;
    }

    /**
     * Removes the entity.
     * Removes the given entity from the database vs using detach, that would
     * only remove it from the entity manager until next flush.
     * @param id
     * @return entity
     */
    public T delete(PK id) {
        transaction.begin();
        T entity = manager.find(entityType, id);

        if (entity != null) {
            manager.remove(entity);
            transaction.commit();
            return entity;
        }

        return null;
    }
    
    /**
     * Deletes all rows in a table.
     * @param table      name of the table to delete contents from. 
     */
    public void deleteAll(String table) {
        // We use a specific convention for naming the tables.
        transaction.begin();
        Query q = manager.createNativeQuery("DELETE FROM " + table);
        q.executeUpdate();
        transaction.commit();
    }
}
