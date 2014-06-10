import java.lang.Math;

public class CardDeck
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
            
        System.out.println(cards[randomNum]);
        
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
