/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tuwien.big.memory.entities;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

/**
 *
 * Contains all current registered players
 */
@ManagedBean(name = "rpp")
@ApplicationScoped
public class RegisteredPlayerPool {

    Hashtable<String, Player> regplayers = null;

    /** Creates a new instance of RegisteredPlayerPool */
    public RegisteredPlayerPool() {
        regplayers = new Hashtable<String, Player>();

        //Add test player
        Player tp = new Player();
        tp.setName("t");
        tp.setPassword("t");
        tp.setStacksize(4);
        regplayers.put("t", tp);

        Player tp2 = new Player();
        tp2.setName("gerhard");
        tp2.setPassword("");
        tp2.setStacksize(4);
        regplayers.put("gerhard", tp2);
    }

    public boolean addPlayer(Player p) {
        if (!regplayers.containsKey(p.getName())) {
            regplayers.put(p.getName(), p);

            return true;
        } else {
            return false;
        }
    }

    public Player getRegisteredPlayer(String username, String password) {
        Player curplayer;
        if ((curplayer = regplayers.get(username)) != null) {
            if (curplayer.getPassword().equals(password)) {
                return curplayer;
            }
        }

        return null;
    }

    /**
     * @return the players
     */
    public List<Player> getRegplayers() {
        return new ArrayList<Player>(regplayers.values());
    }
}
