package ch.virt.kahoot.api.game;

import ch.virt.kahoot.api.data.controller.ControllerResponse;
import ch.virt.kahoot.api.data.player.objects.Nemesis;
import ch.virt.kahoot.api.data.player.PlayerResponse;
import ch.virt.kahoot.api.data.player.PlayerResponseContent;
import ch.virt.kahoot.api.data.player.objects.PointData;
import ch.virt.kahoot.api.data.status.objects.Status;
import ch.virt.kahoot.api.data.status.StatusResponse;

import java.util.HashMap;
import java.util.List;

/**
 * This class represents a more advanced wrapper for a kahoot game
 * @author VirtCode
 * @version 1.0
 */
public abstract class AdvancedGame extends Game {

    @Override
    public void playerMessageReceived(PlayerResponse response) {
        PlayerResponseContent content = response.getData().getContent();

        switch (response.getData().getID()){
            case PlayerResponse.QUESTION_PREPARE:
                questionPreparation(content.getTimeLeft(),
                        content.getQuestionIndex(),
                        content.getGameBlockType());
                break;
            case PlayerResponse.QUESTION_START:
                questionActive(content.getQuestionIndex(),
                        content.getGameBlockType());
                break;
            case PlayerResponse.QUESTION_END:
                questionEnd(content.getQuestionNumber());
                break;
            case PlayerResponse.ANSWER_CORRECT:
                answerResult(content.getType(),
                        content.getChoice(),
                        content.isChoiceCorrect(),
                        content.getChosenText(),
                        content.getPointsGained(),
                        content.getCorrectTexts(),
                        content.getTotalScore(),
                        content.getPointData(),
                        content.getRank(),
                        content.getNemesis());
                break;
            case PlayerResponse.QUIZ_START:
                quizStart(content.getQuizName(),
                        content.getQuizType(),
                        content.getQuestionAnswers());
                break;
            case PlayerResponse.CONNECTION_CONFIRMED:
                connectionUpdate(response.getData().getHost(),
                        content.getPlayerName());
                break;
            case PlayerResponse.QUIZ_FEEDBACK:
                feedbackRequest(content.getQuizType());
                break;
            case PlayerResponse.QUIZ_END:
                quizEnd(content.getRank(),
                        content.getCorrectChoicesCount(),
                        content.getIncorrectChoicesCount(),
                        content.isKicked(),
                        content.isGhost(),
                        content.getUnansweredChoicesCount(),
                        content.getPlayerCount(),
                        content.getStartTime(),
                        content.getQuizUUID(),
                        content.getPlayerName(),
                        content.getTotalScore(),
                        content.getHostUUID(),
                        content.getChallengeUUID(),
                        content.isNonPointGameBlocks());
                break;
            case PlayerResponse.QUIZ_CLOSED:
                podiumUpdate(content.getPodiumMedalType(),
                        content.getPodiumPrimaryMessage(),
                        content.getPodiumSecondaryMessage());
                break;
            case PlayerResponse.QUIZ_FINISHED:
                quizFinished();
                break;
            default: unknownPlayerEvent(response);
        }
    }

    @Override
    public void controllerMessageReceived(ControllerResponse message) {
        switch (message.getData().getType()){
            case "loginResponse":
                if (message.getData().getErrorCode() == null) loginSuccess();
                else loginFailure(message.getData().getErrorCode(), message.getData().getDescription());
                break;
            default: unknownControllerEvent(message);
        }
    }

    @Override
    public void statusMessageReceived(StatusResponse message) {
        switch (message.getData().getType()){
            case "status": statusUpdate(Status.getForString(message.getData().getStatus())); break;
            default: unknownStatusEvent(message);
        }
    }

    /**
     * Occurres when a unrecognised player response comes from the server
     * @param response unrecognised response
     */
    protected abstract void unknownPlayerEvent(PlayerResponse response);
    /**
     * Occurres when a unrecognised controller response comes from the server
     * @param response unrecognised response
     */
    protected abstract  void unknownStatusEvent(StatusResponse response);
    /**
     * Occurres when a unrecognised controller response comes from the server
     * @param response unrecognised response
     */
    protected abstract void unknownControllerEvent(ControllerResponse response);

    protected abstract void statusUpdate(Status status);

    protected abstract void connectionUpdate(String host, String yourName);

    /**
     * Occurres when the login succeeds
     */
    protected abstract void loginSuccess();
    /**
     * Occurres when the login fails
     * @param errorCode error code
     * @param description description of the error
     */
    protected abstract void loginFailure(String errorCode, String description);

    /**
     * Occures when a question is going to happen
     * @param timeLeft time till the question starts
     * @param questionIndex index of the question
     * @param blockType type of the gameblock
     */
    protected abstract void questionPreparation(int timeLeft, int questionIndex, String blockType);
    /**
     * Occures when a question is active and is ready to be answered
     * @param index index of the question
     * @param blockType type of the gameblock
     */
    protected abstract void questionActive(int index, String blockType);
    /**
     * Occurres when a question has ended
     * @param index index of the question
     */
    protected abstract void questionEnd(int index);

    /**
     * Occures when a question has been answered
     * @param blockType type of the gameblock
     * @param choice choice the user has made
     * @param isCorrect whether the choice is correct
     * @param chosenText the text of the chosen answer
     * @param pointsGained the points gained with this answer
     * @param correctAnswers the answers that could have been correct
     * @param totalScore the total score of the player
     * @param data the data about the player score
     * @param rank the rank the player has
     * @param nemesis the nemesis of the player
     */
    protected abstract void answerResult(String blockType, int choice, boolean isCorrect, String chosenText, int pointsGained, List<String> correctAnswers, int totalScore, PointData data, int rank, Nemesis nemesis);
    /**
     * Occurres when the player comes on the podium
     * @param medalType type of the medal the player has
     * @param primaryMessage primary message on the podium
     * @param secondaryMessage secondary message on the podium
     */
    protected abstract void podiumUpdate(String medalType, String primaryMessage, String secondaryMessage);
    /**
     * Occurres when the teacher decides to get feedback
     * @param quizType type the quiz was
     */
    protected abstract void feedbackRequest(String quizType);


    /**
     * Occurres when the quiz starts
     * @param name name of that quiz
     * @param type type of that quiz
     * @param questions the questions that will occur
     */
    protected abstract void quizStart(String name, String type, List<Integer> questions);
    /**
     * Occurres when the quiz ends
     * @param rank final rank of the player
     * @param correctCount count of correct answered questions
     * @param incorrectCount count of incorrect answered questions
     * @param isKicked whether the player has been kicked
     * @param isGhost whether the player is a ghost
     * @param unansweredCount count of questions the player hasn't answered
     * @param playerCount count of players in the game
     * @param startTime time when the quiz started
     * @param quizUUID uuid of the quiz
     * @param playerName name of the player
     * @param totalScore total score of the player
     * @param hostUUID uuid of the host of the game
     * @param challengeUUID uuid of the challenge for the game
     * @param nonPointGameBlocks whether the game has non point blocks
     */
    protected abstract void quizEnd(int rank, int correctCount, int incorrectCount, boolean isKicked, boolean isGhost, int unansweredCount, int playerCount, int startTime, String quizUUID, String playerName, int totalScore, String hostUUID, String challengeUUID, boolean nonPointGameBlocks);
    /**
     * Occurres when the quiz has finally finsished
     */
    protected abstract void quizFinished();

    /**
     * Sends an answer to the game
     * @param questionIndex index of the question to answer
     * @param choice choice the player has made
     */
    protected void sendAnswer(int questionIndex, int choice){
        send("/service/controller", createAnswerResponse(questionIndex, choice));
    }

    /**
     * Sends feedback to the game
     * (Doesn't work yet)
     * @param fun how fun the game was (1 - 5)
     * @param learning whether you did learn something (true / false)
     * @param recommend whether you would recommend it (true / false)
     * @param overall overall goodness of the game (1 - 3)
     * @param totalScore total score of the player
     * @param nickname nickname of the player
     */
    protected void sendFeedback(int fun, boolean learning, boolean recommend, int overall, int totalScore, String nickname){
        send("/service/controller", createFeedback(fun, learning, recommend, overall, totalScore, nickname));
    }

    /**
     * Creates a response body for an answer
     * @param questionIndex index of the question
     * @param choice choice the player has made
     * @return response body to send
     */
    private HashMap<String, Object> createAnswerResponse(int questionIndex, int choice){
        HashMap<String, Object> map = new HashMap<>();

        map.put("content", "{\"choice\":" + choice + ",\"questionIndex\":" + questionIndex + ",\"meta\":{\"lag\":30}}");
        map.put("gameid", Integer.parseInt(gameID));
        map.put("host", "kahoot.it");
        map.put("id", 45);
        map.put("type", "message");

        return map;
        //And yes I know the content is certainly not best practice
    }

    /**
     * Creates a response body for a feedback response
     * @param fun how fun the game was (1 - 5)
     * @param learning whether you did learn something (true / false)
     * @param recommend whether you would recommend it (true / false)
     * @param overall overall goodness of the game (1 - 3)
     * @param totalScore total score of the player
     * @param nickname nickname of the player
     * @return response body to send
     */
    private HashMap<String, Object> createFeedback(int fun, boolean learning, boolean recommend, int overall, int totalScore, String nickname){
        HashMap<String, Object> map = new HashMap<>();

        map.put("content", "{\"totalScore\":" + totalScore + ",\"fun\":" + fun + ",\"learning\":" + learning + ",\"recommend\":" + recommend + ",\"overall\":" + overall + ",\"nickname\":" + nickname + "}");
        map.put("gameid", Integer.parseInt(gameID));
        map.put("host", "kahoot.it");
        map.put("id", 11);
        map.put("type", "message");

        return map;
        //And yes I know the here content is certainly not best practice either
    }

}
