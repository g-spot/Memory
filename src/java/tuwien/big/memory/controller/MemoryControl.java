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
import tuwien.big.memory.webservice.HighScoreResultRequest;
import tuwien.big.memory.webservice.HighScoreService;
import tuwien.big.memory.webservice.HighScoreServiceImplService;

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
    private boolean gameFinished = false;
    private int highscoreRankPlayer1 = 0;
    private int highscoreRankPlayer2 = 0;

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
    private int resultPlayer1 = 0;
    private int resultPlayer2 = 0;

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

    public int getTimeForPlayerInSeconds(MemoryPlayer player) {
        long millisec = mgame.getSpentTime(player);
        return (int)(millisec/1000);
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

    public int getMyHighscoreRank(Player p)
    {
        if(getPlayer1().getName().equals(p.getName()))
            return this.highscoreRankPlayer1;
        else
            return this.highscoreRankPlayer2;
    }

    /**
     * @return the gameFinished
     */
    public boolean isGameFinished() {
        if(this.mgame.isGameOver())
        {
            System.out.println("GAME IS OVER");
            if(!this.gameFinished) // sende results nur 1 mal
            {
                System.out.println("SUBMITTING HIGHSCORES");
                this.gameFinished = true;
                createResults();
                submitHighscore();
            }
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
        
        try
        {
            System.out.println("TIME PLAYER 1: " + getTimeForPlayerInSeconds(getPlayer1()));
            System.out.println("UNCOVERED PLAYER 1: " + getPlayer1().getUncoveredMatchingPairsCount());
            if(getPlayer1().getUncoveredMatchingPairsCount()!=0)
            {
                resultPlayer1 = getTimeForPlayerInSeconds(getPlayer1()) / getPlayer1().getUncoveredMatchingPairsCount();

            }
            else {
                resultPlayer1 = 0;
            }
            System.out.println("RESULT PLAYER 1: " + resultPlayer1);
            System.out.println("TIME PLAYER 2: " + getTimeForPlayerInSeconds(getPlayer2()));
            System.out.println("UNCOVERED PLAYER 2: " + getPlayer2().getUncoveredMatchingPairsCount());
            if(getPlayer2().getUncoveredMatchingPairsCount()!=0)
            {

                resultPlayer2 = getTimeForPlayerInSeconds(getPlayer2()) / getPlayer2().getUncoveredMatchingPairsCount();
            }
            else {
                resultPlayer2 = 0;
            }
            System.out.println("RESULT PLAYER 2: " + resultPlayer2);
        } catch(Exception e) {
            System.out.println("FEHLER CREATERESULTS: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void submitHighscore() {
        try
        {
            System.out.println("SCORE PLAYER 1: " + resultPlayer1);
            System.out.println("SCORE PLAYER 2: " + resultPlayer2);
            HighScoreServiceImplService hs = new HighScoreServiceImplService();
            HighScoreService service = hs.getHighScoreServiceImplPort();
            HighScoreResultRequest request = new HighScoreResultRequest();
            request.setAuthenticationToken("ewa4eva");
            String gameMode = null;
            switch(this.stacksize) {
                case 4:
                    gameMode = "2x2";
                    break;
                case 16:
                    gameMode = "4x4";
                    break;
                case 36:
                    gameMode = "6x6";
                    break;
            }
            System.out.println("GAMEMODE: " + this.stacksize + ", " + gameMode);
            if(gameMode == null) // do not submit unallowed stacksize
                return;

            request.setGameMode(gameMode);

            if(resultPlayer1 != 0)
            {
                System.out.println("SUBMITTING HIGHSCORE FOR PLAYER 1");
                request.setResult(new Integer(getResultPlayer1()));
                request.setUsername(getPlayer1().getName());
                this.highscoreRankPlayer1 = service.publishHighScoreResult(request);
            }
            
            if(resultPlayer2 != 0)
            {
                System.out.println("SUBMITTING HIGHSCORE FOR PLAYER 2");
                request.setResult(new Integer(getResultPlayer2()));
                request.setUsername(getPlayer2().getName());
                this.highscoreRankPlayer2 = service.publishHighScoreResult(request);
            }
        }
        catch(Exception e)
        {
            System.out.println("FEHLER TESTHIGHSCORE: " + e.getMessage());
            e.printStackTrace();;
        }
        System.out.println("END TESTING HIGHSCORE");
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
