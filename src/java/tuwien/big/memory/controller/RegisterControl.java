/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tuwien.big.memory.controller;

import tuwien.big.memory.utilities.Utility;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import tuwien.big.memory.entities.Player;
import tuwien.big.memory.entities.RegisteredPlayerPool;

/**
 *
 */
@ManagedBean(name = "rc")
@SessionScoped
public class RegisterControl {

    @ManagedProperty(value = "#{player}")
    private Player newplayer;
    @ManagedProperty(value = "true")
    private boolean displayprivatedata;
    @ManagedProperty(value = "#{rpp}")
    private RegisteredPlayerPool rpp;
    @ManagedProperty(value = "#{false}")
    private boolean registrationsuccessful;

    /** Creates a new instance of RegisterControl */
    public RegisterControl() {
    }

    public String register() {
        boolean success = getRpp().addPlayer(newplayer);

        if (success == true) {
            registrationsuccessful = true;
        }
        return "register";
    }

    //Checks if the display checkbox changed
    public void displayChanged(ValueChangeEvent e) {
        Boolean show = (Boolean) e.getNewValue();
        if (show != null) {
            displayprivatedata = show;
        }

        FacesContext.getCurrentInstance().renderResponse();
    }

    //Validation of the birthday
    public void validateBirthday(FacesContext ctx, UIComponent component, Object value) throws ValidatorException {
        String bd = (String) value;

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        format.setLenient(false);

        try {
            format.parse(bd);
        } catch (ParseException e) {
            String i18ntext = Utility.getResourceText(ctx, "msg", "birthdateformat");

            FacesMessage msg = new FacesMessage(
                    FacesMessage.SEVERITY_WARN, i18ntext, null);

            throw new ValidatorException(msg);
        }
    }

    /**
     * @return the newplayer
     */
    public Player getNewplayer() {
        return newplayer;
    }

    /**
     * @param newplayer the newplayer to set
     */
    public void setNewplayer(Player newplayer) {
        this.newplayer = newplayer;
    }

    /**
     * @return the displayprivatedata
     */
    public boolean isDisplayprivatedata() {
        return displayprivatedata;
    }

    /**
     * @param displayprivatedata the displayprivatedata to set
     */
    public void setDisplayprivatedata(boolean displayprivatedata) {
        this.displayprivatedata = displayprivatedata;
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
     * @return the registrationsuccessful
     */
    public boolean isRegistrationsuccessful() {
        return registrationsuccessful;
    }

    /**
     * @param registrationsuccessful the registrationsuccessful to set
     */
    public void setRegistrationsuccessful(boolean registrationsuccessful) {
        this.registrationsuccessful = registrationsuccessful;
    }
}
