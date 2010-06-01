/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tuwien.big.memory.controller;

import at.ac.tuwien.big.ewa.memory.MemoryCard;
import javax.faces.bean.ManagedBean;
import at.ac.tuwien.big.ewa.memory.MemoryGame;
import at.ac.tuwien.big.ewa.memory.MemoryPlayer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import tuwien.big.memory.entities.Player;

/**
 *
 * @author Dieter
 */
@ManagedBean(name = "mc")
@ApplicationScoped
public class MemoryControl {

    MemoryGame mgame = null;
    int stacksize = 0;
    private String genre = null;
    private String playername1 = "Player1";
    private String playername2 = "Player2";
    private MemoryPlayer player1 = null;
    private MemoryPlayer player2 = null;
    private String cardpath = "img/card_images/card";
    private String cardpathext = ".jpg";
    private String backcardpath = "img/card_background.png";

    /** Creates a new instance of MemoryControl */
    public MemoryControl() {
    }

    public MemoryControl(String playername, int stacksize, String genre) {
        this.stacksize = stacksize;
        this.genre = genre;
        this.playername1 = playername;
        init();
    }

    public void addPlayer(String playername, int stacksize, String genre) {
        if(player1 == null)
        {
            this.stacksize = stacksize;
            this.genre = genre;
            this.playername1 = playername;
        }
        else
            this.playername2 = playername;
        init();
    }

    public void init() {
        List<MemoryPlayer> playerlist = new ArrayList<MemoryPlayer>();
        if(player1 == null)
        {
            player1 = new MemoryPlayer(playername1);
            playerlist.add(player1);
        }
        else
        {
            player2 = new MemoryPlayer(playername2);
        }

        List<String> cardlist = new ArrayList<String>();

        for (int i = 1; i <= (stacksize / 2); i++) {
            cardlist.add(cardpath + i + cardpathext);
        }

        if(mgame == null)
            mgame = new MemoryGame(playerlist, cardlist);
        else
            mgame.addPlayer(player2);
    }

    public MemoryPlayer getCurrentPlayer() {
        return mgame.getCurrentPlayer();
    }

    public void changeshow(int row, int column) {
        if (mgame.getCardByIndex(row, column).getCurrentState() == MemoryCard.CardState.COVERED) {
            mgame.uncover(mgame.getCurrentPlayer(), mgame.getCardByIndex(row, column));
        }
    }

    public String getTimeCurrentPlayer() {
        long millisec = mgame.getSpentTime(mgame.getCurrentPlayer());

        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        return sdf.format(new Date(millisec));
    }

    public String getTimeForPlayer(MemoryPlayer player) {
        long millisec = mgame.getSpentTime(player);

        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        return sdf.format(new Date(millisec));
    }

    public String getCoveredPairsCount() {
        return Integer.toString(stacksize / 2 - mgame.getCurrentPlayer().getUncoveredMatchingPairsCount());
    }

    /**
     * @return the cards
     */
    public ArrayList<ArrayList> getCards() {
        ArrayList<ArrayList> cards = new ArrayList<ArrayList>();
        ArrayList<MemoryCard> row = null;

        for (int i = 0; i < mgame.getTable().length; i++) {
            row = new ArrayList<MemoryCard>();
            cards.add(row);

            for (int j = 0; j < mgame.getTable()[i].length; j++) {
                row.add(mgame.getTable()[i][j]);
            }
        }

        return cards;
    }

    /**
     * @return the cardpath
     */
    public String getCardpath() {
        return cardpath;
    }

    /**
     * @param cardpath the cardpath to set
     */
    public void setCardpath(String cardpath) {
        this.cardpath = cardpath;
    }

    /**
     * @return the cardpathext
     */
    public String getCardpathext() {
        return cardpathext;
    }

    /**
     * @param cardpathext the cardpathext to set
     */
    public void setCardpathext(String cardpathext) {
        this.cardpathext = cardpathext;
    }

    /**
     * @return the backcardpath
     */
    public String getBackcardpath() {
        return backcardpath;
    }

    /**
     * @param backcardpath the backcardpath to set
     */
    public void setBackcardpath(String backcardpath) {
        this.backcardpath = backcardpath;
    }

    public MemoryPlayer getOpponent(Player p)
        {
            if(player1.getName().equals(p.getName()))
                return player2;
            else
                return player1;
        }

        public boolean isTurn(Player p)
        {
            if(p.getName().equals(mgame.getCurrentPlayer().getName()))
                return true;
            else
                return false;
        }

        public MemoryPlayer getMe(Player p) {
            if(player1.getName().equals(p.getName()))
                return player1;
            else
                return player2;
        }
}
