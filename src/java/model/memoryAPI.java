/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.*;
import java.util.ArrayList;

/**
 *
 * @author christian.kondler
 */
public class memoryAPI {

    public memoryAPI(){
        cards = new ArrayList<CardBean>();

    }

    private ArrayList<CardBean> cards;

    public void shuffleCards(ArrayList<CardBean> list){
       Collections.shuffle(cards);
    }

    public void addCards(CardBean c){
        this.cards.add(c);
    }

    public boolean compareCards(CardBean c1, CardBean c2){
        if(c1.getCardName().equals(c2.getCardName())){
            return true;
        }
        else
            return false;
    }

}
