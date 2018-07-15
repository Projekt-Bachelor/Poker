package de.tu_clausthal.in.bachelorproject2018.poker.network.gamestate.messages;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;
import org.springframework.lang.NonNull;

public class CChipMessage implements IGamestateMessage{

    private final int m_amount;

    private final IPlayer m_player;

    private final ITable m_table;

    public CChipMessage(@NonNull int p_amount, @NonNull IPlayer p_player, @NonNull ITable p_table) {
        this.m_amount = p_amount;
        this.m_player = p_player;
        this.m_table = p_table;
    }

    public int getAmount(){
        return m_amount;
    }

    public IPlayer getPlayer(){
        return m_player;
    }
}
