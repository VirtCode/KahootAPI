package ch.virt.kahoot.api.data.player.objects;

import com.google.gson.annotations.SerializedName;

/**
 * This class represents a json kahoot object for gained streakpoints
 * @author VirtCode
 * @version 1.0
 */
public class AnswerStreakPoints {
    @SerializedName("streakLevel")
    private int level;
    @SerializedName("streakBonus")
    private int bonus;
    @SerializedName("totalStreakPoints")
    private int totalPoints;
    @SerializedName("previousStreakLevel")
    private int previousLevel;
    @SerializedName("previousStreakBonus")
    private int previousBonus;

    /**
     * Returns the level of the streak
     * @return level of the streak
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns the bonus of the streak
     * @return bonus of the streak
     */
    public int getBonus() {
        return bonus;
    }

    /**
     * Returns the total points
     * @return total points
     */
    public int getTotalPoints() {
        return totalPoints;
    }

    /**
     * Returns the previous streak level
     * @return previous streak level
     */
    public int getPreviousLevel() {
        return previousLevel;
    }

    /**
     * Returns the previous streakbonus
     * @return previous streakbonus
     */
    public int getPreviousBonus() {
        return previousBonus;
    }
}
