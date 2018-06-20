package de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck;

import java.util.ArrayList;

public class determineWinner {

    /**
     * Compare the handstatistics and find the best
     * if we have multiple equals as the best, return them as an ArrayList
     * @param handstatisticList as ArrayList
     * @return potentialWinners as ArrayList
     */
    public ArrayList<HandStatistic> findWinner(ArrayList<HandStatistic> handstatisticList){
        //creare list to save all potential winners
        ArrayList<HandStatistic> potentialWinners = new ArrayList<HandStatistic>();
        //we start with the first handstatistic as the best
        potentialWinners.add(handstatisticList.get(0));
        for (int i = 1; i< handstatisticList.size(); i++){
            for (int j = 0; j < 6; j++){
                if (potentialWinners.get(0).getHandEvaluation()[j]> handstatisticList.get(i).getHandEvaluation()[j]){
                    //potential Winner is better
                    break;
                } else if (potentialWinners.get(0).getHandEvaluation()[j]< handstatisticList.get(i).getHandEvaluation()[j]) {
                    //potential Winner is worse
                    //clear all previous splitpot candidates, because we found a better one
                    potentialWinners.clear();
                    //put potentialwinner into the winnerslist to track him in case of splitpot
                    potentialWinners.add(handstatisticList.get(i));
                    break;
                }
                //both candidates are completely equal, and we compared the last elements
                if (j==5){
                    //add the second candidate to the winners list
                    potentialWinners.add(handstatisticList.get(i));
                }
            }
        }
        //return the array with at least 1 element
        return potentialWinners;
    }
}
