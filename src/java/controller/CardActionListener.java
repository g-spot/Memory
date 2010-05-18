package controller;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;
import javax.servlet.http.HttpServletResponse;
import model.CardBean;

@ManagedBean(name="CardActionListener")
@RequestScoped

public class CardActionListener implements AjaxBehaviorListener,ActionListener
{
    

    public void processAction(ActionEvent event) throws AbortProcessingException
    {
        System.out.println("Test Action Listener called..");
        UIComponent source = event.getComponent();
        System.out.println("Source of the Event is " + source.getClass().getName());


        HtmlCommandButton output = (HtmlCommandButton)source;

        if(output.getImage().equals("resources/img/card_background.png"))
        {
            String alt = output.getAlt();
            alt = alt.replace("Card", "");

            //Debug
            System.out.println("alter "+alt);

            String [] coords = alt.split("[_]");

            //DEBUG
            System.out.println("FUCK COORDS " + coords[0] + " " + coords[1] );

            output.setImage("resources/img/card_images/"
                            +(GameController.getDynamicList().get(Integer.parseInt(coords[0]))).get(Integer.parseInt(coords[1]) )
                           );

            GameController.cardClicked(output);
            CardBean cardBean = (CardBean)FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("cardBean");

            if(cardBean.getFoundPairs() >= (cardBean.getCards() / 2))
            {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().dispatch("/game_success.xhtml");
                } catch (IOException ex)
                {
                    Logger.getLogger(CardActionListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    public void processAjaxBehavior(AjaxBehaviorEvent event) throws AbortProcessingException
    {
        System.out.println("Test Action Listener called..");
        UIComponent source = event.getComponent();
        System.out.println("Source of the Event is " + source.getClass().getName());


        HtmlCommandButton output = (HtmlCommandButton)source;

        if(output.getImage().equals("resources/img/card_background.png"))
        {
            String alt = output.getAlt();
            alt = alt.replace("Card", "");

            //Debug
            System.out.println("alter "+alt);

            String [] coords = alt.split("[_]");

            //DEBUG
            System.out.println("FUCK COORDS " + coords[0] + " " + coords[1] );

            output.setImage("resources/img/card_images/"
                            +(GameController.getDynamicList().get(Integer.parseInt(coords[0]))).get(Integer.parseInt(coords[1]) )
                           );
            

            GameController.cardClicked(output);
            CardBean cardBean = (CardBean)FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("cardBean");

            //Outcome

        }
    }
}

