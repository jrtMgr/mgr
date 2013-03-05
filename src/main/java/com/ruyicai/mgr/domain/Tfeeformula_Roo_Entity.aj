// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ruyicai.mgr.domain;

import com.ruyicai.mgr.domain.Tfeeformula;
import java.lang.String;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Tfeeformula_Roo_Entity {
    
    declare @type: Tfeeformula: @Entity;
    
    declare @type: Tfeeformula: @Table(name = "Tfeeformula", schema = "JRTSCH");
    
    @PersistenceContext(unitName = "persistenceUnit")
    transient EntityManager Tfeeformula.entityManager;
    
    @Transactional
    public void Tfeeformula.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Tfeeformula.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Tfeeformula attached = Tfeeformula.findTfeeformula(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Tfeeformula.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Tfeeformula.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Tfeeformula Tfeeformula.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Tfeeformula merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager Tfeeformula.entityManager() {
        EntityManager em = new Tfeeformula().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Tfeeformula.countTfeeformulas() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Tfeeformula o", Long.class).getSingleResult();
    }
    
    public static List<Tfeeformula> Tfeeformula.findAllTfeeformulas() {
        return entityManager().createQuery("SELECT o FROM Tfeeformula o", Tfeeformula.class).getResultList();
    }
    
    public static Tfeeformula Tfeeformula.findTfeeformula(String id) {
        if (id == null || id.length() == 0) return null;
        return entityManager().find(Tfeeformula.class, id);
    }
    
    public static List<Tfeeformula> Tfeeformula.findTfeeformulaEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Tfeeformula o", Tfeeformula.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
