/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Card;
import model.Card.CardStatus;
import model.MemoryBean;

/**
 *
 * @author christian.kondler
 */
public class MemServlet extends HttpServlet implements IMemoryAPI
{

    private MemoryBean memoryBean = null;
    private final String FILENAME_BACKGROUND = "img/card_background.png";

    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
              throws ServletException,java.io.IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        String param = request.getParameter("invalidate");
        
        if(param != null)
        {
            request.getSession().invalidate();
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/table.jsp");
            dispatcher.forward(request, response);
        }
    }
  

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        java.util.Enumeration<String> params = request.getParameterNames();
        String clickedCardID = null;
        if(params.hasMoreElements())
        {
            String cardname = params.nextElement();
            String arr[] = cardname.split("[.]");
            if(arr.length > 0)
                clickedCardID = arr[0];
        }

        memoryBean = (MemoryBean)request.getSession().getAttribute("memoryBean");
        cardClicked(clickedCardID);

        RequestDispatcher dispatcher = null;
        if(memoryBean.getFoundPairs() >= (memoryBean.getCards().size() / 2))
        {
            //resetGame();
            //request.getSession().invalidate();
            dispatcher = getServletContext().getRequestDispatcher("/gameWon.jsp");
        }
        else
        {
            dispatcher = getServletContext().getRequestDispatcher("/table.jsp");
        }
        
        dispatcher.forward(request, response);
    }

    /*private void resetGame()
    {
        memoryBean.resetSelection();
        memoryBean.setFoundPairs(0);
        memoryBean.setTrialCount(0);
        initCards(16);
    }*/
    
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

    public HashMap<String,Card> initCards(int cardCount)
    {
        HashMap<String,Card> cards = new HashMap<String,Card>();

        for(int i=1;i<=cardCount;i++)
        {
            Card c = new Card();
            
            c.setCardID(String.valueOf(i));
            c.setFileName("img/card_background.png");
            c.setStatus(Card.CardStatus.FOLDED);
            cards.put(String.valueOf(i), c);

        }
        return cards;
    }

    public HashMap<String,String> initFileNames(int cardCount)
    {
        HashMap<String,String> fileNames = new HashMap<String,String>();
        for(int i=1;i<=cardCount;i++)
        {
            Random r = new Random();
            int nextID,j;
            while(fileNames.containsKey(String.valueOf((nextID = (r.nextInt(16)) + 1))));

            if(i > (cardCount/2))
                j = i - (cardCount/2);
            else
                j = i;
            
            fileNames.put(String.valueOf(nextID), "img/card_images/card" + j + ".jpg");
        }
        return fileNames;
    }

    public void cardClicked(String cardID)
    {
        Card card = memoryBean.getCards().get(cardID);
        if(card.getStatus() == CardStatus.FOLDED)
        {
            // erste Karte aufdecken
            if(memoryBean.getFirstCard() == null)
            {
                memoryBean.setFirstCard(card);
                card.setStatus(CardStatus.UNFOLDED);
                card.setFileName(memoryBean.getFileNames().get(cardID));
            }
            // zweite Karte aufdecken
            else if(memoryBean.getSecondCard() == null)
            {
                memoryBean.setSecondCard(card);
                card.setStatus(CardStatus.UNFOLDED);
                card.setFileName(memoryBean.getFileNames().get(cardID));

                memoryBean.setTrialCount(memoryBean.getTrialCount() + 1);

                // found Pairs erhöhen
                if(memoryBean.getFirstCard().getFileName().equals(memoryBean.getSecondCard().getFileName()))
                {
                    memoryBean.getFirstCard().setStatus(CardStatus.FOUND);
                    memoryBean.getSecondCard().setStatus(CardStatus.FOUND);
                    memoryBean.setFoundPairs(memoryBean.getFoundPairs() + 1);
                }
            }
            // bereits beide aufgedeckt
            else
            {
                // 1. zwei ungleiche aufgedeckte Karten wieder umdrehen
                if(!memoryBean.getFirstCard().getFileName().equals(memoryBean.getSecondCard().getFileName()))
                {
                    memoryBean.getFirstCard().setStatus(CardStatus.FOLDED);
                    memoryBean.getSecondCard().setStatus(CardStatus.FOLDED);
                    memoryBean.getFirstCard().setFileName(FILENAME_BACKGROUND);
                    memoryBean.getSecondCard().setFileName(FILENAME_BACKGROUND);
                }
                memoryBean.resetSelection();
                
                // 2. neue erste Karte aufdecken
                memoryBean.setFirstCard(card);
                card.setStatus(CardStatus.UNFOLDED);
                card.setFileName(memoryBean.getFileNames().get(cardID));
            }
            
        }
    }

}
