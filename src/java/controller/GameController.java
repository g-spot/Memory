
package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.el.ValueExpression;
import javax.el.ExpressionFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlColumn;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.event.ActionEvent;

/**
 *
 * @author johanneskurz
 */

@ManagedBean(name="GameController")
@SessionScoped

public class GameController //implements Serializable
{

    private static List<List<String>> dynamicList; // Simulate fake DB.
    //private static String[] dynamicHeaders; // Optional.
    private HtmlPanelGroup dynamicDataTableGroup; // Placeholder.

    
    private HtmlCommandButton card1 = null;
    private HtmlCommandButton card2 = null;
    // Actions -----------------------------------------------------------------------------------

    private void loadDynamicList(int col, int row)
    {
        dynamicList = new ArrayList<List<String>>();
        String[][] cols = new String[row][col];
        
        for(int i = 0; i < row ; i++)
        {
            for(int j=1;  j <= col ; j++)
            {
                if((j+(row*i)) <= (col*row)/2)
                {
                    cols[i][j-1] = "card"+(j+((row)*i))+".jpg";
                    System.out.println("card"+(j+((row)*i))+".jpg");
                }
                else
                {
                    cols[i][j-1] = "card"+(j+((row)*i)-8)+".jpg";
                    System.out.println("card"+(j+((row)*i)-8)+".jpg");
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
        
        System.out.println("Fertige Liste:" + dynamicList);
        

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

                // Create <h:outputText value="dynamicHeaders[i]"> for <f:facet name="header"> of column.
                /*HtmlOutputText header = new HtmlOutputText();
                header.setValue("Header");
                column.setHeader(header);*/

                // Create <h:outputText value="#{dynamicItem[" + i + "]}"> for the body of column.
                HtmlCommandButton output = new HtmlCommandButton();
                output.setImage("resources/img/card_background.png");
                output.setAlt("Card"+j+"_"+i);
                column.getChildren().add(output);
            }
                    dynamicDataTableGroup.getChildren().add(dynamicDataTable);
        }
        // Add the datatable to <h:panelGroup binding="#{myBean.dynamicDataTableGroup}">.


    }

    // Getters -----------------------------------------------------------------------------------

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

    public List<List<String>> getDynamicList()
    {
        return dynamicList;
    }

    // Setters -----------------------------------------------------------------------------------

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