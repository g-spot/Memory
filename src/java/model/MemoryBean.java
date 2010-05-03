/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.MemServlet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author gerhard
 */
public class MemoryBean
{

    public MemoryBean()
    {
        MemServlet memAPI = new MemServlet();
        cards = memAPI.initCards(16);
        fileNames = memAPI.initFileNames(16);
        startDate = new Date();
    }

    private HashMap<String, Card> cards;
    private HashMap<String, String> fileNames;
    private Card firstCard = null;
    private Card secondCard = null;
    private Date startDate = null;
    private int foundPairs = 0;
    private int trialCount = 0;

    public HashMap<String, Card> getCards()
    {
        return cards;
    }

    @Override
    public String toString()
    {
        String value = "";
        Set<String> keys = cards.keySet();
        for (String key : keys) {
            value += cards.get(key).toString() + "<br />\n";
        }
        return value;
    }

    /**
     * @return the firstCard
     */
    public Card getFirstCard()
    {
        return firstCard;
    }

    /**
     * @param firstCard the firstCard to set
     */
    public void setFirstCard(Card firstCard)
    {
        this.firstCard = firstCard;
    }

    /**
     * @return the secondCard
     */
    public Card getSecondCard()
    {
        return secondCard;
    }

    /**
     * @param secondCard the secondCard to set
     */
    public void setSecondCard(Card secondCard)
    {
        this.secondCard = secondCard;
    }

    public void resetSelection()
    {
        firstCard = null;
        secondCard = null;
    }

    /**
     * @param cards the cards to set
     */
    public void setCards(HashMap<String, Card> cards)
    {
        this.cards = cards;
    }

    /**
     * @return the currentTime
     */
    public String getCurrentTime()
    {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("m:ss");
        return formatter.format(new Date(now.getTime() - startDate.getTime()));
    }

    /**
     * @return the foundPairs
     */
    public int getFoundPairs()
    {
        return foundPairs;
    }

    /**
     * @param foundPairs the foundPairs to set
     */
    public void setFoundPairs(int foundPairs)
    {
        this.foundPairs = foundPairs;
    }

    /**
     * @return the trialCount
     */
    public int getTrialCount()
    {
        return trialCount;
    }

    /**
     * @param trialCount the trialCount to set
     */
    public void setTrialCount(int trialCount)
    {
        this.trialCount = trialCount;
    }

    /**
     * @return the fileNames
     */
    public HashMap<String, String> getFileNames()
    {
        return fileNames;
    }

    public int getPairsLeft()
    {
        return (cards.size() / 2) - foundPairs;
    }
}
