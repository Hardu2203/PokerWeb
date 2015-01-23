package services;

import PokerFiles.*;
import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by harduNel on 2015-01-12.
 */

@Singleton
public class PokerService implements iPokerService {



   public List<Card> ListofCards;


    public void GenerateDeck() {
        ListofCards = new ArrayList<>();


        for (Suit suit : Suit.values()) {

            for (Rank rank : Rank.values()) {
                Card card = new Card(rank, suit);
                ListofCards.add(card);

            }
        }
    }

    public void shuffle(){
        Collections.shuffle((ListofCards));

    }

    public String deal()
    {
        List<Card> FiveCards = new ArrayList<>();

        for (int i =0; i< 5;i++)
        {
            FiveCards.add(ListofCards.get(i));
            ListofCards.remove(i);
        }

        String a = FiveCards.get(0).toString();
        String b = FiveCards.get(1).toString();
        String c = FiveCards.get(2).toString();
        String d = FiveCards.get(3).toString();
        String e = FiveCards.get(4).toString();

        Hand hand = new Hand(a,b,c,d,e);
        String ant = CalculateHandStrength(hand);
        ant = hand.toString() + "," + ant;

        return  ant;






    }

    public String CalculateHandStrength(Hand hand)
    {
        if (HandEvaluater.isOnePair(hand))
        {
            return "One Pair";
        }
        else if(HandEvaluater.isTwoPair(hand))
        {
            return "Two Pair";
        }
        else  if (HandEvaluater.isThreeOfAKind(hand))
        {
            return "Three Of a Kind";
        }
        else  if (HandEvaluater.isStrait(hand))
        {
            return "Straight";
        }
        else  if (HandEvaluater.isFlush(hand))
        {
            return "Flush";
        }
        else  if (HandEvaluater.isFullHouse(hand))
        {
            return "Full House";
        }
        else  if (HandEvaluater.isFourOfAKind(hand))
        {
            return "Four of a Kind";
        }
        else  if (HandEvaluater.isStraitFlush(hand))
        {
            return "Straight Flush";
        }
        else {return "High Card";}
    }

    public String getname()
    {

       // GenerateDeck();
        shuffle();



        return deal().toString();
    }


    public String DealHand()
    {
        GenerateDeck();
        shuffle();

        return deal().toString();

    }




}

