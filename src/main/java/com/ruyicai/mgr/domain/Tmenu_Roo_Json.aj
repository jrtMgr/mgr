// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ruyicai.mgr.domain;

import com.ruyicai.mgr.domain.Tmenu;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Tmenu_Roo_Json {
    
    public String Tmenu.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static Tmenu Tmenu.fromJsonToTmenu(String json) {
        return new JSONDeserializer<Tmenu>().use(null, Tmenu.class).deserialize(json);
    }
    
    public static String Tmenu.toJsonArray(Collection<Tmenu> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<Tmenu> Tmenu.fromJsonArrayToTmenus(String json) {
        return new JSONDeserializer<List<Tmenu>>().use(null, ArrayList.class).use("values", Tmenu.class).deserialize(json);
    }
    
}
