package com.adaptionsoft.games.trivia.runner;

import java.util.List;

class TriviaCheckAnswer {
    private TriviaDisplayMessage triviaDisplayMessage;
    private int currentPlayer;
    private int[] purses;
    private List<String> players;
    private boolean isGettingOutOfPenaltyBox;
    private boolean[] inPenaltyBox;

    TriviaCheckAnswer(TriviaDisplayMessage triviaDisplayMessage) {
        this.triviaDisplayMessage = triviaDisplayMessage;
    }

    void playerUpdater(List<String> players,int currentPlayer, int[] purses)
    {
        this.players=players;
        this.purses= purses;
        this.currentPlayer=currentPlayer;
    }

    void playerPenaltySetting( boolean[] inPenaltyBox,  boolean isGettingOutOfPenaltyBox)
    {
       this.inPenaltyBox=inPenaltyBox;
       this.isGettingOutOfPenaltyBox=isGettingOutOfPenaltyBox;
    }

    boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]&&!isGettingOutOfPenaltyBox){
            checkCurrentPlayerNumber();
            return true;
        }
        return correctAnswer();
    }

    private boolean correctAnswer()
    {
        triviaDisplayMessage.correctAnswer();
        purses[currentPlayer]++;
        triviaDisplayMessage.coinsNumberInfo(players, currentPlayer, purses);
        boolean winner = didPlayerWin();
        checkCurrentPlayerNumber();
        return winner;
    }

    boolean wrongAnswer(){
        triviaDisplayMessage.wrongAnswer();
        triviaDisplayMessage.wasSentToPenaltyBox(players, currentPlayer);
        inPenaltyBox[currentPlayer] = true;
        checkCurrentPlayerNumber();
        return true;
    }

    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == 6);
    }

    int getCurrentPlayer() {
        return currentPlayer;
    }

    private void checkCurrentPlayerNumber() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
    }
}
