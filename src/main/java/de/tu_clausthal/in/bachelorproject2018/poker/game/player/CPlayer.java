package de.tu_clausthal.in.bachelorproject2018.poker.game.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.tu_clausthal.in.bachelorproject2018.poker.game.PlayerHand;
import de.tu_clausthal.in.bachelorproject2018.poker.game.action.IAction;
import de.tu_clausthal.in.bachelorproject2018.poker.game.table.ITable;

import javax.annotation.Nonnull;


public final class CPlayer implements IPlayer
{
    /**
     * Name des Spielers, über die Annotation kann ich den Feldnamen im JSON Objekt verändern
     */
    @JsonProperty( "getName" )
    private final String m_name;

    private PlayerHand playerhand;
    private int chipsCount;
    private boolean fold = false;
    private int amountBetThisRound = 0;
    private boolean hasCheckedThisRound= false;
    private boolean isAllIn = false;
    private ITable table;


    /**
     * constructor
     * @param p_name Name des Spielers
     */
    public CPlayer( @Nonnull final String p_name, ITable tisch ) {
        m_name = p_name;
        this.playerhand = new PlayerHand();
        this.table = tisch;
    }

    @Nonnull
    @Override
    public String getName()
    {
        return m_name;
    }

    @Override
    public int getAmountBetThisRound()
    {
        return amountBetThisRound;
    }

    @Override
    public void addToAmountBetThisRound(int amount) {
        amountBetThisRound = amountBetThisRound + amount;
    }

    @Override
    public IPlayer getAmountBetThisRound(int p_value )
    {
        amountBetThisRound = p_value;
        return this;
    }




    /**
     * to add chips to the players chipscount
     * @param add
     */
    @Override
    public void addChips(int add){
        chipsCount += add;
    }

    /**
     * check if the player has enough chips to bet, and then substract it from the chipscount
     * @param substract
     * @return boolean if the player had enough chips
     */
    @Override
    public boolean substractChips(int substract){
        if (chipsCount >= substract){
            chipsCount = chipsCount - substract;
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * reset the getAmountBetThisRound for the next round
     */
    public void resetAmountBetThisRound(){
        amountBetThisRound = 0;
    }

    /**
     * getter to be able to access the cards of the player
     * @return PlayerHand
     */
    @Override
    public PlayerHand getPlayerhand(){
        return playerhand;
    }

    /**
     * getter for the chipsCount
     * @return chipsCount as integer
     */
    @Override
    public int getChipsCount(){
        return chipsCount;
    }

    @Override
    public boolean getAllIn() {
        return isAllIn;
    }

    @Override
    public void playerAllIn() {
        isAllIn = true;
    }

    @Override
    public void resetAllIn() {
        isAllIn = false;
    }


    /**
     * check this round
     */
    @Override
    public void check(){
        hasCheckedThisRound = true;
    }

    /**
     * check if the player has folded
     * @return folded as boolean
     */
    @Override
    public boolean checkfolded() {
        return fold;
    }

    @Override
    public ITable getTable() {
        return table;
    }

    /**
     * reset hasChecked, to be able to check next round
     */
    public void resetHasChecked(){
        hasCheckedThisRound = false;
    }

    /**
     * getter for hasChecked
     * @return hasCheckedThisRound as boolean
     */
    public boolean getHasCheckedThisRound(){
        return hasCheckedThisRound;
    }

    /**
     * fold this round, count down the counter of players still in this round
     */
    @Override
    public void fold(){
        table.getGameHub().getChipsHandler().somebodyHasFolded();
        fold = true;
    }



    /**
     * reset folded for the next round
     */
    public void resetFolded(){
        fold = false;
    }



    @Override
    public void accept( final IAction p_action )
    {
        p_action.accept( this );
    }
}
