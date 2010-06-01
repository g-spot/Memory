package tuwien.big.memory.controller;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import at.ac.tuwien.big.ewa.memory.MemoryPlayer;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import tuwien.big.memory.entities.Player;
import tuwien.big.memory.entities.RegisteredPlayerPool;

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
    private boolean showloginfailed;
    private String name;
    private String password;

    /** Creates a new instance of LoginControl */
    public LoginControl() {
    }

    public String login() {
        try
                {
        player = getRpp().getRegisteredPlayer(name, password);
        if (player != null) {
            setShowloginfailed(false);

            //Map<String, Object> appMap = FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
            //MemoryControl mc = (MemoryControl)appMap.get("mc");
            if(mc == null) // first player
            {
                System.out.println("mc IS NULL");
                //mc = new MemoryControl(player.getName(), player.getStacksize(), player.getGenre());

            }
            else
            {
                mc.addPlayer(player.getName(), player.getStacksize(), player.getGenre());
                System.out.println("mc IS NOT NULL");

                //Map<String, Object> appMap = FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
                //MemoryControl mc = (MemoryControl)appMap.get("mc");
            }
            //else
              //  mc.addSecondPlayer(player.getName());

            return "memory";
        } else {
            setShowloginfailed(true);
            return "index";
        }
        } catch(Exception e)
                {
            System.out.println("FEHLER LOGIN(): " + e.toString());
            e.printStackTrace();
        }
        return "index";
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
        if(isMyTurn())
            return "Your turn";
        else
            return "Opponents turn";
    }

    public double getRand()
    {
        return Math.random();
    }

    public void changeshow(int row, int column) {
        if(isMyTurn())
            mc.changeshow(row, column);
    }
}
