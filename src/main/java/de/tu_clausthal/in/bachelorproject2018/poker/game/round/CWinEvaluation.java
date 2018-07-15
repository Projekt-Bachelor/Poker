package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck.EWinCheck;
import de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck.HandStatistic;
import de.tu_clausthal.in.bachelorproject2018.poker.network.IMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;


public final class CWinEvaluation extends IBaseRoundAction {

    protected CWinEvaluation(ITable p_table) {
        super(p_table);
    }

    @Override
    public void accept( final Queue<IRoundAction> p_roundactions, final IMessage p_message )
    {

    }

    @Override
    /**
     * erstellt eine HandStatisticList
     * Erstellt für jeden Spieler, der noch im Spiel ist, eine Handstatistic
     * Ruft das Enum Wincheck für jede Handstatistsic auf
     * Vergleicht die Handstatistiken, und erklärt die Sieger
     * Teilt die Chips an die Gewinner auf
     */
    public Boolean apply( final Queue<IRoundAction> p_p_roundactions )
    {
        ArrayList<HandStatistic> handStatisticList = new ArrayList<HandStatistic>();
        for (IPlayer player : m_table.getGameHub().getPlayerList()){
            HandStatistic handStatistic = new HandStatistic(player);
            handStatisticList.add(handStatistic);
            if (!player.checkfolded()){
                Arrays.stream( EWinCheck.values() )
                        .map( i -> i.apply( m_table ) )
                        .forEach (i-> i.apply( handStatistic) );
            }

        }
        //gibt eine ArrayList von den Gewinnern wieder
        //verteilt die chips an all gewinner
        m_table.getGameHub().getChipsHandler().distributePotToWinner(
                m_table.getGameHub().getDetermineWinner().findWinner(handStatisticList));
        return false;
    }


}
