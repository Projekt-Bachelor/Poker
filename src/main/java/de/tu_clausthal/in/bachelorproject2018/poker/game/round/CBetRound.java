package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import de.tu_clausthal.in.bachelorproject2018.poker.network.IMessage;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.EGamestateManagement;
import de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages.CNotifyMessage;
import org.pmw.tinylog.Logger;

import java.util.Queue;


public final class CBetRound extends IBaseRoundAction {
    /**
     * Spielerobjekt, für den der Bet ausgeführt wird
     */
    private final IPlayer m_player;

    /**
     * Constructor
     * @param p_table
     * @param p_player
     */
    protected CBetRound(ITable p_table, IPlayer p_player) {
        super(p_table);
        m_player = p_player;
    }


    /**
     * Accept wird nach der Aktion des Spielers ausgeführt
     * Falls nötig werden hier neue BetRound-Objekte erzeugt, damit weitere Spieler Aktionen dürchführen können
     * @param p_roundactions
     * @param p_message
     */
    @Override
    public void accept( final Queue<IRoundAction> p_roundactions, final IMessage p_message )
    {
        Logger.info("Spieler: " + p_message.player().getName() + " hat folgende Aktion ausgeführt: " + p_message.type());
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
                p_roundactions.add(new CBetRound(m_table, m_table.getGameHub().getChipsHandler().updateWhoToAsk(m_player)));
            }
        }
    }

    /**
     * wird vor der Aktion des Spielers ausgeführt
     * entscheidet, ob der Spieler überhaupt eine Aktion ausführen brauch/kann
     * return false, wenn der Spieler keine Aktion ausführen brauch
     * return true, wenn das Betround-Objekt auf die Spieleraktion warten soll
     * @param p_roundactions
     * @return
     */
    @Override
    public Boolean apply( final Queue<IRoundAction> p_roundactions )
    {
        //wenn der Spieler allin ist, brauch er keine weitere Aktion ausführen
        if (m_player.getAllIn()){
            Logger.info(m_player.getName() + "ist allin und muss damit nichts mehr machen");
            m_player.check();
            p_roundactions.add(new CBetRound(m_table, m_table.getGameHub().getChipsHandler().updateWhoToAsk(m_player)));
            return false;
        }
        //wenn der Spieler der einzige Spieler noch in der Runde ist
        if(!m_player.checkfolded() && !m_table.getGameHub().getChipsHandler().continuePlayingRound()){
            Logger.info("Es ist nur noch ein Spieler in der Runde. Damit hat " +
            m_player.getName() + " automatisch gewonnen!");
            return false;
        }
        //wenn true, dann bleibt das Objekt liegen und wartet aus eine Nachricht, bei false wird es nach der Ausführung aus der Queue genommen
        //wenn der Spieler nicht gefoldet hat, frag ihn und warte auf eine Antwort
        if (!m_player.checkfolded()){
            Logger.info(m_player.getName() + " hat noch nicht gefoldet! Und ist dran!");
            EGamestateManagement.INSTANCE.apply(m_table.name()).addNotifyMessage(
                    new CNotifyMessage("Du bist dran!", m_table, m_player));

            //send message to player
            return true;
        }

        Logger.info(m_player.getName() + " hat gefoldet!");
        //wenn der Spieler gefoldet hat, erzeug ein neues BetRoundObjekt, sende keine Nachricht und warte nicht
        p_roundactions.add(new CBetRound(m_table, m_table.getGameHub().getChipsHandler().updateWhoToAsk(m_player)));
        return false;
    }
}
