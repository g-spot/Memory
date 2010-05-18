/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.text.SimpleDateFormat;
import javax.faces.bean.ManagedBean;
import java.util.Random;
import java.util.Set;
import javax.faces.validator.ValidatorException;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.event.ValueChangeEvent;
import model.GameBean;
import model.UserBean;
import util.Util;

/**
 *
 * @author Dieter
 */
@ManagedBean
@SessionScoped
public class LoginCtrl {
   
    /** Creates a new instance of LoginCtrl */
    public LoginCtrl() {
        
    }

    private boolean loginfailed;
    private boolean showPersonalDetails;
    private String username;
    private String password;


    private UIInput passwordComponent;

    public UIInput getPasswordComponent()
    {
        return passwordComponent;
    }

    public void setPasswordComponent(UIInput passwordComponent)
    {
        this.passwordComponent = passwordComponent;
    }


    //Login - check password
    public String login()
    {
        if(getGameBean() == null)
            return "/register_failure.xhtml";

        UserBean userBean = null;
        if((userBean = getGameBean().getUserList().get(getUsername())) == null)
            return "/login_failure.xhtml";
        if(!userBean.getPassword().equals(getPassword()))
            return "/login_failure.xhtml";
        return "/game.xhtml";
    }

    public Object register()
    {
        UserBean userBean = getUserBean();
        GameBean gameBean = getGameBean();
        if(userBean == null || gameBean == null)
            return "/register_failure.xhtml";
        gameBean.getUserList().put(userBean.getUsername(), userBean);
        return "/register_success.xhtml";
    }

    //Checks if "Show details" checkbox is checked
    public void showPersonalDetailsChanged(ValueChangeEvent e){
        Boolean show = (Boolean) e.getNewValue();
        if(show != null)
            setShowPersonalDetails((boolean) show);

        FacesContext.getCurrentInstance().renderResponse();
    }

    //Validation of the username - UserList auslesen ob User vorhanden mit Password .. wenn nein -> Meldung
    public void validateUsername(FacesContext ctx, UIComponent component, Object value) throws ValidatorException
    {
        String username = (String)value;

        System.out.println("yeaaahh");

        if(getGameBean().getUserList().containsKey(username))
        {
            FacesMessage msg = new FacesMessage(
            FacesMessage.SEVERITY_WARN,"User " + username + " existiert bereits", null);
            throw new ValidatorException(msg);
        }
    }

    public void validatePassword(FacesContext ctx, UIComponent component, Object value) throws ValidatorException
    {
        String passwordProof = (String)value;
        String password = (String) getPasswordComponent().getLocalValue();

        if(password == null || !password.equals(passwordProof))
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Util.getMessage(ctx, "validate_passwordnotequal"), null);
            throw new ValidatorException(msg);
        }
        if(!stringCharIntControl(password)){
            FacesMessage msg = new FacesMessage(
            FacesMessage.SEVERITY_WARN, Util.getMessage(ctx, "validate_passwordnotvalid"), null);
            throw new ValidatorException(msg);
        }
        if(!stringCharIntControl(passwordProof)){
            FacesMessage msg = new FacesMessage(
            FacesMessage.SEVERITY_WARN, Util.getMessage(ctx, "validate_passwordproofnotvalid"), null);
            throw new ValidatorException(msg);
        }
    }

    //Validation of the username - UserList auslesen ob User vorhanden mit Password .. wenn nein -> Meldung
    public void validateDateOfBirth(FacesContext ctx, UIComponent component, Object value) throws ValidatorException
    {
        String birthday = (String)value;

        if(birthday == null || birthday.isEmpty())
            return;

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        try
        {
            formatter.parse(birthday);
        }
        catch(Exception e)
        {
            FacesMessage msg = new FacesMessage(
            FacesMessage.SEVERITY_WARN, Util.getMessage(ctx, "validate_dateofbirthnotvalid"), null);
            throw new ValidatorException(msg);
        }
    }

    private boolean stringCharIntControl(String string)
    {
        int intOccurence = 0;
        int charOccurence = 0;

        for(int i=0;i<string.length();i++)
        {
            char temp = string.charAt(i);
            if(Character.isDigit(temp))
            {
                intOccurence++;
            }
            else if(Character.isLetter(temp))
            {
                charOccurence++;
            }
        }

        if(intOccurence > 0 && charOccurence > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
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

    private GameBean getGameBean()
    {
        GameBean gameBean = (GameBean)FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("gameBean");

        if(gameBean == null)
        {
            
            gameBean = new GameBean();
            FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("gameBean", gameBean);
        }
        return gameBean;
    }

    private UserBean getUserBean()
    {
        return (UserBean)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userBean");
    }

    /**
     * @return the showPersonalDetails
     */
    public boolean isShowPersonalDetails() {
        return showPersonalDetails;
    }

    /**
     * @param showPersonalDetails the showPersonalDetails to set
     */
    public void setShowPersonalDetails(boolean showPersonalDetails) {
        this.showPersonalDetails = showPersonalDetails;
    }

}
