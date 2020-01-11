package ch.virt.kahoot.api.data.status;

public enum Status {
    ACTIVE,
    MISSING,
    NULL;
    
    public static Status getForString(String s){
        switch (s){
            case "ACTIVE": return ACTIVE;
            case "MISSING": return MISSING;
            default: return NULL;
        }
    }
}
