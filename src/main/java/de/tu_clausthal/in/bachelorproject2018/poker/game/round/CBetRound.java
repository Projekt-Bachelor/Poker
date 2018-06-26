package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import de.tu_clausthal.in.bachelorproject2018.poker.network.IMessage;

import java.util.Queue;


public final class CBetRound extends IBaseRoundAction {
    /**
     * Spielerobjekt, für den der Bet ausgeführt wird
     */
    private final IPlayer m_player;

    protected CBetRound(ITable p_table, IPlayer p_player) {
        super(p_table);
        m_player = p_player;
    }

    /*public CBetRound( final IPlayer p_player )
    {
        m_player = p_player;
    }
    */

    @Override
    public void accept( final Queue<IRoundAction> p_roundactions, final IMessage p_message )
    {
        if ( !m_player.equals( p_message.player() ) )
            throw new RuntimeException( "Player passt nicht" );

        p_message.get().accept( m_player );
        //wenn die queue leer ist
        if (p_roundactions.isEmpty()){
            //wenn der nachfolgende Spieler weniger gesetzt hat, als das maximum, muss ein neues BetRoundObjekt erzeugt werden
            if (m_table.getGameHub().getChipsHandler().updateWhoToAsk(m_player).getAmountBetThisRound() <
                    m_table.getGameHub().getChipsHandler().getHighestBidThisRound()
                    //oder wenn bisher nur gecheckt wurde, aber noch nicht alle gecheckt haben
                    || ( m_table.getGameHub().getChipsHandler().getNewRound() &&
                        !m_table.getGameHub().getChipsHandler().updateWhoToAsk(m_player).getChecked())
                    ){
                new CBetRound(m_table, m_table.getGameHub().getChipsHandler().updateWhoToAsk(m_player));
            }
        }
    }

    @Override
    public Boolean apply( final Queue<IRoundAction> p_roundactions )
    {
        //wenn true, dann bleibt das Objekt liegen und wartet aus eine Nachricht, bei false wird es nach der Ausführung aus der Queue genommen
        //wenn der Spieler nicht gefoldet hat, frag ihn und warte auf eine Antwort
        if (!m_player.checkfolded()){
            //send message to player
            return true;
        }
        //wenn der Spieler gefoldet hat, erzeug ein neues BetRoundObjekt, sende keine Nachricht und warte nicht
        new CBetRound(m_table, m_table.getGameHub().getChipsHandler().updateWhoToAsk(m_player));
        return false;
    }
}
