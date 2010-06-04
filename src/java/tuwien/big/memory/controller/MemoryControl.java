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
    private boolean gameReady = false;
    private String gameState = "Game Status: Waiting for players...";

    // GAMEMODE
    int stacksize = 0;

    private String genre;

    // NAMEN DER SPIELER
    private String playername1 = "Player1";
    private String playername2 = "Player2";

    private MemoryPlayer player1 = null;
    private MemoryPlayer player2 = null;
    private String cardpath = "img/card_images/card";
    private String cardpathext = ".jpg";
    private String backcardpath = "img/card_background.png";

    // RESULTS
    private int resultPlayer1;
    private int resultPlayer2;

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
        if(getPlayer1() == null)
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
        if(getPlayer1() == null)
        {
            setPlayer1(new MemoryPlayer(playername1));
            playerlist.add(getPlayer1());
        }
        else
        {
            setPlayer2(new MemoryPlayer(playername2));
        }

        List<String> cardlist = new ArrayList<String>();

        for (int i = 1; i <= (stacksize / 2); i++) {
            cardlist.add(cardpath + i + cardpathext);
        }

        if(mgame == null)
            mgame = new MemoryGame(playerlist, cardlist);
        else
            mgame.addPlayer(getPlayer2());
    }

    public void restartInGame() {
        List<MemoryPlayer> playerlist = new ArrayList<MemoryPlayer>();

        playerlist.add(getPlayer1());
        if(getPlayer2() != null)
            playerlist.add(getPlayer2());

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
        if(getPlayer1().getName().equals(p.getName()))
            return getPlayer2();
        else
            return getPlayer1();
    }

    public boolean isTurn(Player p)
    {
        if(p.getName().equals(mgame.getCurrentPlayer().getName()))
            return true;
        else
            return false;
    }

    public MemoryPlayer getMe(Player p)
    {
        if(getPlayer1().getName().equals(p.getName()))
            return getPlayer1();
        else
            return getPlayer2();
    }

    /**
     * @return the gameFinished
     */
    public boolean isGameFinished() {
        if(this.mgame.isGameOver())
        {
            createResults();
            return true;
        }
        else {
            return false;
        }
    }

    public String getGenre()
    {
        return this.genre;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    // liefert die Highscore Werte
    // TODO: checken ob korrekte Werte
    // liefert 0 zurück wenn Spieler kein einziges Paar korrekt aufgedeckt hat

    private void createResults()
    {
        if(getPlayer1().getUncoveredMatchingPairsCount()!=0)
        {
            int time = Integer.getInteger(getTimeForPlayer(getPlayer1()));
            this.setResultPlayer1(time / getPlayer1().getUncoveredMatchingPairsCount());
        }
        else {
            this.setResultPlayer1(0);
        }

        if(getPlayer2().getUncoveredMatchingPairsCount()!=0)
        {
            int time = Integer.getInteger(getTimeForPlayer(getPlayer2()));
            this.setResultPlayer2(time / getPlayer2().getUncoveredMatchingPairsCount());
        }
        else {
            this.setResultPlayer1(0);
        }
    }

    /**
     * @return the resultPlayer1
     */
    public int getResultPlayer1() {
        return resultPlayer1;
    }

    /**
     * @param resultPlayer1 the resultPlayer1 to set
     */
    public void setResultPlayer1(int resultPlayer1) {
        this.resultPlayer1 = resultPlayer1;
    }

    /**
     * @return the resultPlayer2
     */
    public int getResultPlayer2() {
        return resultPlayer2;
    }

    /**
     * @param resultPlayer2 the resultPlayer2 to set
     */
    public void setResultPlayer2(int resultPlayer2) {
        this.resultPlayer2 = resultPlayer2;
    }

    /**
     * @return the player1
     */
    public MemoryPlayer getPlayer1() {
        return player1;
    }

    /**
     * @param player1 the player1 to set
     */
    public void setPlayer1(MemoryPlayer player1) {
        this.player1 = player1;
    }

    /**
     * @return the player2
     */
    public MemoryPlayer getPlayer2() {
        return player2;
    }

    /**
     * @param player2 the player2 to set
     */
    public void setPlayer2(MemoryPlayer player2) {
        this.player2 = player2;
    }

    /**
     * @return the gameReady
     */
    public boolean isGameReady() {
        return gameReady;
    }

    /**
     * @param gameReady the gameReady to set
     */
    public void setGameReady(boolean gameReady) {
        this.gameReady = gameReady;
    }

    public void setGameState(String str)
    {
        this.gameState = str;
    }

    public String getGameState()
    {
        return this.gameState;
    }

    public String joinGame()
    {
        return "memory";
    }


}
