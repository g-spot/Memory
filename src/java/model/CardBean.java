/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlCommandButton;

/**
 *
 * @author johanneskurz
 */

@ManagedBean(name="CardBean")
@SessionScoped

public class CardBean
{

    private HtmlCommandButton card1 = null;
    private HtmlCommandButton card2 = null;
    private Date startDate = null;
    private int foundPairs = 0;
    private int trialCount = 0;
    private int cards=0;

    public CardBean()
    {
            startDate = new Date();
    }

    public CardBean(int cardCount)
    {

    }

    public HtmlCommandButton getCard1()
    {
        return card1;
    }

    /**
     * @param card1 the card1 to set
     */
    public void setCard1(HtmlCommandButton card1) {
        this.card1 = card1;
    }

    /**
     * @return the card2
     */
    public HtmlCommandButton getCard2() {
        return card2;
    }

    /**
     * @param card2 the card2 to set
     */
    public void setCard2(HtmlCommandButton card2) {
        this.card2 = card2;
    }

    public void resetSelection()
    {
        card1 = null;
        card2 = null;
    }

    public String getCurrentTime()
    {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("m:ss");
        return formatter.format(new Date(now.getTime() - getStartDate().getTime()));
    }

    public int getPairsLeft()
    {
        return (cards / 2) - getFoundPairs();
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the foundPairs
     */
    public int getFoundPairs() {
        return foundPairs;
    }

    /**
     * @param foundPairs the foundPairs to set
     */
    public void setFoundPairs(int foundPairs) {
        this.foundPairs = foundPairs;
    }

    /**
     * @return the trialCount
     */
    public int getTrialCount() {
        return trialCount;
    }

    /**
     * @param trialCount the trialCount to set
     */
    public void setTrialCount(int trialCount) {
        this.trialCount = trialCount;
    }

    /**
     * @return the cards
     */
    public int getCards() {
        return cards;
    }

    /**
     * @param cards the cards to set
     */
    public void setCards(int cards) {
        this.cards = cards;
    }

}
