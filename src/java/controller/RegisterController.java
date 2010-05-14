/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author johanneskurz
 */

public class RegisterController implements javax.faces.validator.Validator
{

    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException
    {
        System.out.println("IHR SEID MIR ALLE WURSCHT");
        if(component.getId().equals("password_component"))
        {
            if(value.equals("irgendwas"))
            {
                FacesMessage fm = new FacesMessage("Ihr seid scheiße");
                throw new ValidatorException(fm);
            }

        }
            FacesMessage fm = new FacesMessage("so ne abgefuckte scheiße");
                throw new ValidatorException(fm);
        /*
        if(value1 == null)
        {
            
            value1 = value.toString();
            throw new ValidatorException(new FacesMessage("FUCK YOU"));

        } else if(value2 == null)
          {
            value2 = value.toString();
          } else if (value1 != null && value2 != null)
            {
                if(!value1.equals(value2))
                {
                    throw new ValidatorException(new FacesMessage("Passwörter stimmen nicht überein!"));
                }
            }
        throw new UnsupportedOperationException("Ficke dich, es tut noch nichts!");
         *
         */
    }



}
