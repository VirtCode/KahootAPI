package ch.virt.kahoot.api.data.player;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author VirtCode
 * @version 1.0
 */
public class PlayerResponseContent {

    //ID: 14
    @SerializedName("playerName")
    private String playerName;
    @SerializedName("quizType")
    private String quizType;
    @SerializedName("playerV2")
    private boolean playerV2;

    //ID: 9
    @SerializedName("quizName")
    private String quizName;
    @SerializedName("quizQuestionAnswers")
    private List<Integer> questionAnswers;

    //ID: 1 & 2

    @SerializedName("questionIndex")
    private int questionIndex;
    @SerializedName("gameBlockType")
    private String gameBlockType;
    @SerializedName("timeLeft")
    private int timeLeft;

    //ID: 4
    @SerializedName("questionNumber")
    private int questionNumber;

    //ID: 8
    @SerializedName("type")
    private String type;
    @SerializedName("choice")
    private int choice;
    @SerializedName("isCorrect")
    private boolean isChoiceCorrect;
    @SerializedName("text")
    private String chosenText;
    @SerializedName("receivedTime")
    private int timeReceived;
    @SerializedName("pointsQuestion")
    private boolean questionGivePoints;
    @SerializedName("points")
    private int pointsGained;
    @SerializedName("correctAnswers")
    private List<String> correctTexts;
    @SerializedName("totalScore")
    private int totalScore;
    @SerializedName("pointsData")
    private PointData pointData;
    @SerializedName("rank")
    private int rank;
    @SerializedName("nemesis")
    private Nemesis nemesis;

    //ID 3
    @SerializedName("correctCount")
    private int correctChoicesCount;
    @SerializedName("incorrectCount")
    private int incorrectChoicesCount;
    @SerializedName("isKicked")
    private boolean kicked;
    @SerializedName("isGhost")
    private boolean ghost;
    @SerializedName("unansweredCount")
    private int unansweredChoicesCount;
    @SerializedName("playerCount")
    private int playerCount;
    @SerializedName("startTime")
    private int startTime;
    @SerializedName("quizId")
    private String quizUUID;
    @SerializedName("name")
    private String winPlayerName;
    @SerializedName("hostId")
    private String hostUUID;
    @SerializedName("challengeId")
    private String challengeUUID;
    @SerializedName("isOnlyNonPointGameBlockKahoot")
    private boolean nonPointGameBlocks;

    //ID: 13
    @SerializedName("podiumMedalType")
    private String podiumMedalType;
    @SerializedName("primaryMessage")
    private String podiumPrimaryMessage;
    @SerializedName("secondaryMessage")
    private String podiumSecondaryMessage;



    public String getPlayerName() {
        return playerName;
    }

    public String getQuizType() {
        return quizType;
    }

    public boolean isPlayerV2() {
        return playerV2;
    }

    public String getQuizName() {
        return quizName;
    }

    public List<Integer> getQuestionAnswers() {
        return questionAnswers;
    }

    public int getQuestionIndex() {
        return questionIndex;
    }

    public String getGameBlockType() {
        return gameBlockType;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getType() {
        return type;
    }

    public int getChoice() {
        return choice;
    }

    public boolean isChoiceCorrect() {
        return isChoiceCorrect;
    }

    public String getChosenText() {
        return chosenText;
    }

    public int getTimeReceived() {
        return timeReceived;
    }

    public boolean isQuestionGivePoints() {
        return questionGivePoints;
    }

    public int getPointsGained() {
        return pointsGained;
    }

    public List<String> getCorrectTexts() {
        return correctTexts;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public PointData getPointData() {
        return pointData;
    }

    public int getRank() {
        return rank;
    }

    public Nemesis getNemesis() {
        return nemesis;
    }
}
