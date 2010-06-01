/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tuwien.big.memory.utilities;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;

/**
 *
 * @author Dieter
 */
public class Utility {

    public static String getResourceText(FacesContext ctx,
            String bundleName, String key) {
        String text;
        try {
            Application app = ctx.getApplication();
            ResourceBundle bundle = app.getResourceBundle(
                    ctx, bundleName);
            text = bundle.getString(key);
        } catch (MissingResourceException e) {
            return "???" + key + "???";
        }

        return text;
    }
}
