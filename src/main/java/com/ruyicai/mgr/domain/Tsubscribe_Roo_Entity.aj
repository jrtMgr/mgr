// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ruyicai.mgr.domain;

import com.ruyicai.mgr.domain.Tsubscribe;
import java.lang.String;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Tsubscribe_Roo_Entity {
    
    declare @type: Tsubscribe: @Entity;
    
    declare @type: Tsubscribe: @Table(name = "TSUBSCRIBE", schema = "JRTSCH");
    
    @PersistenceContext(unitName = "persistenceUnit")
    transient EntityManager Tsubscribe.entityManager;
    
    @Transactional
    public void Tsubscribe.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Tsubscribe attached = Tsubscribe.findTsubscribe(this.flowno);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Tsubscribe.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Tsubscribe.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    public static final EntityManager Tsubscribe.entityManager() {
        EntityManager em = new Tsubscribe().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Tsubscribe.countTsubscribes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Tsubscribe o", Long.class).getSingleResult();
    }
    
    public static List<Tsubscribe> Tsubscribe.findAllTsubscribes() {
        return entityManager().createQuery("SELECT o FROM Tsubscribe o", Tsubscribe.class).getResultList();
    }
    
    public static Tsubscribe Tsubscribe.findTsubscribe(String flowno) {
        if (flowno == null || flowno.length() == 0) return null;
        return entityManager().find(Tsubscribe.class, flowno);
    }
    
    public static List<Tsubscribe> Tsubscribe.findTsubscribeEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Tsubscribe o", Tsubscribe.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
