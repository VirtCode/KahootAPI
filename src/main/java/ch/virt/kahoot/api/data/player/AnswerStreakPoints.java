package ch.virt.kahoot.api.data.player;

import com.google.gson.annotations.SerializedName;

/**
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

    public int getLevel() {
        return level;
    }

    public int getBonus() {
        return bonus;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public int getPreviousLevel() {
        return previousLevel;
    }

    public int getPreviousBonus() {
        return previousBonus;
    }
}
