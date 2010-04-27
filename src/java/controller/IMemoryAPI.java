/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.util.HashMap;
import model.Card;

/**
 *
 * @author gerhard
 */
interface IMemoryAPI {
    public HashMap<String,Card> initCards(int cardCount);
    public HashMap<String,String> initFileNames(int cardCount);
    public void cardClicked(String cardID);
}
