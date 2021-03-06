// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ruyicai.mgr.domain;

import com.ruyicai.mgr.domain.Torderform;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Torderform_Roo_Entity {
    
    declare @type: Torderform: @Entity;
    
    declare @type: Torderform: @Table(name = "TORDERFORM", schema = "JRTSCH");
    
    @PersistenceContext(unitName = "persistenceUnit")
    transient EntityManager Torderform.entityManager;
    
    @Transactional
    public void Torderform.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Torderform.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Torderform attached = Torderform.findTorderform(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Torderform.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Torderform.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Torderform Torderform.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Torderform merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager Torderform.entityManager() {
        EntityManager em = new Torderform().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Torderform.countTorderforms() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Torderform o", Long.class).getSingleResult();
    }
    
    public static List<Torderform> Torderform.findAllTorderforms() {
        return entityManager().createQuery("SELECT o FROM Torderform o", Torderform.class).getResultList();
    }
    
    public static Torderform Torderform.findTorderform(BigDecimal id) {
        if (id == null) return null;
        return entityManager().find(Torderform.class, id);
    }
    
    public static List<Torderform> Torderform.findTorderformEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Torderform o", Torderform.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
