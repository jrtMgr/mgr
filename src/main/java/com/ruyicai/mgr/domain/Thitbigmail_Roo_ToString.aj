// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ruyicai.mgr.domain;

import java.lang.String;

privileged aspect Thitbigmail_Roo_ToString {
    
    public String Thitbigmail.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Mail: ").append(getMail()).append(", ");
        sb.append("Memo: ").append(getMemo()).append(", ");
        sb.append("Type: ").append(getType());
        return sb.toString();
    }
    
}
