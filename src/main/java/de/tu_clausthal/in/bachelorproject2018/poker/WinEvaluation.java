package de.tu_clausthal.in.bachelorproject2018.poker;

public class WinEvaluation {
    private StartHub gameHub;
    Player winner;

    public WinEvaluation(StartHub gameHub){
        this.gameHub = gameHub;
    }

    public Player evaluateWinner(){
        //dummy
        winner = gameHub.getPlayerList().get(0);
        return winner;
    }
}
