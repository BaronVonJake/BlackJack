sss
import java.util.ArrayList;
import java.awt.*;
import java.applet.*;

public class BlackJack extends Applet
{
    public CardDeck deck;
    public Player p1;
    private boolean title = false; 
    private boolean isTableDrawn = false;

    public void init()
    {
        deck = new CardDeck();
        p1 = new Player();
    }
    
    
    public void paint(Graphics g)
    {
        //Draws the title screen if title is false.
        //Draws the board.
        //Draw the player's cards.
        //Draws total.
        //Checks if player and dealer's cards are bust or not.
        if (!isTableDrawn)
            drawTable(g);
        
    }       
    
    public void drawTitle(Graphics g)
    {
        //Draws title 
        //Sets title variable to true
    }
    
    public void drawTable(Graphics g)
    {
        //Draws the playing table.
        g.drawLine(0,449,500,449);
        g.setColor(new Color(34, 150, 34));
        g.fillRect(0,0,500,449);
        isTableDrawn = true;
    }
    
    public void drawCards(Graphics g)
    {
        //Counts the amount of cards in player's hand.
        //For Loop to draw the correct number of cards.
        //display's image based on return given from checkCard() method.
        //Puts the cards all next to each other in a row.  
    }
    
    public String checkCard(Card c)
    {
        //Instantiate String variable that holds card's file name.
        //Huge and annoying series of case switch.
        //case switch one is checking for the suit
        //case switch in each of the cases to check for number
        //Append results of switches on the String.
        //Maybe add exception handling for no buggy buggy.
        //returns name of card's file.
        return null;
    }
    
    public boolean checkForBust(Player p)
    {
        //Checks if player p getAmtCards() > 21
        //Return true if player is bust.
        //Return false if player is not bust.
        return true;
    }
    
    public void drawTotal(Graphics g)
    {
        //Draws the returnValueOfCards() the player currently has.
        g.drawString("Total Number of Cards - " + p1.returnValueOfCards(), 30, 475);
    }
    
    public void win(Graphics g)
    {
        //Displays stuff if player won.
    }
    
    public void lose(Graphics g)
    {
        //Displays stuff if player lost.
    }
    
    
    
    public void test()
    {
       deck.dealCard(p1);
       //System.out.println(p1.readHand());
    }
    
    public static void main (String args[])
    {
       
    }
}

class Player
{
    public ArrayList<Card> hand = new ArrayList<Card>();

    public Player()
    {
        
    }
    
    public int getAmtCards()
    {
        return hand.size();
    }
    
    public void resetHand()
    {
        //Removes all cards from ArrayList
        hand.clear();
    }
    
    
    public int returnValueOfCards()
    {
        int totalVal = 0;
        
        for (Card c : hand)
           totalVal = totalVal + c.getCardNum();
           
        return totalVal;
    }
    
    public Card readCard(int index)
    {
        //Get a specific card number from the hand
        try
        {
            return hand.get(index);
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            return null;
        }  
    }
    
    
}
