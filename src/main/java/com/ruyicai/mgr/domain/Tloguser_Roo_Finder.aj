// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ruyicai.mgr.domain;

import com.ruyicai.mgr.domain.Tloguser;
import java.lang.String;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Tloguser_Roo_Finder {
    
    public static TypedQuery<Tloguser> Tloguser.findTlogusersByNickname(String nickname) {
        if (nickname == null || nickname.length() == 0) throw new IllegalArgumentException("The nickname argument is required");
        EntityManager em = Tloguser.entityManager();
        TypedQuery<Tloguser> q = em.createQuery("SELECT o FROM Tloguser AS o WHERE o.nickname = :nickname", Tloguser.class);
        q.setParameter("nickname", nickname);
        return q;
    }
    
}
