/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import java.util.Random;
import java.util.TimeZone;
import javax.faces.validator.ValidatorException;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.event.ValueChangeEvent;
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
    private UserBean user;

    private UIInput passwordComponent;

    public UIInput getPasswordComponent()
    {
        return passwordComponent;
    }

    public void setPasswordComponent(UIInput passwordComponent)
    {
        this.passwordComponent = passwordComponent;
    }

    //Getter and Setter
   
    public boolean isLoginfailed() {
        return loginfailed;
    }

    public void setLoginfailed(boolean loginfailed) {
        this.loginfailed = loginfailed;
    }

    public boolean isDisplayonline() {
        return showPersonalDetails;
    }

    public void setDisplayonline(boolean showPersonalDetails) {
        this.showPersonalDetails = showPersonalDetails;
    }


    public int getOnlineCustomers()
    {
        return new Random().nextInt(10) + 1;
    }

    //Login - check password
    public String login()
    {
        if(user.getPassword().equals("secret"))
        {
            loginfailed = false;
            return "/table.xhtml";
        }

        else
        {
            loginfailed = true;
            return "/login.xhtml";
        }
    }

    //Checks if "Show details" checkbox is checked
    public void displayChanged(ValueChangeEvent e){
        Boolean show = (Boolean) e.getNewValue();
        if(show != null)
            showPersonalDetails = show;

        FacesContext.getCurrentInstance().renderResponse();
    }

    //Validation of the username - UserList auslesen ob User vorhanden mit Password .. wenn nein -> Meldung
    public void validateUsername(FacesContext ctx, UIComponent component, Object value) throws ValidatorException
    {
        String username = (String)value;

        if(!username.equals("Markus") && !username.equals("Heidi"))
        {
            FacesMessage msg = new FacesMessage(
            FacesMessage.SEVERITY_WARN,"Wrong username!", null);
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

    //Validation of the password, 8 chars, at least 1 char and 1 digit
    public void validatePassWord(FacesContext ctx, UIComponent component, Object value) throws ValidatorException
    {
        String password = (String)value;

        if(!stringCharIntControl(password)){
            FacesMessage msg = new FacesMessage(
            FacesMessage.SEVERITY_WARN,"Passwort nicht gueltig, mind. ein Buchstabe und 1 Zahl", null);
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
}
