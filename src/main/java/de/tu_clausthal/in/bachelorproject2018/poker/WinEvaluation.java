package de.tu_clausthal.in.bachelorproject2018.poker;

import java.util.Arrays;
import java.util.Collections;

public class WinEvaluation {
    private StartHub gameHub;
    private int[] rankArray = new int[15];
    //rankarray is 0-14m but 0 and 1 will always be empty. easier to think about the array without subsctracting 2 all the time

    public WinEvaluation(StartHub gameHub){
        this.gameHub = gameHub;
    }

    //add tablecards to handcards of each player
    public void combineCards(Player player){
        for (int i = 0; i<5; i++) {
            player.getPlayerhand().getHandCards().add(gameHub.getCardDealer().getTableCards().get(i));
        }
    }
    public int[] createRankArray(Player player){
        //set rankArray to 0
        for(int i = 0; i< rankArray.length; i++ ){
            rankArray[i]=0;
        }
        for (Card card : player.getPlayerhand().getHandCards()){
            rankArray[card.getValue()]++;
        }
        return rankArray;
    }

    public void evaluateHands(){
        //start handevaluation for every player
        int sameCards;
        int sameCards2;
        int largeGroupRank;
        int smallGroupRank;
        int[] orderedRank;
        int orderedRankcounter;
        boolean straight;
        int straightHigh;
        int clubFlushCounter;
        int spadeFlushCounter;
        int heartFlushCounter;
        int diamondFlushCounter;
        boolean normalFlush;
        int flushIndex;
        int[] orderedFlushValues;
        int orderedFlushValuesIndex;
        boolean straightFlush;
        int straightFlushHighIndex;
        for (Player player : gameHub.getPlayerList()) {
            if (!player.checkFolded()) {
                combineCards(player);
                createRankArray(player);

                //assume we have only single cards, assign default rank to the groups
                //in general check for everything but straights and flushes
                sameCards = 1;
                sameCards2 = 1;
                largeGroupRank = 0;
                smallGroupRank = 0;
                orderedRank = new int[5];
                orderedRankcounter = 0;
                straight = false;
                straightHigh = 0;
                clubFlushCounter = 0;
                spadeFlushCounter = 0;
                heartFlushCounter = 0;
                diamondFlushCounter = 0;
                normalFlush = false;
                flushIndex = 5;
                orderedFlushValues = new int[7];
                orderedFlushValuesIndex = 0;
                straightFlush = false;
                straightFlushHighIndex = 0;
                for (int i = 14; i > 1; i--) {
                    if (rankArray[i] > sameCards) {
                        //if we found a triple after we already found a pair, we save the pair before overwriting the triple
                        if (sameCards != 1) {
                            sameCards2 = sameCards;
                            smallGroupRank = largeGroupRank;
                        }
                        //save the highest occurence
                        sameCards = rankArray[i];
                        largeGroupRank = i;
                    } else if (rankArray[i] > sameCards2) {
                        //if we found a second lower pair
                        sameCards2 = rankArray[i];
                        smallGroupRank = i;
                    }
                    //order every card not used in the part before
                    if (rankArray[i] >= 1 && orderedRankcounter < 5 && i != largeGroupRank && i != smallGroupRank) {
                        orderedRank[orderedRankcounter] = i;
                        orderedRankcounter++;
                    }
                }
                if (sameCards == 3) {
                    if (sameCards2 >= 2 && 6 > player.getPlayerhand().getHandEvaluation()[0]) {
                        //full house, save triple and pair
                        player.getPlayerhand().setHandEvaluation(0, 6);
                        player.getPlayerhand().setHandEvaluation(1, largeGroupRank);
                        player.getPlayerhand().setHandEvaluation(2, smallGroupRank);
                    } else if (3 > player.getPlayerhand().getHandEvaluation()[0]) {
                        //save triple and high cards
                        player.getPlayerhand().setHandEvaluation(0, 3);
                        player.getPlayerhand().setHandEvaluation(1, largeGroupRank);
                        player.getPlayerhand().setHandEvaluation(2, orderedRank[0]);
                        player.getPlayerhand().setHandEvaluation(3, orderedRank[1]);
                    }

                }
                if (sameCards == 2) {
                    if (sameCards2 == 2 && 2 > player.getPlayerhand().getHandEvaluation()[0]) {
                        //double pair, save both pairs and highcard
                        player.getPlayerhand().setHandEvaluation(0, 2);
                        player.getPlayerhand().setHandEvaluation(1, largeGroupRank);
                        player.getPlayerhand().setHandEvaluation(2, smallGroupRank);
                        player.getPlayerhand().setHandEvaluation(3, orderedRank[0]);
                    } else if (1 > player.getPlayerhand().getHandEvaluation()[0]) {
                        //just one pair, asve pair and 3 highest cards
                        player.getPlayerhand().setHandEvaluation(0, 1);
                        player.getPlayerhand().setHandEvaluation(1, largeGroupRank);
                        player.getPlayerhand().setHandEvaluation(2, orderedRank[0]);
                        player.getPlayerhand().setHandEvaluation(3, orderedRank[1]);
                        player.getPlayerhand().setHandEvaluation(4, orderedRank[2]);
                    }
                }
                if (sameCards == 4 && 7 > player.getPlayerhand().getHandEvaluation()[0]) {
                    //four of a kind, save four and highest card
                    player.getPlayerhand().setHandEvaluation(0, 7);
                    player.getPlayerhand().setHandEvaluation(1, largeGroupRank);
                    player.getPlayerhand().setHandEvaluation(2, orderedRank[0]);
                }
                //check for straight with ace as 1
                if (rankArray[14] >= 1 && rankArray[2] >= 1 && rankArray[3] >= 1 && rankArray[4] >= 1 && rankArray[5] >= 1) {
                    straight = true;
                    straightHigh = 5;
                }
                for (int i = 2; i < 11; i++) {
                    //straight
                    if (rankArray[i] >= 1 && rankArray[i + 1] >= 1 && rankArray[i + 2] >= 1 && rankArray[i + 3] >= 1 &&
                            rankArray[i + 4] >= 1) {
                        straight = true;
                        straightHigh = i + 4;
                    }
                }
                if (straight && 4 > player.getPlayerhand().getHandEvaluation()[0]) {
                    player.getPlayerhand().setHandEvaluation(0, 4);
                    player.getPlayerhand().setHandEvaluation(1, straightHigh);
                }
                if (sameCards == 1 && 0 == player.getPlayerhand().getHandEvaluation()[0]) {
                    //high card, save the 5 highest cards
                    player.getPlayerhand().setHandEvaluation(0, 0);
                    for (int i = 1; i < 6; i++) {
                        player.getPlayerhand().setHandEvaluation(i, orderedRank[i - 1]);
                    }
                }
                //check for normal flush
                for (int i = 0; i < 4; i++) {
                    for (Card card : player.getPlayerhand().getHandCards()) {
                        if (card.getSuitIndex() == i) {
                            switch (i) {
                                case 0:
                                    clubFlushCounter++;
                                    break;
                                case 1:
                                    spadeFlushCounter++;
                                    break;
                                case 2:
                                    heartFlushCounter++;
                                    break;
                                case 3:
                                    diamondFlushCounter++;
                                    break;
                            }

                        }
                    }
                }
                if (clubFlushCounter >= 5) {
                    flushIndex = 0;
                    normalFlush = true;
                } else if (spadeFlushCounter >= 5) {
                    flushIndex = 1;
                    normalFlush = true;
                } else if (heartFlushCounter >= 5) {
                    flushIndex = 2;
                    normalFlush = true;
                } else if (diamondFlushCounter >= 5) {
                    flushIndex = 3;
                    normalFlush = true;
                }
                if (normalFlush) {
                    for (Card card : player.getPlayerhand().getHandCards()) {
                        if (card.getSuitIndex() == flushIndex) {
                        orderedFlushValues[orderedFlushValuesIndex] = card.getValue();
                        orderedFlushValuesIndex++;
                        }
                    }
                    //orderflushvalues is in ascending order!
                    Arrays.sort(orderedFlushValues);
                    if(5 > player.getPlayerhand().getHandEvaluation()[0]){
                        //save the 5 highest cards in the flush suit
                        player.getPlayerhand().setHandEvaluation(0, 5);
                        for (int i = 0; i<5; i++){
                            player.getPlayerhand().setHandEvaluation(i+1, orderedFlushValues[6-i]);
                        }
                    }

                }

                if (normalFlush && straight){
                    //check for straight flush
                    for (int i = 0; i<3; i++){
                        if (orderedFlushValues[i+1] == orderedFlushValues[i]+1 && orderedFlushValues[i+2] == orderedFlushValues[i]+2 &&
                                orderedFlushValues[i+3] == orderedFlushValues[i]+3 &&orderedFlushValues[i+4] == orderedFlushValues[i]+4){
                            straightFlush = true;
                            straightFlushHighIndex = i+4;

                        }
                    }
                }
                if(straightFlush){
                    if (8 > player.getPlayerhand().getHandEvaluation()[0]){
                        //save straightflush and the 5 straight cards in the right suit
                        player.getPlayerhand().setHandEvaluation(0, 8);
                        for (int i = 0;i < 5; i++){
                            player.getPlayerhand().setHandEvaluation(i+1, orderedFlushValues[straightFlushHighIndex-i]);
                        }
                    }
                }
            }
        }
        //all handEvaluations done
    }

    public Player evaluateWinner(){
        Player potentialWinner = gameHub.getPlayerList().get(0);
        for (int i = 1; i< gameHub.getPlayerList().size(); i++){
            for (int j = 0; j < 6; j++){
                if (potentialWinner.getPlayerhand().getHandEvaluation()[j]> gameHub.getPlayerList().get(i).getPlayerhand().getHandEvaluation()[j]){
                    break;
                } else if (potentialWinner.getPlayerhand().getHandEvaluation()[j]< gameHub.getPlayerList().get(i).getPlayerhand().getHandEvaluation()[j]) {
                        potentialWinner = gameHub.getPlayerList().get(i);
                    }
                }
        }
        return potentialWinner;
    }

}
