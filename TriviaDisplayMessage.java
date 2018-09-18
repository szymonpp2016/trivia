package com.adaptionsoft.games.trivia.runner;
import java.util.List;

class  TriviaDisplayMessage {

    void askQuestion(TriviaProcess triviaProcess) {
        if (triviaProcess.currentCategory().equals("Pop"))
            System.out.println(triviaProcess.popQuestions.removeFirst());
        if (triviaProcess.currentCategory().equals("Science"))
            System.out.println(triviaProcess.scienceQuestions.removeFirst());
        if (triviaProcess.currentCategory().equals("Sports"))
            System.out.println(triviaProcess.sportsQuestions.removeFirst());
        if (triviaProcess.currentCategory().equals("Rock"))
            System.out.println(triviaProcess.rockQuestions.removeFirst());
    }

    void wrongAnswer() {
        System.out.println("Question was incorrectly answered");
    }

    void wasSentToPenaltyBox(List players, int currentPlayer) {
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
    }

    void correctAnswer() {
        System.out.println("Answer was corrent!!!!");
    }

    void coinsNumberInfo(List players, int currentPlayer, int[] purses) {
        System.out.println(players.get(currentPlayer)
                + " now has "
                + purses[currentPlayer]
                + " Gold Coins.");
    }

    void addNewPlayer(String playerName, List players) {
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + (players.indexOf(playerName)+1));
    }

    void currentPlayerRollNumber(List players, int currentPlayer, int roll) {
        System.out.println(players.get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);
    }

    void gettingOutPenaltyBox(List players, int currentPlayer) {
        System.out.println(players.get(currentPlayer)+" is getting out of the penalty box");
    }

    void newPlayerLocation(List players, int currentPlayer, int[] places) {
        System.out.println(players.get(currentPlayer)
                + "'s new location is "
                + places[currentPlayer]);
    }

    void currentCategory(String currentCategory) {
        System.out.println("The category is " + currentCategory);
    }

    void notGettingOutPenaltyBox(List players, int currentPlayer) {
        System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
    }
}

