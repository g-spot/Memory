/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tuwien.big.memory.controller;

import at.ac.tuwien.big.ewa.memory.MemoryCard;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import at.ac.tuwien.big.ewa.memory.MemoryGame;
import at.ac.tuwien.big.ewa.memory.MemoryPlayer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dieter
 */
@ManagedBean(name = "mc")
@SessionScoped
public class MemoryControl {

    MemoryGame mgame = null;
    int stacksize = 0;
    String playername = "Player1";
    private String cardpath = "img/card_images/card";
    private String cardpathext = ".jpg";
    private String backcardpath = "img/card_background.png";

    /** Creates a new instance of MemoryControl */
    public MemoryControl() {
    }

    public MemoryControl(String playername, int stacksize) {
        this.stacksize = stacksize;
        this.playername = playername;
        init();
    }

    public void init() {
        List<MemoryPlayer> playerlist = new ArrayList<MemoryPlayer>();
        playerlist.add(new MemoryPlayer(playername));

        List<String> cardlist = new ArrayList<String>();

        for (int i = 1; i <= (stacksize / 2); i++) {
            cardlist.add(cardpath + i + cardpathext);
        }

        mgame = new MemoryGame(playerlist, cardlist);
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
}
