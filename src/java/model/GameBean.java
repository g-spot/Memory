/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author johanneskurz
 */
@ManagedBean(name="GameBean")
@RequestScoped
public class GameBean
{
    private HashMap<String,UserBean> userList;

    /** Creates a new instance of GameBean */
    public GameBean() 
    {
        userList = new HashMap<String,UserBean>();
    }

    /**
     * @return the userList
     */
    public HashMap<String, UserBean> getUserList()
    {
        return userList;
    }

}
