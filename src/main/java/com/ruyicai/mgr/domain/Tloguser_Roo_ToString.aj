// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ruyicai.mgr.domain;

import java.lang.String;

privileged aspect Tloguser_Roo_ToString {
    
    public String Tloguser.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Agencyno: ").append(getAgencyno()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Nickname: ").append(getNickname()).append(", ");
        sb.append("Prestr: ").append(getPrestr()).append(", ");
        sb.append("Pwd: ").append(getPwd()).append(", ");
        sb.append("State: ").append(getState());
        return sb.toString();
    }
    
}
