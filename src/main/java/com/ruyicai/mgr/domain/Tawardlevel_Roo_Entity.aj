// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ruyicai.mgr.domain;

import com.ruyicai.mgr.domain.Tawardlevel;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Tawardlevel_Roo_Entity {
    
    declare @type: Tawardlevel: @Entity;
    
    declare @type: Tawardlevel: @Table(name = "TAWARDLEVEL", schema = "JRTSCH");
    
    @PersistenceContext(unitName = "persistenceUnit")
    transient EntityManager Tawardlevel.entityManager;
    
    @Transactional
    public void Tawardlevel.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Tawardlevel.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Tawardlevel attached = Tawardlevel.findTawardlevel(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Tawardlevel.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Tawardlevel.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Tawardlevel Tawardlevel.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Tawardlevel merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager Tawardlevel.entityManager() {
        EntityManager em = new Tawardlevel().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Tawardlevel.countTawardlevels() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Tawardlevel o", Long.class).getSingleResult();
    }
    
    public static List<Tawardlevel> Tawardlevel.findAllTawardlevels() {
        return entityManager().createQuery("SELECT o FROM Tawardlevel o", Tawardlevel.class).getResultList();
    }
    
    public static Tawardlevel Tawardlevel.findTawardlevel(BigDecimal id) {
        if (id == null) return null;
        return entityManager().find(Tawardlevel.class, id);
    }
    
    public static List<Tawardlevel> Tawardlevel.findTawardlevelEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Tawardlevel o", Tawardlevel.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
