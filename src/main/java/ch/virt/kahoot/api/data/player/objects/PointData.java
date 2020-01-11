package ch.virt.kahoot.api.data.player.objects;

import ch.virt.kahoot.api.data.player.objects.AnswerStreakPoints;
import com.google.gson.annotations.SerializedName;

/**
 * This class represents a kahoot json object for data about gained points
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

    /**
     * Returns the cid of the resposne
     * @return cid of the resposne
     */
    public int getCid() {
        return cid;
    }

    /**
     * Returns the info about the streak and its points
     * @return info about the streak and its points
     */
    public AnswerStreakPoints getStreakPoints() {
        return streakPoints;
    }

    /**
     * Returns the points the question gives
     * @return points the question gives
     */
    public int getQuestionPoints() {
        return questionPoints;
    }

    /**
     * Returns the total score with bonuses
     * @return total score with bonuses
     */
    public int getTotalPointsWithBonus() {
        return totalPointsWithBonus;
    }

    /**
     * Returns the total score without bonuses
     * @return total score without bonuses
     */
    public int getTotalPointsWithoutBonus() {
        return totalPointsWithoutBonus;
    }
}
