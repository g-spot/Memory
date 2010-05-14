/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.util.ResourceBundle;
import javax.faces.context.FacesContext;

/**
 *
 * @author gerhard
 */
public class Util {

    public static String getMessage(FacesContext ctx, String messageKey)
    {
        String message = null;
        ResourceBundle bundle = null;
        try
        {
            bundle = ResourceBundle.getBundle("i18n", ctx.getViewRoot().getLocale());
            message = bundle.getString(messageKey);
        }
        catch(Exception e)
        {
            message = "Application error: Could not retrive message '" + messageKey + "'";
        }
        return message;
    }

}
