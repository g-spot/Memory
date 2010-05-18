package controller;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

@ManagedBean(name="TestActionListener")
@RequestScoped

public class TestActionListener implements ActionListener
{
    public TestActionListener()
    {
    }

    public void processAction(ActionEvent event) throws AbortProcessingException
    {
        System.out.println("Test Action Listener called..");
        UIComponent source = event.getComponent();
        System.out.println("Source of the Event is " + source.getClass().getName());
        
                        HtmlCommandButton output = (HtmlCommandButton)source;

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

                        //TODO: CLICKED HANDLING
    }
}

