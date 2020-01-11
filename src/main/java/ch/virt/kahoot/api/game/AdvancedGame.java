package ch.virt.kahoot.api.game;

import ch.virt.kahoot.api.data.controller.ControllerResponse;
import ch.virt.kahoot.api.data.player.Nemesis;
import ch.virt.kahoot.api.data.player.PlayerResponse;
import ch.virt.kahoot.api.data.player.PlayerResponseContent;
import ch.virt.kahoot.api.data.player.PointData;
import ch.virt.kahoot.api.data.status.Status;
import ch.virt.kahoot.api.data.status.StatusResponse;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

/**
 * @author VirtCode
 * @version 1.0
 */
public abstract class AdvancedGame extends Game {

    Gson gson;

    public AdvancedGame(){
        gson = new Gson();
    }

    @Override
    public void playerMessageReceived(PlayerResponse response) {
        System.out.println("Player Packet received: " + response.getData().getID());

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
                connectionUpdate(response.getData().getHost(), content.getPlayerName());
        }
    }

    @Override
    public void controllerMessageReceived(ControllerResponse message) {
        System.out.println("Controller Packet recieved!");

        switch (message.getData().getType()){
            case "loginResponse": loginSuccess();
        }
    }

    @Override
    public void statusMessageReceived(StatusResponse message) {
        if (message.getData().getType().equals("status")){
            statusUpdate(Status.getForString(message.getData().getStatus()));
        }
    }

    protected void unknownPlayerEvent(PlayerResponse response){}

    protected void unknownStatusEvent(StatusResponse response){}

    protected void unknownControllerEvent(ControllerResponse response){}

    protected abstract void loginSuccess();

    protected abstract void questionPreparation(int timeLeft, int questionIndex, String blockType);

    protected abstract void questionActive(int index, String blockType);

    protected abstract void questionEnd(int index);

    protected abstract void answerResult(String blockType, int choice, boolean isCorrect, String chosenText, int pointsGained, List<String> correctAnswers, PointData data, int rank, Nemesis nemesis);

    protected abstract void statusUpdate(Status status);

    protected abstract void connectionUpdate(String host, String yourName);

    protected abstract void quizStart(String name, String type, List<Integer> questions);

    protected abstract void feedbackRequest(String quizType);

    protected abstract void quizEnd(int rank, int correctCount, int incorrectCount, boolean isKicked, boolean isGhost, int unansweredCount, int playerCount, int startTime, String quizUUID, String playerName, int totalScore, String hostUUID, String challengeUUID, boolean nonPointGameBlocks);

    protected abstract void podiumUpdate(String medalType, String primaryMessage, String secondaryMessage);

    protected abstract void quizFinished(); //TODO: LOOKUP logs (testfiles) test feedback, do nemesis

    protected void sendAnswer(int questionIndex, int choice){
        send("/service/controller", createAnswerResponse(questionIndex, choice));
    }

    protected void sendFeedback(int fun, int learning, int recommend, int overall, int totalScore, String nickname){
        send("/service/controller", createFeedback(fun, learning, recommend, overall, totalScore, nickname));
    }

    private HashMap<String, Object> createAnswerResponse(int questionIndex, int choice){
        HashMap<String, Object> map = new HashMap<>();

        map.put("content", "{\"choice\":" + choice + ",\"questionIndex\":" + questionIndex + ",\"meta\":{\"lag\":30}}");
        map.put("gameid", Integer.parseInt(gameID));
        map.put("host", "kahoot.it");
        map.put("id", 45);
        map.put("type", "message");

        return map;
        //And yes I know this content is certainly not best practice
    }

    private HashMap<String, Object> createFeedback(int fun, int learning, int recommend, int overall, int totalScore, String nickname){
        HashMap<String, Object> map = new HashMap<>();

        map.put("content", "{\"totalScore\":" + totalScore + ",\"fun\":" + fun + ",\"learning\":" + learning + ",\"recommend\":" + recommend + ",\"overall\":" + overall + ",\"nickname\":" + nickname + "}");
        map.put("gameid", Integer.parseInt(gameID));
        map.put("host", "kahoot.it");
        map.put("id", 11);
        map.put("type", "message");

        return map;
        //And yes I know this content is certainly not best practice either
    }

}
