package de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck;

import de.tu_clausthal.in.bachelorproject2018.poker.game.Card;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;

import java.util.Arrays;

public class CFlush extends IBaseWinCheckAction {
    protected CFlush(ITable p_table) {
        super(p_table);
    }

    //check for flush and straighflush
    @Override
    /**
     * kontrolliert auf Flush und Straightflush
     * trägt dies möglicherweise in die Handstatistic ein
     */
    public IWinCheckAction apply( final HandStatistic handStatistic )
    {
        //counts the cards of the suit
        int clubFlushCounter = 0;
        int spadeFlushCounter = 0;
        int heartFlushCounter = 0;
        int diamondFlushCounter = 0;
        //true if we found a flush
        boolean normalFlush = false;
        //save the suit of the flush
        int flushIndex = 5;
        //save all the cards of the flush suit
        int[] orderedFlushValues = new int[7];
        //to count through orderedFlushValues
        int orderedFlushValuesIndex = 0;
        //true if we found straight flush
        boolean straightFlush = false;
        //save highest element of straight flush
        int straightFlushHighIndex = 0;

        //check for normal flush, count each element
        for (int i = 0; i < 4; i++) {
            for (Card card : handStatistic.getPlayer().getPlayerhand().getHandCards()) {
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
        //get to know which flush it is
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
            //if we have a flush, get the highcards of the flushsuit
            for (Card card : handStatistic.getPlayer().getPlayerhand().getHandCards()) {
                if (card.getSuitIndex() == flushIndex) {
                    orderedFlushValues[orderedFlushValuesIndex] = card.getValue();
                    orderedFlushValuesIndex++;
                }
            }
            //sort orderedflushvalues in ascending order!
            Arrays.sort(orderedFlushValues);
            if(5 > handStatistic.getHandEvaluation()[0]){
                //save the 5 highest cards in the flush suit
                handStatistic.setHandEvaluation(0, 5);
                for (int i = 0; i<5; i++){
                    handStatistic.setHandEvaluation(i+1, orderedFlushValues[6-i]);
                }
            }

        }

        //STRAIGHTFLUSH
        if (normalFlush && handStatistic.isStraight()){
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
            if (8 > handStatistic.getHandEvaluation()[0]){
                //save straightflush and the 5 straight cards in the right suit
                handStatistic.setHandEvaluation(0, 8);
                for (int i = 0;i < 5; i++){
                    handStatistic.setHandEvaluation(i+1, orderedFlushValues[straightFlushHighIndex-i]);
                }
            }
        }

        return this;
    }
}
