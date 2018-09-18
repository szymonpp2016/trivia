package com.adaptionsoft.games.trivia.runner;

class TriviaGame {
    private TriviaProcess triviaPorcess = new TriviaProcess();

    void prepareTheGame() {
        triviaPorcess.sendObjects(triviaPorcess);
        triviaPorcess.addPlayers();
        triviaPorcess.prepareTheQuestions();
    }

    void startTheGame() {
        triviaPorcess.runTheGame();
    }
}




