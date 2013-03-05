// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ruyicai.mgr.domain;

import com.ruyicai.mgr.domain.CompositePK;
import com.ruyicai.mgr.domain.Tlotctrl;
import java.util.List;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Tlotctrl_Roo_Entity {
    
    declare @type: Tlotctrl: @Entity;
    
    declare @type: Tlotctrl: @Table(name = "TLOTCTRL", schema = "JRTSCH");
    
    @PersistenceContext(unitName = "persistenceUnit")
    transient EntityManager Tlotctrl.entityManager;
    
    @EmbeddedId
    private CompositePK Tlotctrl.id;
    
    public CompositePK Tlotctrl.getId() {
        return this.id;
    }
    
    public void Tlotctrl.setId(CompositePK id) {
        this.id = id;
    }
    
    @Transactional
    public void Tlotctrl.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Tlotctrl.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Tlotctrl attached = Tlotctrl.findTlotctrl(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Tlotctrl.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Tlotctrl.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Tlotctrl Tlotctrl.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Tlotctrl merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager Tlotctrl.entityManager() {
        EntityManager em = new Tlotctrl().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Tlotctrl.countTlotctrls() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Tlotctrl o", Long.class).getSingleResult();
    }
    
    public static List<Tlotctrl> Tlotctrl.findAllTlotctrls() {
        return entityManager().createQuery("SELECT o FROM Tlotctrl o", Tlotctrl.class).getResultList();
    }
    
    public static Tlotctrl Tlotctrl.findTlotctrl(CompositePK id) {
        if (id == null) return null;
        return entityManager().find(Tlotctrl.class, id);
    }
    
    public static List<Tlotctrl> Tlotctrl.findTlotctrlEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Tlotctrl o", Tlotctrl.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
