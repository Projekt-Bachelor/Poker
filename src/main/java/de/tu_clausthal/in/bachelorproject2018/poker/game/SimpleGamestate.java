package de.tu_clausthal.in.bachelorproject2018.poker.game;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;

public class SimpleGamestate {
    private GameHub gameHub;

    public SimpleGamestate(GameHub gameHub){
        this.gameHub = gameHub;
    }

    public String getSimpleGamestate(){
        String result= "Die Spieler haben folgende Chipswerte: ";
        for (IPlayer player: gameHub.getPlayerList()){
            result += player.getName() + " hat " + player.getChipsCount() + " Chips. ";
            result += "Diese Runde hat er bisher " + player.getAmountBetThisRound() + " Chips gesetzt. ";
        }

        result += "Bisher liegen die folgenden Karten auf dem Tisch: ";
        for (Card card: gameHub.getCardDealer().getTableCards()){
            result += card.toString();
        }
        return result;
    }
}
