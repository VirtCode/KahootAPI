package ch.virt.kahoot.api.data.player;

import ch.virt.kahoot.api.data.player.objects.Nemesis;
import ch.virt.kahoot.api.data.player.objects.PointData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * CAUTION:
 * Most of those field will be null depending on the response, the class was built upon
 * Most used fields per request will be noted in the correct method in the AdvancedGame class
 * The variables have comments that give a rough overview which fields belong to which response
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

    /**
     * Returns the name of the player
     * @return name of the player
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Returns the type of the quiz
     * @return type of the quiz
     */
    public String getQuizType() {
        return quizType;
    }

    /**
     * Returns whether the player is on version 2
     * @return player is on version 2
     */
    public boolean isPlayerV2() {
        return playerV2;
    }

    /**
     * Returns the name of the current quiz
     * @return name of the current quiz
     */
    public String getQuizName() {
        return quizName;
    }

    /**
     * Returns the questions with their answers in an array
     * @return questions with their answers in an array
     */
    public List<Integer> getQuestionAnswers() {
        return questionAnswers;
    }

    /**
     * Returns the index of the current question
     * @return index of the current question
     */
    public int getQuestionIndex() {
        return questionIndex;
    }

    /**
     * Returns the current game block type
     * @return current game block type
     */
    public String getGameBlockType() {
        return gameBlockType;
    }

    /**
     * Returns the time left until the question starts
     * @return time left until the question starts
     */
    public int getTimeLeft() {
        return timeLeft;
    }

    /**
     * Returns the question index in a somekind wierd spot
     * (I guess)
     * @return question index in a somekind wierd spot
     */
    public int getQuestionNumber() {
        return questionNumber;
    }

    /**
     * Returns the type of the quiz
     * @return type of the quiz
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the returns the chosen answer
     * @return returns the chosen answer
     */
    public int getChoice() {
        return choice;
    }

    /**
     * Returns whether the choice is correct
     * @return choice is correct
     */
    public boolean isChoiceCorrect() {
        return isChoiceCorrect;
    }

    /**
     * Returns the text of the chosen answer
     * @return text of the chosen answer
     */
    public String getChosenText() {
        return chosenText;
    }

    /**
     * Returns the received time of the question
     * @return received time of the question
     */
    public int getTimeReceived() {
        return timeReceived;
    }

    /**
     * Returns whether the gives points
     * @return gives points
     */
    public boolean isQuestionGivePoints() {
        return questionGivePoints;
    }

    /**
     * Returns the amount of points gained with the question
     * @return amount of points gained with the question
     */
    public int getPointsGained() {
        return pointsGained;
    }

    /**
     * Returns the text of the correct answers
     * @return text of the correct answers
     */
    public List<String> getCorrectTexts() {
        return correctTexts;
    }

    /**
     * Returns the total score of the player
     * @return total score of the player
     */
    public int getTotalScore() {
        return totalScore;
    }

    /**
     * Returns the data of the player score and gained points
     * @return data of the player score and gained points
     */
    public PointData getPointData() {
        return pointData;
    }

    /**
     * Returns the rank the player has
     * (hopefully #1)
     * @return rank the player has
     */
    public int getRank() {
        return rank;
    }

    /**
     * Returns the nemesis of the player
     * @return nemesis of the player
     */
    public Nemesis getNemesis() {
        return nemesis;
    }

    /**
     * Returns the count of correct choices the player has made at the end of the game
     * @return count of correct choices the player has made at the end of the game
     */
    public int getCorrectChoicesCount() {
        return correctChoicesCount;
    }

    /**
     * Returns the the count of incorrect choices the player has made at the end of the game
     * @return the count of incorrect choices the player has made at the end of the game
     */
    public int getIncorrectChoicesCount() {
        return incorrectChoicesCount;
    }

    /**
     * Returns whether the player has been kicked
     * @return player has been kicked
     */
    public boolean isKicked() {
        return kicked;
    }

    /**
     * Returns whether the player is a ghost
     * (somehow)
     * @return player is a ghost
     */
    public boolean isGhost() {
        return ghost;
    }

    /**
     * Returns the count of questions the player hasn't answered during the game
     * @return count of questions the player hasn't answered during the game
     */
    public int getUnansweredChoicesCount() {
        return unansweredChoicesCount;
    }

    /**
     * Returns the amount of players participating
     * @return amount of players participating
     */
    public int getPlayerCount() {
        return playerCount;
    }

    /**
     * Returns the start time of the quiz
     * @return start time of the quiz
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Returns the uuid of the quiz played
     * @return uuid of the quiz played
     */
    public String getQuizUUID() {
        return quizUUID;
    }

    /**
     * Returns the name of the winning player
     * @return name of the winning player
     */
    public String getWinPlayerName() {
        return winPlayerName;
    }

    /**
     * Returns the uuid of the host
     * @return uuid of the host
     */
    public String getHostUUID() {
        return hostUUID;
    }

    /**
     * Returns the uuid of the challenge
     * @return uuid of the challenge
     */
    public String getChallengeUUID() {
        return challengeUUID;
    }

    /**
     * Returns whether the game has nonpoint gameblocks
     * @return game has nonpoint gameblocks
     */
    public boolean isNonPointGameBlocks() {
        return nonPointGameBlocks;
    }

    /**
     * Returns the type of medal on the podium
     * @return type of medal on the podium
     */
    public String getPodiumMedalType() {
        return podiumMedalType;
    }

    /**
     * Returns the primary message on the podium
     * @return primary message on the podium
     */
    public String getPodiumPrimaryMessage() {
        return podiumPrimaryMessage;
    }

    /**
     * Returns the secondary message on the podium
     * @return secondary message on the podium
     */
    public String getPodiumSecondaryMessage() {
        return podiumSecondaryMessage;
    }
}
