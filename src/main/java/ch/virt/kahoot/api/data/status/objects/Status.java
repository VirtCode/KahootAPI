package ch.virt.kahoot.api.data.status.objects;

/**
 * This enum represents the status of a kahoot game
 * @author VirtCode
 * @version 1.0
 */
public enum Status {
    ACTIVE,
    MISSING,
    NULL;

    /**
     * Returns the status for a sting
     * @return status for a sting
     * @param s String to convert to status
     */
    public static Status getForString(String s){
        switch (s){
            case "ACTIVE": return ACTIVE;
            case "MISSING": return MISSING;
            default: return NULL;
        }
    }
}
