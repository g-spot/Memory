package tuwien.big.memory.controller;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import at.ac.tuwien.big.ewa.memory.MemoryPlayer;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import tuwien.big.memory.entities.Player;
import tuwien.big.memory.entities.RegisteredPlayerPool;
import tuwien.big.memory.utilities.Utility;

/**
 *
 * @author Dieter
 */
@ManagedBean(name = "lc")
@SessionScoped
public class LoginControl {

    @ManagedProperty(value = "#{player}")
    private Player player;
    @ManagedProperty(value = "#{rpp}")
    private RegisteredPlayerPool rpp;
    @ManagedProperty(value = "#{mc}")
    private MemoryControl mc;
    @ManagedProperty(value = "false")
    private boolean showloginfailed = false;
    private boolean loggedIn = false;
    private String name;
    private String password;
    

    /** Creates a new instance of LoginControl */
    public LoginControl() {
    }

    /*public void testHighscore() {
        System.out.println("TESTING HIGHSCORE");
        try
        {
            HighScoreServiceImplService hs = new HighScoreServiceImplService();
            HighScoreService service = hs.getHighScoreServiceImplPort();
            int result = 0;
            HighScoreResultRequest request = new HighScoreResultRequest();
            request.setAuthenticationToken("ewa4eva");
            request.setGameMode("4x4");
            request.setResult(new Integer(4));
            request.setUsername("gerhard");
            result = service.publishHighScoreResult(request);
            System.out.println("TESTHIGHSCORE RESULT: " + result);
        }
        catch(Exception e)
        {
            System.out.println("FEHLER TESTHIGHSCORE: " + e.getMessage());
            e.printStackTrace();;
        }
        System.out.println("END TESTING HIGHSCORE");
    }*/

    public String login() {
        //testHighscore();
        try
        {
        player = getRpp().getRegisteredPlayer(name, password);
        if (player != null) {
            setShowloginfailed(false);
            loggedIn = true;
            if(mc == null) // first player
            {
                System.out.println("mc IS NULL");
            }
            else
            {
                mc.addPlayer(player.getName(), player.getStacksize(), player.getGenre());
                System.out.println("mc IS NOT NULL");
            }

            if((mc.getPlayer1() == null) && (mc.getPlayer2() == null))
            {
                this.mc.setGameReady(false);
                this.mc.setGameState("Game Status: Waiting for players...");
                return "index";
            }
            else if ((mc.getPlayer1() != null) && (mc.getPlayer2() == null))
            {
                this.mc.setGameReady(false);
                this.mc.setGameState("Game Status: Waiting for opponent...");
                return "index";
            }
            else if ((mc.getPlayer1() != null) && (mc.getPlayer2() != null))
                this.mc.setGameReady(true);
                return "memory";

                }
        else if(player != null && mc.getPlayer1() != null && mc.getPlayer2() != null)
        {
            return "memory";
        }

        else {
            setShowloginfailed(true);
            loggedIn = false;
            return "index";
        }
        } catch(Exception e)
          {
            System.out.println("FEHLER LOGIN(): " + e.toString());
            e.printStackTrace();
          }
        return "memory";
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the rpp
     */
    public RegisteredPlayerPool getRpp() {
        return rpp;
    }

    /**
     * @param rpp the rpp to set
     */
    public void setRpp(RegisteredPlayerPool rpp) {
        this.rpp = rpp;
    }

    /**
     * @return the showloginfailed
     */
    public boolean isShowloginfailed() {
        return showloginfailed;
    }

    /**
     * @param showloginfailed the showloginfailed to set
     */
    public void setShowloginfailed(boolean showloginfailed) {
        this.showloginfailed = showloginfailed;
    }

    /**
     * @return the mc
     */
    public MemoryControl getMc() {
        return mc;
    }

    /**
     * @param mc the mc to set
     */
    public void setMc(MemoryControl mc) {
        this.mc = mc;
    }

    /**
     * @return the opponent
     */
    public MemoryPlayer getOpponent() {
        return mc.getOpponent(player);
    }

    public MemoryPlayer getMe() {
        return mc.getMe(player);
    }

    public boolean isMyTurn()
    {
        return mc.isTurn(player);
    }

    public String getMytime() {
        return mc.getTimeForPlayer(getMe());
    }

    public String getOpponentstime() {
        if(getOpponent() != null)
            return mc.getTimeForPlayer(getOpponent());
        else
            return "00:00";
    }

    public String getTurn()
    {
        if(mc.isGameFinished())
        {
            if(mc.getMyHighscoreRank(player) == 0)
                return Utility.getResourceText(FacesContext.getCurrentInstance(), "msg", "game_finished_error");
            else
                return Utility.getResourceText(FacesContext.getCurrentInstance(), "msg", "game_finished_success") + " " + mc.getMyHighscoreRank(player) + ")";
        }
        else if(isMyTurn())
            return Utility.getResourceText(FacesContext.getCurrentInstance(), "msg", "your_turn");
        else
            return Utility.getResourceText(FacesContext.getCurrentInstance(), "msg", "opponents_turn");
    }

    public double getRand()
    {
        return Math.random();
    }

    public void changeshow(int row, int column) {
        if(isMyTurn())
            mc.changeshow(row, column);
    }

    
    /**
     * @return the loggedIn
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * @param loggedIn the loggedIn to set
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
