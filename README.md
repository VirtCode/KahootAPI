# KahootAPI
## About
As the title suggests, this api is capable of communicating with the kahoot servers to simulate a kahoot client.
## Version
The current version is **1.0**
## Features
### Included
* Functionality to join classic games
* Receiving all events sent by the server
* Answering classic and true or false questions
* Obtain pretty much all information as possible about the current gameplay
### Scheduled
* Giving feedback to the creator
### Missing
* Sending answers to questions that are exclusive to pro creators
* Participating in team games
## Usage
### Getting the API
Currently, you have to downloat your preffered jar in the release section. If you do not wanna mess with dependencies, you can downloat the jar with dependencies included. It will hopefully be in the maven central repository sometime.
### In Code
If you want to check whether a game with a certain pin is hosted, you can check this like that:
```java
Kahoot.gameExists("[game-code]", exists -> {
  if (exists) //exists
  else //does not exist
});
```
If you now want to join a existent game, you can do this like that: 
```java
Kahoot.joinGame("[player-name]", "[game-id]", [your-game-wrapper]);
```
As you see, you first need to create a game wrapper. To create one, you should create a class extening:
* Game, if you want to process the game relatively low level
* AdvancedGame, if you want to process the game with methods for each event and methods to send answers to the server
#### With subclass of Game
Now you should override all needed methods. You get:
* playerMessageReceived
  Tells you when and what message from the player channel has been recieved
* controllerMessageReceived
  Tells you when and what message from the status channel has been recieved
* statusMessageReceived
  Tells you when and what message from the status channel has been recieved
* connectionFailure
  Tells you whether and why the connection failed
* connectionSuccess
  Tells you that the connection succeeded
For more indepth knowledge, you can look into the provided JavaDoc
#### With subclass of AdvancedGame
Now you should override all needed methods here too. You get:
* the connection confirmation methods from above
* methods for when an unrecognized packet has been received:
  unknownPlayerEvent, unkownStatusEvent, unkownControllerEvent. Those methods work as the message methods from above
* methods for every recognized event from the server.
You also can call the methods:
* sendAnswer
  This method sends an answer package back to the server with its given credentials
* sendFeedback
  This method does currently not work though
I think all here listed methods are pretty self explainatory but if you want to know more, you can have a look at the JavaDoc too.
##### Example
To get you started right away, here is a small example:
In your main class call:
```java
Kahoot.joinGame("[player-name]", "[game-id]", new [your-class-to-process-the-game]());
```
Now in your "class to process the game" class extend AdvancedGame and override all needed methods.
You should create following variables:
```java
private Integer[] questions;
```
In your quizStart method you should write:
```java
this.questions = questions.toArray(new Integer[0]);
```
And finally in your questionActive method:
```java
sendAnswer(index, (int) Math.floor(Math.random() * questions[index]));
```
As you might have already guessed, you have created a client that sends a random answer to each question.
Of course you can now replaye the choice with an input got from the user and create like so a kahoot client.
## Disclaimer
This project is only intended for experimental usage.
## Licence
This project is licenced under the MIT Licence. Please consider reading the LICENCE file for more information.
