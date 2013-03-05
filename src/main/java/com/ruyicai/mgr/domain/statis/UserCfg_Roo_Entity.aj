// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ruyicai.mgr.domain.statis;

import com.ruyicai.mgr.domain.statis.UserCfg;
import java.lang.Integer;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UserCfg_Roo_Entity {
    
    declare @type: UserCfg: @Entity;
    
    declare @type: UserCfg: @Table(name = "TUSERCFG", schema = "JRTSTATIS");
    
    @PersistenceContext(unitName = "persistenceUnit")
    transient EntityManager UserCfg.entityManager;
    
    @Transactional
    public void UserCfg.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void UserCfg.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            UserCfg attached = UserCfg.findUserCfg(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void UserCfg.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void UserCfg.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public UserCfg UserCfg.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        UserCfg merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager UserCfg.entityManager() {
        EntityManager em = new UserCfg().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long UserCfg.countUserCfgs() {
        return entityManager().createQuery("SELECT COUNT(o) FROM UserCfg o", Long.class).getSingleResult();
    }
    
    public static List<UserCfg> UserCfg.findAllUserCfgs() {
        return entityManager().createQuery("SELECT o FROM UserCfg o", UserCfg.class).getResultList();
    }
    
    public static UserCfg UserCfg.findUserCfg(Integer id) {
        if (id == null) return null;
        return entityManager().find(UserCfg.class, id);
    }
    
    public static List<UserCfg> UserCfg.findUserCfgEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM UserCfg o", UserCfg.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
