
package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.el.ELContext;
import javax.el.MethodInfo;

import javax.faces.context.FacesContext;
import javax.el.ValueExpression;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.application.Application;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlColumn;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import model.CardBean;

import model.GameBean;

/**
 *
 * @author johanneskurz
 */

@ManagedBean(name="GameController")
@SessionScoped

public class GameController // implements Serializable
{

    private static List<List<String>> dynamicList; // Simulate fake DB.
    //private static String[] dynamicHeaders; // Optional.
    private HtmlPanelGroup dynamicDataTableGroup; // Placeholder.

    private final static String FILENAME_BACKGROUND = "resources/img/card_background.png";



    private void loadDynamicList(int col, int row)
    {
        dynamicList = new ArrayList<List<String>>();
        String[][] cols = new String[row][col];

        //CardBean initialisieren/erstellen wenn nicht vorhanden
        CardBean cardBean = (CardBean)FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("cardBean");

        if(cardBean == null)
        {
            //System.out.println("cardBean IS NULL THERFORE CREATING IT MOTHERFUCKERS");
            cardBean = new CardBean(row*col);
            FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("cardBean", cardBean);
        }


        for(int i = 0; i < row ; i++)
        {
            for(int j=1;  j <= col ; j++)
            {
                if((j+(row*i)) <= (col*row)/2)
                {
                    cols[i][j-1] = "card"+(j+((row)*i))+".jpg";
                    //System.out.println("card"+(j+((row)*i))+".jpg");
                }
                else
                {
                    cols[i][j-1] = "card"+(j+((row)*i)-8)+".jpg";
                    //System.out.println("card"+(j+((row)*i)-8)+".jpg");
                }
            }  
        }

        //shuffle(cols);
        for(int i=0;i<row;i++)
        {
            shuffle(cols[i]);
            dynamicList.add(Arrays.asList(cols[i]));
        }

        
        for(int i=0; i< dynamicList.size();i++)
        {
            Collections.shuffle(dynamicList);
            Collections.shuffle(dynamicList.get(i));
        }
        
        //System.out.println("Fertige Liste:" + dynamicList);
        

    }

    private void populateDynamicDataTable()
    {
        ExpressionFactory ef = ExpressionFactory.newInstance();
        dynamicDataTableGroup = new HtmlPanelGroup();
        HtmlDataTable dynamicDataTable;

        // Iterate over columns.
        for(int j =0; j< dynamicList.size(); j++)
        {
            dynamicDataTable = new HtmlDataTable();
            dynamicDataTable.setValueExpression("value", ef.createValueExpression("1", String.class));
            dynamicDataTable.setVar("dynamicItem");
            
            for (int i = 0; i < dynamicList.get(j).size(); i++)
            {

                // Create <h:column>.
                HtmlColumn column = new HtmlColumn();
                dynamicDataTable.getChildren().add(column);

                
                HtmlCommandButton output = new HtmlCommandButton();

                output.setImage(FILENAME_BACKGROUND);
                output.setAlt("Card"+j+"_"+i);
                output.setImmediate(true);
                
                javax.faces.component.behavior.AjaxBehavior ajax = new javax.faces.component.behavior.AjaxBehavior();
                ajax.addAjaxBehaviorListener(new CardActionListener());
                output.addClientBehavior("click", ajax);
                
                //output.setValueExpression("ajax",ef.createValueExpression("true", String.class) );
                /*
                ActionListener al = new CardActionListener();
                output.addActionListener(al);
                */

                column.getChildren().add(output);
            }
                dynamicDataTableGroup.getChildren().add(dynamicDataTable);
        }

    }


    public static void cardClicked(HtmlCommandButton hcb)
    {
        System.out.println("FUCKING CARD HAS BEEN CLICKED!");
        CardBean cardBean = (CardBean)FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("cardBean");

        if(cardBean == null)
        {
            System.out.println("gameBean IS NULL THERFORE CREATING IT MOTHERFUCKERS");
            cardBean = new CardBean();
            FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("cardBean", cardBean);
        }


            if(cardBean.getCard1() == null)
            {
                cardBean.setCard1(hcb);
            }
            // zweite Karte aufdecken
            else if(cardBean.getCard2() == null)
            {
                cardBean.setCard2(hcb);

                //count erhöhen
                //memoryBean.setTrialCount(memoryBean.getTrialCount() + 1);

                // found Pairs erhöhen
                if(cardBean.getCard2().getImage().equals(cardBean.getCard1().getImage()))
                {
                    //foundpairs ++
                    cardBean.setFoundPairs(cardBean.getFoundPairs() + 1);
                    cardBean.resetSelection();
                }
            }
            // bereits beide aufgedeckt
            else
            {
                // 1. zwei ungleiche aufgedeckte Karten wieder umdrehen
                if(!cardBean.getCard2().getImage().equals(cardBean.getCard1().getImage()))
                {
                    cardBean.getCard1().setImage(FILENAME_BACKGROUND);
                    cardBean.getCard2().setImage(FILENAME_BACKGROUND);
                    /*
                    memoryBean.getFirstCard().setStatus(CardStatus.FOLDED);
                    memoryBean.getSecondCard().setStatus(CardStatus.FOLDED);
                    memoryBean.getFirstCard().setFileName(FILENAME_BACKGROUND);
                    memoryBean.getSecondCard().setFileName(FILENAME_BACKGROUND);
                     * */
                    
                }
                cardBean.resetSelection();
                cardBean.setCard1(hcb);
                // 2. neue erste Karte aufdecken
                //memoryBean.setFirstCard(card);
                //card.setStatus(CardStatus.UNFOLDED);
                //card.setFileName(memoryBean.getFileNames().get(cardID));
            }

        }

    


    public HtmlPanelGroup getDynamicDataTableGroup()
    {
        // This will be called once in the first RESTORE VIEW phase.
        if (dynamicDataTableGroup == null)
        {
            loadDynamicList(4,4); // Preload dynamic list.
            populateDynamicDataTable(); // Populate editable datatable.
        }

        return dynamicDataTableGroup;
    }

    public static List<List<String>> getDynamicList()
    {
        return dynamicList;
    }



    public void setDynamicDataTableGroup(HtmlPanelGroup dynamicDataTableGroup)
    {
        this.dynamicDataTableGroup = dynamicDataTableGroup;
    }

    private void exch(String[] a, int i, int j)
    {
        String swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // take as input an array of strings and rearrange them in random order
    private void shuffle(String[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int r = i + (int) (Math.random() * (N-i));   // between i and N-1
            exch(a, i, r);
        }
    }

}