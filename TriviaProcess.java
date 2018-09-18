package com.adaptionsoft.games.trivia.runner;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

class TriviaProcess {
    private TriviaProcess triviaProcess;
    private TriviaDisplayMessage triviaDisplayMessage = new TriviaDisplayMessage();
    private TriviaCheckAnswer triviaCheckAnswer = new TriviaCheckAnswer(triviaDisplayMessage);

    private int currentPlayer = 0;
    private int[] places = new int[6];
    private int[] purses = new int[6];

    private List<String> players = new LinkedList<>();
    LinkedList<String> popQuestions = new LinkedList<>();
    LinkedList<String> scienceQuestions = new LinkedList<>();
    LinkedList<String> sportsQuestions = new LinkedList<>();
    LinkedList<String> rockQuestions = new LinkedList<>();

    private boolean isGettingOutOfPenaltyBox;
    private boolean[] inPenaltyBox = new boolean[6];

    void sendObjects(TriviaProcess triviaProcess)
    {
        this.triviaProcess=triviaProcess;
    }

    void addPlayers() {
        addNewPlayer("Chet");
        addNewPlayer("Pat");
        addNewPlayer("Sue");
    }

    private void addNewPlayer(String playerName) {
        players.add(playerName);
        int playerNumber = players.indexOf(playerName)+1;
        places[playerNumber] = 0;
        purses[playerNumber] = 0;
        inPenaltyBox[playerNumber] = false;
        triviaDisplayMessage.addNewPlayer(playerName, players);
    }

    void runTheGame() {
        Random random = new Random();
        if (isPlayable()) {
            do {
                currentPlayer=triviaCheckAnswer.getCurrentPlayer();
                roll(random.nextInt(5) + 1);
            }
            while (goodOrBadAnswer(random.nextInt(9)));
        }
    }

    private boolean goodOrBadAnswer(int randomAnswerNumber) {
        triviaCheckAnswer.playerUpdater(players, currentPlayer, purses);
        triviaCheckAnswer.playerPenaltySetting(inPenaltyBox, isGettingOutOfPenaltyBox);
        if (randomAnswerNumber == 7) {
            return  triviaCheckAnswer.wrongAnswer();
        } else {
            return  triviaCheckAnswer.wasCorrectlyAnswered();
        }
    }

    void prepareTheQuestions() {
        IntStream.range(1, 50).parallel().forEach(
                i-> {popQuestions.addLast("Pop Question " + i);
                    scienceQuestions.addLast("Science Question " + i);
                    sportsQuestions.addLast("Sports Question " + i);
                    rockQuestions.addLast("Rock Question " + i);
                });
    }

    private boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    private int howManyPlayers() {
        return players.size();
    }

    private void roll(int roll) {
        triviaDisplayMessage.currentPlayerRollNumber(players, currentPlayer, roll);
        if (inPenaltyBox[currentPlayer]) {
            checkPenaltyGettingOut(roll);
        }
        playerMoves(roll);
    }

    private void checkPenaltyGettingOut(int roll)
    {
        if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;
            triviaDisplayMessage.gettingOutPenaltyBox(players, currentPlayer);
        } else {
            triviaDisplayMessage.notGettingOutPenaltyBox(players, currentPlayer);
            isGettingOutOfPenaltyBox = false;
        }
    }

    private void playerMoves(int roll) {
        places[currentPlayer] = places[currentPlayer] + roll;
        if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
        triviaDisplayMessage.newPlayerLocation(players, currentPlayer, places);
        triviaDisplayMessage.currentCategory(currentCategory());
        triviaDisplayMessage.askQuestion(triviaProcess);
    }

    String currentCategory() { //6
        if (places[currentPlayer] == 0 || places[currentPlayer] == 4 || places[currentPlayer] == 8) return "Pop";
        if (places[currentPlayer] == 1 || places[currentPlayer] == 5 || places[currentPlayer] == 9) return "Science";
        if (places[currentPlayer] == 2 || places[currentPlayer] == 6 || places[currentPlayer] == 10) return "Sports";
        return "Rock";
    }
}


