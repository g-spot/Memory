/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author art-jackal
 */
public class CardBean {

    private int cardID = 0;
    private String cardName = " ";

    public int getCardID (){
        return this.cardID;
    }

    public void setCardID(int ID){
        this.cardID = ID;
    }

    public String getCardName (){
        return this.cardName;
    }

    public void setCardName(String name){
        this.cardName = name;
    }


}
