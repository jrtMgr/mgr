// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ruyicai.mgr.domain;

import com.ruyicai.mgr.domain.Tlotstrategy;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Tlotstrategy_Roo_Entity {
    
    declare @type: Tlotstrategy: @Entity;
    
    declare @type: Tlotstrategy: @Table(name = "TLOTSTRATEGY", schema = "JRTSCH");
    
    @PersistenceContext(unitName = "persistenceUnit")
    transient EntityManager Tlotstrategy.entityManager;
    
    @Transactional
    public void Tlotstrategy.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Tlotstrategy.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Tlotstrategy attached = Tlotstrategy.findTlotstrategy(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Tlotstrategy.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Tlotstrategy.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Tlotstrategy Tlotstrategy.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Tlotstrategy merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager Tlotstrategy.entityManager() {
        EntityManager em = new Tlotstrategy().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Tlotstrategy.countTlotstrategys() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Tlotstrategy o", Long.class).getSingleResult();
    }
    
    public static List<Tlotstrategy> Tlotstrategy.findAllTlotstrategys() {
        return entityManager().createQuery("SELECT o FROM Tlotstrategy o", Tlotstrategy.class).getResultList();
    }
    
    public static Tlotstrategy Tlotstrategy.findTlotstrategy(long id) {
        return entityManager().find(Tlotstrategy.class, id);
    }
    
    public static List<Tlotstrategy> Tlotstrategy.findTlotstrategyEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Tlotstrategy o", Tlotstrategy.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
