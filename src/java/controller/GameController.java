
package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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

public class GameController implements Serializable
{

    private static ArrayList<List<String>> dynamicList; // Simulate fake DB.
    //private static String[] dynamicHeaders; // Optional.
    private HtmlPanelGroup dynamicDataTableGroup; // Placeholder.

    // Actions -----------------------------------------------------------------------------------

    private void loadDynamicList()
    {

        // Set headers (optional).
       // dynamicHeaders = new String[] {"ID", "Name", "Value"};

        // Set rows. This is a stub example, just do your dynamic thing.
        dynamicList = new ArrayList<List<String>>();
        dynamicList.add(Arrays.asList(new String[] { "ID1", "Name1", "Value1" }));
        dynamicList.add(Arrays.asList(new String[] { "ID2", "Name2", "Value2" }));
        dynamicList.add(Arrays.asList(new String[] { "ID3", "Name3", "Value3" }));
        //dynamicList.add(Arrays.asList(new String[] { "ID4", "Name4", "Value4" }));
        //dynamicList.add(Arrays.asList(new String[] { "ID5", "Name5", "Value5" }));

    }

    private void populateDynamicDataTable()
    {
        ExpressionFactory ef = ExpressionFactory.newInstance();

        // Create <h:dataTable value="#{myBean.dynamicList}" var="dynamicItem">.
        HtmlDataTable dynamicDataTable = new HtmlDataTable();
        dynamicDataTable.setValueExpression("value", 
                ef.createValueExpression(GameController.dynamicList, List.class) );
        dynamicDataTable.setVar("dynamicItem");


        // Iterate over columns.
        for (int i = 0; i < dynamicList.get(0).size(); i++)
        {

            // Create <h:column>.
            HtmlColumn column = new HtmlColumn();
            dynamicDataTable.getChildren().add(column);

            // Create <h:outputText value="dynamicHeaders[i]"> for <f:facet name="header"> of column.
            //HtmlOutputText header = new HtmlOutputText();
            //header.setValue(dynamicHeaders[i]);
            //column.setHeader(header);

            // Create <h:outputText value="#{dynamicItem[" + i + "]}"> for the body of column.

            HtmlCommandButton output = new HtmlCommandButton();
            int j = i+1;
            output.setImage("img/card_images/card"+j+".jpg");

            //output.setValueExpression("value",ef.createValueExpression("#{dynamicItem[" + i + "]}", String.class));
            column.getChildren().add(output);
        }

        // Add the datatable to <h:panelGroup binding="#{myBean.dynamicDataTableGroup}">.
        dynamicDataTableGroup = new HtmlPanelGroup();
        dynamicDataTableGroup.getChildren().add(dynamicDataTable);
    }

    // Getters -----------------------------------------------------------------------------------

    public HtmlPanelGroup getDynamicDataTableGroup()
    {
        // This will be called once in the first RESTORE VIEW phase.
        if (dynamicDataTableGroup == null)
        {
            loadDynamicList(); // Preload dynamic list.
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


}
