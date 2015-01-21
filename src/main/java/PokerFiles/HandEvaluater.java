package PokerFiles;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by harduNel on 2015-01-12.
 */
public class HandEvaluater {


    private static boolean hasThreeOfAKind(Hand hand) {
        Map map = new HashMap<Rank, Integer>();

        for (Rank rank : Rank.values()) {
            map.put(rank, 0);
        }

        for (Card card : hand.getCards()) {
            map.replace(card.getRank(), (Integer) map.get(card.getRank()) + 1);
        }

        if (map.containsValue(3))
            return true;
        else
            return false;
    }

    private static boolean hasOnePair(Hand hand) {
        Map map = new HashMap<Rank, Integer>();

        for (Rank rank : Rank.values()) {
            map.put(rank, 0);
        }

        for (Card card : hand.getCards()) {
            map.replace(card.getRank(), (Integer) map.get(card.getRank()) + 1);
        }

        if (numPairs(map) == 1)
            return true;
        else
            return false;
    }

    private static boolean hasTwoPair(Hand hand) {
        Map map = new HashMap<Rank, Integer>();

        for (Rank rank : Rank.values()) {
            map.put(rank, 0);
        }

        for (Card card : hand.getCards()) {
            map.replace(card.getRank(), (Integer) map.get(card.getRank()) + 1);
        }

        if (numPairs(map) == 2)
            return true;
        else
            return false;
    }

    private static int numPairs(Map map) {
        int count = 0;

        //only check values
        for (Object value : map.values()) {
            Integer temp = (Integer) value;

            if (temp == 2)
                count++;
        }

        return count;
    }

    private static boolean hasFlush(Hand hand) {
        Suit previousSuit = null;

        for (Card card : hand.getCards()) {
            if (previousSuit != null && card.getSuit().ordinal() != previousSuit.ordinal()) {
                return false;
            }
            previousSuit = card.getSuit();
        }
        return true;
    }

    private static boolean hasStrait(Hand hand) {
        Collections.sort(hand.getCards(), new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.getRank().compareTo(o2.getRank());
            }
        });

        Rank previousRank = null;

        for (Card card : hand.getCards()) {
            if (previousRank != null && card.getRank().ordinal() != previousRank.ordinal() + 1) {
                return false;
            }
            previousRank = card.getRank();
        }
        return true;
    }

    private static boolean hasFourOfAKind(Hand hand) {
        LinkedList<Rank> previousRank = new LinkedList<Rank>();
        int count = 0;

        for (Card card : hand.getCards()) {
            if (!previousRank.contains(card.getRank()))
                previousRank.add(card.getRank());

            if (previousRank.size() > 2) {
                return false;
            }
        }
        return true;
    }

    private static Map functionalRankMap(Hand hand) {
        Map<Rank, Long> map = hand.getCards().stream().collect(Collectors.groupingBy(c -> c.getRank(), Collectors.counting()));
        return map;
    }

//    private static Map functionalRankMap(Hand hand) {
//        Map<Rank, Long> map = hand.getCards().stream().collect(Collectors.groupingBy(c -> c.getRank(), Collectors.counting()));
//        return map;
//    }

    private static boolean functionalHasFourOfAKind(Hand hand)
    {
        Map<Rank, Long> map = functionalRankMap(hand);

        if(map.containsValue(4L))
            return true;
        else
            return false;
    }

//    private static boolean functionalHasStrait(Hand hand)
//    {
//        Map<Rank, Long> map = functionalRankMap(hand);
//
//        List<Rank> ranks = hand.getCards().stream().map(c -> c.getRank()).collect(Collectors.toList());
//
//        return hand.getCards().stream().allMatch(c -> c.getSuit() == hand.getCards().get(0).getSuit()) // check flush
//                && (ranks.stream().mapToInt(r -> r.ordinal()).max().getAsInt() - ranks.stream().mapToInt(r -> r.ordinal()).min().getAsInt() == 4)
//                && ranks.stream().distinct().count() == 5;
//
//    }

    public static boolean isStraitFlush(Hand hand) {
        if (hasStrait(hand) && hasFlush(hand))
            return true;
        else
            return false;
    }


    //isFourOfAKind()
    public static boolean isFourOfAKind(Hand hand) {
        return hasFourOfAKind(hand);
    }

    //isFullHouse()
    public static boolean isFullHouse(Hand hand) {
        if (hasThreeOfAKind(hand) && hasOnePair(hand))
            return true;
        else
            return false;
    }

    //isFlush()
    public static boolean isFlush(Hand hand) {
        if (hasFlush(hand) && !hasStrait(hand))
            return true;
        else
            return false;
    }

    //isStrait
    public static boolean isStrait(Hand hand) {
        if (!hasFlush(hand) && hasStrait(hand))
            return true;
        else
            return false;
    }

    //isThreeOfAKind
    public static boolean isThreeOfAKind(Hand hand) {
        if (hasThreeOfAKind(hand) && !hasOnePair(hand))
            return true;
        else
            return false;
    }

    //isTwoPair
    public static boolean isTwoPair(Hand hand) {
        if (hasTwoPair(hand))
            return true;
        else
            return false;
    }

    //isOnePair
    public static boolean isOnePair(Hand hand) {
        if (!hasFourOfAKind(hand) && !hasThreeOfAKind(hand) && hasOnePair(hand))
            return true;
        else
            return false;
    }
}
