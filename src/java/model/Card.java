/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author art-jackal
 */
public class Card {

    public enum CardStatus {FOLDED,UNFOLDED,FOUND};

    private String cardID = "";
    private String fileName = "";
    private CardStatus status;

    public String getCardID (){
        return this.cardID;
    }

    public void setCardID(String ID){
        this.cardID = ID;
    }

    public String getFileName (){
        return this.fileName;
    }

    public void setFileName(String name){
        this.fileName = name;
    }
    
    public CardStatus getStatus() {
        return status;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }

    public String toString()
    {
        return cardID + ", " + fileName + " - STATUS: " + status;
    }


}
