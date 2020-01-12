package ch.virt.kahoot.api.connection;

/**
 * This class provides a listener whether a game exists or not
 * @author VirtCode
 * @version 1.0
 */
public interface GameExistsListener {

    /**
     * Tells the subscriber whether a game exists or not
     * @param exists game exists
     */
    void gameExists(boolean exists);
}
