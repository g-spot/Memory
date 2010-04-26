/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
public class MemServlet extends HttpServlet implements IMemoryAPI{

    private MemoryBean memoryBean = null;
    private final String FILENAME_BACKGROUND = "img/card_background.png";
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet memServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet memServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
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
    throws ServletException, IOException {
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
            request.getSession().invalidate();
            dispatcher = getServletContext().getRequestDispatcher("/gameWon.jsp");
        }
        else
        {
            dispatcher = getServletContext().getRequestDispatcher("/table.jsp");
        }
        
        dispatcher.forward(request, response);
    }

    private void resetGame()
    {
        memoryBean.resetSelection();
        memoryBean.setFoundPairs(0);
        memoryBean.setTrialCount(0);
        MemServlet.initCards(16);
    }

    /*
    @Override
    public void init() throws ServletException
    {
        System.out.println("SAMMA HIER?");
        memoryBean.setCards(initCards(16));
    }
*/
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public static HashMap<String,Card> initCards(int cardCount) {
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

    public void cardClicked(String cardID)
    {
        Card card = memoryBean.getCards().get(cardID);
        if(card.getStatus() == CardStatus.FOLDED)
        {
            int cardIDInt = Integer.parseInt(cardID);
            if(Integer.parseInt(cardID) > (memoryBean.getCards().size()/2))
                cardIDInt = cardIDInt - (memoryBean.getCards().size()/2);
            if(memoryBean.getFirstCard() == null)
            {
                memoryBean.setFirstCard(card);
                card.setStatus(CardStatus.UNFOLDED);
                card.setFileName("img/card_images/card" + cardIDInt + ".jpg");
            }
            else if(memoryBean.getSecondCard() == null)
            {
                memoryBean.setSecondCard(card);
                card.setStatus(CardStatus.UNFOLDED);
                card.setFileName("img/card_images/card" + cardIDInt + ".jpg");

                memoryBean.setTrialCount(memoryBean.getTrialCount() + 1);
            /*}
            else
            {*/
                if(memoryBean.getFirstCard().getFileName().equals(memoryBean.getSecondCard().getFileName()))
                {
                    memoryBean.getFirstCard().setStatus(CardStatus.FOUND);
                    memoryBean.getSecondCard().setStatus(CardStatus.FOUND);
                    memoryBean.setFoundPairs(memoryBean.getFoundPairs() + 1);
                }
                else
                {
                    memoryBean.getFirstCard().setStatus(CardStatus.FOLDED);
                    memoryBean.getSecondCard().setStatus(CardStatus.FOLDED);
                    memoryBean.getFirstCard().setFileName(FILENAME_BACKGROUND);
                    memoryBean.getSecondCard().setFileName(FILENAME_BACKGROUND);
                }
                memoryBean.resetSelection();
            }
            
        }
    }

}
