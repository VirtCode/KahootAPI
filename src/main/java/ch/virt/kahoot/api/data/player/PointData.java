package ch.virt.kahoot.api.data.player;

import com.google.gson.annotations.SerializedName;

/**
 * @author VirtCode
 * @version 1.0
 */
public class PointData {
    @SerializedName("cid")
    private int cid;
    @SerializedName("answerStreakPoints")
    private AnswerStreakPoints streakPoints;
    @SerializedName("questionPoints")
    private int questionPoints;
    @SerializedName("totalPointsWithBonuses")
    private int totalPointsWithBonus;
    @SerializedName("totalPointsWithoutBonuses")
    private int totalPointsWithoutBonus;

    public int getCid() {
        return cid;
    }

    public AnswerStreakPoints getStreakPoints() {
        return streakPoints;
    }

    public int getQuestionPoints() {
        return questionPoints;
    }

    public int getTotalPointsWithBonus() {
        return totalPointsWithBonus;
    }

    public int getTotalPointsWithoutBonus() {
        return totalPointsWithoutBonus;
    }
}
