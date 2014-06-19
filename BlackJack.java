import java.util.ArrayList;
import java.awt.*;
import java.applet.*;
import java.awt.Image.*;
import java.awt.event.*;
import java.lang.Math;

public class BlackJack extends Applet implements ActionListener
{
    public CardDeck deck;
    public Player p1, dealer;
    Button hit, stand;
    int x;
    boolean title;

    public void init()
    {
        deck = new CardDeck();
        p1 = new Player();
        dealer = new Player();
        
        deck.dealCard(p1);
        deck.dealCard(p1);
        
        deck.dealCard(dealer);
        deck.dealCard(dealer);
        
        
        hit = new Button("Hit");
        hit.setPreferredSize(new Dimension(75, 50));
        add(hit);
        hit.addActionListener(this);
        
        stand = new Button("Stand");
        stand.setPreferredSize(new Dimension(75, 50));
        add(stand);
        stand.addActionListener(this);
    }
    
    public void paint(Graphics g)
    {
        //Draws the title screen if title is false.
        //Draws the board.
        //Draw the player's cards.
        //Draws total.
        //Checks if player and dealer's cards are bust or not.
       
        drawTable(g);
        drawDealerCards(g);
        drawTotal(g);
        drawCards(g);
        
        if(checkForBust(p1))
        {
            lose(g);
        }
        
        if(checkForBust(dealer))
        {
            win(g);
        }
        
        if(x == 1)
        {
            deck.dealCard(p1);
            x = 0;
            repaint();
        }
        
        if(x == 2)
        {
            if(p1.returnValueOfCards() > dealer.returnValueOfCards() && !checkForBust(p1))
               win(g);
            else if(p1.returnValueOfCards() < dealer.returnValueOfCards() || checkForBust(p1))
               lose(g);
            else
                g.drawString("IT'S A DRAW!", 235, 475);
            redrawDealerCards(g);
        }
        
    }   
    
    public void actionPerformed(ActionEvent e)
    {
        Button source = (Button)e.getSource();
        if(source.getLabel() == "Hit")
        {
            x = 1;
            repaint();
        }
        else if(source.getLabel() == "Stand")
        {
            x = 2;
            repaint();
        }
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
        hit.setLocation(350, 450);
        stand.setLocation(425, 450);
    }
    
    public void drawCards(Graphics g)
    {
        //Counts the amount of cards in player's hand.
        //For Loop to draw the correct number of cards.
        //display's image based on return given from checkCard() method.
        //Puts the cards all next to each other in a row.
        for(int i = 0; i < p1.getAmtCards(); i++)
        {
            g.drawImage(getImage(getCodeBase(), checkCard(p1.readCard(i)) + ".PNG"), 50 + (i * 97), 50, 96, 134, this);
        }
    }
    
    public void drawDealerCards(Graphics g)
    {
        //Counts the amount of cards in dealer's hand.
        //For Loop to draw the correct number of cards.
        //display's image based on return given from checkCard() method.
        //Puts the cards all next to each other in a row.
        for(int i = 0; i < dealer.getAmtCards(); i++)
        {
            if (i == dealer.getAmtCards() - 1)
                g.drawImage(getImage(getCodeBase(), "Card-Back-01.PNG"), 50 + (i * 97), 300, 96, 134, this);
            else
                g.drawImage(getImage(getCodeBase(), checkCard(dealer.readCard(i)) + ".PNG"), 50 + (i * 97), 300, 96, 134, this);
        }
    }
    
    public void redrawDealerCards(Graphics g)
    {
        //Counts the amount of cards in dealer's hand.
        //For Loop to draw the correct number of cards.
        //display's image based on return given from checkCard() method.
        //Puts the cards all next to each other in a row.
        for(int i = 0; i < dealer.getAmtCards(); i++)
        {
                g.drawImage(getImage(getCodeBase(), checkCard(dealer.readCard(i)) + ".PNG"), 50 + (i * 97), 300, 96, 134, this);
        }
    }
    
    public String checkCard(Card c)
    {
        //Instantiate String variable that holds card's file name.
        //Huge and annoying series of case switch.
        //case switch one is checking for the suit
        //Append results of switches on the String.
        //Maybe add exception handling for no buggy buggy.
        //returns name of card's file.
        
        switch (c.getSuit())
        {
            case(0): return "s" + c.getCardNum();
            case(1): return "c" + c.getCardNum();
            case(2): return "h" + c.getCardNum();
            case(3): return "d" + c.getCardNum();
        }
        return null;
    }
    
    public boolean checkForBust(Player p)
    {
        //Checks if player p getAmtCards() > 21
        //Return true if player is bust.
        //Return false if player is not bust.
        if (p.returnValueOfCards() > 21)
            return true;
        else
            return false;
    }
    
    public void drawTotal(Graphics g)
    {
        //Draws the returnValueOfCards() the player currently has.
        g.setColor(Color.BLACK);
        g.drawString("Total Number of Cards - " + p1.returnValueOfCards(), 30, 475);
    }
    
    public void win(Graphics g)
    {
        //Displays stuff if player won.
        g.drawString("YOU'RE WINNER", 235, 475);
    }
    
    public void lose(Graphics g)
    {
        //Displays stuff if player lost.
        g.drawString("YOU'RE LOSER", 235, 475);
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
        int totalAces = 0;
        
        for (Card c : hand)
            if (c.getCardNum == 1)
                totalAces++;
            if (c.getCardNum() > 10)
                totalVal = totalVal + 10;
            else
                totalVal = totalVal + c.getCardNum();
                
        for (int i = 0; i < totalAces; i++)
            if (totalVal + 10 < 21)
                totalVal = totalVal + 10;
        
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

//CardDeck

class CardDeck
{
    Card[] cards = new Card[51];
    //Set all dealt cards to null
    
    public CardDeck()
    {
        resetDeck();
    }
    
    public void resetDeck()
    {
        for(int i = 0; i < cards.length; i++)
        {
            //Initialize all cards
            //Set up array.
            System.out.println(i);
            cards[i] = new Card(i + 1);
        }
    }
    
    public void dealCard(Player p) //(Player p)
    {
        //Give one of the 52 cards to target Player p
        //Make sure the same card isn't dealt twice!
        //Give card in array to player, then set card to null.
        //Randomly Generate number of card, if null then generate another number.
        
        int randomNum = (int)(Math.random() * 52 - 1);
        //System.out.println(randomNum);
        
        //Checks if card isn't in deck
        if (cards[randomNum] == null)
            dealCard(p);
        
        p.hand.add(cards[randomNum]);
        cards[randomNum] = null; 
        
    }
    
    public void readDeck()
    {
        for (Card c : cards)
            System.out.println(c);
    }
}

class Card
{
    private int cardNumber; //Number of card. 11 - Jack 12 - Queen, 13 - King
    private int suit; //1-13 Spade; 14-26 Clubs; 27-39 Hearts; 40-52 Diamonds. All numbered respectively.
                            //0 - Spade; 1 - Clubs; 2 - Hearts; 3 - Diamonds
    
    public Card(int number)
    {
        //Divide to determine suit.
        suit = number / 13;
        //Modulus to determine number.
        cardNumber = number % 13;
        
        if (cardNumber == 0)
        {
            suit--;
            cardNumber = 13;
        }
    }
    
    public int getSuit()
    {
        return suit;
    }
    
    public int getCardNum()
    {
        return cardNumber;
    }
    
    public String toString()
    {
        String suitName = "Error";
        
        switch (suit)
        {
            case(0): suitName = "Spades";
                     break;
            case(1): suitName = "Clubs";
                     break;
            case(2): suitName = "Hearts";
                     break;
            case(3): suitName = "Diamonds";
                     break;    
        }
        
        switch (cardNumber)
        {
            case(1): return  "Ace" + " of " + suitName + "\n";
            case(11): return  "Jack" + " of " + suitName + "\n";
            case(12): return  "Queen" + " of " + suitName + "\n";
            case(13): return  "King" + " of " + suitName + "\n";
        }
        
        return  cardNumber + " of " + suitName + "\n";
    }
}
