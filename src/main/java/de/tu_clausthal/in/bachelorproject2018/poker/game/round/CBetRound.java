package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.IMessage;

import java.util.Queue;


public final class CBetRound implements IRoundAction {
    /**
     * Spielerobjekt, für den der Bet ausgeführt wird
     */
    private final IPlayer m_player;

    public CBetRound( final IPlayer p_player )
    {
        m_player = p_player;
    }


    @Override
    public void accept( final Queue<IRoundAction> p_roundactions, final IMessage p_message )
    {
    }

    @Override
    public Boolean apply( final Queue<IRoundAction> p_roundactions )
    {
        // wenn true, dann bleibt das Objekt liegen und wartet aus eine Nachricht, bei false wird es nach der Ausführung aus der Queue genommen
        return true;
    }
}
